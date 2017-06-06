package com.gw.newstart.kotlin.net

import com.gw.newstart.kotlin.model.ResponseEntity
import com.gw.newstart.kotlin.model.UserEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by GongWen on 17/6/5.
 */
interface ApiPostService {
    @FormUrlEncoded
    @POST("/login")
    fun getDataFromNetwork(
            @Field("username") username: String,
            @Field("password") password: String
    ): Observable<ResponseEntity<UserEntity>>
}