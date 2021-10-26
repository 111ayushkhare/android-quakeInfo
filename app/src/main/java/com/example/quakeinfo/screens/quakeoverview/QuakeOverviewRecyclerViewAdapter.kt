package com.example.quakeinfo.screens.quakeoverview

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
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

        val timeAtQuake = convertLongTimeToDateTime(currentItem.time)

        holder.quakeMagnitude.text = (Math.round(currentItem.mag.toDouble() * 10.0) / 10.0).toString()
        holder.quakePlace.text = currentItem.place
        holder.quakeTime.text = timeAtQuake

        val gmmIntentUri = Uri.parse("geo:0, 0?q=" + currentItem.place)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                context.startActivity(mapIntent)
            }
        })

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            var sendText = "Nature's threat ! The specified location - " + currentItem.place + " encountered an earthquake with a magnitude of " + currentItem.mag + " on " + timeAtQuake + ". Please be safe !"
            putExtra(Intent.EXTRA_TEXT, sendText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        holder.itemView.setOnLongClickListener {
            context.startActivity(shareIntent)
            true
        }

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

