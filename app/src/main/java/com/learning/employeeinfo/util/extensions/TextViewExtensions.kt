package com.learning.employeeinfo.util.extensions

import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.textfield.TextInputLayout
import com.learning.employeeinfo.util.sealed.UiText

fun AppCompatTextView?.isValid(): Boolean {

    return (this != null && text != null)

}

fun AppCompatTextView?.getTextFromTextView(): String {

    return validateString()

}

fun AppCompatTextView?.validateString(): String {

    return if (isValid()) this!!.text.toString().validateString() else ""

}

fun AppCompatTextView.setStringRes(id: Int) {

    text = context.getStringResources(id)

}

fun AppCompatTextView.setTextForTextView(uiText: UiText) {

    text = uiText.asString(context)

}

fun AppCompatTextView.setTextForTextView(input: String?) {

    text = input

}

fun AppCompatTextView.setTextForTextView(resId: Int) {

    val input = context.getStringResources(resId)

    setTextForTextView(input)

}

fun AppCompatTextView.setErrors(uiText: UiText) {

    setErrors(uiText.asString(context))

}

fun AppCompatTextView.setErrors(resId: Int) {

    setErrors(context.getStringResources(resId))

}

fun AppCompatTextView.setErrors(input: String?) {

    setTextForTextView(input)

    if (input.isValidString()) slideUp() else slideDown()

}

fun AppCompatAutoCompleteTextView.setTextForTextView(resId: Int, isFilter: Boolean = false) {

    val input = context.getStringResources(resId)

    setTextForTextView(input, isFilter)

}

fun AppCompatAutoCompleteTextView.setTextForTextView(input: String?, isFilter: Boolean = false) {

    setText(input, isFilter)

}

fun AppCompatAutoCompleteTextView.getTextFromTextView(): String {

    return if (text.toString().isValidString()) text.toString().trim() else ""

}

fun AppCompatAutoCompleteTextView.onItemClickListeners(
    onItemClickListener: ((parent: AdapterView<*>, view: View, position: Int, id: Long) -> Unit),
    isKeyBoardHide: Boolean = true
) {

    setOnClickListener { if (isKeyBoardHide) hideSoftKeyBoard() }

    this.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

        onItemClickListener.invoke(parent, view, position, id)

    }

    if (isKeyBoardHide) keyListener = null

    onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->

        if (hasFocus) {

            if (isKeyBoardHide) hideSoftKeyBoard()

        }

    }

}

fun AppCompatAutoCompleteTextView.clearTextInputLayout(textInputLayout: TextInputLayout) {

    setOnClickListener { textInputLayout.clearTextInputLayout() }

}

fun AppCompatAutoCompleteTextView.isValidString(): Boolean {

    return if (isValid()) text.toString().isValidString() else false

}

fun AppCompatAutoCompleteTextView.validateString(): String {

    return if (isValid()) text.toString().validateString() else ""

}

fun AppCompatAutoCompleteTextView.validateLength(): Int {

    return if (isValid()) text.toString().validateLength() else 0

}

fun AppCompatAutoCompleteTextView.isValid(): Boolean = text != null

fun AppCompatAutoCompleteTextView.clearText() = setTextForTextView("")

fun AppCompatAutoCompleteTextView.getList() =

    ArrayList<String>().also {

        for (i in 0 until adapter.count) {

            it.add(adapter.getItem(i).toString())
        }
    }

fun AppCompatAutoCompleteTextView.setTextForSpinner(text: String) {

    if (text in getList()) setTextForTextView(text)
    else setTextForTextView("")

}

fun AppCompatAutoCompleteTextView.clearText(isFilter: Boolean = false) = setText("", isFilter)

fun AppCompatTextView.visibleOrGone(string: String?) {

    if (string.isValidString()) visible()
    else gone()

    setTextForTextView(string.validateString())

}