package app.presentation.extension

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

fun String.tramsTimeAgo(): String =
    this.transTimeStamp().let {
        DateUtils.getRelativeTimeSpanString(it).toString()
    }

/**
 * 2018-08-13T08:51:43Z -> time stamp
 */
fun String.transTimeStamp(): Long =

    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        .let {
            it.timeZone = TimeZone.getTimeZone("GMT+1")
            it.parse(this)?.time ?: 0
        }