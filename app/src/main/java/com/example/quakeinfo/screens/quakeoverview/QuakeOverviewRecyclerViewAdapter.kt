package com.example.quakeinfo.screens.quakeoverview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quakeinfo.R
import com.example.quakeinfo.network.models.QuakeData
import com.example.quakeinfo.network.models.QuakeFeatures
import java.text.SimpleDateFormat

class QuakeOverviewRecyclerViewAdapter(private val context: QuakeOverviewFragment) :
    RecyclerView.Adapter<QuakeDataViewHolder>() {

    private val quakeFeaturesArrayList = ArrayList<QuakeFeatures>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakeDataViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.quake_item_view, parent, false)
        return QuakeDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuakeDataViewHolder, position: Int) {
        val currentItem = quakeFeaturesArrayList[position].properties

        holder.quakeMagnitude.text = (Math.round(currentItem.mag.toDouble() * 10.0) / 10.0).toString()
        holder.quakePlace.text = currentItem.place
        holder.quakeTime.text = convertLongTimeToDateTime(currentItem.time)
    }
    
    override fun getItemCount(): Int = quakeFeaturesArrayList.size

    fun updateFeaturesList(updatedFeaturesList: List<QuakeFeatures>) {
        quakeFeaturesArrayList.clear()
        quakeFeaturesArrayList.addAll(updatedFeaturesList)

        notifyDataSetChanged()
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertLongTimeToDateTime(longTime: Long): String =
        SimpleDateFormat("MMM dd, yyyy HH:mm:ss").format(longTime)
}

class QuakeDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var quakeMagnitude: TextView = itemView.findViewById(R.id.quake_magnitude)
    var quakePlace: TextView = itemView.findViewById(R.id.quake_place)
    var quakeTime: TextView = itemView.findViewById(R.id.quake_time)
}

