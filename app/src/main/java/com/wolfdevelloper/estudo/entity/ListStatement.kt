package com.wolfdevelloper.estudo.entity

import kotlinx.serialization.Serializable


@Serializable
data class ListStatement(
    var statementList: MutableList<Statement> = ArrayList(),
    var error: Error = Error()
)