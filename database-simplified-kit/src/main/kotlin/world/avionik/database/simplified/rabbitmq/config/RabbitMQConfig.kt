package world.avionik.database.simplified.rabbitmq.config

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
class RabbitMQConfig(
    val host: String,
    val username: String,
    val port: Int,
    val password: String
) {

    object Default {
        fun get(): RabbitMQConfig {
            return RabbitMQConfig(
                "127.0.0.1",
                "user",
                1234,
                "password"
            )
        }
    }

}