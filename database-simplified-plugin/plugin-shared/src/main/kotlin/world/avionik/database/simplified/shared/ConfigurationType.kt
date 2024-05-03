package world.avionik.database.simplified.shared

import world.avionik.database.simplified.arango.ArangoConfiguration
import world.avionik.database.simplified.arango.ArangoSettings
import world.avionik.database.simplified.jedis.JedisConfiguration
import world.avionik.database.simplified.jedis.JedisSettings
import world.avionik.database.simplified.morphia.MorphiaSettings
import world.avionik.database.simplified.rabbitmq.RabbitMQConfiguration
import world.avionik.database.simplified.rabbitmq.RabbitMQSettings

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

        override fun getArangoSettings(): ArangoConfiguration {
            return ArangoSettings.fromConfig()
        }

        override fun getRabbitMQSettings(): RabbitMQConfiguration {
            return RabbitMQSettings.fromConfig()
        }
    },

    KUBE_SECRET {
        override fun getMorphiaSettings(): String {
            return MorphiaSettings.fromKubeSecret()
        }

        override fun getJedisSettings(): JedisConfiguration {
            return JedisSettings.fromKubeSecret()
        }

        override fun getArangoSettings(): ArangoConfiguration {
            return ArangoSettings.fromKubeSecret()
        }

        override fun getRabbitMQSettings(): RabbitMQConfiguration {
            return RabbitMQSettings.fromKubeSecret()
        }
    },

    ENV {
        override fun getMorphiaSettings(): String {
            return MorphiaSettings.fromEnv()
        }

        override fun getJedisSettings(): JedisConfiguration {
            return JedisSettings.fromEnv()
        }

        override fun getArangoSettings(): ArangoConfiguration {
            return ArangoSettings.fromEnv()
        }

        override fun getRabbitMQSettings(): RabbitMQConfiguration {
            return RabbitMQSettings.fromEnv()
        }
    };

    abstract fun getMorphiaSettings(): String

    abstract fun getJedisSettings(): JedisConfiguration

    abstract fun getArangoSettings(): ArangoConfiguration

    abstract fun getRabbitMQSettings(): RabbitMQConfiguration

}