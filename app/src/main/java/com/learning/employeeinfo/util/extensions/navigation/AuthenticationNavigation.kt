package com.learning.employeeinfo.util.extensions.navigation

sealed class AuthenticationNavigation {

    object LoginToRegistration : AuthenticationNavigation()

    object RegistrationToLogin : AuthenticationNavigation()

}
