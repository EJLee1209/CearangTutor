package com.dldmswo1209.caerangtutor.Fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.databinding.FragmentHomeBinding
import com.dldmswo1209.caerangtutor.viewModel.MainViewModel
import kotlinx.coroutines.selects.select

class HomeFragment : Fragment(R.layout.fragment_home), AdapterView.OnItemSelectedListener{
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : MainViewModel by activityViewModels()
    private var selectLanguage = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resultTextView.movementMethod = ScrollingMovementMethod()

        viewModel.function.observe(viewLifecycleOwner){
            it.forEach { func->
                if(func.language == selectLanguage){
                    binding.resultTextView.text = func.content
                    return@observe
                }
            }
            binding.resultTextView.text = "함수가 존재하지 않습니다."
        }

        binding.functionNameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val keyword = text.toString()
                if(keyword == "") return
                viewModel.getFunction(keyword)
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        linkedSpinnerAdapter()
    }

    private fun linkedSpinnerAdapter(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.program_language,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.languageSpinner.adapter = adapter

        }
        binding.languageSpinner.onItemSelectedListener = this
    }

    // Sinner 클릭 이벤트 처리
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        (parent?.getChildAt(0) as TextView).setTextColor(Color.WHITE)

        when(pos) {
            0 -> {
                // Java
                selectLanguage = "Java"
            }
            1 -> {
                // JS
                selectLanguage = "JS"
            }
            2 -> {
                // Kotlin
                selectLanguage = "Kotlin"
            }
            3 -> {
                // Python
                selectLanguage = "Python"
            }
            4 -> {
                // C
                selectLanguage = "C"
            }
            5 -> {
                // C++
                selectLanguage = "C++"
            }
            6 -> {
                // C#
                selectLanguage = "C#"
            }
            else -> {
                // test
                selectLanguage = "test"
            }
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onResume() {
        super.onResume()
        linkedSpinnerAdapter()
    }
}