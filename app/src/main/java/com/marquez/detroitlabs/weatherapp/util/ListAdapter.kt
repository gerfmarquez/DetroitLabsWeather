package com.marquez.detroitlabs.weatherapp.util


import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class ListAdapter<T, V : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, V>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .build()
) {

}
