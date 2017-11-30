package com.hjx.android.kotlinsharecar.presenter

/**
 * Created by hjx on 2017-10-23.
 *You can make it better
 */
interface BaseView{
    fun showLoading(cancelable:Boolean)
    fun dimissLoading()
    fun showMsg(str: String)
}