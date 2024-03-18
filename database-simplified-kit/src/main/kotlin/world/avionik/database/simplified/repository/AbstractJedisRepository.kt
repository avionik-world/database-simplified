package world.avionik.database.simplified.repository

import eu.thesimplecloud.jsonlib.JsonLib
import world.avionik.database.simplified.DatabaseSimplifiedKit

/**
 * @author Niklas Nieberler
 */

abstract class AbstractJedisRepository<V : Any>(
    private val valueClass: Class<V>,
    private val databasePattern: String
) : Repository {

    private val jedisPool = DatabaseSimplifiedKit.instance.getJedisPool()

    fun findAll(): List<V> {
        this.jedisPool.resource.use { jedis ->
            val keys = jedis.keys("$databasePattern:*")
            return keys.filter { jedis.exists(it) }
                .map { jedis.get(it) }
                .mapNotNull { getObjectFromJsonString(it) }
        }
    }

    fun findAllKeys(): Set<String> {
        return this.jedisPool.resource.use { it.keys("$databasePattern:*") }
    }

    fun find(key: String): V? {
        this.jedisPool.resource.use {
            if (!it.exists("$databasePattern:$key"))
                return null
            return getObjectFromJsonString(it.get("$databasePattern:$key"))
        }
    }

    fun remove(key: String) {
        this.jedisPool.resource.use { it.del("$databasePattern:$key") }
    }

    fun insert(key: String, value: V) {
        val jsonString = JsonLib.fromObject(value).getAsJsonString()
        this.jedisPool.resource.use { it.set("$databasePattern:$key", jsonString) }
    }

    fun clear() {
        this.jedisPool.resource.use { it.del("$databasePattern:*") }
    }

    private fun getObjectFromJsonString(string: String): V {
        return JsonLib.fromJsonString(string).getObject(this.valueClass)
    }

}