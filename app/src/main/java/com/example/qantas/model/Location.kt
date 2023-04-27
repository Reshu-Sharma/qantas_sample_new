package com.example.qantas.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val aboveSeaLevel: Int,
    val latitude: Double,
    val latitudeDirection: String,
    val latitudeRadius: Double,
    val longitude: Double,
    val longitudeDirection: String,
    val longitudeRadius: Double
):Parcelable