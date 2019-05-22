package com.example.inratingapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

import com.example.inratingapp.Model.Post
import com.example.inratingapp.Model.reaction.ReactionResponse
import com.example.inratingapp.Repository.Repository

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private var post : MediatorLiveData<Post> = MediatorLiveData()
    private var likers : MediatorLiveData<ReactionResponse> = MediatorLiveData()
    private var commentators : MediatorLiveData<ReactionResponse> = MediatorLiveData()
    private var mentions : MediatorLiveData<ReactionResponse> = MediatorLiveData()
    private var reposters : MediatorLiveData<ReactionResponse> = MediatorLiveData()

    fun fetchPost(id:Int) : LiveData<Post> {
        post.addSource(Repository.fetchPost(id)) { data -> post.setValue(data) }
        return post
    }

    fun fetchLikers(id:Int) : LiveData<ReactionResponse> {
        likers.addSource(Repository.fetchLikers(id)) { data -> likers.setValue(data) }
        return likers
    }

    fun fetchCommentators(id:Int) : LiveData<ReactionResponse> {
        commentators.addSource(Repository.fetchCommentators(id)) { data -> commentators.setValue(data) }
        return commentators
    }

    fun fetchMentions(id:Int) : LiveData<ReactionResponse> {
        mentions.addSource(Repository.fetchMentions(id)) { data -> mentions.setValue(data) }
        return mentions
    }

    fun fetchReposters(id:Int) : LiveData<ReactionResponse> {
        reposters.addSource(Repository.fetchReposters(id)) { data -> reposters.setValue(data) }
        return reposters
    }
}