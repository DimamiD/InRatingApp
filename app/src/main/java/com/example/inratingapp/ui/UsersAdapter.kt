package com.example.inratingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.example.inratingapp.databinding.UserBinding
import com.example.inratingapp.Model.reaction.User

class UsersAdapter(private val mDataList: List<User>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userBinding = UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(userBinding)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user =  mDataList[position]
        Picasso.get()
            .load(user.avatar.urlMedium)
            .into(holder.binding.avatar)

        holder.binding.nickname.text = user.nickname
    }

    inner class UserViewHolder(val binding: UserBinding) : RecyclerView.ViewHolder(binding.root)
}