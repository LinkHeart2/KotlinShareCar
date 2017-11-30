package com.hjx.android.kotlinsharecar.di.components

import com.hjx.android.kotlinsharecar.di.modules.LoginModule
import com.hjx.android.kotlinsharecar.di.scopes.UserScope
import com.hjx.android.kotlinsharecar.view.activity.LoginActivity
import dagger.Component

/**
 * Created by hjx on 2017-10-23.
 *You can make it better
 */
@UserScope
@Component(modules = arrayOf(LoginModule::class),dependencies = arrayOf(NetComponent::class))
interface LoginComponent{
    fun inject(activity: LoginActivity)
}