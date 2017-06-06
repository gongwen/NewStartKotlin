package com.gw.newstart.kotlin.net

import com.gw.newstart.kotlin.net.interceptor.CacheInterceptor
import com.gw.newstart.kotlin.net.interceptor.GlobalParametersInterceptor
import com.gw.newstart.kotlin.net.interceptor.HttpLoggingInterceptor
import com.gw.newstart.kotlin.utils.Constant
import com.gw.newstart.kotlin.utils.FileUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by GongWen on 17/6/5.
 */
object OkHttpClientManager {
    private val cacheMaxSize = (1024 * 1024 * 50).toLong()
    private val connectTimeout: Long = 15
    private val readTimeout: Long = 25
    private val writeTimeout: Long = 25

    private var mOkHttpClient: OkHttpClient? = null;
    private val cacheFile = FileUtils.getCacheDir("HttpCache")
    private val cache = Cache(cacheFile, cacheMaxSize)//缓存文件

    fun getOkHttpClient(): OkHttpClient {
        if (mOkHttpClient == null) {
            val builder = OkHttpClient.Builder()
            builder
                    .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                    .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                    .addInterceptor(GlobalParametersInterceptor())
                    .cache(cache).addInterceptor(CacheInterceptor())
                    .retryOnConnectionFailure(true)
            if (Constant.DEBUGGABLE) {
                val mHttpLoggingInterceptor = HttpLoggingInterceptor()
                mHttpLoggingInterceptor.level = HttpLoggingInterceptor.Level.ME
                builder.addNetworkInterceptor(mHttpLoggingInterceptor)
            }
            mOkHttpClient = builder.build()
        }
        return mOkHttpClient as OkHttpClient
    }
}