package world.avionik.database.simplified.jedis

import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

/**
 * @author Niklas Nieberler
 */

class JedisConfiguration(
    private val host: String,
    private val port: Int,
    private val password: String
) {

    fun createJedisPool(): JedisPool {
        return JedisPool(
            JedisPoolConfig(),
            this.host,
            this.port,
            null,
            this.password
        )
    }

}