package io.lojong.com

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class
 */
@HiltAndroidApp
class AppClass : Application(){
    fun context() = this
}