package world.avionik.database.simplified.arango

import world.avionik.database.simplified.kubernetes.KubernetesSecrets

/**
 * @author Niklas Nieberler
 */

class ArangoSecretLoader(
    private val namespace: String
) {

    private val secretName = "arango-secret"
    private val defaultSecrets = hashMapOf(
        Pair("host", "127.0.0.1"),
        Pair("port", "127"),
        Pair("password", "password"),
    )

    private fun createSecret(): HashMap<String, String> {
        KubernetesSecrets.createSecret(
            this.namespace,
            this.secretName,
            this.defaultSecrets
        )
        return this.defaultSecrets
    }

    fun get(): ArangoConfiguration {
        val secrets = KubernetesSecrets.getSecret(this.namespace, this.secretName)?.data ?: createSecret()
        return ArangoConfiguration(
            secrets["host"] ?: throw NullPointerException("failed to find host"),
            secrets["port"]?.toInt() ?: throw NullPointerException("failed to find port"),
            secrets["password"] ?: throw NullPointerException("failed to find password"),
        )
    }

}