package com.example.qantas.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Airport(
    val airportCode: String,
    val airportName: String,
    val city: City,
    val country: Country,
    val domesticAirport: Boolean,
    val eticketableAirport: Boolean,
    val internationalAirport: Boolean,
    val location: Location,
    val onlineIndicator: Boolean,
    val region: Region,
    val regionalAirport: Boolean,
    val state: State
):Parcelable