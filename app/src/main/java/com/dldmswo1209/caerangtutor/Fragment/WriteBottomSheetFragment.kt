package com.dldmswo1209.caerangtutor.Fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.dldmswo1209.caerangtutor.MainActivity
import com.dldmswo1209.caerangtutor.R
import com.dldmswo1209.caerangtutor.databinding.FragmentWriteBottomSheetBinding
import com.dldmswo1209.caerangtutor.model.AddFunctionBody
import com.dldmswo1209.caerangtutor.model.Post
import com.dldmswo1209.caerangtutor.model.User
import com.dldmswo1209.caerangtutor.repository.Repository
import com.dldmswo1209.caerangtutor.retrofitApi.MyApi
import com.dldmswo1209.caerangtutor.retrofitApi.RetrofitInstance
import com.dldmswo1209.caerangtutor.viewModel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.core.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentWriteBottomSheetBinding
    private lateinit var user: User
    private var post = Post()
    private val retrofit = RetrofitInstance.getInstance().create(MyApi::class.java)
    private val repository = Repository()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = (activity as MainActivity).currentUser

        binding.closeButton.setOnClickListener {
            dialog?.dismiss()
        }
        binding.LanguageTextView.setOnClickListener {
            val languages: Array<String> = resources.getStringArray(R.array.program_language)

            val builder = AlertDialog.Builder(requireContext())

            builder.setTitle("언어를 선택해주세요")
            builder.setItems(languages){ p0, pos->
                // 아이템 선택 이벤트
                binding.LanguageTextView.text = languages[pos]
            }
            builder.create().show()

        }

        binding.addButton.setOnClickListener {
            val funcName = binding.functionNameEditText.text.toString()
            val code = binding.codeEditText.text.toString()
            val language = binding.LanguageTextView.text.toString()
            val description = binding.descriptionEditText.text.toString()
            if(funcName == "" || language == "" || code ==""){
                Toast.makeText(requireContext(), "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.d("testt", "code : ${code.contains("\n")}")

            post = Post(funcName,language,code,description,user)
            val addFunctionBody = AddFunctionBody(funcName, description, code, user.name, language)

            retrofit.addFunction(addFunctionBody).enqueue(object: Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        val result = response.body().toString()
                        if(result == "해당 함수가 이미 존재합니다."){
                            Toast.makeText(requireContext(), "이미 존재하는 함수 이름입니다.", Toast.LENGTH_SHORT).show()
                            return
                        }else if(result == "함수 추가 성공"){
                            repository.writePost(post)
                        }
                        Log.d("testt", "observe : ${result}")

                    }else{
                        Log.d("testt", "onResponse: fail")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {}

            })

            dialog?.dismiss()
        }



    }
}