package com.hjx.android.kotlinsharecar.presenter

/**
 * Created by hjx on 2017-10-24.
 *You can make it better
 */
interface PersonalContract{

    interface View:BaseView{
        fun onLogoutSuccess()
    }
    interface Presenter{
        fun logout()
    }
}