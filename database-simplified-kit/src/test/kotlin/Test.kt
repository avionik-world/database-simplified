import world.avionik.database.simplified.DatabaseSimplifiedKit
import world.avionik.database.simplified.jedis.JedisSettings
import world.avionik.database.simplified.morphia.MorphiaSettings

class Test {

    init {
        DatabaseSimplifiedKit.createConfiguration()
            .withMorphia(MorphiaSettings.fromConfig())
            .withJedis(JedisSettings.fromConfig())
            .start()
    }

}