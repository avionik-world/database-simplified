package world.avionik.database.simplified.morphia.config

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
class MorphiaConfig(
    val connectionString: String
) {

    object Default {
        fun get(): MorphiaConfig {
            return MorphiaConfig("connectionString")
        }
    }

}