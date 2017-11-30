package com.hjx.android.kotlinsharecar.di.components

import com.hjx.android.kotlinsharecar.di.modules.NetModule
import com.hjx.android.kotlinsharecar.model.api.ApiService
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by hjx on 2017-10-20.
 *You can make it better
 */

@Singleton
@Component(modules = arrayOf(NetModule::class))
interface NetComponent{
    fun getApiService(): ApiService
    fun getOkHttp():OkHttpClient
    fun getRetrofit():Retrofit
}