package com.shady.dogskmm.android.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shady.dogskmm.entities.Breed
import com.shady.dogskmm.entities.Dog
import com.shady.dogskmm.repository.DogsRepo
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val repo = DogsRepo()

    fun getBreeds(): MutableLiveData<List<Breed>> {
        val data = MutableLiveData<List<Breed>>()
        viewModelScope.launch {
            try {
                data.postValue(repo.getBreeds())
            } catch (e: Exception) {
                Log.e("Get Breeds:", "Error ${e.localizedMessage}")
            }
        }
        return data
    }

    fun getDogs(breedId: Int): MutableLiveData<List<Dog>> {
        val data = MutableLiveData<List<Dog>>()
        viewModelScope.launch {
            try {
                data.postValue(repo.getDogs(breedId))
            } catch (e: Exception) {
                Log.e("Get Dogs:", "Error ${e.localizedMessage}")
            }
        }
        return data
    }
}