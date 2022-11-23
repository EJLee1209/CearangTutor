package com.dldmswo1209.caerangtutor.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dldmswo1209.caerangtutor.model.AddFunctionBody
import com.dldmswo1209.caerangtutor.model.Post
import com.dldmswo1209.caerangtutor.model.User
import com.dldmswo1209.caerangtutor.retrofitApi.MyApi
import com.dldmswo1209.caerangtutor.retrofitApi.RetrofitInstance
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Repository {
    private val retrofit = RetrofitInstance.getInstance().create(MyApi::class.java)
    private val database = Firebase.database.reference

    suspend fun getFunction(keyword: String) = retrofit.getFunction(keyword)

    fun getUserData(uid: String): LiveData<User> {
        var userData = MutableLiveData<User>()
        database.child("User/${uid}").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val data = snapshot.getValue(User::class.java) ?: return

                    userData.postValue(data)
                }
            }

            override fun onCancelled(error: DatabaseError) {}

        })

        return userData
    }

    fun getAllPost(): LiveData<MutableList<Post>>{
        val postList = MutableLiveData<MutableList<Post>>()

        database.child("Post").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<Post>()
                if(snapshot.exists()){
                    snapshot.children.forEach {
                        val post = it.getValue(Post::class.java)?: return@forEach

                        dataList.add(post)
                    }
                }
                postList.postValue(dataList)
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        return postList
    }

    fun writePost(post: Post){
        val db = database.child("Post").push()
        val key = db.key.toString()

        post.key = key
        db.setValue(post)
    }

}