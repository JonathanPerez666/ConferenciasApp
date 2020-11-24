package com.platzi.conferenciasapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.platzi.conferenciasapp.model.Conference
import com.platzi.conferenciasapp.network.Callback
import com.platzi.conferenciasapp.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }
        fun getScheduleFromFirebase() {
            firestoreService.getSchedule(object: Callback<List<Conference>> {
                override fun onSuccess(result: List<Conference>?) {
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