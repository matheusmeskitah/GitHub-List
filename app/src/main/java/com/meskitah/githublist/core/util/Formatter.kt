package com.meskitah.githublist.core.util

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

fun String.formatToDate(context: Context): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    return android.text.format.DateFormat.getMediumDateFormat(context)
        .format(sdf.parse(this) ?: Date())
}