package com.learning.employeeinfo.util.extensions.navigation

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.learning.employeeinfo.AuthenticationActivity
import com.learning.employeeinfo.MainActivity

fun Fragment.navigate(activityNavigation: ActivityNavigation, isFinishedPrevious: Boolean) {

    requireActivity().navigate(activityNavigation, isFinishedPrevious)

}

fun Activity.navigate(activityNavigation: ActivityNavigation, isFinishedPrevious: Boolean) {

    when (activityNavigation) {

        ActivityNavigation.ToMainActivity -> startActivity(
            Intent(this, MainActivity::class.java)
        )

        ActivityNavigation.ToAuthenticationActivity -> startActivity(
            Intent(this, AuthenticationActivity::class.java)
        )

    }

    if (isFinishedPrevious) finish()

}