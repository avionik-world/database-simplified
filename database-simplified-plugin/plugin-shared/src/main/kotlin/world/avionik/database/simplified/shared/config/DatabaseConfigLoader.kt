package world.avionik.database.simplified.shared.config

import world.avionik.configkit.ConfigLoader
import world.avionik.configkit.format.YamlFileFormatter
import java.io.File

/**
 * @author Niklas Nieberler
 */

class DatabaseConfigLoader : ConfigLoader<DatabaseConfig>(
    File("database/config.yaml"),
    YamlFileFormatter(
        DatabaseConfig.serializer()
    ),
    { DatabaseConfig.Default.get() }
)