package com.example.inratingapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import com.example.inratingapp.viewmodels.PostViewModel
import com.example.inratingapp.Model.reaction.ReactionResponse
import com.example.inratingapp.R
import com.example.inratingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val ID = 2729
    private lateinit var viewModel: PostViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)

        viewModel.fetchPost(ID).observe(this, Observer {
            binding.views.views.text = it.views.toString()
            binding.bookmarks.bookmarks.text = it.bookmarks.toString()
        })

        viewModel.fetchLikers(ID).observe(this, Observer {
            val localBinding = binding.likers
            updateUI(localBinding.label, localBinding.recycler, it)
        })

        viewModel.fetchCommentators(ID).observe(this, Observer {
            val localBinding = binding.commentators
            updateUI(localBinding.label, localBinding.recycler, it)
        })

        viewModel.fetchMentions(ID).observe(this, Observer {
            val localBinding = binding.mentions
            updateUI(localBinding.label, localBinding.recycler, it)
        })

        viewModel.fetchReposters(ID).observe(this, Observer {
            val localBinding = binding.reposters
            updateUI(localBinding.label, localBinding.recycler, it)
        })

    }

    private fun updateUI(textView: TextView, recycler: RecyclerView, it: ReactionResponse) {
        if(it.meta.total > 0) {
            textView.text = it.meta.total.toString()
            recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recycler.adapter = UsersAdapter(it.data)
        }
    }
}
