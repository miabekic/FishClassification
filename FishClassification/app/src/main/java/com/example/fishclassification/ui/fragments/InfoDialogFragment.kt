package com.example.fishclassification.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.fishclassification.R
import com.example.fishclassification.data.Prediction
import com.example.fishclassification.databinding.FragmentDialogBinding


class InfoDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogBinding

    private var probabilityBlackSeaSprat = ""
    private var probabilityGiltHeadBream = ""
    private var probabilityHourseMackerel = ""
    private var probabilityRedMullet = ""
    private var probabilityRedSeaBream = ""
    private var probabilitySeaBass = ""
    private var probabilityShrimp = ""
    private var probabilityStripedRedMullet = ""
    private var probabilityTrout = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogBinding.inflate(inflater)

        binding.apply {
            tvScoredBlackSeaSprat.text=getString(R.string.probabilityBlackSeaSprat, probabilityBlackSeaSprat)
            tvScoredGiltHeadBream.text=getString(R.string.probabilityGiltHeadBream, probabilityGiltHeadBream)
            tvScoredHourseMackerel.text=getString(R.string.probabilityHourseMackerel,probabilityHourseMackerel)
            tvScoredRedMullet.text=getString(R.string.probabilityRedMullet, probabilityRedMullet)
            tvScoredRedSeaBream.text=getString(R.string.probabilityRedSeaBream, probabilityRedSeaBream)
            tvScoredSeaBass.text=getString(R.string.probabilitySeaBass, probabilitySeaBass)
            tvScoredShrimp.text= getString(R.string.probabilityShrimp, probabilityShrimp)
            tvScoredStripedRedMullet.text=getString(R.string.probabilityStripedRedMullet, probabilityStripedRedMullet)
            tvScoredTrout.text=getString(R.string.probabilityTrout, probabilityTrout)
        }
        binding.btnClose.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    fun setInfo(prediction: Prediction){
        probabilityBlackSeaSprat=prediction.scoredProbabilitiesBlackSeaSprat.toString()
        probabilityGiltHeadBream=prediction.scoredProbabilitiesGiltHeadBream.toString()
        probabilityHourseMackerel=prediction.scoredProbabilitiesHourseMackerel.toString()
        probabilityRedMullet=prediction.scoredProbabilitiesRedMullet.toString()
        probabilityRedSeaBream=prediction.scoredProbabilitiesRedSeaBream.toString()
        probabilitySeaBass=prediction.scoredProbabilitiesSeaBass.toString()
        probabilityShrimp=prediction.scoredProbabilitiesShrimp.toString()
        probabilityStripedRedMullet=prediction.scoredProbabilitiesTrout.toString()
        probabilityTrout=prediction.scoredProbabilitiesTrout.toString()
    }

}
