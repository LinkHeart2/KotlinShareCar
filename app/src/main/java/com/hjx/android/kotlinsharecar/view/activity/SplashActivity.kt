package com.hjx.android.kotlinsharecar.view.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hjx.android.kotlinsharecar.R
import com.hjx.android.kotlinsharecar.app.AppSp
import com.hjx.android.kotlinsharecar.util.LogUtil
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


class SplashActivity : AppCompatActivity() ,EasyPermissions.PermissionCallbacks{


    companion object {
        const private val PERMISSION_REQUEST:Int = 1

    }

    protected val needPermissions:Array<String> = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE
            )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatus()

    }

    private fun initStatus() {
        if(Build.VERSION.SDK_INT >= 21) {
            var decorView = window.decorView
            var option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT
        }

    }

    override fun onStart() {
        super.onStart()
        requsetCodePermission()

    }

    @AfterPermissionGranted(PERMISSION_REQUEST)
    private fun requsetCodePermission(){
        if(!EasyPermissions.hasPermissions(this, *needPermissions)){
            EasyPermissions.requestPermissions(this,"需要这些权限", PERMISSION_REQUEST,*needPermissions)
        }else{
            setContentView(R.layout.activity_splash)
            Handler().postDelayed(Runnable { kotlin.run { doJump() } } , 2000L)
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        showMissingPermissionDialog()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {
        recreate()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    fun showMissingPermissionDialog(){
        var builder = AlertDialog.Builder(this)
        builder.setTitle("提示")
        builder.setMessage("当前应用缺少必要权限。请点击\"设置\"-\"权限\"-打开所需权限。")
        builder.setNegativeButton("取消", DialogInterface.OnClickListener{ dialog:DialogInterface,_:Int ->
            dialog.dismiss()
        })
        builder.setPositiveButton("设置", DialogInterface.OnClickListener{ _, _ ->
            startAppSettings()
        })
        builder.setCancelable(false)
        builder.create().show()
    }

    /**
     * 启动应用设置
     */
    private fun startAppSettings(){
        var intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.setData(Uri.parse("package:"+packageName))
        startActivity(intent)
    }




    fun doJump() {
        val userId = AppSp.getUserSp().getLong(AppSp.ID, -1L)
        LogUtil.d("userID = " + userId)
        if (userId == -1L || AppSp.getUserSp().getBoolean(AppSp.IS_OPEN_FINGER, false)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
//        else if ("" == AppSp.getUserSp().getString(AppSp.NICK_NAME, "")) {
//            val intent = Intent(this, BasicInformationActivity::class.java)
//            startActivity(intent)
//        }
        else {
            val intent = Intent(this, PersonalActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}
