package com.dldmswo1209.caerangtutor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.databinding.PostItemBinding
import com.dldmswo1209.caerangtutor.model.Post

class PostListAdapter: ListAdapter<Post,PostListAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.userNameTextView.text = post.writer.name
            binding.postTextView.text = post.code
            binding.titleTextView.text = post.funcName
            binding.profileImageView.setImageResource(R.drawable.user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<Post>(){
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.key == newItem.key
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }

        }
    }


}