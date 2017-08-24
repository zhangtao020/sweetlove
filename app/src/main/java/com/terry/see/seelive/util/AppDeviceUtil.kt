package com.terry.see.seelive.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

/**
 * Created by terry on 2017/8/23.
 */
class AppDeviceUtil {

    companion object {

        fun getScreenWidth(activity: Activity): Int {
            val dm = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }

        fun dpToPx(context: Context, dp: Int): Int {
            return (dp * context.resources.displayMetrics.density + 0.5).toInt()
        }
    }
}