package com.example.qantas.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val cityCode: String,
    val cityName: String,
    val timeZoneName: String
):Parcelable