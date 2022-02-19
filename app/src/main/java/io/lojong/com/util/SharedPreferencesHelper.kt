package io.lojong.com.util

import android.content.Context

const val SHARED_PREFERENCES = "sharedPreferences"

class SharedPreferencesHelper(private val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    fun saveInt(key: String, value: Int?) = sharedPreferences.edit().run {
        value?.let { putInt(key, it) }
        apply()
    }

    fun getInt(key: String) = sharedPreferences.run {
        getInt(key,0)
    }
}



