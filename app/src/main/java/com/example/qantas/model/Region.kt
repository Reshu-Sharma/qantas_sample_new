package com.example.qantas.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Region(
    val regionCode: String,
    val regionName: String
):Parcelable