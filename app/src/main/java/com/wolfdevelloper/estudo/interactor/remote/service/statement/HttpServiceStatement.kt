package com.wolfdevelloper.estudo.interactor.remote.service.statement

import com.wolfdevelloper.estudo.entity.ListStatement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


@Experimental(Experimental.Level.ERROR)
annotation class HttpApi

@HttpApi
class HttpServiceStatement : IServiceStatement {

    override fun getStatement(
        id: Int,
        sucess: (listStatement: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {

        GlobalScope.async {
            var httpConn: HttpURLConnection? = null
            var isReader: InputStreamReader? = null
            var bufReader: BufferedReader? = null
            val readTextBuf = StringBuffer()

            try {
                val url =
                    URL(com.wolfdevelloper.estudo.interactor.remote.connect.URL.WEB_SERVICE + "statements/$id")
                httpConn = url.openConnection() as HttpURLConnection
                httpConn.requestMethod = com.wolfdevelloper.estudo.interactor.remote.connect.URL.REQUEST_METHOD_GET
                httpConn.connectTimeout = 10000
                httpConn.readTimeout = 10000
                val inputStream = httpConn.inputStream
                isReader = InputStreamReader(inputStream)
                bufReader = BufferedReader(isReader)
                var line: String? = bufReader.readLine()
                while (line != null) {
                    readTextBuf.append(line)
                    line = bufReader.readLine()
                }
            } catch (ex: IOException) {
                launch(Dispatchers.Main) {
                    failure.invoke(ex)
                }
            } finally {
                try {
                    if (bufReader != null) {
                        bufReader.close()
                        bufReader = null
                    }

                    if (isReader != null) {
                        isReader.close()
                        isReader = null
                    }

                    if (httpConn != null) {
                        httpConn.disconnect()
                        httpConn = null
                    }

                    if (readTextBuf.isNotEmpty())
                        launch(Dispatchers.Main) {
                            sucess.invoke(
                                Response.success(
                                    Json.parse(
                                        ListStatement.serializer(),
                                        readTextBuf.toString()
                                    )
                                )
                            )
                        }
                } catch (ex: IOException) {
                    launch(Dispatchers.Main) {
                        failure.invoke(ex)
                    }
                }
            }
        }
    }
}