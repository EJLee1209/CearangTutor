
package com.dldmswo1209.caerangtutor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("isLogin", Context.MODE_PRIVATE)
        val uid = sharedPreferences.getString("uid", "").toString()

        CoroutineScope(Dispatchers.Default).launch {
            async {
                delay(2000)
            }.await()

            if(uid == ""){
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }else{
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }
    }
}