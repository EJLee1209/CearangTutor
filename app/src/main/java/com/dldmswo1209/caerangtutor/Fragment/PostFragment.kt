package com.dldmswo1209.caerangtutor.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.adapter.PostListAdapter
import com.dldmswo1209.caerangtutor.data.posts
import com.dldmswo1209.caerangtutor.databinding.FragmentPostBinding

class PostFragment : Fragment(R.layout.fragment_post) {
    private lateinit var binding: FragmentPostBinding
    private val adapter = PostListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostBinding.bind(view)

        binding.postRecyclerView.adapter = adapter
        adapter.submitList(posts)
    }
}