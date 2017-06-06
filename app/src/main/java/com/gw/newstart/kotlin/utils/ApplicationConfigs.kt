package com.gw.newstart.kotlin.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.gw.newstart.kotlin.MainApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Created by GongWen on 17/6/5.
 */
class ApplicationConfigs {
    companion object {
        fun config() {
            registerActivityLifecycleCallbacks()
            initLogger()
        }

        private fun initLogger() {
            val formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                    .methodCount(3)         // (Optional) How many method line to show. Default 2
                    //.methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                    //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                    .tag("DEBUG")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                    .build()
            Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
                override fun isLoggable(priority: Int, tag: String?): Boolean {
                    return Constant.DEBUGGABLE
                }
            })
        }

        private fun registerActivityLifecycleCallbacks() {
            MainApplication.instance.registerActivityLifecycleCallbacks(
                    object : Application.ActivityLifecycleCallbacks {
                        override fun onActivityPaused(activity: Activity) {
                        }

                        override fun onActivityStarted(activity: Activity) {
                        }

                        override fun onActivityDestroyed(activity: Activity) {
                        }

                        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
                        }

                        override fun onActivityStopped(activity: Activity) {
                        }

                        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                        }

                        override fun onActivityResumed(activity: Activity) {

                            ActivityManager.setCurrentActivity(activity)
                        }

                    }
            )
        }
    }
}