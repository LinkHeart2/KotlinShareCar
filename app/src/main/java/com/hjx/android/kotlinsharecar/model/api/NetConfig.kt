package com.hjx.android.kotlinsharecar.model.api

import java.util.concurrent.TimeUnit

/**
 * Created by hjx on 2017-10-20.
 *You can make it better
 */
class NetConfig{
    companion object {
        const val BASE_URL = "https://mcsharecar.messcat.com/"

        val TIME_UNIT:TimeUnit = TimeUnit.SECONDS
        const val TIME_OUT: Long = 20

    }
}