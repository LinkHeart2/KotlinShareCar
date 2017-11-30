package com.hjx.android.kotlinsharecar.view.activity

import android.content.Intent
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.hjx.android.kotlinsharecar.R
import com.hjx.android.kotlinsharecar.app.CarApplication
import com.hjx.android.kotlinsharecar.app.toast
import com.hjx.android.kotlinsharecar.di.components.DaggerLoginComponent
import com.hjx.android.kotlinsharecar.di.modules.LoginModule
import com.hjx.android.kotlinsharecar.model.entity.LoginEmtity
import com.hjx.android.kotlinsharecar.presenter.LoginContract
import com.hjx.android.kotlinsharecar.presenter.LoginPresenter
import com.hjx.android.kotlinsharecar.util.CheckUtil
import com.hjx.android.kotlinsharecar.view.base.BaseActivity
import com.hjx.android.kotlinsharecar.view.widget.dialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : BaseActivity(), LoginContract.View {

    companion object {
        var phones = arrayOf("18878554194","17688375076")
    }

    @Inject
    lateinit var mPresenter: LoginPresenter

    private lateinit var loadingDialog: LoadingDialog

    override fun getResId(): Int = R.layout.activity_login

    override fun initView() {
        loadingDialog = LoadingDialog(this)
    }

    override fun initEventAndData() {
        DaggerLoginComponent.builder()
                .netComponent(CarApplication.instance?.netcomponent)
                .loginModule(LoginModule(this))
                .build()
                .inject(this)

        tv_start.setOnClickListener(this)
        tv_register_code.setOnClickListener(this)
    }

    override fun showLoading(cancel: Boolean) {
        loadingDialog.setCancelable(cancel)
        loadingDialog.show()
    }

    override fun dimissLoading() {
        loadingDialog.dissmiss()
    }

    override fun showMsg(str: String) {
        toast(str, Toast.LENGTH_SHORT)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.tv_start -> {
                    if(checkParams()){
                        showLoading(false)
                        mPresenter.doLogin(et_login_phone.text.toString(),et_login_smsCode.text.toString())
                    }
                }
                R.id.tv_register_code -> {
                    val phone = et_login_phone.text.toString()
                        if (TextUtils.isEmpty(phone)) {
                            showMsg("请输入手机号")
                            return
                        }
                        if (!CheckUtil.isPhone(phone)) {
                            showMsg("请输入正确的手机号")
                            return
                        }

//                        phones.forEach {
//                            if(it.equals(phone)) {
                                isTimeDown = true
                                timer.start()
                                mPresenter.sendCode(phone)
//                                return
//                            }
//                        }
//                        showMsg("测试版本，不支持该手机号")

                }
            }
        }
    }

    private fun checkParams(): Boolean {
        val phone = et_login_phone.text.toString()
        if (TextUtils.isEmpty(phone)) {
            showMsg("请输入手机号")
            return false
        }
        if (!CheckUtil.isPhone(phone)) {
            showMsg("请输入正确的手机号")
            return false
        }
        val smsCode = et_login_smsCode.text.toString()
        if (TextUtils.isEmpty(smsCode)) {
            showMsg("请输入验证码")
            return false
        }
        return true
    }

    private var isTimeDown = false
    var timer: CountDownTimer = object : CountDownTimer(60000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            if (tv_register_code != null) {
                tv_register_code.isEnabled = false
                tv_register_code.text = (millisUntilFinished / 1000).toString() + "s"
            }
        }

        override fun onFinish() {
            if (tv_register_code != null) {
                tv_register_code.isEnabled = true
                tv_register_code.setTextColor(resources.getColor(R.color.colorPrimary))
                tv_register_code.text = resources.getString(R.string.get_code)
                isTimeDown = false
            }
        }

    }

    override fun doJump(bean: LoginEmtity) {


//        if (TextUtils.isEmpty(bean.email)||TextUtils.isEmpty(bean.mobile)) {
//            return
//        }else{
            var intent = Intent(this,PersonalActivity::class.java)

            startActivity(intent)
            finish()
//        }
//        val license = bean.license
//        if (license == null || "" == license || "2" == license) {
//            mActivity.doJump(LoginActivity.DRIVER_LICENSE)
//            return
//        }
//        val deposit = bean.deposit
//        if (deposit == null || "" == deposit || "0" == deposit) {
//            mActivity.doJump(LoginActivity.CAR_DEPOSIT)
//            return
//        }

//        mActivity.doJump(LoginActivity.MAP_INDEX)
    }
}
