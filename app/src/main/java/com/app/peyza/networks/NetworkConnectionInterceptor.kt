package com.app.peyza.networks

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.app.peyza.R
import com.app.peyza.base.NoInternetException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException

class NetworkConnectionInterceptor(
    var context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if (!isInternetAvailable()) {
                throw NoInternetException(context.getString(R.string.not_internet_connection))
            } else {
                Log.i("LoggingInterceptor", "inside intercept callback")
                val request = chain.request()
                val t1 = System.nanoTime()
                var requestLog = String.format(
                    "Sending request %s on %s%n%s",
                    request.url, chain.connection(), request.headers
                )
                if (request.method.compareTo("post_list_adapter", ignoreCase = true) == 0) {
                    requestLog =
                        "\n" + requestLog + "\n" + bodyToString(
                            request
                        )
                }
                Log.d("TAG", "request\n$requestLog")
                val response = chain.proceed(request)
                val t2 = System.nanoTime()
                val responseLog = String.format(
                    "Received response for %s in %.1fms%n%s",
                    response.request.url, (t2 - t1) / 1e6, response.headers
                )

                val bodyString = response.body!!.string()
                Log.d("TAG", "response only\n$bodyString")
                Log.d("TAG", "response\n$responseLog\n$bodyString")
                return response.newBuilder()
                    .body(bodyString.toResponseBody(response.body!!.contentType()))
                    .build()
            }

        } catch (e: Exception) {
            throw NoInternetException(e.message!!)
        }

    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

    companion object {
        fun bodyToString(request: Request): String {
            return try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                copy.body!!.writeTo(buffer)
                buffer.readUtf8()
            } catch (e: IOException) {
                "did not work"
            }
        }
    }
}