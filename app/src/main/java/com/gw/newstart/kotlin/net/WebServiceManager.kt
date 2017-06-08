package com.gw.newstart.kotlin.net

import com.google.gson.Gson
import com.gw.newstart.kotlin.utils.Constant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by GongWen on 17/6/5.
 */
object WebServiceManager {
    private val mWebServiceMap: ConcurrentHashMap<Class<*>, Any> = ConcurrentHashMap()
    private var mRetrofit: Retrofit? = null;

    private fun getRetrofit(): Retrofit {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                    .baseUrl(Constant.HOST)
                    .client(OkHttpClientManager.getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return mRetrofit as Retrofit
    }

    private fun <T> getWebService(clazz: Class<T>): T {
        var mWebService = mWebServiceMap[clazz];
        if (mWebService == null) {
            mWebService = getRetrofit().create(clazz)
            mWebServiceMap[clazz] = mWebService as Any
        }
        return mWebService as T
    }

    fun getApiGetService() = getWebService(ApiGetService::class.java)

    fun getApiPostService() = getWebService(ApiPostService::class.java)
}