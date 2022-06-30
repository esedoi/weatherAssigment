package com.paul.weatherassigment.homefragment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paul.weatherassigment.InterFaceManager
import com.paul.weatherassigment.R
import com.paul.weatherassigment.data.HomeItem

import com.paul.weatherassigment.databinding.ItemImgBinding
import com.paul.weatherassigment.databinding.ItemWeatherBinding

class HomeAdapter(private val onClick: InterFaceManager) :
    ListAdapter<HomeItem, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PIC -> {
                PicHolder(ItemImgBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ))
            }
            TYPE_TEXT -> {
                WeatherHolder(
                    ItemWeatherBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ))
            }
            else -> {
                throw ClassCastException("Unknown viewType $viewType")
            }
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is WeatherHolder -> {
                holder.bind(getItem(position) as HomeItem.WeatherText, onClick)
            }

        }
    }


    class WeatherHolder(private var binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: HomeItem.WeatherText, onClick: InterFaceManager) {

            val context = itemView.context
            itemView.setOnClickListener {
                onClick.click(item.time)
            }

            binding.startTime.text = replace(item.time.startTime)
            binding.endTime.text = replace(item.time.endTime)
            binding.temperture.text = context.getString(R.string.temperature,
                item.time.parameter.parameterName,
                item.time.parameter.parameterUnit)


        }

        private fun replace(string: String): String {
            val old = "-"
            val new = "/"
            return string.replace(old, new)
        }
    }

    class PicHolder(binding: ItemImgBinding) : RecyclerView.ViewHolder(binding.root)


    companion object DiffCallback : DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem == newItem
        }

        private const val TYPE_TEXT = 0x00
        private const val TYPE_PIC = 0x01

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HomeItem.WeatherText -> TYPE_TEXT
            is HomeItem.Pic -> TYPE_PIC
        }
    }

}
