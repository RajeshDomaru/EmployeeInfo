package com.learning.employeeinfo.data.api.authenticator

import com.learning.employeeinfo.R
import com.learning.employeeinfo.util.EmployeeInfoApplication
import com.learning.employeeinfo.util.extensions.validateString
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HeaderInterceptor : Interceptor {

    companion object {

        const val TIMESTAMP = "Timestamp"

        const val ACCEPT = "Accept"

        const val CONTENT_TYPE = "Content-Type"

    }

   private fun getDateUTCFormat(): String? = try {

        val simpleDateFormat =
            SimpleDateFormat("EEEE, MMMM dd, yyyy HH: mm: ss: SSS a", Locale.ENGLISH)

        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

        simpleDateFormat.format(Date())

    } catch (e: Exception) {

        e.printStackTrace()

        null

    }

    override fun intercept(chain: Interceptor.Chain): Response {

        if (InternetService.instance.isOnline()) {

            val timestamp = getDateUTCFormat()

            val request = chain.request().newBuilder()
                .addHeader(ACCEPT, "text/plain")
                .addHeader(CONTENT_TYPE, "application/json")
                .addHeader(TIMESTAMP, timestamp.validateString())

            return chain.proceed(request.build())

        } else {

            throw IOException(EmployeeInfoApplication.instance.getString(R.string.no_internet_connection))

        }

    }

}