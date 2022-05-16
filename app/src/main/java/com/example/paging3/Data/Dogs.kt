package com.example.paging3.Data

import com.google.gson.annotations.SerializedName

data class Dogs(
    @SerializedName("breeds")
    val breeds:List<Breeds>?,
    @SerializedName("url")
    val url:String?
)

data class Breeds(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("name")
    val name:String?
)
