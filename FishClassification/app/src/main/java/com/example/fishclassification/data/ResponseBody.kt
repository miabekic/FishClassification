package com.example.fishclassification.data

import com.google.gson.annotations.SerializedName

data class ResponseBody(
    @SerializedName("Results")
    val results: Results
)

data class Results(
    @SerializedName("WebServiceOutput0")
    val output: List<Prediction>
)


