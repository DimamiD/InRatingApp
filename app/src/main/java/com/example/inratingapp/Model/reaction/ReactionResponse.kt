package com.example.inratingapp.Model.reaction

import com.google.gson.annotations.SerializedName

data class ReactionResponse(
    @SerializedName("data")
    val data:List<User>,
    @SerializedName("meta")
    val meta: Meta
)