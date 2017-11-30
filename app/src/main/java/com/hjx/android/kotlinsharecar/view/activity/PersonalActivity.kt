package com.hjx.android.kotlinsharecar.view.activity

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import com.hjx.android.kotlinsharecar.R
import com.hjx.android.kotlinsharecar.app.AppSp
import com.hjx.android.kotlinsharecar.app.CarApplication
import com.hjx.android.kotlinsharecar.app.toast
import com.hjx.android.kotlinsharecar.di.components.DaggerPersonalComponent
import com.hjx.android.kotlinsharecar.di.modules.PersonalModule
import com.hjx.android.kotlinsharecar.presenter.PersonalContract
import com.hjx.android.kotlinsharecar.presenter.PersonalPresenter
import com.hjx.android.kotlinsharecar.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_personal.*
import javax.inject.Inject


class PersonalActivity : BaseActivity() ,PersonalContract.View{
    override fun onLogoutSuccess() {
        logout()
    }

    override fun showLoading(cancelable: Boolean) {
    }

    override fun dimissLoading() {
    }

    override fun showMsg(str: String) {
        toast(str, Toast.LENGTH_SHORT)
    }

    @Inject lateinit var mPresenter:PersonalPresenter

    override fun getResId() = R.layout.activity_personal

    override fun initView() {

    }

    override fun initEventAndData() {
        DaggerPersonalComponent.builder().netComponent(CarApplication.instance!!.netcomponent)
                .personalModule(PersonalModule(this)).build().inject(this)

        ivCancel.setOnClickListener { finish() }

        btnLogout.setOnClickListener{showLogoutDialog()}

    }

    override fun onClick(v: View?) {

    }


    private fun showLogoutDialog() {
        val view = layoutInflater.inflate(R.layout.logout_window_layout, null)
        var popupWindow = PopupWindow(view, resources.displayMetrics.widthPixels * 2 / 3, resources.displayMetrics.heightPixels / 5)
        view.findViewById<TextView>(R.id.btn_positive).setOnClickListener {
            mPresenter.logout()
            if (popupWindow.isShowing)
                popupWindow.dismiss()
        }
        view.findViewById<TextView>(R.id.btn_navigate).setOnClickListener{
            if (popupWindow.isShowing)
                popupWindow.dismiss()
        }

        popupWindow.isFocusable = true
        popupWindow.isOutsideTouchable = true
        popupWindow.setBackgroundDrawable(BitmapDrawable())

        popupWindow.setOnDismissListener{ backgroundAlpha(1.0f) }

        backgroundAlpha(0.6f)
        popupWindow.showAtLocation(btnLogout, Gravity.TOP, 0, resources.displayMetrics.heightPixels * 2 / 7)

    }


    //退出操作
    private fun logout() {
        AppSp.clearUser()
        AppSp.clearOrder()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


    //改变窗口透明度
    fun backgroundAlpha(bgAlpha: Float) {
        val lp = window.attributes
        lp.alpha = bgAlpha //0.0-1.0
        window.attributes = lp
    }

}
