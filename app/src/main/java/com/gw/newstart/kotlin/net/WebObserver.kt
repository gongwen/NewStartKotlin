package com.gw.newstart.kotlin.net

import android.app.Activity
import com.gw.newstart.kotlin.model.ResponseEntity
import com.gw.newstart.kotlin.utils.Constant
import com.gw.newstart.kotlin.utils.ToastUtil
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by GongWen on 17/6/5.
 */
open abstract class WebObserver<T> constructor(mActivity: Activity) : Observer<ResponseEntity<T>> {
    private val mRefActivity: WeakReference<Activity> = WeakReference(mActivity)

    override fun onSubscribe(d: Disposable) {
        val activity = mRefActivity.get()
        if (activity == null || activity.isFinishing || activity.isDestroyed) {
            if (!d.isDisposed) {
                d.dispose()
            }
        }
    }

    override fun onNext(responseEntity: ResponseEntity<T>) {
        if (responseEntity.isSuccess()) {
            onSuccess(responseEntity.body)
        } else {
            onBusiness(responseEntity.code, responseEntity.msg)
        }
    }

    override fun onError(e: Throwable) {
        onFailure(e)
    }

    abstract fun onSuccess(data: T)

    open fun onBusiness(code: Int, msg: String) {
        ToastUtil.showToastShort(code.toString() + "---" + msg)
    }

    open fun onFailure(e: Throwable) {
        if (Constant.DEBUGGABLE) {
            val causeThrowable = e.cause
            val errorMsg = causeThrowable?.toString() ?: e.message
            Logger.i("onFailure:Throwable--->$errorMsg")
        }
        if (e is SocketException) {//ConnectException is a subClass of SocketException
        } else if (e is UnknownHostException) {
        } else if (e is SocketTimeoutException) {
        }
    }

    override fun onComplete() {
    }


}