package com.example.consumodeapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ApiAdapter (val images:List<String>): RecyclerView.Adapter<ApiViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApiViewHolder(layoutInflater.inflate(R.layout.item_api,parent,false))
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = images.size
}