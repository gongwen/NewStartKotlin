package com.gw.newstart.kotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import com.gw.newstart.kotlin.MainApplication

/**
 * Created by GongWen on 17/6/8.
 */
private val connectivityMgr: ConnectivityManager = MainApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun isNetworkAvailable(): Boolean {
    val networkInfo = connectivityMgr.activeNetworkInfo
    /// if no network is available networkInfo will be null
    if (networkInfo != null && networkInfo.isConnected) {
        return true
    }
    return false
}