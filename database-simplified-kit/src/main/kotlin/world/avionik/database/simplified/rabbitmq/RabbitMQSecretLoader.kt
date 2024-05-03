package world.avionik.database.simplified.rabbitmq

import world.avionik.database.simplified.kubernetes.KubernetesSecrets
import java.util.*
import kotlin.collections.HashMap

/**
 * @author Niklas Nieberler
 */

class RabbitMQSecretLoader(
    private val namespace: String
) {

    private val secretName = "rabbitmq-secret"
    private val defaultSecrets = hashMapOf(
        Pair("host", ""),
        Pair("username", "user"),
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

    fun get(): RabbitMQConfiguration {
        val secrets = KubernetesSecrets.getSecret(this.namespace, this.secretName)?.data ?: createSecret()
        return RabbitMQConfiguration(
            secrets["host"]?.decodeBase64() ?: throw NullPointerException("failed to find host"),
            secrets["username"]?.decodeBase64() ?: throw NullPointerException("failed to find username"),
            secrets["port"]?.decodeBase64()?.toInt() ?: throw NullPointerException("failed to find port"),
            secrets["password"]?.decodeBase64() ?: throw NullPointerException("failed to find password"),
        )
    }

    private fun String.decodeBase64(): String {
        return String(Base64.getDecoder().decode(this))
    }

}