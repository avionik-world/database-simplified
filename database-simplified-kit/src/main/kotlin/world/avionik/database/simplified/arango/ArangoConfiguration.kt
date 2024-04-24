package world.avionik.database.simplified.arango

import com.arangodb.ArangoDB
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

/**
 * @author Niklas Nieberler
 */

class ArangoConfiguration(
    private val host: String,
    private val port: Int,
    private val password: String
) {

    fun createArango(): ArangoDB {
        return ArangoDB.Builder()
            .host(this.host, this.port)
            .password(this.password)
            .build()
    }

}