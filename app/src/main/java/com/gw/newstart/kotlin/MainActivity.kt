package com.gw.newstart.kotlin

import android.os.Bundle
import com.gw.newstart.kotlin.model.UserEntity
import com.gw.newstart.kotlin.net.WebObserver
import com.gw.newstart.kotlin.net.WebServiceManager
import com.gw.newstart.kotlin.rxhelper.SchedulersCompat
import com.orhanobut.logger.Logger
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiGetService = WebServiceManager.getApiGetService()
        apiGetService.getDataFromNetwork()
                .compose(bindToLifecycle())
                .compose(SchedulersCompat.applyIoSchedulers())
                .subscribe(object : WebObserver<UserEntity>(this) {
                    override fun onSuccess(data: UserEntity) {
                        Logger.i("get--->" + data.toString())
                    }
                })
        val apiPostService = WebServiceManager.getApiPostService()
        apiPostService.getDataFromNetwork("用户名", "密码")
                .compose(bindToLifecycle())
                .compose(SchedulersCompat.applyIoSchedulers())
                .subscribe(object : WebObserver<UserEntity>(this) {
                    override fun onSuccess(data: UserEntity) {
                        Logger.i("post--->" + data.toString())
                    }
                })
    }
}
