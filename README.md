# Database Simplified 🤙
Here you can easily set up and use ArangoDB, RabbitMQ, Morphia and Jedis.

## Using Database Simplified in your plugin

### Maven
```xml
<dependencies>
 <dependency>
    <groupId>world.avionik</groupId>
    <artifactId>database-simplified</artifactId>
    <version>1.1.0</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

### Gradle
```groovy
dependencies {
    compileOnly 'world.avionik:database-simplified:1.1.0'
}
```

## How to create a new Configuration
There are two different ways to enter your sensitive data during setup. You can either use a **config file** in which you configure your database or simply use the **system environments**.

``` kotlin
DatabaseSimplifiedKit.createConfiguration()
    .withMorphia(MorphiaSettings.fromConfig()) // Of course you can also use fromEnv() here
    .withJedis(JedisSettings.fromEnv()) // Of course you can also use fromConfig() here
    .withArango(ArangoSettings.fromKubeSecret()) // Of course you can also use fromEnv() here
    .withRabbitMQ(RabbitMQSettings.fromConfig()) // Of course you can also use fromEnv() here
    .start()
```

### 
If you are wondering how to create a new Morphia database, then you have come to the right place. Here are also two examples of how to create such a database.

```kotlin
val morphiaDatastore = DatabaseSimplifiedKit.instance.createMorphiaDatastore("dbName")

// Create a new database with kotlin extensions
val morphiaDatastore = createMorphiaDatastore("dbName")
```


## How to use the MorphiaRepository 
First of all, you need an entity class. Let's take this class as an example:
``` kotlin
@Entity("test_entity")
class TestEntity(
    @Id val uniqueId: UUID,
    val firstString: String
)
```

This is what a new repository could look like now. The UUID serves as an **identifier** for the entity.
``` kotlin
class TestEntityRepository(
    datastore: Datastore
) : AbstractMorphiaRepository<UUID, TestEntity>(
    datastore,
    TestEntity::class.java
)
```

You now have various options for using your entity. You can retrieve all entities or create and delete new ones.

``` kotlin
class TestEntityRepository(
    datastore: Datastore
) : AbstractMorphiaRepository<UUID, TestEntity>(
    datastore,
    TestEntity::class.java
) {
    
    init {
        val entityUniqueId = UUID.randomUUID()

        // Gets you all entities from this repository
        findAll().thenAccept { 
            listOf(it.toList())
        }

        // Would you like to get your entity class with an identifier? Here's how:
        findOrNull(entityUniqueId).thenAccept { 
            println(it?.uniqueId)
        }

        // You can save a new entity to the repository as follows
        val newEntity = TestEntity(entityUniqueId, "second")
        save(newEntity)
        
        // You can use this to remove an entity. entityUniqueId is the identifier of the class 
        remove(entityUniqueId)
    }
    
}
```


## How to use the JedisRepository 
Here, too, you need an entity class. Here is the example:
``` kotlin
class TestEntity(
    val firstString: String,
    val secondString: String
)
```

The repository looks almost exactly like the Morphia repository. The only difference is that there is a **database pattern** instead of an identifier. This pattern is placed in front of the Jedis key.
``` kotlin
class TestEntityRepository : AbstractJedisRepository<TestEntity>(
    TestEntity::class.java,
    "test_entity" // Here is the database pattern
)
```

Just like with the Morphia repository, you can let off steam here. You can retrieve all entities or create and delete new ones.

``` kotlin
class TestEntityRepository : AbstractJedisRepository<TestEntity>(
    TestEntity::class.java,
    "test_entity" // Here is the database pattern
) {

    init {
        // Gets you all entities from this repository
        findAll().forEach {
            listOf(it.firstString)
        }

        // Would you like to get your entity class with an key? Here's how:
        val entity = find("jedis_key")
        println(entity?.firstString)

        val newEntity = TestEntity("Hello", "Second")
        insert("jedis_key", newEntity)

        // You can use this to remove an entity. jedis_key is the jedis key.
        remove("jedis_key")
    }
    
}
```
