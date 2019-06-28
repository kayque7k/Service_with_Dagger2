package com.wolfdevelloper.estudo.entity

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    var code: Int = 0,
    var message: String = ""
)