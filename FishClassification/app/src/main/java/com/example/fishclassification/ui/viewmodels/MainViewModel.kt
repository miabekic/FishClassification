package com.example.fishclassification.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _image = MutableLiveData<Bitmap>()
    private val _prediction=MutableLiveData<String>()

    val image: LiveData<Bitmap> = _image
    val prediction: LiveData<String> = _prediction

    fun resetPrediction(){
        _prediction.value=""
    }

    fun setPrediction(predictedFish: String){
        _prediction.value=predictedFish
    }

    fun setImage(img: Bitmap){
        _image.value=img
    }
}