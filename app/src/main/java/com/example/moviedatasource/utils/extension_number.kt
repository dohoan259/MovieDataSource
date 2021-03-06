package com.example.moviedatasource.utils

import android.content.Context

val Any?.safe get() = Unit

fun Float.getNumberOfColumns(context: Context): Int {
    val screenWidth = context.resources.displayMetrics.widthPixels
    return screenWidth.div(this).toInt()
}

fun Number?.format(pattern: String) = String.format(pattern, this)