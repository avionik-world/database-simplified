package world.avionik.database.simplified.arango

import world.avionik.database.simplified.arango.config.ArangoConfigLoader
import world.avionik.database.simplified.jedis.JedisConfiguration
import world.avionik.database.simplified.jedis.JedisSecretLoader

/**
 * @author Niklas Nieberler
 */

object ArangoSettings {

    /**
     * Gets the arango configuration from a system environment
     * @param host the environment to create the configuration
     * @param port the environment to create the configuration
     * @param password the environment to create the configuration
     * @return configuration from the config
     */
    fun fromEnv(
        host: String = "ARANGO_HOST",
        port: String = "ARANGO_PORT",
        password: String = "ARANGO_PASSWORD"
    ): ArangoConfiguration {
        return ArangoConfiguration(
            System.getenv(host),
            System.getenv(port).toInt(),
            System.getenv(password)
        )
    }

    /**
     * Gets the arango configuration from the config
     * @param path directory path of the config
     * @return configuration from the config
     */
    fun fromConfig(path: String = "database"): ArangoConfiguration {
        val arangoConfig = ArangoConfigLoader(path).load()
        return ArangoConfiguration(
            arangoConfig.host,
            arangoConfig.port,
            arangoConfig.password
        )
    }

    /**
     * Gets the arango configuration from the kubernetes secrets
     * @param namespace where the secret is stored
     * @return configuration from the config
     */
    fun fromKubeSecret(namespace: String = "database"): ArangoConfiguration {
        return ArangoSecretLoader(namespace).get()
    }

}