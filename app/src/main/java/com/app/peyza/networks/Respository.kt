package com.app.peyza.networks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.peyza.views.models.CovidData

class Respository(private val api: Api) : SafeApiRequest() {

    //simplified version of the retrofit call that comes from support with coroutines
    //Note that this does NOT handle errors, to be added

    suspend fun covidDataApi(
    ): LiveData<Resource<CovidData>> {
        val signUpData = MutableLiveData<Resource<CovidData>>()
        return apiRequest(signUpData) { api.covidDataApi() }
    }
}