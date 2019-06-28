package com.wolfdevelloper.estudo.interactor.remote.service.statement

import com.wolfdevelloper.estudo.entity.ListStatement
import com.wolfdevelloper.estudo.interactor.remote.connect.URL
import com.wolfdevelloper.estudo.interactor.remote.iapi.IStatementApi
import com.wolfdevelloper.estudo.interactor.remote.service.statement.IServiceStatement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Experimental(Experimental.Level.ERROR)
annotation class TestApi

@TestApi
class TestServiceStatement : IServiceStatement {

    override fun getStatement(
        id: Int,
        sucess: (listStatement: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL.WEB_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IStatementApi::class.java)

        GlobalScope.async {
            try {
                val call = retrofit.suspStatement(id)
                launch(Dispatchers.Main) {
                    if (call.isSuccessful)
                        sucess.invoke(call)
                    else
                        failure.invoke(Throwable(call.message()))
                }
            } catch (t: Throwable) {
                launch(Dispatchers.Main) {
                    failure.invoke(t)
                }
            }
        }
    }

}