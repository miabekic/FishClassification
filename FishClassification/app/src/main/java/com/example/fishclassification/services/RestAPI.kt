package com.example.fishclassification.services

import com.example.fishclassification.data.RequestBody
import com.example.fishclassification.data.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestAPI {
    @Headers(
        "Authorization: Bearer 8al7WEUJsQDzmVhQj3qp9fmEEugWA9eK",
        "Content-Type: application/json")
    @POST("/score")
    fun predictFish(@Body entryData: RequestBody): Call<ResponseBody>
}

