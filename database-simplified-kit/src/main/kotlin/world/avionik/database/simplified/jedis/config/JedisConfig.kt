package world.avionik.database.simplified.jedis.config

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
class JedisConfig(
    val host: String,
    val port: Int,
    val password: String
) {

    object Default {
        fun get(): JedisConfig {
            return JedisConfig(
                "127.0.0.1",
                1234,
                "password"
            )
        }
    }

}