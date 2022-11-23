package com.dldmswo1209.caerangtutor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dldmswo1209.caerangtutor.databinding.ActivityRegisterBinding
import com.dldmswo1209.caerangtutor.model.SignUpBody
import com.dldmswo1209.caerangtutor.model.User
import com.dldmswo1209.caerangtutor.retrofitApi.MyApi
import com.dldmswo1209.caerangtutor.retrofitApi.RetrofitInstance
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private val auth = Firebase.auth
    private val dbRef = Firebase.database.reference
    private val retrofit = RetrofitInstance.getInstance().create(MyApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val studentId = binding.studentIdEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val name = binding.nameEditText.text.toString()
            val team = binding.teamEditText.text.toString()

            if(email ==  "" ||studentId == "" || password == "" || name == "" || team == ""){
                Toast.makeText(this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val signUpBody = SignUpBody(name, password, studentId, team)

            retrofit.signUp(signUpBody).enqueue(object: Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val result = response.body().toString()

                    if(result == "회원가입 성공"){
                        Log.d("testt", "onResponse: db 저장 완료")
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener {
                                if(it.isSuccessful){
                                    // db에 저장
                                    val uid = auth.currentUser?.uid.toString()
                                    val newUser = User(email, password, studentId, name, team, uid)
                                    dbRef.child("User/${uid}").setValue(newUser)

                                    finish()
                                }

                            }
                    }else{
                        Toast.makeText(this@RegisterActivity, "이미 회원정보가 존재합니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("testt", "onFailure: ${t.message}")
                }

            })





        }
    }
}