package com.hjx.android.kotlinsharecar.app

import android.app.Application
import com.hjx.android.kotlinsharecar.BuildConfig
import com.hjx.android.kotlinsharecar.di.components.DaggerNetComponent
import com.hjx.android.kotlinsharecar.di.components.NetComponent
import com.hjx.android.kotlinsharecar.di.modules.NetModule
import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger

/**
 * Created by hjx on 2017-10-19.
 *You can make it better
 */
class CarApplication : Application() {

    companion object {

        var instance:CarApplication? = null

        fun getContext() = instance!!
    }

    lateinit var netcomponent:NetComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        initLog()
        initSp()
        initNet()
    }

    private fun initSp() {
        AppSp.init(getContext())
    }

    fun initLog(){
        var logLevel:LogLevel
        if(BuildConfig.IS_LOG){
            logLevel = LogLevel.FULL
        }else{
            logLevel = LogLevel.NONE
        }
        Logger.init("hjx")
                .methodCount(3)
                .logLevel(logLevel)
    }

    fun initNet(){
        netcomponent = DaggerNetComponent.builder().netModule(NetModule()).build()
    }
}