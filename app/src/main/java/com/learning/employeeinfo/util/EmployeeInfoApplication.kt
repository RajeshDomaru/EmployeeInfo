package com.learning.employeeinfo.util

import android.app.Application
import com.learning.employeeinfo.data.api.authenticator.InternetService

class EmployeeInfoApplication : Application() {

    companion object {

        lateinit var instance: EmployeeInfoApplication

    }

    override fun onCreate() {

        super.onCreate()

        instance = this

        setupServices()

    }

    private fun setupServices() {

        InternetService.instance.initializeWithApplicationContext(this)

    }

}