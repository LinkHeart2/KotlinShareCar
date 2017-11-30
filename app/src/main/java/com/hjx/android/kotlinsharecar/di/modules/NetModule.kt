package com.hjx.android.kotlinsharecar.di.modules

import com.google.gson.GsonBuilder
import com.hjx.android.kotlinsharecar.BuildConfig
import com.hjx.android.kotlinsharecar.model.api.ApiService
import com.hjx.android.kotlinsharecar.model.api.NetConfig
import com.hjx.android.kotlinsharecar.model.util.HttpLogger
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



/**
 * Created by hjx on 2017-10-20.
 *You can make it better
 */
@Module
class NetModule{

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        var interceptor = HttpLoggingInterceptor(HttpLogger())
        interceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        var okHttpClient = OkHttpClient.Builder()
                .connectTimeout(NetConfig.TIME_OUT, NetConfig.TIME_UNIT)
                .readTimeout(NetConfig.TIME_OUT, NetConfig.TIME_UNIT)
                .addNetworkInterceptor(interceptor)
                .build()
        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()//使用 gson coverter，统一日期请求格式
        var retrofit = Retrofit.Builder()
                .baseUrl(NetConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}