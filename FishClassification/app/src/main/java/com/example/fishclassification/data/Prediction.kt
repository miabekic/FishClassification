package com.example.fishclassification.data


import com.google.gson.annotations.SerializedName

data class Prediction(
    var category: String,
    var id: Int,
    @SerializedName("Scored Probabilities_Black Sea Sprat")
    var scoredProbabilitiesBlackSeaSprat: Double,
    @SerializedName("Scored Probabilities_Gilt-Head Bream")
    var scoredProbabilitiesGiltHeadBream: Double,
    @SerializedName("Scored Probabilities_Hourse Mackerel")
    var scoredProbabilitiesHourseMackerel: Double,
    @SerializedName("Scored Probabilities_Red Mullet")
    var scoredProbabilitiesRedMullet: Double,
    @SerializedName("Scored Probabilities_Red Sea Bream")
    var scoredProbabilitiesRedSeaBream: Double,
    @SerializedName("Scored Probabilities_Sea Bass")
    var scoredProbabilitiesSeaBass: Double,
    @SerializedName("Scored Probabilities_Shrimp")
    var scoredProbabilitiesShrimp : Double,
    @SerializedName("Scored Probabilities_Striped Red Mullet")
    var scoredProbabilitiesStripedRedMullet: Double,
    @SerializedName("Scored Probabilities_Trout")
    var scoredProbabilitiesTrout : Double,
    @SerializedName("Scored Labels")
    var scoredLabels: String
)
