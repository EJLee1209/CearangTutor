package com.dldmswo1209.caerangtutor.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.databinding.FragmentWriteBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WriteBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentWriteBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.closeButton.setOnClickListener {
            dialog?.dismiss()
        }
        binding.addButton.setOnClickListener {
            val funcName = binding.functionNameEditText.text.toString()
            val code = binding.codeEditText.text.toString()

        }

    }
}