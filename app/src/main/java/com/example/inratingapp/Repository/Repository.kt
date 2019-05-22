package com.example.inratingapp.Repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.inratingapp.Model.Post
import com.example.inratingapp.Model.reaction.ReactionResponse
import com.example.inratingapp.Repository.Data.BASE_URL
import com.example.inratingapp.Repository.Data.NETWORK_ERROR_MESSAGE
import com.example.inratingapp.Repository.Data.TOKEN


object Repository {
    private val api: PostApi
    private val retrofit: Retrofit

    init {
        val client = OkHttpClient.Builder().addInterceptor(Interceptor.invoke {
            val newRequest = it.request().newBuilder()
                .addHeader("Authorization", "Bearer $TOKEN")
                .build()
            return@invoke it.proceed(newRequest)
        }).build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(PostApi::class.java)
    }

    fun fetchPost(id:Int): LiveData<Post> {
        val call = api.getPost(id)
        val data = MutableLiveData<Post>()

        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.body() != null)
                    data.postValue(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e(TAG, NETWORK_ERROR_MESSAGE + t.message)
            }
        })

        return data
    }

    fun fetchLikers(id:Int): LiveData<ReactionResponse> {
        return fetch(api.getLikers(id))
    }

    fun fetchCommentators(id:Int): LiveData<ReactionResponse> {
        return fetch(api.getCommentators(id))
    }

    fun fetchMentions(id:Int): LiveData<ReactionResponse> {
        return fetch(api.getMentions(id))
    }

    fun fetchReposters(id:Int): LiveData<ReactionResponse> {
        return fetch(api.getReposters(id))
    }

    private fun fetch(call: Call<ReactionResponse>): MutableLiveData<ReactionResponse> {
        val data = MutableLiveData<ReactionResponse>()
        call.enqueue(object : Callback<ReactionResponse> {
            override fun onResponse(call: Call<ReactionResponse>, response: Response<ReactionResponse>) {
                if (response.body() != null)
                    data.postValue(response.body())
            }

            override fun onFailure(call: Call<ReactionResponse>, t: Throwable) {
                Log.e(TAG, NETWORK_ERROR_MESSAGE + t.message)
            }
        })
        return data
    }
}