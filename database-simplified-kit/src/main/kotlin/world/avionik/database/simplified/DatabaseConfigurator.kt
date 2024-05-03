package world.avionik.database.simplified

import com.arangodb.ArangoDB
import com.mongodb.client.MongoClient
import com.rabbitmq.client.Connection
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import world.avionik.database.simplified.arango.ArangoConfiguration
import world.avionik.database.simplified.jedis.JedisConfiguration
import world.avionik.database.simplified.morphia.MorphiaClientFactory
import world.avionik.database.simplified.rabbitmq.RabbitMQConfiguration

/**
 * @author Niklas Nieberler
 */

class DatabaseConfigurator {

    private var arangoDB: ArangoDB? = null
    private var mongoClient: MongoClient? = null
    private var jedisPool: JedisPool? = null
    private var rabbitConnection: Connection? = null

    /**
     * Sets the morphia database
     * @param connection the connection string of the morphia database
     * @return configurator instance
     */
    fun withMorphia(connection: String): DatabaseConfigurator {
        this.mongoClient = MorphiaClientFactory(connection).createClient()
        return this
    }

    /**
     * Sets the jedis database
     * @param configuration the jedis configuration
     * @return configurator instance
     */
    fun withJedis(configuration: JedisConfiguration): DatabaseConfigurator {
        this.jedisPool = configuration.createJedisPool()
        return this
    }

    /**
     * Sets the arango database
     * @param configuration the arango configuration
     * @return configurator instance
     */
    fun withArango(configuration: ArangoConfiguration): DatabaseConfigurator {
        this.arangoDB = configuration.createArango()
        return this
    }

    /**
     * Sets the rabbitmq database
     * @param configuration the rabbitmq configuration
     * @return configurator instance
     */
    fun withRabbitMQ(configuration: RabbitMQConfiguration): DatabaseConfigurator {
        this.rabbitConnection = configuration.createConnection()
        return this
    }

    fun start(): DatabaseSimplifiedKit {
        return DatabaseSimplifiedKit(
            this.arangoDB,
            this.jedisPool,
            this.mongoClient,
            this.rabbitConnection
        )
    }

}