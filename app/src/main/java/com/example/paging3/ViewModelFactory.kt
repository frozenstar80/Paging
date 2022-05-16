package com.example.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paging3.Data.Network.ApiService
import com.example.paging3.Data.Repository.LoginRepository
import com.example.paging3.viewModel.LoginViewModel

class ViewModelFactory constructor(private val apiService: ApiService)  : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(LoginRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }



}