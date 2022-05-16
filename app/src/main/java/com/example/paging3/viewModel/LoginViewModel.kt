package com.example.paging3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.paging3.Data.Repository.LoginRepository
import com.example.paging3.utils.Resource
import kotlinx.coroutines.Dispatchers


class LoginViewModel(private val mainRepository: LoginRepository) : ViewModel() {

    fun getUsers(username : String,password:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers(username, password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}