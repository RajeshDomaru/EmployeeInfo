package com.learning.employeeinfo.util.extensions.navigation

sealed class ActivityNavigation {

    object ToMainActivity : ActivityNavigation()

    object ToAuthenticationActivity : ActivityNavigation()

}
