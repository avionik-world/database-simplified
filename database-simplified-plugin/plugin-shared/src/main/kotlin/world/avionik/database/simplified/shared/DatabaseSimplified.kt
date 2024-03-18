package world.avionik.database.simplified.shared

import world.avionik.database.simplified.DatabaseSimplifiedKit
import world.avionik.database.simplified.shared.config.DatabaseConfigLoader

/**
 * @author Niklas Nieberler
 */

object DatabaseSimplified {

    /**
     * Creates a new database configuration
     */
    fun create() {
        val databaseConfig = DatabaseConfigLoader().load()

        val configuration = DatabaseSimplifiedKit.createConfiguration()
        val configurationType = databaseConfig.configurationType

        if (databaseConfig.enableMorphia)
            configuration.withMorphia(configurationType.getMorphiaSettings())
        if (databaseConfig.enableJedis)
            configuration.withJedis(configurationType.getJedisSettings())

        configuration.start()
    }

}