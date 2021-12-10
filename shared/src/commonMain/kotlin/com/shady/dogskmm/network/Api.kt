package com.shady.dogskmm.network

import com.shady.dogskmm.entities.Breed
import com.shady.dogskmm.entities.Dog

interface Api {
    suspend fun getBreeds(): List<Breed>

    suspend fun getDogs(breedId: Int): List<Dog>
}