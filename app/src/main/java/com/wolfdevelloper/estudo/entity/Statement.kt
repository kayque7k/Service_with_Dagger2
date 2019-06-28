package com.wolfdevelloper.estudo.entity

import kotlinx.serialization.Serializable

@Serializable
data class Statement(
    var title: String = "",
    var desc: String = "",
    var date: String = "",
    var value: Double = 0.0
)