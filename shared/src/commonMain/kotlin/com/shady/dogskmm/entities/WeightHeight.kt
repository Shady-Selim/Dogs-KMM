package com.shady.dogskmm.entities

import kotlinx.serialization.Serializable

@Serializable
data class WeightHeight (
    val imperial : String,
    val metric : String
)
