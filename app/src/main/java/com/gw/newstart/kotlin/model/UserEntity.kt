package com.gw.newstart.kotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by GongWen on 17/6/5.
 */
data class UserEntity constructor(
        @SerializedName("username") var username: String,
        @SerializedName("password") var password: String,
        @SerializedName("os") var os: String
) {
    override fun toString(): String {
        return "UserEntity(username='$username', password='$password', os='$os')"
    }
}