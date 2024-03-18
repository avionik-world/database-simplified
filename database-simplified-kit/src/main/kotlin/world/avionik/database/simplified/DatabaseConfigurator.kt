package world.avionik.database.simplified

import com.mongodb.client.MongoClient
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import world.avionik.database.simplified.jedis.JedisConfiguration
import world.avionik.database.simplified.morphia.MorphiaClientFactory

/**
 * @author Niklas Nieberler
 */

class DatabaseConfigurator {

    private var mongoClient: MongoClient? = null
    private var jedisPool: JedisPool? = null

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

    fun start(): DatabaseSimplifiedKit {
        return DatabaseSimplifiedKit(
            this.jedisPool,
            this.mongoClient
        )
    }

}