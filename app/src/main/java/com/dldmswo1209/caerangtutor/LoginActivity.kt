package com.dldmswo1209.caerangtutor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dldmswo1209.caerangtutor.databinding.ActivityLoginBinding
import com.dldmswo1209.caerangtutor.model.SignInBody
import com.dldmswo1209.caerangtutor.model.SignUpBody
import com.dldmswo1209.caerangtutor.retrofitApi.MyApi
import com.dldmswo1209.caerangtutor.retrofitApi.RetrofitInstance
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if(email == "" || password == ""){
                Toast.makeText(this, "모든 정보를 입력해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    val uid = auth.currentUser?.uid
                    val sharedPreferences = getSharedPreferences("isLogin", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("uid", uid.toString()).apply()

                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    Toast.makeText(this, "이메일 또는 비밀번호 오류", Toast.LENGTH_SHORT).show()
                }
            }




        }
    }
}