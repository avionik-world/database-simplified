package world.avionik.database.simplified.morphia

import dev.morphia.Datastore
import world.avionik.database.simplified.DatabaseSimplifiedKit

/**
 * @author Niklas Nieberler
 */

/**
 * Creates a new datastore with a database name
 * @param dbName name of the new datastore
 * @return created datastore instance
 */
fun createMorphiaDatastore(dbName: String): Datastore {
    return DatabaseSimplifiedKit.instance.createMorphiaDatastore(dbName)
}