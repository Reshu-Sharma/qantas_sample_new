package com.example.qantas.ui.airportlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qantas.domain.AirportListUseCase
import com.example.qantas.model.Airport
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AirportsViewModel @Inject constructor():ViewModel() {

    @Inject
    lateinit var airportListUseCase: AirportListUseCase
    private val airportsList = MutableLiveData<List<Airport>>()
    var airportsListLiveData: LiveData<List<Airport>> = airportsList

    private val error = MutableLiveData<String>()
    var errorLiveData: LiveData<String> = error


    fun getAirports(){
        val response = airportListUseCase.executeGetAirPortList()
        val observer = getObserver()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    private fun getObserver(): DisposableSingleObserver<List<Airport>> {
        return object :DisposableSingleObserver<List<Airport>>(){
            override fun onError(e: Throwable) {
                var message = e.message
                if (e is HttpException)
                    message = message+" "+e.code()
                error.postValue(message)
            }

            override fun onSuccess(airports: List<Airport>) {
                airportsList.postValue(airports)
            }

        }
    }

}