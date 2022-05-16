package com.example.paging3.Data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status"         ) var status         : Int?    = null,
    @SerializedName("data"           ) var data           : Data?   = Data(),
    @SerializedName("successMessage" ) var successMessage : String? = null
)
