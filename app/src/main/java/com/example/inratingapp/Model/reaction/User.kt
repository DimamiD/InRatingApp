package com.example.inratingapp.Model.reaction

import com.example.inratingapp.Model.reaction.Avatar
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("nickname")
    val nickname:String,

    @SerializedName("avatar_image")
    val avatar: Avatar
)