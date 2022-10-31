package com.dldmswo1209.caerangtutor.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dldmswo1209.caerangtutor.model.Function
import com.dldmswo1209.caerangtutor.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = Repository()

    private var _function = MutableLiveData<List<Function>>()
    val function : LiveData<List<Function>>
        get() = _function

    fun getFunction(searchType: String, keyWord: String) = viewModelScope.launch {
        _function.postValue(repository.getFunction(searchType, keyWord))
    }

}