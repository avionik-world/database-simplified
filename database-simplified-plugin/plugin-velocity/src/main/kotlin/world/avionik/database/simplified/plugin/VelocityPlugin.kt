package world.avionik.database.simplified.plugin

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import world.avionik.database.simplified.DatabaseSimplifiedKit
import world.avionik.database.simplified.jedis.JedisSettings
import world.avionik.database.simplified.morphia.MorphiaSettings
import world.avionik.database.simplified.shared.DatabaseSimplified
import java.util.logging.Logger

/**
 * @author Niklas Nieberler
 */

@Plugin(id = "database-simplified", name = "database-simplified", version = "1.0.1", authors = ["Avionik", "MrManHD"])
class VelocityPlugin @Inject constructor(
    val server: ProxyServer,
    val logger: Logger,
) {

    @Subscribe
    fun handleInitialize(event: ProxyInitializeEvent) {
        DatabaseSimplified.create()
    }

}