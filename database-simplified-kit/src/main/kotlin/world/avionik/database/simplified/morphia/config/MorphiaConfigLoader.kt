package world.avionik.database.simplified.morphia.config

import world.avionik.configkit.ConfigLoader
import world.avionik.configkit.format.YamlFileFormatter
import java.io.File

/**
 * @author Niklas Nieberler
 */

class MorphiaConfigLoader(
    path: String
) : ConfigLoader<MorphiaConfig>(
    File("$path/morphia-settings.yaml"),
    YamlFileFormatter(
        MorphiaConfig.serializer()
    ),
    { MorphiaConfig.Default.get() }
)