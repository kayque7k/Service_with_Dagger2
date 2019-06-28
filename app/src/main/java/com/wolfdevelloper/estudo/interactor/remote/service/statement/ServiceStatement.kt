package com.wolfdevelloper.estudo.interactor.remote.service.statement

import com.wolfdevelloper.estudo.entity.ListStatement
import com.wolfdevelloper.estudo.interactor.remote.connect.URL
import com.wolfdevelloper.estudo.interactor.remote.iapi.IStatementApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceStatement : IServiceStatement {

    override fun getStatement(
        id: Int,
        sucess: (listStatement: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {

        val retrofit = Retrofit.Builder()
            .baseUrl(URL.WEB_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IStatementApi::class.java)

        val call = retrofit.statement(id = id)
        call.enqueue(object :Callback<ListStatement?> {
            override fun onResponse(call: Call<ListStatement?>, response: Response<ListStatement?>) {
                sucess.invoke(response)
            }

            override fun onFailure(call: Call<ListStatement?>, t: Throwable) {
                failure.invoke(t)
            }
        })
    }
}