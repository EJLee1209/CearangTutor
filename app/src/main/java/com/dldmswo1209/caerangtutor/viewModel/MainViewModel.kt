package com.dldmswo1209.caerangtutor.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dldmswo1209.caerangtutor.model.Function
import com.dldmswo1209.caerangtutor.model.Post
import com.dldmswo1209.caerangtutor.model.User
import com.dldmswo1209.caerangtutor.repository.Repository
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {
    private val repository = Repository()

    private var _function = MutableLiveData<List<Function>>()
    val function : LiveData<List<Function>>
        get() = _function

    private var _addFunctionResponse = MutableLiveData<String>()
    val addFunctionResponse : LiveData<String>
        get() = _addFunctionResponse


    fun getFunction(keyWord: String) = viewModelScope.launch {
        _function.postValue(repository.getFunction(keyWord))
    }


    fun getUserData(uid: String): LiveData<User>{
        val user = MutableLiveData<User>()
        repository.getUserData(uid).observeForever{
            user.postValue(it)
        }
        return user
    }

    fun getAllPost(): LiveData<MutableList<Post>>{
        val postList = MutableLiveData<MutableList<Post>>()
        repository.getAllPost().observeForever{
            postList.postValue(it)
        }
        return postList
    }

    fun writePost(post: Post){
        repository.writePost(post)
    }

}