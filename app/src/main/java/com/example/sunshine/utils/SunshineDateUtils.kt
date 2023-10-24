package com.example.sunshine.utils

import android.content.Context
import com.example.sunshine.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class SunshineDateUtils {

    private fun elapsedDaysSinceEpoch(utcDate: Long): Long {
        return TimeUnit.MILLISECONDS.toDays(utcDate)
    }

    private fun getDayName(context: Context?, dateInMillis: Long): String? {

        val daysFromEpochToProvidedDate: Long =
            elapsedDaysSinceEpoch(dateInMillis)
        val daysFromEpochToToday: Long =
            elapsedDaysSinceEpoch(System.currentTimeMillis())
        return when ((daysFromEpochToProvidedDate - daysFromEpochToToday).toInt()) {
            0 -> context?.getString(R.string.today_label)
            1 -> context?.getString(R.string.tomorrow_label)
            else -> {
                val dayFormat = SimpleDateFormat("EEEE",
                    context?.let { Locale(it.getString(R.string.language),it.getString(R.string.country)) })
                dayFormat.format(dateInMillis)
            }
        }
    }

    private fun getLocalMidnightFromNormalizedUtcDate(normalizedUtcDate: Long): Long {
        val timeZone = TimeZone.getDefault()

        val gmtOffset = timeZone.getOffset(normalizedUtcDate).toLong()
        return normalizedUtcDate - gmtOffset
    }

    private fun getReadableDateString(context: Context?, timeInMillis: Long): String {
        return SimpleDateFormat("EEEE, dd 'de' MMMM", context?.let { Locale(it.getString(R.string.language),it.getString(R.string.country)) })
        .format(timeInMillis)
    }

    fun getFriendlyDateString(
        context: Context?,
        date: String,
        showFullDate: Boolean
    ): String? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val normalizedUtcMidnight = format.parse(date).time
        val locale = context?.let { Locale(it.getString(R.string.language),it.getString(R.string.country)) }


        val localDate: Long =
            getLocalMidnightFromNormalizedUtcDate(normalizedUtcMidnight)

        val daysFromEpochToProvidedDate: Long = elapsedDaysSinceEpoch(localDate)

        val daysFromEpochToToday: Long =
            elapsedDaysSinceEpoch(System.currentTimeMillis())
        return if (daysFromEpochToProvidedDate == daysFromEpochToToday || showFullDate) {

            val dayName: String? = getDayName(context, localDate)
            val readableDate: String = getReadableDateString(context, localDate)
            if (daysFromEpochToProvidedDate - daysFromEpochToToday < 2) {

                val localizedDayName = SimpleDateFormat("EEEE",  locale).format(localDate)
                readableDate.replace(localizedDayName, dayName?:"")
            } else {
                readableDate
            }
        } else if (daysFromEpochToProvidedDate < daysFromEpochToToday + 7) {
            getDayName(context, localDate)
        } else {
            SimpleDateFormat("EEEE \ndd 'de' MMMM", locale).format(localDate)
        }
    }
}