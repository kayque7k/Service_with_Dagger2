package com.wolfdevelloper.estudo.TestServiceStatement

import com.wolfdevelloper.estudo.entity.ListStatement
import com.wolfdevelloper.estudo.interactor.remote.connect.URL
import com.wolfdevelloper.estudo.interactor.remote.iapi.IStatementApi
import com.wolfdevelloper.estudo.interactor.remote.service.statement.IServiceStatement
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestServiceStatement : IServiceStatement {

    @Test
    override fun getStatement(
        id: Int,
        sucess: (listStatement: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL.WEB_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IStatementApi::class.java)

        val call = retrofit.statement(id).execute()

        if(call.isSuccessful)
            sucess.invoke(call)
        else
            failure.invoke(Throwable(call.message()))
    }

}