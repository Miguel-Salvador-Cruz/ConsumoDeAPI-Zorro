package com.example.consumodeapi

import com.google.gson.annotations.SerializedName

class ApiResponse (
    @SerializedName("image") var image: List<String>,
    @SerializedName("link") var link:String)