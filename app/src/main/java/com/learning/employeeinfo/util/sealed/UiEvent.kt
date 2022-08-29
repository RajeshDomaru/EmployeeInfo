package com.learning.employeeinfo.util.sealed

import com.learning.employeeinfo.util.extensions.navigation.AuthenticationNavigation
import com.learning.employeeinfo.util.extensions.navigation.MainActivityNavigation
import com.learning.employeeinfo.util.extensions.navigation.ActivityNavigation

sealed class UiEvent {

    data class Snack(
        val uiText: UiText,
        val isChild: Boolean = false
    ) : UiEvent()

    data class Toast(val uiText: UiText) : UiEvent()

    data class Dialog(val uiDialog: UiDialog) : UiEvent()

    data class NavigationInAuthentication(val authenticationNavigation: AuthenticationNavigation) :
        UiEvent()

    data class NavigationInMain(val mainActivityNavigation: MainActivityNavigation) : UiEvent()

    data class NavigateToActivity(
        val activityNavigation: ActivityNavigation,
        val isFinishedPrevious: Boolean = true
    ) : UiEvent()

}
