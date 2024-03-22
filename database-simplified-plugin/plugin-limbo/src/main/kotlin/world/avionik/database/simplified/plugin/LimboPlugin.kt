package world.avionik.database.simplified.plugin

import com.loohp.limbo.plugins.LimboPlugin
import world.avionik.database.simplified.shared.DatabaseSimplified

/**
 * @author Niklas Nieberler
 */

class LimboPlugin : LimboPlugin() {

    override fun onLoad() {
        DatabaseSimplified.create()
    }

}