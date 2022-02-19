package io.lojong.com.util

import android.view.View
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.setVisible(){
    this.visibility = View.VISIBLE
}

fun LottieAnimationView.setGone(){
    this.visibility = View.GONE
}