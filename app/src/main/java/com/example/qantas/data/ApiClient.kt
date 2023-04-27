package com.example.qantas.data

import com.example.qantas.model.Airport
import io.reactivex.Single
import retrofit2.http.GET

interface ApiClient {

    @GET("flight/refData/airport")
    fun getAirports(): Single<List<Airport>>
}