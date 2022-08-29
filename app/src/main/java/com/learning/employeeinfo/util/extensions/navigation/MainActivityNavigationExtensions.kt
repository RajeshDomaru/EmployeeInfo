package com.learning.employeeinfo.util.extensions.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.learning.employeeinfo.R
import com.learning.employeeinfo.util.constants.DO_REFRESH

fun Fragment.navigate(mainActivityNavigation: MainActivityNavigation) {

    try {

        findNavController().navigation(
            requireActivity() as AppCompatActivity,
            mainActivityNavigation
        )

    } catch (e: Exception) {

        e.printStackTrace()

    }

}

fun AppCompatActivity.navigate(mainActivityNavigation: MainActivityNavigation) {

    try {

//        findNavController(R.id.bnvMainActivity).navigation(this, mainActivityNavigation)

    } catch (e: Exception) {

        e.printStackTrace()

    }

}

fun NavController.navigation(
    activity: AppCompatActivity,
    mainActivityNavigation: MainActivityNavigation,
) {

    try {

        with(mainActivityNavigation) {

            when (this) {

                is MainActivityNavigation.OnBackPressed -> {

                    activity.supportFragmentManager.setFragmentResult(DO_REFRESH, Bundle().apply {
                        putBoolean(DO_REFRESH, doRefresh)
                    })

                    activity.onBackPressed()
                }

            }

        }

    } catch (e: Exception) {

        e.printStackTrace()

    }

}
