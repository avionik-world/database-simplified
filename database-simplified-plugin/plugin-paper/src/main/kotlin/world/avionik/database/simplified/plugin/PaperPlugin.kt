package world.avionik.database.simplified.plugin

import org.bukkit.plugin.java.JavaPlugin
import world.avionik.database.simplified.shared.DatabaseSimplified

/**
 * @author Niklas Nieberler
 */

class PaperPlugin : JavaPlugin() {

    override fun onLoad() {
        DatabaseSimplified.create()
    }

}