package com.gw.newstart.kotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by GongWen on 17/6/5.
 */
data class ResponseEntity<T>
constructor(
        @SerializedName("code") var code: Int,
        @SerializedName("msg") var msg: String,
        @SerializedName("body") var body: T
) {
    fun isSuccess(): Boolean {
        return code == 200
    }

    override fun toString(): String {
        return "ResponseEntity(code=$code, msg='$msg', body=$body)"
    }
}