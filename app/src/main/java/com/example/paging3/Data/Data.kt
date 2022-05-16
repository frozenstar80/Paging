package com.example.paging3.Data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("key"  ) var key  : Int?    = null
)
