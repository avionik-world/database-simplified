package world.avionik.database.simplified.arango

import world.avionik.database.simplified.kubernetes.KubernetesSecrets
import java.util.*
import kotlin.collections.HashMap

/**
 * @author Niklas Nieberler
 */

class ArangoSecretLoader(
    private val namespace: String
) {

    private val secretName = "arango-secret"
    private val defaultSecrets = hashMapOf(
        Pair("host", ""),
        Pair("port", ""),
        Pair("password", ""),
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
            secrets["host"]?.decodeBase64() ?: throw NullPointerException("failed to find host"),
            secrets["port"]?.decodeBase64()?.toInt() ?: throw NullPointerException("failed to find port"),
            secrets["password"]?.decodeBase64() ?: throw NullPointerException("failed to find password"),
        )
    }

    private fun String.decodeBase64(): String {
        return String(Base64.getDecoder().decode(this))
    }

}