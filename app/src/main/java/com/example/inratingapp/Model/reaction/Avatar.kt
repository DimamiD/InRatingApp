package com.example.inratingapp.Model.reaction

import com.google.gson.annotations.SerializedName

data class Avatar (
    @SerializedName("id")
    val id:Int,
    @SerializedName("url")
    val url:String,
    @SerializedName("url_medium")
    val urlMedium:String,
    @SerializedName("url_small")
    val urlSmall:String
)
