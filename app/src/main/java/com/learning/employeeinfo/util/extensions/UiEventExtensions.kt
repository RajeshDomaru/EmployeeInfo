package com.learning.employeeinfo.util.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.learning.employeeinfo.util.constants.MESSAGE
import com.learning.employeeinfo.util.extensions.navigation.navigate
import com.learning.employeeinfo.util.sealed.UiDialog
import com.learning.employeeinfo.util.sealed.UiEvent
import com.learning.employeeinfo.util.sealed.UiSuccess
import com.learning.employeeinfo.util.sealed.UiText

fun BottomSheetDialogFragment.uiEventAction(uiEventNew: UiEvent, requestKey: String) {

    with(uiEventNew) {

        when (this) {

            is UiEvent.NavigateToActivity -> navigate(activityNavigation, isFinishedPrevious)

            is UiEvent.NavigationInAuthentication -> navigate(authenticationNavigation)

            is UiEvent.NavigationInMain -> navigate(mainActivityNavigation)

            is UiEvent.Snack -> showSnack(isChild, uiText, requestKey)

            is UiEvent.Toast -> toast(uiText.asString(requireContext()))

            is UiEvent.Dialog -> {

                with(uiDialog) {

                    when (this) {

                        is UiDialog.Api -> showSnack(isChild, uiText, requestKey)

                        is UiDialog.PasswordError -> {
//                            PasswordErrorDialog(requireContext(), password).show()
                        }

                        is UiDialog.Success -> {

                            showDialog(requestKey, uiSuccess)

                            dismiss()

                        }

                    }

                }

            }

        }

    }

}

private fun BottomSheetDialogFragment.showSnack(
    isChild: Boolean,
    uiText: UiText,
    requestKey: String
) {

    if (isChild) snack(uiText.asString(requireContext()))
    else requireActivity().supportFragmentManager.setFragmentResult(
        requestKey, Bundle().apply {
            putString(MESSAGE, uiText.asString(requireContext()))
        }
    )

}

fun Fragment.uiEventAction(uiEventNew: UiEvent, requestKey: String = "") {

    with(uiEventNew) {

        when (this) {

            is UiEvent.NavigateToActivity -> navigate(activityNavigation, isFinishedPrevious)

            is UiEvent.NavigationInAuthentication -> navigate(authenticationNavigation)

            is UiEvent.NavigationInMain -> navigate(mainActivityNavigation)

            is UiEvent.Snack -> snack(uiText.asString(requireContext()))

            is UiEvent.Toast -> toast(uiText.asString(requireContext()))

            is UiEvent.Dialog -> {

                with(uiDialog) {

                    when (this) {

                        is UiDialog.Api -> snack(uiText.asString(requireContext()))

                        is UiDialog.PasswordError -> {
//                            PasswordErrorDialog(requireContext(), password).show()
                        }

                        is UiDialog.Success -> showDialog(requestKey, uiSuccess)

                    }

                }

            }

        }

    }

}

private fun Fragment.showDialog(requestKey: String, uiSuccess: UiSuccess) {

    with(uiSuccess) {

        when (this) {


            else -> {}
        }

    }

}