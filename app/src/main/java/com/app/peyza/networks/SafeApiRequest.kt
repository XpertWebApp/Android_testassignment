package com.app.peyza.networks

import androidx.lifecycle.MutableLiveData
import com.app.peyza.utils.Constants
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(
        data: MutableLiveData<Resource<T>>,
        call: suspend () -> Response<T>
    ): MutableLiveData<Resource<T>> {
        val response = call.invoke()
        if (response.isSuccessful) {

            data.value = response.body()?.let {
                Resource.Success(it, "Success", Status.SUCCESS)
            }

        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                    data.value = Resource.Error(message = message.toString(), status = Status.ERROR)
                } catch (e: JSONException) {

                    message.append(error)
                    if (response.code() == 401)
                        data.value = Resource.Error(Constants.UNAUTHORIZED, Status.ERROR)
                    else
                        data.value = Resource.Error(response.message(), Status.ERROR)

                }


            }

        }

        return data
    }

}