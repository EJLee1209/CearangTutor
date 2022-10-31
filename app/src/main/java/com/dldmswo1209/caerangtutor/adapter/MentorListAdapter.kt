package com.dldmswo1209.caerangtutor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.caerangtutor.data.Mentor
import com.dldmswo1209.caerangtutor.databinding.MentorItemBinding

class MentorListAdapter : ListAdapter<Mentor, MentorListAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding: MentorItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(mentor: Mentor){
            binding.mentorNameTextView.text = mentor.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MentorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Mentor>(){
            override fun areItemsTheSame(oldItem: Mentor, newItem: Mentor): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Mentor, newItem: Mentor): Boolean {
                return oldItem == newItem
            }

        }
    }

}