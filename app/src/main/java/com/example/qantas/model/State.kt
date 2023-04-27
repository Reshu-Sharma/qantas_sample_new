package com.example.qantas.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    val stateCode: String,
    val stateName: String
):Parcelable