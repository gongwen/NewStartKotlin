package com.gw.newstart.kotlin

import android.app.Application
import com.gw.newstart.kotlin.utils.ApplicationConfigs

/**
 * Created by GongWen on 17/6/5.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        ApplicationConfigs.config()
    }

    companion object {
        @JvmStatic lateinit var instance: MainApplication
    }


}