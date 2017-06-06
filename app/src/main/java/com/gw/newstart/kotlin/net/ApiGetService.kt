package com.gw.newstart.kotlin.net

import com.gw.newstart.kotlin.model.ResponseEntity
import com.gw.newstart.kotlin.model.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by GongWen on 17/6/5.
 */
interface ApiGetService {
    @GET("/login?username=用户名&password=密码")
    fun getDataFromNetwork(): Observable<ResponseEntity<UserEntity>>
}