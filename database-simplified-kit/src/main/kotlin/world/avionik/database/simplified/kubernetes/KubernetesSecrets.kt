package world.avionik.database.simplified.kubernetes

import io.fabric8.kubernetes.api.model.ObjectMetaBuilder
import io.fabric8.kubernetes.api.model.Secret
import io.fabric8.kubernetes.client.KubernetesClientBuilder


/**
 * @author Niklas Nieberler
 */

object KubernetesSecrets {

    val namespace = KubernetesClientBuilder().build().namespace

    fun createSecret(namespace: String, secretName: String, secrets: Map<String, String>) {
        val secret = Secret()
        secret.metadata = ObjectMetaBuilder().withName(secretName).build()
        secret.data = secrets

        val kubernetesClient = KubernetesClientBuilder().build()
        kubernetesClient.secrets()
            .inNamespace(namespace)
            .resource(secret)
            .create()
    }

    fun getSecret(namespace: String, secretName: String): Secret? {
        val kubernetesClient = KubernetesClientBuilder().build()
        return kubernetesClient.secrets()
            .inNamespace(namespace)
            .withName(secretName)
            .get()
    }

}