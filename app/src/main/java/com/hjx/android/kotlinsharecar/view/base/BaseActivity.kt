package com.hjx.android.kotlinsharecar.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

abstract class BaseActivity : AppCompatActivity() ,View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResId())
        initView()
        initEventAndData()
    }

    abstract fun getResId():Int

    abstract fun initView()

    abstract fun initEventAndData()

}
