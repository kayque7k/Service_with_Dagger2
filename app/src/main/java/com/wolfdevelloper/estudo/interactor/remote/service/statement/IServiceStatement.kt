package com.wolfdevelloper.estudo.interactor.remote.service.statement

import com.wolfdevelloper.estudo.entity.ListStatement
import retrofit2.Response

interface IServiceStatement {

    fun getStatement(
        id: Int = 0,
        sucess: (listStatement: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )
}