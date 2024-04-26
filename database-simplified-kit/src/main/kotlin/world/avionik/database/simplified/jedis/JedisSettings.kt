package world.avionik.database.simplified.jedis

import world.avionik.database.simplified.jedis.config.JedisConfigLoader
import world.avionik.database.simplified.kubernetes.KubernetesSecrets

/**
 * @author Niklas Nieberler
 */

object JedisSettings {

    /**
     * Gets the jedis configuration from a system environment
     * @param host the environment to create the configuration
     * @param port the environment to create the configuration
     * @param password the environment to create the configuration
     * @return configuration from the config
     */
    fun fromEnv(
        host: String = "JEDIS_HOST",
        port: String = "JEDIS_PORT",
        password: String = "JEDIS_PASSWORD"
    ): JedisConfiguration {
        return JedisConfiguration(
            System.getenv(host),
            System.getenv(port).toInt(),
            System.getenv(password)
        )
    }

    /**
     * Gets the jedis configuration from the config
     * @param path directory path of the config
     * @return configuration from the config
     */
    fun fromConfig(path: String = "database"): JedisConfiguration {
        val jedisConfig = JedisConfigLoader(path).load()
        return JedisConfiguration(
            jedisConfig.host,
            jedisConfig.port,
            jedisConfig.password
        )
    }

    /**
     * Gets the jedis configuration from the kubernetes secrets
     * @param namespace where the secret is stored
     * @return configuration from the config
     */
    fun fromKubeSecret(namespace: String = "database"): JedisConfiguration {
        return JedisSecretLoader(namespace).get()
    }

}