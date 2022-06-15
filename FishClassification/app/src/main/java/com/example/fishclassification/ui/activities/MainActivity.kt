package com.example.fishclassification.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.fishclassification.utils.Base64Encoder
import com.example.fishclassification.services.APIService
import com.example.fishclassification.data.FishData
import com.example.fishclassification.data.Prediction
import com.example.fishclassification.data.RequestBody
import com.example.fishclassification.databinding.ActivityMainBinding
import com.example.fishclassification.ui.viewmodels.MainViewModel
import com.example.fishclassification.ui.fragments.InfoDialogFragment
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel = MainViewModel()
    private val galleryPermission = Manifest.permission.READ_EXTERNAL_STORAGE
    private val cameraPermission = Manifest.permission.CAMERA
    private val galleryRequestCode = 1
    private val cameraRequestCode= 2
    private val prediction = Prediction("", 1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "")
    private var function = 0
    private val base64Encoder = Base64Encoder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater).apply {
            btnGallery.setOnClickListener{ chooseImageFromGallery() }
            btnCamera.setOnClickListener { takePicture() }
            btnPrediction.setOnClickListener {
                if(bitmap!=null) {
                    setEnabledButtons(false)
                    val fish=listOf(FishData(base64Encoder.bitmapToBase64(bitmap!!), 1, ""))
                    val entryData= RequestBody.create(fish)
                    val apiService= APIService()
                    apiService.predictFish(entryData){
                        if(it?.results?.output != null){
                            prediction.scoredLabels=it.results.output[0].scoredLabels
                            prediction.scoredProbabilitiesBlackSeaSprat=it.results.output[0].scoredProbabilitiesBlackSeaSprat
                            prediction.scoredProbabilitiesGiltHeadBream=it.results.output[0].scoredProbabilitiesGiltHeadBream
                            prediction.scoredProbabilitiesHourseMackerel=it.results.output[0].scoredProbabilitiesHourseMackerel
                            prediction.scoredProbabilitiesRedMullet=it.results.output[0].scoredProbabilitiesRedMullet
                            prediction.scoredProbabilitiesRedSeaBream=it.results.output[0].scoredProbabilitiesRedSeaBream
                            prediction.scoredProbabilitiesSeaBass=it.results.output[0].scoredProbabilitiesSeaBass
                            prediction.scoredProbabilitiesShrimp=it.results.output[0].scoredProbabilitiesShrimp
                            prediction.scoredProbabilitiesStripedRedMullet=it.results.output[0].scoredProbabilitiesStripedRedMullet
                            prediction.scoredProbabilitiesTrout=it.results.output[0].scoredProbabilitiesTrout
                            viewModel.setPrediction(prediction.scoredLabels)
                            btnDetails.visibility= View.VISIBLE
                            setEnabledButtons(true)
                        } else {
                            Toast.makeText(baseContext, "Something went wrong! Please try again.", Toast.LENGTH_SHORT).show()
                            setEnabledButtons(true)
                        }
                    }
                } else{
                    Toast.makeText(baseContext, "You did not select picture.", Toast.LENGTH_SHORT).show()
                }
            }
            btnDetails.setOnClickListener {
                val dialog= InfoDialogFragment()
                dialog.setInfo(prediction)
                dialog.show(supportFragmentManager, "Info")

            }
        }
        viewModel.image.observe(this) { binding.imgVFish.setImageBitmap(it) }
        viewModel.image.observe(this){
            viewModel.resetPrediction()
            binding.btnDetails.visibility=View.INVISIBLE
        }
        viewModel.prediction.observe(this){binding.tvPrediction.text=it}
        setContentView(binding.root)
    }

    private fun setEnabledButtons(enabled: Boolean){
        binding.btnGallery.isEnabled=enabled
        binding.btnCamera.isEnabled=enabled
        binding.btnPrediction.isEnabled=enabled
    }

    private lateinit var currentPhotoPath: String

    private fun chooseImageFromGallery() {
        if(hasPermissionCompat(galleryPermission)){
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            function=galleryRequestCode
            resultLauncher.launch(intent)
        } else {
            requestPermissionCompat(arrayOf(galleryPermission),galleryRequestCode)
        }
    }

    private fun takePicture() {
        if(hasPermissionCompat(cameraPermission)){
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            function=cameraRequestCode
            intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromTakenPicture())
            resultLauncher.launch(intent)
        } else {
            requestPermissionCompat(arrayOf(cameraPermission),cameraRequestCode)
        }
    }

    private var bitmap: Bitmap? = null
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if(function==galleryRequestCode){
                val imageUri=data?.data
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            }else if(function==cameraRequestCode){
                bitmap=BitmapFactory.decodeFile(currentPhotoPath)
            }
            if(bitmap!=null){
                viewModel.setImage(bitmap!!)
            }
        }
    }

    private lateinit var pictureUri: Uri
    private fun getUriFromTakenPicture(): Uri {
        val fileName = "fish"
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile(fileName, ".png", storageDirectory)
        currentPhotoPath = imageFile.absolutePath
        pictureUri=FileProvider.getUriForFile(this, "com.example.fishclassification.fileprovider", imageFile)
        return pictureUri
    }

    private fun AppCompatActivity.hasPermissionCompat (permission: String): Boolean{
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }
    private fun AppCompatActivity.requestPermissionCompat(permission: Array<String>, requestCode: Int){
        ActivityCompat.requestPermissions(this, permission, requestCode)
    }

}