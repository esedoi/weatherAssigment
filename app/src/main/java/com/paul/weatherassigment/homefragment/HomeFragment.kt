package com.paul.weatherassigment.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.weatherassigment.InterFaceManager
import com.paul.weatherassigment.MobileNavigationDirections
import com.paul.weatherassigment.data.HomeItem
import com.paul.weatherassigment.data.Time
import com.paul.weatherassigment.data.WeatherData
import com.paul.weatherassigment.databinding.FragmentHomeBinding
import com.paul.weatherassigment.getVmFactory

const val LOCATION = "臺北市"
const val ELEMENT = "MinT"
const val APIKEY = "CWB-54943AEB-4492-43F9-9A1A-A965076DB7F9"

class HomeFragment : Fragment(), InterFaceManager {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeAdapter: HomeAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null

    private val homeViewModel by viewModels<HomeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeAdapter = HomeAdapter(this)
        layoutManager = LinearLayoutManager(this.context)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = homeAdapter

        homeViewModel.weather.observe(viewLifecycleOwner) {
            val list = toHomeItem(it)
            homeAdapter.submitList(list)
        }


        homeViewModel.getWeather(APIKEY,
            listOf(LOCATION),
            listOf(ELEMENT))


        return root
    }

    private fun toHomeItem(weatherData: WeatherData): List<HomeItem> {

        val items = mutableListOf<HomeItem>()

        for ((index, item) in weatherData.records.location[0].weatherElement[0].time.withIndex()) {

            when (index % 3) {

                2 -> {
                    items.add(HomeItem.Pic("Pic"))
                    items.add(HomeItem.WeatherText(item))
                }
                else -> {
                    items.add(HomeItem.WeatherText(item))
                }
            }

        }

        return items

    }

    override fun click(time: Time) {
        findNavController().navigate(MobileNavigationDirections.actionGlobalNavigationDetail(time))
    }
}