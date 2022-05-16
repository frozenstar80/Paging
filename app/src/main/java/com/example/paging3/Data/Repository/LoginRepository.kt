package com.example.paging3.Data.Repository

import com.example.paging3.Data.Network.ApiService

class LoginRepository constructor(private val apiService: ApiService) {

    suspend fun getUsers(username : String,password:String) = apiService.login(username, password)
}