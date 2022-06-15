package com.example.fishclassification.services

import com.example.fishclassification.data.RequestBody
import com.example.fishclassification.data.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIService {
    fun predictFish(entryData: RequestBody, onResult: (ResponseBody?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestAPI::class.java)
        retrofit.predictFish(entryData).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    onResult(response.body())
                }
            }
        )
    }
}
