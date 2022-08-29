package com.learning.employeeinfo.util.extensions.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.learning.employeeinfo.ui.login.LoginFragmentDirections

fun Fragment.navigate(authenticationNavigation: AuthenticationNavigation) {

    try {

        findNavController().navigation(requireActivity(), authenticationNavigation)

    } catch (e: Exception) {

        e.printStackTrace()

    }

}

fun NavController.navigation(
    activity: Activity,
    authenticationNavigation: AuthenticationNavigation
) {

    when (authenticationNavigation) {
        AuthenticationNavigation.LoginToRegistration -> navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        AuthenticationNavigation.RegistrationToLogin -> activity.onBackPressed()
    }

}
