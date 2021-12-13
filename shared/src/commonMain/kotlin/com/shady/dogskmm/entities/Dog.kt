package com.shady.dogskmm.entities

import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    val id : String,
    val url : String
)

/*val breeds : List<Bread>,
val id : String,
val url : String,
val width : Int,
val height : Int*/
