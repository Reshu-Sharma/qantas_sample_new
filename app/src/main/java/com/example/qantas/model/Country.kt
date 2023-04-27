package com.example.qantas.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val countryCode: String,
    val countryName: String
):Parcelable