package com.gw.newstart.kotlin.utils

import android.widget.Toast
import com.gw.newstart.kotlin.MainApplication

/**
 * Created by GongWen on 17/6/5.
 */
object ToastUtil {
    fun showToastShort(text: String?) {
        Toast.makeText(MainApplication.instance, text, Toast.LENGTH_SHORT).show()
    }

    fun showToastLong(text: String?) {
        Toast.makeText(MainApplication.instance, text, Toast.LENGTH_LONG).show()
    }
}