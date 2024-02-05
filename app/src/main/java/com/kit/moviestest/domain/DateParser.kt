package com.kit.moviestest.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.parseIntoString(): String =
    SimpleDateFormat(
        "d MMMM yyyy",
        Locale.getDefault()
    ).format(this)

fun Date.retrieveYear(): String = SimpleDateFormat(
    "yyyy",
    Locale.getDefault()
).format(this)