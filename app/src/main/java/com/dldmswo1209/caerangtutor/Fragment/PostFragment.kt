package com.dldmswo1209.caerangtutor.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dldmswo1209.caerangtutor.MainActivity
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.adapter.PostListAdapter
import com.dldmswo1209.caerangtutor.databinding.FragmentPostBinding
import com.dldmswo1209.caerangtutor.model.Post
import com.dldmswo1209.caerangtutor.model.User
import com.dldmswo1209.caerangtutor.viewModel.MainViewModel

class PostFragment : Fragment(R.layout.fragment_post) {
    private lateinit var binding: FragmentPostBinding
    private val adapter = PostListAdapter()
    private val viewModel: MainViewModel by activityViewModels()
    private var allPost = mutableListOf<Post>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostBinding.bind(view)

        binding.postRecyclerView.adapter = adapter

        binding.writeButton.setOnClickListener {
            val bottomSheet = WriteBottomSheetFragment()
            bottomSheet.show((activity as MainActivity).supportFragmentManager, bottomSheet.tag)
        }

        binding.searchNameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val keyword = text.toString()
                if(keyword == "") {
                    adapter.submitList(allPost)
                    return
                }
                viewModel.getFunctionFromAuthor(text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        viewModel.getAllPost().observe(viewLifecycleOwner){
            adapter.submitList(it)
            allPost = it
        }

        viewModel.functionListFromAuthor.observe(viewLifecycleOwner){
            val postList = mutableListOf<Post>()
            it.forEach { function ->
                val user = User()
                user.name = function.author
                val post = Post(function.func_name, function.language, function.content, function.description, user, "")
                postList.add(post)
            }

            adapter.submitList(postList)
        }



    }
}