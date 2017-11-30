package com.hjx.android.kotlinsharecar.model.api

import com.hjx.android.kotlinsharecar.model.entity.BaseEmtity
import com.hjx.android.kotlinsharecar.model.entity.LoginEmtity
import com.hjx.android.kotlinsharecar.model.entity.VersionEmtity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by hjx on 2017-10-20.
 *You can make it better
 */
interface ApiService{

    /**
     * 1.4.	会员登录时获取验证码

     * @param mobile 会员登录手机号码
     * *
     * @return
     */
    @FormUrlEncoded
    @POST("login/sendLoginCode")
    fun sendLoginCode(@Field("mobile") mobile: String): Observable<BaseEmtity<String>>

    /**
        获取版本号
     * @param type 1=安卓 2=IOS
     * *
     * @return
     */
    @FormUrlEncoded
    @POST("version/anotherVersion")
    fun anotherVersion(@Field("type") type: Int): Observable<BaseEmtity<VersionEmtity>>


    /**
     * 注册与登录
     */
    @FormUrlEncoded
    @POST("login/doLogin")
    fun doLogin(
            @Field("mobile")mobile:String,
            @Field("smsCode")smsCode:String,
            @Field("version")version:String,
            @Field("type")type:Int
    ):Observable<BaseEmtity<LoginEmtity>>

    /**
     * 退出登录
     */
    @FormUrlEncoded
    @POST("member/doLogout")
    fun doLogout(
            @Field("memberID") memberID: Long,
                 @Field("accessToken") accessToken: String
    ): Observable<BaseEmtity<String>>
}