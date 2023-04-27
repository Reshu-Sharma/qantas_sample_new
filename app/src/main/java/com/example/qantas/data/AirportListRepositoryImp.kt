package com.example.qantas.data

import com.example.qantas.domain.AirportListRepository
import com.example.qantas.model.Airport
import io.reactivex.Single
import javax.inject.Inject

class AirportListRepositoryImp @Inject constructor(apiClient: ApiClient): AirportListRepository {
  private var apiClient: ApiClient = apiClient
  override fun getAirports(): Single<List<Airport>> {
    return apiClient.getAirports()
  }
}