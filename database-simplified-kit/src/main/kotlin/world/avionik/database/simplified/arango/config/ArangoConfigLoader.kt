package world.avionik.database.simplified.arango.config

import world.avionik.configkit.ConfigLoader
import world.avionik.configkit.format.YamlFileFormatter
import java.io.File

/**
 * @author Niklas Nieberler
 */

class ArangoConfigLoader(
    path: String
) : ConfigLoader<ArangoConfig>(
    File("$path/arango-settings.yaml"),
    YamlFileFormatter(
        ArangoConfig.serializer()
    ),
    { ArangoConfig.Default.get() }
)