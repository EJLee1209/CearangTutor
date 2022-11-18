package com.dldmswo1209.caerangtutor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dldmswo1209.caerangtutor.databinding.ActivityRegisterBinding
import com.dldmswo1209.caerangtutor.model.SignUpBody
import com.dldmswo1209.caerangtutor.retrofitApi.MyApi
import com.dldmswo1209.caerangtutor.retrofitApi.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private val retrofit = RetrofitInstance.getInstance().create(MyApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val studentId = binding.studentIdEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val name = binding.nameEditText.text.toString()
            val team = binding.teamEditText.text.toString()

            if(studentId == "" || password == "" || name == "" || team == ""){
                Toast.makeText(this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val signUpBody = SignUpBody(name, password, studentId, team)
            retrofit.signUp(signUpBody).enqueue(object: Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val result = response.body().toString()

                    if(result == "회원가입 성공"){
                        Toast.makeText(this@RegisterActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@RegisterActivity, "이미 존재하는 사용자입니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("testt", "onFailure: ${t.message}")
                }

            })
        }
    }
}