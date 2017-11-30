package com.hjx.android.kotlinsharecar.app

import android.content.Context
import android.widget.Toast
import com.hjx.android.kotlinsharecar.util.ToastUtils

/**
 * Created by hjx on 2017-10-26.
 *You can make it better
 */
fun Context.toast(msg: String, length:Int = Toast.LENGTH_SHORT){
    if(Toast.LENGTH_LONG == length)
        ToastUtils.showLongToastSafe(this,msg)
    else
        ToastUtils.showShortToastSafe(this,msg)
}
