package com.hjx.android.kotlinsharecar.presenter

import com.hjx.android.kotlinsharecar.model.entity.LoginEmtity

/**
 * Created by hjx on 2017-10-23.
 *You can make it better
 */
interface LoginContract {
    interface Presenter{
        fun sendCode(phone: String)
        fun doLogin(phone:String,smsCode:String)
    }

    interface View : BaseView{
        fun doJump(bean: LoginEmtity)
    }
}