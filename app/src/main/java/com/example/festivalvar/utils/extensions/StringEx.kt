package com.mobillium.birebirdiyet.utils.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun String.toSpanned(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        return Html.fromHtml(this)
    }
}

fun Double.formatMoney(): String {
    val symbols = DecimalFormatSymbols.getInstance(Locale("tr", "TR"))
    symbols.groupingSeparator = ','
    symbols.decimalSeparator = '.'
    val df = DecimalFormat("#.##", symbols)
    df.groupingSize = 3
    df.isGroupingUsed = true
    df.maximumFractionDigits = 2
    df.minimumFractionDigits = 2
    return df.format(this)
}

fun String.toDate(): String {
    var date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(this)
    return SimpleDateFormat("dd MMMM yyyy", Locale("tr", "TR")).format(date).toString()
}

fun String.toDateMonthDay(): String {
    var date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(this)
    return SimpleDateFormat("dd MMMM", Locale("tr", "TR")).format(date).toString()
}

fun String.toDateYear(): String {
    var date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(this)
    return SimpleDateFormat("yyyy", Locale("tr", "TR")).format(date).toString()
}

fun String.toHours(): String {
    var date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this)
    return SimpleDateFormat("HH:mm", Locale("tr", "TR")).format(date).toString()
}