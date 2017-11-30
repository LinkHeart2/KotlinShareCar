package com.hjx.android.kotlinsharecar.model.api

import java.lang.RuntimeException

/**
 * Created by hjx on 2017-10-24.
 *You can make it better
 */
class RxError(var errorCode:String,var msg:String): RuntimeException(msg)