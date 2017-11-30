package com.hjx.android.kotlinsharecar.model.entity

/**
 * Created by hjx on 2017-10-23.
 *You can make it better
 */
data class BaseEmtity<T>(
        val status: String,
        val message:String,
        val result:T?
){
    fun isSuccess()="200".equals(status)
}