package com.gw.newstart.kotlin.rxhelper

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by GongWen on 17/6/5.
 */
class SchedulersCompat {
    companion object {
        @JvmStatic private val computationTransformer = object : ObservableTransformer<Any, Any> {
            override fun apply(upstream: Observable<Any>): ObservableSource<Any> {
                return upstream.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
        @JvmStatic private val ioTransformer = object : ObservableTransformer<Any, Any> {
            override fun apply(upstream: Observable<Any>): ObservableSource<Any> {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
        @JvmStatic private val newThreadTransformer = object : ObservableTransformer<Any, Any> {
            override fun apply(upstream: Observable<Any>): ObservableSource<Any> {
                return upstream.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
        @JvmStatic private val singleTransformer = object : ObservableTransformer<Any, Any> {
            override fun apply(upstream: Observable<Any>): ObservableSource<Any> {
                return upstream.subscribeOn(Schedulers.single())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
        @JvmStatic private val trampolineTransformer = object : ObservableTransformer<Any, Any> {
            override fun apply(upstream: Observable<Any>): ObservableSource<Any> {
                return upstream.subscribeOn(Schedulers.trampoline())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
        @JvmStatic private val executorTransformer = object : ObservableTransformer<Any, Any> {
            override fun apply(upstream: Observable<Any>): ObservableSource<Any> {
                return upstream.subscribeOn(Schedulers.trampoline())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }

        @JvmStatic fun <T> applyComputationSchedulers(): ObservableTransformer<T, T> {
            return computationTransformer as ObservableTransformer<T, T>
        }

        @JvmStatic fun <T> applyIoSchedulers(): ObservableTransformer<T, T> {
            return ioTransformer as ObservableTransformer<T, T>
        }

        @JvmStatic fun <T> applyNewThreadSchedulers(): ObservableTransformer<T, T> {
            return newThreadTransformer as ObservableTransformer<T, T>
        }

        @JvmStatic fun <T> applySingleSchedulers(): ObservableTransformer<T, T> {
            return singleTransformer as ObservableTransformer<T, T>
        }

        @JvmStatic fun <T> applyTrampolineSchedulers(): ObservableTransformer<T, T> {
            return trampolineTransformer as ObservableTransformer<T, T>
        }

        @JvmStatic fun <T> applyExecutorSchedulers(): ObservableTransformer<T, T> {
            return executorTransformer as ObservableTransformer<T, T>
        }
    }
}