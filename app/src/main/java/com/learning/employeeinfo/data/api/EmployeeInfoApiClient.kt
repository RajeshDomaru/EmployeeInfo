package com.learning.employeeinfo.data.api

import com.google.gson.GsonBuilder
import com.learning.employeeinfo.BuildConfig
import com.learning.employeeinfo.data.api.authenticator.HeaderInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object EmployeeInfoApiClient {

    fun client(): EmployeeInfoApiServices {

        val gson = GsonBuilder().setLenient().create()

        val client = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(if (BuildConfig.LOG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

        client.readTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .connectionPool(ConnectionPool(30, 30, TimeUnit.SECONDS))
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(logging)

        return Retrofit.Builder().baseUrl(BuildConfig.BASEURL)
            .client(client.build())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(EmployeeInfoApiServices::class.java)

    }

}