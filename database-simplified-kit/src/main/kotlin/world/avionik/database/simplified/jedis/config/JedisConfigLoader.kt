package world.avionik.database.simplified.jedis.config

import world.avionik.configkit.ConfigLoader
import world.avionik.configkit.format.YamlFileFormatter
import java.io.File

/**
 * @author Niklas Nieberler
 */

class JedisConfigLoader(
    path: String
) : ConfigLoader<JedisConfig>(
    File("$path/jedis-settings.yaml"),
    YamlFileFormatter(
        JedisConfig.serializer()
    ),
    { JedisConfig.Default.get() }
)