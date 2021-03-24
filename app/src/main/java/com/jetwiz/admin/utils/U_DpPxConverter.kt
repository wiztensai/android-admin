package com.jetwiz.admin.utils

import android.content.Context
import android.util.DisplayMetrics

class U_DpPxConverter {
    companion object {
        fun dpToPixel(dp: Int, context: Context): Int {
            val dpCalculation = context.getResources().getDisplayMetrics().density
            return (dpCalculation * dp).toInt()
        }
    }
}