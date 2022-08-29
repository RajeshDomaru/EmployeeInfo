package com.learning.employeeinfo.data.api

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName(value = "message", alternate = ["error"])
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("timestamp")
    val timestamp: Long
)
