package com.wolfdevelloper.estudo.interactor.remote.iapi

import com.wolfdevelloper.estudo.entity.ListStatement
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IStatementApi {

    @GET("statements/{id}")
    fun statement(@Path("id") id: Int) : Call<ListStatement?>

    @GET("statements/{id}")
    suspend fun suspStatement(@Path("id") id: Int) : Response<ListStatement?>
}