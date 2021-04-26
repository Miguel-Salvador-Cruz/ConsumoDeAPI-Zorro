package com.example.consumodeapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.consumodeapi.databinding.ItemApiBinding
import com.squareup.picasso.Picasso

class ApiViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val  binding = ItemApiBinding.bind(view)

    fun bind(image:String){
        Picasso.get().load(image).into(binding.ivApi)
    }
}