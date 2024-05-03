package world.avionik.database.simplified.rabbitmq

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

/**
 * @author Niklas Nieberler
 */

class RabbitMQConfiguration(
    private val host: String,
    private val username: String,
    private val port: Int,
    private val password: String
) {

    fun createConnection(): Connection {
        val connectionFactory = ConnectionFactory()
        connectionFactory.host = this.host
        connectionFactory.port = this.port
        connectionFactory.username = this.username
        connectionFactory.password = this.password
        connectionFactory.virtualHost = "/"
        connectionFactory.channelRpcTimeout = 0
        connectionFactory.networkRecoveryInterval = 10000
        connectionFactory.isAutomaticRecoveryEnabled = true
        return connectionFactory.newConnection()
    }

}