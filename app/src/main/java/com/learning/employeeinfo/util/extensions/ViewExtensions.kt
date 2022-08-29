package com.learning.employeeinfo.util.extensions

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import androidx.core.widget.NestedScrollView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.learning.employeeinfo.R

fun View.visible() {

    visibility = View.VISIBLE

}

fun View.gone() {

    visibility = View.GONE

}

fun View.visibleOrGone(isVisible: Boolean) = if (isVisible) visible() else gone()

fun View.visibleOrInVisible(isVisible: Boolean) = if (isVisible) visible() else inVisible()

fun visibleOrGone(isVisible: Boolean, vararg args: View) {

    args.forEach { view -> if (isVisible) view.visible() else view.gone() }

}

fun View.inVisible() {

    visibility = View.INVISIBLE

}

fun View.disable() {

    isClickable = false

    isFocusable = false

}

fun View.enable() {

    isClickable = true

    isFocusable = true

}

fun View.enableOrDisable(isEnable: Boolean, textInputLayout: TextInputLayout? = null) {

    if (isEnable) enableView(textInputLayout) else disableView(textInputLayout)

}

fun View.enableView(textInputLayout: TextInputLayout? = null) {

    isEnabled = true

    textInputLayout?.apply {

        isEnabled = true

        boxBackgroundColor = ContextCompat.getColor(this.context, R.color.transparent_color)

    }

}

fun View.disableView(textInputLayout: TextInputLayout? = null) {

    isEnabled = false

    textInputLayout?.apply {

        isEnabled = false

        boxBackgroundColor = ContextCompat.getColor(this.context, R.color.et_disable_color)

    }

}

fun NestedScrollView.setCollapseActionButton(
    floatingActionButton: FloatingActionButton?,
    checkoutLayout: FrameLayout? = null,
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && floatingActionButton != null) {

        val handler = Handler(Looper.getMainLooper())

        this.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->

            if (scrollY > oldScrollY && floatingActionButton.isShown) {

                hideAndShow(false, floatingActionButton, checkoutLayout, handler)

            }

            if (scrollY < oldScrollY && !floatingActionButton.isShown) {

                hideAndShow(true, floatingActionButton, checkoutLayout, handler)

            }

            if (scrollY == 0) {

                hideAndShow(true, floatingActionButton, checkoutLayout, handler)

            }

        })

    }

}

fun hideAndShow(
    isShow: Boolean,
    floatingActionButton: FloatingActionButton,
    checkoutLayout: FrameLayout?,
    handler: Handler,
) {

    if (isShow) {

        floatingActionButton.show()

        checkoutLayout?.slideUp()

        handler.removeCallbacksAndMessages(null)

    } else {

        floatingActionButton.hide()

        checkoutLayout?.slideDown()

        handler.postDelayed({

            if (!floatingActionButton.isShown)

                hideAndShow(true, floatingActionButton, checkoutLayout, handler)

        }, 2500)

    }

}

fun View.slideUp() {

    if (isVisibility()) return

    visible()

    val animate = TranslateAnimation(
        0F,
        0F,
        height.toFloat(),
        0F
    )

    animate.duration = 500

    animate.fillAfter = true

    startAnimation(animate)

}

fun View.slideDown() {

    if (!isVisibility()) return

    gone()

    val animate = TranslateAnimation(0F, 0F, 0F, height.toFloat())

    animate.duration = 500

    animate.fillAfter = true

    startAnimation(animate)

}

fun BottomNavigationView.show() {

    if (visibility == View.VISIBLE) return

    try {
        val parent = parent as ViewGroup
        // View needs to be laid out to create a snapshot & know position to animate. If view isn't
        // laid out yet, need to do this manually.
        if (!isLaidOut) {
            measure(
                View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.AT_MOST)
            )
            layout(parent.left, parent.height - measuredHeight, parent.right, parent.height)
        }

        val drawable = BitmapDrawable(context.resources, drawToBitmap())
        drawable.setBounds(left, parent.height, right, parent.height + height)
        parent.overlay.add(drawable)
        ValueAnimator.ofInt(parent.height, top).apply {
            startDelay = 100L
            duration = 300L
            interpolator = AnimationUtils.loadInterpolator(
                context,
                android.R.interpolator.linear_out_slow_in
            )
            addUpdateListener {
                val newTop = it.animatedValue as Int
                drawable.setBounds(left, newTop, right, newTop + height)
            }
            doOnEnd {
                parent.overlay.remove(drawable)
                visibility = View.VISIBLE
            }
            start()
        }
    } catch (e: Exception) {

        e.printStackTrace()
    }

}

fun BottomNavigationView.hide() {
    if (visibility == View.GONE) return

    try {

        val drawable = BitmapDrawable(context.resources, drawToBitmap())
        val parent = parent as ViewGroup
        drawable.setBounds(left, top, right, bottom)
        parent.overlay.add(drawable)
        visibility = View.GONE
        ValueAnimator.ofInt(top, parent.height).apply {
            startDelay = 100L
            duration = 200L
            interpolator = AnimationUtils.loadInterpolator(
                context,
                android.R.interpolator.fast_out_linear_in
            )
            addUpdateListener {
                val newTop = it.animatedValue as Int
                drawable.setBounds(left, newTop, right, newTop + height)
            }
            doOnEnd {
                parent.overlay.remove(drawable)
            }
            start()

        }

    } catch (e: Exception) {

        e.printStackTrace()

    }

}

fun View.focusOnView() {

    Handler(Looper.getMainLooper()).postDelayed({

        val rect = Rect(0, 0, width, height)

        requestRectangleOnScreen(rect, false)

    }, 500)

}

fun View.setOnClickListeners(onClickListeners: ((View) -> Unit)) {

    var lastClickTime: Long = 0

    setOnClickListener {

        if (SystemClock.elapsedRealtime() - lastClickTime > 2000) {

            lastClickTime = SystemClock.elapsedRealtime()

            onClickListeners.invoke(it)

        }

    }

}

fun View.isVisibility(): Boolean {

    return visibility == View.VISIBLE

}

fun View.hideSoftKeyBoard() {

    try {

        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)
            ?.hideSoftInputFromWindow(windowToken, 0)

    } catch (e: Exception) {

        e.printStackTrace()

    }

}

fun View.showSoftKeyBoard() {

    try {

        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)?.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )

    } catch (e: Exception) {

        e.printStackTrace()

    }
}

fun View.focusOnCvvNumber() {

    Handler(Looper.getMainLooper()).postDelayed({

        val rect = Rect(0, 0, width, height)

        this.requestRectangleOnScreen(rect, false)

    }, 1300)

}