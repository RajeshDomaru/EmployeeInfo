package com.learning.employeeinfo.util.extensions

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.learning.employeeinfo.R
import com.learning.employeeinfo.util.core.FieldError
import com.learning.employeeinfo.util.sealed.UiText

fun TextInputLayout.visible() {

    visibility = View.VISIBLE

    error = null

}

fun TextInputLayout.gone() {

    visibility = View.GONE

    error = null

}

fun TextInputLayout.disableLayout() {

    boxBackgroundColor = ContextCompat.getColor(context, R.color.et_disable_color)

}

fun TextInputLayout.enableLayout() {

    boxBackgroundColor = ContextCompat.getColor(context, R.color.transparent_color)

}

fun TextInputLayout?.clearTextInputLayout() {

    this?.apply {

        if (isErrorEnabled) {

            error = null

            isErrorEnabled = false

        }

    }

}

fun TextInputLayout.setErrorMessage(fieldError: FieldError): Boolean =
    setErrorMessage(fieldError.uiText.asString(context), fieldError.isValidPreviousView)

fun TextInputLayout.setErrorMessage(
    uiText: UiText,
    isValidPreviousView: Boolean = false
): Boolean = setErrorMessage(uiText.asString(context), isValidPreviousView)

fun TextInputLayout.setErrorMessage(
    message: String,
    isValidPreviousView: Boolean = false
): Boolean {

    error = message

    errorIconDrawable = null

    if (!message.isValidString()) clearErrorMessage()

    return if (isValidPreviousView) {
        focusOnView()
        false
    } else true

}

fun TextInputLayout.setErrorMessage(resourcesId: Int, isValidPreviousView: Boolean = false) {

    setErrorMessage(context.getStringResources(resourcesId), isValidPreviousView)

}

fun TextInputLayout.clearErrorMessage() {

    error = null

    isErrorEnabled = false

}