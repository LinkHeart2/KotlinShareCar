package com.hjx.android.kotlinsharecar.di.modules

import com.hjx.android.kotlinsharecar.di.scopes.UserScope
import com.hjx.android.kotlinsharecar.presenter.LoginContract
import dagger.Module
import dagger.Provides

/**
 * Created by hjx on 2017-10-23.
 *You can make it better
 */
@Module
class LoginModule(var view: LoginContract.View){

    @Provides
    @UserScope
    fun provideView():LoginContract.View = view


}