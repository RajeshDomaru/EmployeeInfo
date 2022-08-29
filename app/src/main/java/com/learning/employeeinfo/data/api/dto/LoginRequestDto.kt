package com.learning.employeeinfo.data.api.dto

data class LoginRequestDto(val method: String, val email_address: String, val password: String)
