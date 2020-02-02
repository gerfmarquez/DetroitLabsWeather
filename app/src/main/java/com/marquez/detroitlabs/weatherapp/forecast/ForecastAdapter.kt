package com.marquez.detroitlabs.weatherapp.forecast

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.marquez.detroitlabs.weatherapp.R
import com.marquez.detroitlabs.weatherapp.util.ListAdapter
import java.text.SimpleDateFormat


class ForecastAdapter (val context: Context) :  ListAdapter<ForecastData, ForecastAdapter.ForecastHolder>(
        diffCallback =  object : DiffUtil.ItemCallback<ForecastData>() {
            override fun areItemsTheSame(oldItem: ForecastData, newItem: ForecastData): Boolean {
                return oldItem.dateTime == newItem.dateTime
            }
            override fun areContentsTheSame(oldItem: ForecastData, newItem: ForecastData): Boolean {
                return oldItem.dateTime == newItem.dateTime
            }
        }
) {

    class ForecastHolder(val forecastRowLayout: LinearLayout) : RecyclerView.ViewHolder(forecastRowLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ForecastHolder {
        val searchResultLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_result_list, parent, false) as LinearLayout
        return ForecastHolder(searchResultLayout)
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {
        holder.forecastRowLayout.findViewById<TextView>(R.id.tv_forecast_temperature)
            .text = ""+getItem(position).temperature
        holder.forecastRowLayout.findViewById<TextView>(R.id.tv_forecast_date_time)
            .text =  toDate(getItem(position).dateTime)
        holder.forecastRowLayout.findViewById<ImageView>(R.id.img_forecast_icon)
            .setImageDrawable( getImage(context, getItem(position).icon ))
    }
    companion object {
        fun getImage(context: Context, name: String?): Drawable? {
            val iconName = "a"+name
            return context.resources
                .getDrawable(context.resources.getIdentifier(iconName, "drawable" , context.packageName))
        }
        fun toDate(dateTime : Long) : String {
            val simpleDateFormat  = SimpleDateFormat("EEE, d MMM HH:mm aaa")
            return simpleDateFormat.format(dateTime * 1000 )
        }
    }
}