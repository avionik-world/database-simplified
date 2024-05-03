package world.avionik.database.simplified.rabbitmq

import world.avionik.database.simplified.arango.config.ArangoConfigLoader
import world.avionik.database.simplified.kubernetes.KubernetesSecrets
import world.avionik.database.simplified.rabbitmq.config.RabbitMQConfigLoader

/**
 * @author Niklas Nieberler
 */

object RabbitMQSettings {

    /**
     * Gets the rabbitmq configuration from a system environment
     * @param host the environment to create the configuration
     * @param username the environment to create the configuration
     * @param port the environment to create the configuration
     * @param password the environment to create the configuration
     * @return configuration from the config
     */
    fun fromEnv(
        host: String = "RABBITMQ_HOST",
        username: String = "RABBITMQ_USERNAME",
        port: String = "RABBITMQ_PORT",
        password: String = "RABBITMQ_PASSWORD"
    ): RabbitMQConfiguration {
        return RabbitMQConfiguration(
            System.getenv(host),
            System.getenv(username),
            System.getenv(port).toInt(),
            System.getenv(password)
        )
    }

    /**
     * Gets the rabbitmq configuration from the config
     * @param path directory path of the config
     * @return configuration from the config
     */
    fun fromConfig(path: String = "database"): RabbitMQConfiguration {
        val rabbitMQConfig = RabbitMQConfigLoader(path).load()
        return RabbitMQConfiguration(
            rabbitMQConfig.host,
            rabbitMQConfig.username,
            rabbitMQConfig.port,
            rabbitMQConfig.password
        )
    }

    /**
     * Gets the rabbitmq configuration from the kubernetes secrets
     * @param namespace where the secret is stored
     * @return configuration from the config
     */
    fun fromKubeSecret(namespace: String = KubernetesSecrets.namespace): RabbitMQConfiguration {
        return RabbitMQSecretLoader(namespace).get()
    }

}