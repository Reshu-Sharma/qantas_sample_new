package com.example.qantas.domain

import com.example.qantas.model.Airport
import io.reactivex.Single

interface AirportListRepository {

  fun getAirports(): Single<List<Airport>>

}