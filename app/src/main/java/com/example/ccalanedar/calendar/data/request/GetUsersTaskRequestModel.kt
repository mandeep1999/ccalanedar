package com.example.ccalanedar.calendar.data.request

import com.google.gson.annotations.SerializedName

data class GetUsersTaskRequestModel(
    @SerializedName("user_id")
    var userId: Int? = null
)
