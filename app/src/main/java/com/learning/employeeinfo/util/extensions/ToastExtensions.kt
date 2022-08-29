package com.learning.employeeinfo.util.extensions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val LENGTH_SHORT = Toast.LENGTH_SHORT
const val LENGTH_LONG = Toast.LENGTH_LONG

fun Int.validateToastDuration() =
    if (this == LENGTH_SHORT || this == LENGTH_LONG) this else Toast.LENGTH_SHORT

fun Context.toast(resourcesId: Int, duration: Int = LENGTH_SHORT) {

    val message = getStringResources(resourcesId)

    if (message.isValidString()) toast(message, duration)

}

fun Context.toast(message: String?, duration: Int = LENGTH_SHORT) {

    if (message.isValidString())

        Toast.makeText(this, message, duration.validateToastDuration()).show()

}

fun Dialog.toast(resourcesId: Int, duration: Int = LENGTH_SHORT) {

    val message = context.getStringResources(resourcesId)

    toast(message, duration)

}


fun Dialog.toast(message: String?, duration: Int = LENGTH_SHORT) {

    context.toast(message, duration)

}

fun BottomSheetDialogFragment.toast(resourcesId: Int, duration: Int = LENGTH_SHORT) {

    val message = dialog?.window?.context?.getStringResources(resourcesId)

    toast(message, duration)

}

fun BottomSheetDialogFragment.toast(message: String?, duration: Int = LENGTH_SHORT) {

    dialog?.window?.context?.toast(message, duration)

}

fun Fragment.toast(resourcesId: Int, duration: Int = LENGTH_SHORT) {

    val message = getStringResources(resourcesId)

    toast(message, duration)

}

fun Fragment.toast(message: String?, duration: Int = LENGTH_SHORT) {

    requireContext().toast(message, duration)

}

fun Activity.toast(resourcesId: Int, duration: Int = LENGTH_SHORT) {

    val message = getStringResources(resourcesId)

    toast(message, duration)

}

fun Activity.toast(message: String?, duration: Int = LENGTH_SHORT) {

    applicationContext.toast(message, duration)

}