package world.avionik.database.simplified.shared.config

import kotlinx.serialization.Serializable
import world.avionik.database.simplified.shared.ConfigurationType

/**
 * @author Niklas Nieberler
 */

@Serializable
class DatabaseConfig(
    val enableMorphia: Boolean,
    val enableJedis: Boolean,
    val configurationType: ConfigurationType
) {

    object Default {
        fun get(): DatabaseConfig {
            return DatabaseConfig(
                enableMorphia = true,
                enableJedis = true,
                configurationType = ConfigurationType.CONFIG
            )
        }
    }

}