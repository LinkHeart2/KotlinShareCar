package com.hjx.android.kotlinsharecar.presenter

import android.text.TextUtils
import com.hjx.android.kotlinsharecar.app.AppSp
import com.hjx.android.kotlinsharecar.model.api.ApiService
import com.hjx.android.kotlinsharecar.model.api.RxError
import com.hjx.android.kotlinsharecar.model.api.RxExtraction
import com.hjx.android.kotlinsharecar.model.api.RxSchedulers
import com.hjx.android.kotlinsharecar.model.entity.LoginEmtity
import com.hjx.android.kotlinsharecar.model.entity.VersionEmtity
import com.hjx.android.kotlinsharecar.util.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by hjx on 2017-10-23.
 *You can make it better
 */
class LoginPresenter @Inject constructor(var apiService: ApiService, var view: LoginContract.View) : LoginContract.Presenter {

    /**
     * 登录(手机号，验证码)
     * 先请求版本接口获得版本号再作为参数请求登录接口
     */
    override fun doLogin(phone: String, smsCode: String) {
        apiService.anotherVersion(1)
                .map(RxExtraction<VersionEmtity>())
                .flatMap { emtity ->
                    var bean = emtity.result
                    LogUtil.d("login")
                    apiService.doLogin(phone, smsCode, bean!!.version!!, 1)
                }
                .map(RxExtraction<LoginEmtity>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ emtity ->
                    var bean = emtity.result

                    val edit = AppSp.editUser()
                    edit.putString(AppSp.ACCESS_TOKEN, bean!!.accessToken)
                    edit.putLong(AppSp.ID, bean.id!!.toLong())

                    edit.putString(AppSp.NICK_NAME, bean.nickName)
                    edit.putString(AppSp.EMAIL, bean.email)
                    edit.putString(AppSp.BIRTH_DAY, bean.birthday)
                    edit.putString(AppSp.SEX, bean.sex)
                    edit.putString(AppSp.DEPOSIT, bean.deposit)

                    val img = bean.headImg
                    if (!TextUtils.isEmpty(img)) {
                        if (img != null) {
                            edit.putString(AppSp.HEAD_IMG, img.substring(1))
                        }
                    }
                    edit.putString(AppSp.MOBILE, bean.mobile)
                    edit.putString(AppSp.LICENSE, bean.license)
                    edit.apply()

                    view.dimissLoading()
                    view.doJump(bean)
                }, {
                    error ->
                    view.dimissLoading()
                    if (error is RxError)
                        view.showMsg(error.msg)
                    else
                        error.printStackTrace()
                })


    }

    /**
     * 发送验证码
     */
    override fun sendCode(phone: String) {
        apiService.sendLoginCode(phone).compose(RxSchedulers.io_main())
                .map(RxExtraction<String>())
                .subscribe({
                    view.showMsg("发送成功")
                }, {
                    error ->
                    if (error is RxError)
                        view.showMsg(error.msg)
                    else
                        error.printStackTrace()
                })
    }


}