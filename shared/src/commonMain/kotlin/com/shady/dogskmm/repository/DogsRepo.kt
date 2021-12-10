package com.shady.dogskmm.repository

import com.shady.dogskmm.network.ApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogsRepo {
    suspend fun getBreeds() = withContext(Dispatchers.Default) {
        val response = ApiImpl().getBreeds()
        if (response.isNotEmpty())
            response
        else
            emptyList()
    }

    suspend fun getDogs(breedId: Int) = withContext(Dispatchers.Default) {
        val response = ApiImpl().getDogs(breedId)
        if (response.isNotEmpty())
            response
        else
            emptyList()
    }
}