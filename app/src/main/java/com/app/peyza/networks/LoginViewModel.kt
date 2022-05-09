package com.app.peyza.views.modules.loginModule

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.peyza.base.NoInternetException
import com.app.peyza.networks.Resource
import com.app.peyza.networks.Respository
import com.app.peyza.networks.Status
import com.app.peyza.views.models.CovidData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(val repository: Respository) : ViewModel() {
    var job: Job? = null

    var covidData = MediatorLiveData<Resource<CovidData>>()
    var covid: MutableLiveData<Resource<CovidData>>? = null

    fun covidDataApi() {
        covid = MutableLiveData()
        covid?.value = Resource.Loading(Status.LOADING)
        covidData.addSource(covid!!) {
            covidData.value = it
        }

        job = viewModelScope.launch {
            try {
                covidData.addSource(repository.covidDataApi()) {
                    covidData.value = it
                }

            } catch (e: NoInternetException) {
                covid = MutableLiveData()
                covid?.value = Resource.Error(e.message!!, Status.ERROR)
                covidData.addSource(covid!!) {
                    covidData.value = it
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}