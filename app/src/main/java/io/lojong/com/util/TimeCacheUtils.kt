package io.lojong.com.util

import io.lojong.com.AppClass
import java.text.SimpleDateFormat
import java.util.*

class TimeCacheUtils {
    private val CACHE_DATE = "cache_date"

    fun isValidCache(): Boolean {
        return getCurrentDate() == getLastRequestTime()
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(Calendar.getInstance().time)
    }

    fun setLastRequestTime() {
        var sp = SharedPreferencesHelper(AppClass().applicationContext)
        sp.saveString(CACHE_DATE, getCurrentDate())
    }

    private fun getLastRequestTime(): String {
        var sp = SharedPreferencesHelper(AppClass().applicationContext)
        return sp.getString(CACHE_DATE).toString()
    }
}