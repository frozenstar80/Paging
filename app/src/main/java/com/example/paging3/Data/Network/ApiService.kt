package com.example.paging3.Data.Network

import com.example.paging3.Data.Dogs
import com.example.paging3.Data.LoginResponse
import retrofit2.http.*

interface ApiService {

    companion object{
        const val BASE_URL = "https://anwitrix-hr-us.herokuapp.com/"
    }


    @Headers("Content-Type:application/json")
    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username:String,
        @Field("password") password:String
    ):LoginResponse


    @GET("v1/images/search")
    suspend fun getAllDogs(
        @Query("page") page:Int,
        @Query("limit") limit:Int
    ):List<Dogs>
}