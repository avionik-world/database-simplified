package world.avionik.database.simplified.morphia

import world.avionik.database.simplified.kubernetes.KubernetesSecrets
import world.avionik.database.simplified.morphia.config.MorphiaConfigLoader

/**
 * @author Niklas Nieberler
 */

object MorphiaSettings {

    /**
     * Gets the connection string from a system environment
     * @param environment connection string
     * @return connection string from the environment variable
     */
    fun fromEnv(environment: String = "MORPHIA_CONNECTION_STRING"): String {
        return System.getenv(environment)
    }

    /**
     * Gets the connection string from the morphia config
     * @param path directory path of the config
     * @return connection string from the config
     */
    fun fromConfig(path: String = "database"): String {
        val morphiaConfig = MorphiaConfigLoader(path).load()
        return morphiaConfig.connectionString
    }

    /**
     * Gets the connection string from the kubernetes secrets
     * @param namespace where the secret is stored
     * @return configuration from the config
     */
    fun fromKubeSecret(namespace: String = KubernetesSecrets.namespace): String {
        return MorphiaSecretLoader(namespace).get()
    }

}