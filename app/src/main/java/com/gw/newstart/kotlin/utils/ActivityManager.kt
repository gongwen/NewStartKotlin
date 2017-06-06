package com.gw.newstart.kotlin.utils

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * Created by GongWen on 17/6/5.
 */
object ActivityManager {
    private var mCurrentActivityWeakRef: WeakReference<Activity>? = null

    fun getCurrentActivity(): Activity? {
        return mCurrentActivityWeakRef?.get()
    }

    fun setCurrentActivity(mActivity: Activity) {
        mCurrentActivityWeakRef = WeakReference(mActivity)
    }
}