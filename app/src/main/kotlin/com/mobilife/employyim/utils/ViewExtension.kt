package com.mobilife.employyim.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

fun Activity.makeStatusBarTransparent(lightTheme: Boolean = false) {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        "View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR" make the text of status bar to black
        if (lightTheme) {
            decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        statusBarColor = Color.TRANSPARENT
    }
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}