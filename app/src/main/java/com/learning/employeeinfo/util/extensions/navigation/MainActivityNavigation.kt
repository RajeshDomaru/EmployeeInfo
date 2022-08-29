package com.learning.employeeinfo.util.extensions.navigation

sealed class MainActivityNavigation {

    class OnBackPressed(val doRefresh: Boolean = false) : MainActivityNavigation()

}
