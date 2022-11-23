package com.dldmswo1209.caerangtutor.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.adapter.MentorListAdapter
import com.dldmswo1209.caerangtutor.data.mentors
import com.dldmswo1209.caerangtutor.databinding.FragmentMentorBinding


class MentorFragment : Fragment(R.layout.fragment_mentor) {
    private lateinit var binding: FragmentMentorBinding
    val adapter = MentorListAdapter(mentors)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMentorBinding.bind(view)

        binding.mentorRecyclerView.adapter = adapter

    }
}