package io.lojong.com.util

import android.content.Context

const val SHARED_PREFERENCES = "sharedPreferences"

class SharedPreferencesHelper(private val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    fun saveString(key: String, value: String?) = sharedPreferences.edit().run {
        putString(key,value )
        apply()
    }

    fun getString(key: String) = sharedPreferences.run {
        getString(key,"")
    }
}



