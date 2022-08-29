package com.learning.employeeinfo.util.extensions

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.showOrHide(isShow: Boolean = false, vararg views: View = emptyArray()) {

    if (isShow) {

        visible()

        views.forEach { it.gone() }

    } else {

        gone()

        views.forEach { it.visible() }

    }

}