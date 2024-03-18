package world.avionik.database.simplified.morphia

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.bson.UuidRepresentation
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import java.util.concurrent.TimeUnit

/**
 * @author Niklas Nieberler
 */

class MorphiaClientFactory(
    private val connectionString: String
) {

    /**
     * Creates a new MongoClient with custom client settings
     * @return new MongoClient
     */
    fun createClient(): MongoClient {
        return MongoClients.create(createClientSettings())
    }

    private fun createClientSettings(): MongoClientSettings {
        val pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        val codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry)
        return MongoClientSettings.builder()
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .codecRegistry(codecRegistry)
            .applyToSocketSettings { it.readTimeout(5, TimeUnit.SECONDS) }
            .applyToClusterSettings { it.serverSelectionTimeout(5, TimeUnit.SECONDS) }
            .applyConnectionString(ConnectionString(this.connectionString))
            .build()
    }

}