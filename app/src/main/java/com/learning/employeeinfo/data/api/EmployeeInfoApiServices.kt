package com.learning.employeeinfo.data.api

import com.learning.employeeinfo.BuildConfig
import com.learning.employeeinfo.data.api.dto.LoginRequestDto
import com.learning.employeeinfo.data.api.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface EmployeeInfoApiServices {

    @POST(BuildConfig.BASEURL + ApiConstants.SIGN_IN)
    suspend fun signIn(@Body signInRequestDto: LoginRequestDto): NetworkResponse<LoginResponseDto, ErrorResponse>

}