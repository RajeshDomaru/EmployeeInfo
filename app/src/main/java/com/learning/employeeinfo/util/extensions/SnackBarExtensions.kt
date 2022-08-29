package com.learning.employeeinfo.util.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.learning.employeeinfo.R


fun Int?.validateSnackBarDuration(): Int {

    return if ((this != null) &&
        this == Snackbar.LENGTH_SHORT ||
        this == Snackbar.LENGTH_LONG ||
        this == Snackbar.LENGTH_INDEFINITE
    ) this else Snackbar.LENGTH_SHORT

}

fun Fragment.snack(
    resourceId: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    requireActivity().snack(resourceId, duration, actionMessage, listener)

}

fun Fragment.snack(
    message: String?,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    requireActivity().snack(message, duration, actionMessage, listener)

}

fun BottomSheetDialogFragment.snack(
    resourceId: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    dialog?.window?.decorView?.snack(resourceId, duration, actionMessage, listener)

}

fun BottomSheetDialogFragment.snack(
    message: String?,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    dialog?.window?.decorView?.snack(message, duration, actionMessage, listener)

}

fun View.snack(
    resourceId: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    make(context.getStringResources(resourceId), duration, actionMessage, listener)

}

fun View.snack(
    message: String?,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    make(message, duration, actionMessage, listener)

}

fun Context.snack(
    resourceId: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    val view = (this as Activity).findViewById(android.R.id.content) as View

    view.make(view.context.getStringResources(resourceId), duration, actionMessage, listener)

}

fun Context.snack(
    message: String?,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    val view = (this as Activity).findViewById(android.R.id.content) as View

    view.make(message, duration, actionMessage, listener)

}

fun View.make(
    message: String?,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionMessage: String? = null,
    listener: ((View) -> Unit)? = null
) {

    if (message.isNullOrEmpty()) return

    try {

        val newDuration = duration.validateSnackBarDuration()

        val snackBar = Snackbar.make(this, message, newDuration)
            .setAction(actionMessage, listener)

        val snackBarView = snackBar.view

        val snackTextView =
            snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView?

        snackTextView?.maxLines = 20

        snackBarView.setOnClickListener { snackBar.dismiss() }

        snackBar.setBackgroundTint(ContextCompat.getColor(context, R.color.black))

        snackBar.setTextColor(ContextCompat.getColor(context, R.color.white))

        snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.teal_700))

        snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE

        return snackBar.show()

    } catch (e: Exception) {

        e.printStackTrace()

        return context.toast(message)

    }

}