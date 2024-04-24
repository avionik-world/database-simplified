package world.avionik.database.simplified

import com.arangodb.ArangoDB
import com.mongodb.client.MongoClient
import dev.morphia.Datastore
import dev.morphia.Morphia
import redis.clients.jedis.JedisPool
import java.lang.IllegalStateException

/**
 * @author Niklas Nieberler
 */

class DatabaseSimplifiedKit(
    private val arangoDB: ArangoDB?,
    private val jedisPool: JedisPool?,
    private val mongoClient: MongoClient?
) {

    init {
        instance = this
    }

    /**
     * Creates a new datastore with a database name
     * @param dbName name of the new datastore
     * @return created datastore instance
     */
    fun createMorphiaDatastore(dbName: String): Datastore {
        if (this.mongoClient == null)
            throw IllegalStateException("No morphia database has been set up")
        val datastore = Morphia.createDatastore(this.mongoClient, dbName)
        datastore.ensureIndexes()
        return datastore
    }

    /**
     * Gets the created jedis pool
     * @return jedis pool instance
     */
    fun getJedisPool(): JedisPool {
        return this.jedisPool ?: throw IllegalStateException("No jedis database has been set up")
    }

    /**
     * Gets the created arangodb
     * @return arango instance
     */
    fun getArangoDB(): ArangoDB {
        return this.arangoDB ?: throw IllegalStateException("No arangoDB database has been set up")
    }

    companion object {
        lateinit var instance: DatabaseSimplifiedKit
            private set

        /**
         * Creates a new database configurator instance to configure this system
         * @return new instance of [DatabaseConfigurator]
         */
        fun createConfiguration(): DatabaseConfigurator {
            return DatabaseConfigurator()
        }
    }

}