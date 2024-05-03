package world.avionik.database.simplified.rabbitmq.config

import world.avionik.configkit.ConfigLoader
import world.avionik.configkit.format.YamlFileFormatter
import java.io.File

/**
 * @author Niklas Nieberler
 */

class RabbitMQConfigLoader(
    path: String
) : ConfigLoader<RabbitMQConfig>(
    File("$path/rabbitmq-settings.yaml"),
    YamlFileFormatter(
        RabbitMQConfig.serializer()
    ),
    { RabbitMQConfig.Default.get() }
)