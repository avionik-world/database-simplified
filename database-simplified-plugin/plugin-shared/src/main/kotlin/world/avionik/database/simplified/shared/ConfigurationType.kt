package world.avionik.database.simplified.shared

import world.avionik.database.simplified.jedis.JedisConfiguration
import world.avionik.database.simplified.jedis.JedisSettings
import world.avionik.database.simplified.morphia.MorphiaSettings

/**
 * @author Niklas Nieberler
 */

enum class ConfigurationType {

    CONFIG {
        override fun getMorphiaSettings(): String {
            return MorphiaSettings.fromConfig()
        }

        override fun getJedisSettings(): JedisConfiguration {
            return JedisSettings.fromConfig()
        }
    },

    ENV {
        override fun getMorphiaSettings(): String {
            return MorphiaSettings.fromEnv()
        }

        override fun getJedisSettings(): JedisConfiguration {
            return JedisSettings.fromEnv()
        }
    };

    abstract fun getMorphiaSettings(): String

    abstract fun getJedisSettings(): JedisConfiguration

}