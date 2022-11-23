package com.dldmswo1209.caerangtutor.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dldmswo1209.caerangtutor.MainActivity
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.adapter.PostListAdapter
import com.dldmswo1209.caerangtutor.databinding.FragmentPostBinding
import com.dldmswo1209.caerangtutor.viewModel.MainViewModel

class PostFragment : Fragment(R.layout.fragment_post) {
    private lateinit var binding: FragmentPostBinding
    private val adapter = PostListAdapter()
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostBinding.bind(view)

        binding.postRecyclerView.adapter = adapter

        binding.writeButton.setOnClickListener {
            val bottomSheet = WriteBottomSheetFragment()
            bottomSheet.show((activity as MainActivity).supportFragmentManager, bottomSheet.tag)
        }

        viewModel.getAllPost().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

    }
}