package com.platzi.conferenciasapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.platzi.conferenciasapp.model.Conference
import com.platzi.conferenciasapp.model.Speaker
import com.platzi.conferenciasapp.network.Callback
import com.platzi.conferenciasapp.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakerFromFirebase()
    }
    fun getSpeakerFromFirebase() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSchedule.postValue(result)
                processFinished()
            }
            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }
    fun processFinished(){
        isLoading.value = true
    }
}