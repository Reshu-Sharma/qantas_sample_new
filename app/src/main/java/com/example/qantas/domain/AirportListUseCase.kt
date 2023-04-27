package com.example.qantas.domain

import com.example.qantas.model.Airport
import io.reactivex.Single
import javax.inject.Inject

class AirportListUseCase @Inject constructor(airportListRepository: AirportListRepository){
  private var airportListRepository: AirportListRepository = airportListRepository;

  fun executeGetAirPortList(): Single<List<Airport>>{
    return airportListRepository.getAirports();
  }

}