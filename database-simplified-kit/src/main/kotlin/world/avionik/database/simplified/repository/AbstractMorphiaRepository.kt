package world.avionik.database.simplified.repository

import dev.morphia.Datastore
import dev.morphia.query.Query
import dev.morphia.query.filters.Filters
import java.util.concurrent.CompletableFuture

/**
 * @author Frederick Baier, Niklas Nieberler
 */

abstract class AbstractMorphiaRepository<I : Any, T : Any>(
    val datastore: Datastore,
    private val entityClass: Class<T>
) : Repository {

    fun findAll(): CompletableFuture<List<T>> {
        return CompletableFuture.supplyAsync {
            this.datastore.find(this.entityClass).toList()
        }
    }

    fun findOrNull(identifier: I): CompletableFuture<T?> {
        return CompletableFuture.supplyAsync {
            createIdentifierQuery(identifier).first()
        }
    }

    fun save(value: T): CompletableFuture<Unit> {
        return CompletableFuture.supplyAsync {
            this.datastore.save(value)
        }
    }

    fun remove(identifier: I) {
        CompletableFuture.supplyAsync {
            createIdentifierQuery(identifier).delete()
        }
    }

    fun count(): CompletableFuture<Long> {
        return CompletableFuture.supplyAsync {
            this.datastore.find(this.entityClass).count()
        }
    }

    fun createIdentifierQuery(identifier: I): Query<T> {
        return this.datastore.find(this.entityClass)
            .filter(Filters.eq("_id", identifier))
    }

}