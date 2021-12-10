package com.shady.dogskmm.entities

import kotlinx.serialization.Serializable

@Serializable
data class Breed(
    val id : Int,
    val name : String
)

/*val weight : WeightHeight,
val height : WeightHeight,
val id : Int,
val name : String,
val bred_for : String,
val breed_group : String,
val life_span : String,
val temperament : String,
val origin : String,
val reference_image_id : String*/
