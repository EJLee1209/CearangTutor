package com.dldmswo1209.caerangtutor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.data.Post
import com.dldmswo1209.caerangtutor.databinding.PostItemBinding

class PostListAdapter(val postList: MutableList<Post>) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.userNameTextView.text = post.name
            binding.postTextView.text = post.text
            binding.profileImageView.setImageResource(R.drawable.user)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size



}