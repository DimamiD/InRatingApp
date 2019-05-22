package com.example.inratingapp.Repository

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

import com.example.inratingapp.Model.Post
import com.example.inratingapp.Model.reaction.ReactionResponse


interface PostApi {
    @POST("posts/get")
    fun getPost(@Query("id") id:Int): Call<Post>

    @POST("posts/likers/all")
    fun getLikers(@Query("id") id:Int): Call<ReactionResponse>

    @POST("posts/commentators/all")
    fun getCommentators(@Query("id") id:Int): Call<ReactionResponse>

    @POST("posts/mentions/all")
    fun getMentions(@Query("id") id:Int): Call<ReactionResponse>

    @POST("posts/reposters/all")
    fun getReposters(@Query("id") id:Int): Call<ReactionResponse>

    @POST("posts/get")
    fun getPost(@Query("slug") slug:String): Call<Post>
}