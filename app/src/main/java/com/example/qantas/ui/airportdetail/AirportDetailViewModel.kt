package com.example.qantas.ui.airportdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qantas.model.Airport
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AirportDetailViewModel @Inject constructor(): ViewModel() {

  private val airportName = MutableLiveData<String>()
  var airportNameLiveData: LiveData<String> = airportName

  private val countryName = MutableLiveData<String>()
  var countryNameLiveData: LiveData<String> = countryName

  private val timeZone = MutableLiveData<String>()
  var timeZoneLiveData: LiveData<String> = timeZone

  private val address = MutableLiveData<String>()
  var addressLiveData: LiveData<String> = address



  fun prepareDetailScreenData(airport: Airport){
    airport.let {
      airportName.value = airport.airportName
      countryName.value = airport.country.countryName
      timeZone.value = airport.city.timeZoneName
      address.value = "${airport.region.regionName},${airport.city.cityName}"
    }
  }
}