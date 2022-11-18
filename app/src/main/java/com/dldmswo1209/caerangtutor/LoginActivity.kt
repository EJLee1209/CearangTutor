package com.dldmswo1209.caerangtutor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dldmswo1209.caerangtutor.databinding.ActivityLoginBinding
import com.dldmswo1209.caerangtutor.model.SignInBody
import com.dldmswo1209.caerangtutor.model.SignUpBody
import com.dldmswo1209.caerangtutor.retrofitApi.MyApi
import com.dldmswo1209.caerangtutor.retrofitApi.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val retrofit = RetrofitInstance.getInstance().create(MyApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.loginButton.setOnClickListener {
            val studentId = binding.studentIdEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if(studentId == "" || password == ""){
                Toast.makeText(this, "모든 정보를 입력해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val signInBody = SignInBody(studentId, password)
            retrofit.signIn(signInBody).enqueue(object: Callback<SignUpBody>{
                override fun onResponse(call: Call<SignUpBody>, response: Response<SignUpBody>) {
                    val user = response.body() ?: return
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("currentUser", user)
                    startActivity(intent)

                    // 자동로그인을 위해 로그인한 사용자의 학번을 저장
                    val sharedPreferences = getSharedPreferences("isLogin", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("studentId", user.studentId).apply()

                    finish()
                }

                override fun onFailure(call: Call<SignUpBody>, t: Throwable) {
                    Log.d("testt", "onFailure: ${t.message}")
                }

            })

        }
    }
}