package com.dldmswo1209.caerangtutor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.data.Mentor
import com.dldmswo1209.caerangtutor.databinding.MentorItemBinding

class MentorListAdapter(val mentorList: MutableList<Mentor>) : RecyclerView.Adapter<MentorListAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: MentorItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(mentor: Mentor){
            binding.mentorNameTextView.text = mentor.name
            binding.profileImageView.setImageResource(R.drawable.user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MentorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mentorList[position])
    }

    override fun getItemCount(): Int = mentorList.size

}