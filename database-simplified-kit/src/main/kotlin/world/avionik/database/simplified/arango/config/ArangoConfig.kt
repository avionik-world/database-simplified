package world.avionik.database.simplified.arango.config

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
class ArangoConfig(
    val host: String,
    val port: Int,
    val password: String
) {

    object Default {
        fun get(): ArangoConfig {
            return ArangoConfig(
                "127.0.0.1",
                1234,
                "password"
            )
        }
    }

}