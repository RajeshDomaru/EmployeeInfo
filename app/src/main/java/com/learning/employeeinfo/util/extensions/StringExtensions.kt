package com.learning.employeeinfo.util.extensions

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.learning.employeeinfo.util.constants.DECIMAL_FORMAT

fun Context.getStringResources(stringId: Int?) = if (stringId != null) {

    try {

        resources.getString(stringId)

    } catch (e: Exception) {

        e.printStackTrace()

        ""

    }

} else ""

fun Fragment.getStringResources(stringId: Int?) = requireContext().getStringResources(stringId)

fun Activity.getStringResources(stringId: Int?) = applicationContext.getStringResources(stringId)

fun String?.validateString() = if (isNullOrEmpty()) "" else trim()

fun CharSequence?.validateString() = if (isNullOrEmpty()) "" else toString().trim()

fun String?.isValidString(): Boolean =

    if (isNullOrEmpty()) false
    else trim { it <= ' ' }.isNotEmpty() &&

            !trim { it <= ' ' }.equals("null", ignoreCase = true) &&

            !trim { it <= ' ' }.equals("", ignoreCase = true)

fun CharSequence?.isValidString(): Boolean {

    if (isNullOrEmpty()) return false

    val textValue = toString()

    return textValue.trim { it <= ' ' }.isNotEmpty() &&

            !textValue.trim { it <= ' ' }.equals("null", ignoreCase = true) &&

            !textValue.trim { it <= ' ' }.equals("", ignoreCase = true)

}

fun String?.validateLength() = validateString().length

fun CharSequence?.validateLength() = validateString().length

fun String?.stringToFloat() =
    if (this != null && isValidString() && toFloatOrNull() != null) toFloat() else 0F

fun String?.stringToInt() = if (this != null && isValidString()) toInt() else 0

fun String?.stringFloatOrNull(): Float? = if (isValidString()) stringToFloat() else null

fun String?.stringToDecimal(): String =
    String.format(DECIMAL_FORMAT, validateString().stringToFloat())

fun String?.getFirstChar(): String = if (isValidString()) this?.get(0).toString() else ""