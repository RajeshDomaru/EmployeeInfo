package com.learning.employeeinfo.util

import com.learning.employeeinfo.R
import com.learning.employeeinfo.util.extensions.*
import com.learning.employeeinfo.util.sealed.UiText

val EMPTY = UiText.StringResource(R.string.empty)

fun String?.validateEmailAddress(isRequired: Boolean = true): UiText = when {
    !isValidString() && isRequired -> UiText.StringResource(R.string.email_required)
    isValidString() && !validateString().isValidEmail() -> UiText.StringResource(R.string.invalid_email_address)
    else -> EMPTY
}

fun String?.validateUsername(): UiText = when {
    !isValidString() -> UiText.StringResource(R.string.username_required)
    validateLength() > 75 -> UiText.StringResource(R.string.username_invalid)
    else -> EMPTY
}

fun String?.validatePassword(isFormatNeeds: Boolean = true): Pair<UiText, Boolean> = when {
    !isValidString() -> Pair(UiText.StringResource(R.string.password_required), false)
    isFormatNeeds && !isValidPassword() -> Pair(
        UiText.StringResource(R.string.invalid_password), true
    )
    else -> Pair(EMPTY, false)
}

fun String?.validateConfirmPassword(password: String): UiText = when {
    !isValidString() -> UiText.StringResource(R.string.confirm_password_required)
    !isValidPassword() -> UiText.StringResource(R.string.invalid_confirm_password)
    isPasswordsMisMatch(this, password) -> UiText.StringResource(R.string.password_not_match)
    else -> EMPTY
}

fun String?.validatePhoneNumber(): UiText = when {
    !isValidString() -> UiText.StringResource(R.string.phone_number_required)
    validateLength() != 10 -> UiText.StringResource(R.string.incorrect_phone_number)
    else -> EMPTY
}

fun String?.validateDateOfBirth(): UiText = when {
    !isValidString() -> UiText.StringResource(R.string.dob_required)
    else -> EMPTY
}