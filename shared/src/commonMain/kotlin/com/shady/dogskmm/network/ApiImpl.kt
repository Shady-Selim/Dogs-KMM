package com.shady.dogskmm.network

import com.shady.dogskmm.entities.Breed
import com.shady.dogskmm.entities.Dog
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class ApiImpl: Api  {
    val baseUrl = "https://api.thedogapi.com/v1/"

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
                coerceInputValues = true
            })
        }
    }

    override suspend fun getBreeds(): List<Breed> {
        return httpClient.get("${baseUrl}breeds")
    }

    override suspend fun getDogs(breedId: Int): List<Dog> {
        return httpClient.get("${baseUrl}images/search?breed_id=${breedId}&limit=102&page=0&order=asc")
    }

}