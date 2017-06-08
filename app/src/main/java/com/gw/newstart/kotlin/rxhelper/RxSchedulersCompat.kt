package com.gw.newstart.kotlin.rxhelper

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by GongWen on 17/6/8.
 */

fun <T> Observable<T>.applyComputationSchedulers(): Observable<T> {
    return compose { upstream ->
        upstream.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Observable<T>.applyIoSchedulers(): Observable<T> {
    return return compose { upstream ->
        upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Observable<T>.applyNewThreadSchedulers(): Observable<T> {
    return compose { upstream ->
        upstream.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}


fun <T> Observable<T>.applySingleSchedulers(): Observable<T> {
    return compose { upstream ->
        upstream.subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Observable<T>.applyTrampolineSchedulers(): Observable<T> {
    return compose { upstream ->
        upstream.subscribeOn(Schedulers.trampoline())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Observable<T>.applyExecutorSchedulers(): Observable<T> {
    return compose { upstream ->
        upstream.subscribeOn(Schedulers.from(ExecutorManager.eventExecutor))
                .observeOn(AndroidSchedulers.mainThread())
    }
}