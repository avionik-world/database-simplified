package world.avionik.database.simplified.morphia

import world.avionik.database.simplified.kubernetes.KubernetesSecrets
import java.util.*
import kotlin.collections.HashMap

/**
 * @author Niklas Nieberler
 */

class MorphiaSecretLoader(
    private val namespace: String
) {

    private val secretName = "morphia-secret"
    private val defaultSecrets = hashMapOf(
        Pair("connectionString", ""),
    )

    private fun createSecret(): HashMap<String, String> {
        KubernetesSecrets.createSecret(
            this.namespace,
            this.secretName,
            this.defaultSecrets
        )
        return this.defaultSecrets
    }

    fun get(): String {
        val secrets = KubernetesSecrets.getSecret(this.namespace, this.secretName)?.data ?: createSecret()
        return secrets["connectionString"]?.decodeBase64()
            ?: throw NullPointerException("failed to find connectionString")
    }

    private fun String.decodeBase64(): String {
        return String(Base64.getDecoder().decode(this))
    }

}