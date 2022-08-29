package com.learning.employeeinfo.util.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.learning.employeeinfo.util.constants.CREDIT_CARD_FORMAT
import com.learning.employeeinfo.util.constants.DAY_MONTH_YEAR_FORMAT
import com.learning.employeeinfo.util.constants.DOB_FORMAT
import com.learning.employeeinfo.util.constants.PASSWORD_SPECIAL_CHARACTERS
import java.math.BigDecimal
import java.util.regex.Pattern

fun String?.isValidEIN(): Boolean {

    return if (!this.isNullOrEmpty()) {

        val regex = "^[0-9]\\d?-\\d{7}$"

        val einPattern = Pattern.compile(regex)

        val einMatcher = einPattern.matcher(this)

        einMatcher.matches()

    } else {

        false

    }

}

fun String?.isCorrectEIN(): Boolean {

    if (this.isNullOrEmpty() || !this.isValidEIN()) return false

    val target = this.replace(Regex("[()-//s]"), "").trim()

    if (target.isRepeatedChar()) return false

    if (target == "123456789") return false

    when (this.substring(0, 2)) {

        "00", "07", "08", "09", "17", "18", "19", "28",
        "29", "49", "69", "70", "78", "79", "89",
        -> return false

    }

    return true

}

fun CharSequence.formatTextSSN(): String {

    val formatted = java.lang.StringBuilder()

    var count = 0

    for (i in indices) {

        if (Character.isDigit(this[i])) {

            if (count == 3 || count == 5) formatted.append("-")

            formatted.append(this[i])

            ++count

        }

    }

    return formatted.toString()

}

fun String.isCorrectSSN(): Boolean {

    try {

        if (!isValidSSN()) return false

        val target = this.replace(Regex("[()-//s]"), "").trim()

        if (target.isEmpty()) return false

        val firstNumber = target.substring(0, 1)

        if (firstNumber == "9") return false

        val first3Number = target.substring(0, 3)

        if (first3Number == "666") return false

        return !target.isRepeatedChar()

    } catch (e: Exception) {

        e.printStackTrace()

        return false

    }

}

fun String?.isRepeatedChar(): Boolean {

    val target = this?.replace(Regex("[()-//s]"), "")?.trim()

    if (target.isNullOrEmpty()) return true

    var repeatedCount = 0

    val firstString = target.substring(0, 1)

    val otherChar = target.substring(1)

    val charArray = otherChar.toCharArray()

    for (i in charArray.indices) {

        if (firstString == charArray[i].toString()) {

            repeatedCount += 1

        }

    }

    if (repeatedCount == charArray.size) return true

    return false

}

fun String.isValidEmail(): Boolean {

    if (!isValidDots()) return false

    val emailPattern = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")

    val emailMatcher = emailPattern.matcher(this)

    return emailMatcher.matches()

}

fun String?.isValidDots(): Boolean {

    if (this.isNullOrEmpty()) return false

    val regex = "@(?!.*?\\.\\.)[^@]+$"

    val pattern = Pattern.compile(regex)

    val matcher = pattern.matcher(this)

    return matcher.find()

}

fun String?.isZipCodeSequence(): Boolean {

    if (this != null && isNotEmpty()) {

        if (contains("-")) replace("-", "")

        var count = 0

        for (i in this.indices) {

            if (this[0] == this[i] && i < 5) count++

        }

        if (count == 5) return true

    }

    return false

}

fun String?.isValidZipCode(): Boolean {

    return if (this == null) {

        false

    } else {

        val text = "^\\d{5}(-\\d{4})?$"

        val orgName: Pattern = Pattern.compile(text)

        orgName.matcher(this).matches()

    }

}

fun multiplyAmount(number1: Float?, number2: Float?): Float {

    if (number1 == null || number2 == null) return 0f

    try {

        val grossAmount = BigDecimal(number1.toString())

        val taxAmount = BigDecimal(number2.toString())

        val multipliedAmount = grossAmount.multiply(taxAmount).toFloat()

        return if (multipliedAmount <= 0) 0f else multipliedAmount

    } catch (e: Exception) {

        e.printStackTrace()

        val normalMul = number1 * number2

        return if (normalMul <= 0) 0f else normalMul

    }

}

fun String?.isStatusSuccess(): Boolean {

    if (isNullOrEmpty()) return false

    return trim().equals("Success", true)

}

fun String?.isStatusFailure(): Boolean {

    if (isNullOrEmpty()) return true

    return trim().equals("Failure", true)

}

fun Context.checkExternalStorageWritable(): Boolean {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    } else {

        true

    }

}

fun String?.isValidSSN(): Boolean {

    if (this.isNullOrEmpty()) return false

    return Pattern.compile("^\\d{3}-?\\d{2}-?\\d{4}$").matcher(this).matches()

}

fun Pair<Float?, Float?>.withNegative(): Float = first.validateFloat() - second.validateFloat()

fun Pair<Float, Float>.withoutNegative(): Float {

    val value = first - second

    return if (value > 0F) value else 0F

}

fun String?.isValidPassword(): Boolean {

    return if (this != null && isValidString()) {

        val sPattern =
            Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[$PASSWORD_SPECIAL_CHARACTERS]).{8,}$")

        sPattern.matcher(this).matches()

    } else false

}

fun isPasswordsMisMatch(password: String?, confirmPassword: String) =
    (!password.isValidPassword() || !confirmPassword.isValidPassword() || password != confirmPassword)

fun getMonthDayFromDatePicker(monthOfYear: Int, dayOfMonth: Int): String =
    String.format(DOB_FORMAT, (monthOfYear + 1), dayOfMonth)

fun getDayMonthYearFromDatePicker(monthOfYear: Int, dayOfMonth: Int, yearNum: Int): String =
    String.format(DAY_MONTH_YEAR_FORMAT, (monthOfYear + 1), dayOfMonth, yearNum)

fun getMonthYearFromCreditCard(monthOfYear: Int, year: Int): String =
    String.format(CREDIT_CARD_FORMAT, monthOfYear, year)
