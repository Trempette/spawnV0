package fr.webedia.spawn.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {

        fun parseStringToDateInMillis(str: String): Date {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.FRANCE)
            return formatter.parse(str)
        }

        fun parseDateToStringApi(date: Date): String {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.FRANCE)
            return formatter.format(date)
        }

        fun getTomorrowDate(date: Date): Long {
            val c = Calendar.getInstance()
            c.time = date
            c.add(Calendar.DAY_OF_MONTH, 1)
            return c.timeInMillis
        }

        fun getYearMonthandDay(date: Date): String {
            var format = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
            return format.format(date)
        }

        fun getWordedYearMonthAndDay(date: Date): String {
            var format = SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE)
            return format.format(date)
        }

        fun getHoursAndMinutes(date: Date): String {
            return getHoursAndMinutes(date.time)
        }

        fun getHoursAndMinutes(millis: Long): String {
            val format = SimpleDateFormat("HH:mm", Locale.FRANCE)
            val c = Calendar.getInstance()
            c.timeInMillis = millis
            return format.format(c.time)
        }

        fun isSameDay(selectedDate: Date?, showDate: Long): Boolean {
            val format = SimpleDateFormat("yyyyMMdd", Locale.FRANCE)
            if (selectedDate == null) {
                val today = Date()
                today.time = System.currentTimeMillis()
                return format.format(selectedDate) == format.format(today)
            } else {
                val showtimeDate = Date()
                showtimeDate.time = showDate
                return format.format(selectedDate) == format.format(showtimeDate)
            }
        }

    }

}