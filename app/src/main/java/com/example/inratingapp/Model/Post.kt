package com.example.inratingapp.Model

import com.google.gson.annotations.SerializedName

data class Post(
        @SerializedName("id")
        val id:Int,

        @SerializedName("slug")
        val slug:String,

        @SerializedName("views_count")
        val views:Int,

        @SerializedName("likes_count")
        val likes:Int,

        @SerializedName("comments_count")
        val comments:Int,

        @SerializedName("reposts_count")
        val reposts:Int,

        @SerializedName("bookmarks_count")
        val bookmarks:Int
)