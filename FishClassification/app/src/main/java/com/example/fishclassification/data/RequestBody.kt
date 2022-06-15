package com.example.fishclassification.data


import com.google.gson.annotations.SerializedName

data class RequestBody(
    @SerializedName("Inputs")
    val inputs: Inputs,

    @SerializedName("GlobalParameters")
    val globalParameters: String
) {
    companion object {
        fun create(fishData: List<FishData>): RequestBody {
            val inputs = Inputs(fishData)
            return RequestBody(inputs, "")
        }
    }
}
    data class Inputs(
        @SerializedName("WebServiceInput0")
        val input: List<FishData>
    )

