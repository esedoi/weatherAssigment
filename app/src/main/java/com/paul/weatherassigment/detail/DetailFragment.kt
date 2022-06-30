package com.paul.weatherassigment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.paul.weatherassigment.R

import com.paul.weatherassigment.databinding.FragmentDetailBinding



class DetailFragment : Fragment() {

    private val arg: DetailFragmentArgs by navArgs()


    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val time = arg.time

        binding.detailStart.text = time.startTime
        binding.detailEnd.text = time.endTime
        binding.detailTemperture.text = getString(R.string.temperature
            , time.parameter.parameterName
            , time.parameter.parameterUnit
        )

        return  binding.root
    }


}