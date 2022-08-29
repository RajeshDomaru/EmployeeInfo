package com.learning.employeeinfo.data.api.dto

data class UserDto(
    val create_date_time: String?,
    val device_token: String?,
    val email_address: String?,
    val employee_limit: Int?,
    val login_date_time: String?,
    val mobile_number: String?,
    val password: String?,
    val user_id: Int?,
    val username: String?
)