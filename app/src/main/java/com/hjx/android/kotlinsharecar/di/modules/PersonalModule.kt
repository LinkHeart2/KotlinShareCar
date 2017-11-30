package com.hjx.android.kotlinsharecar.di.modules

import com.hjx.android.kotlinsharecar.di.scopes.UserScope
import com.hjx.android.kotlinsharecar.presenter.PersonalContract
import dagger.Module
import dagger.Provides

/**
 * Created by hjx on 2017-10-24.
 *You can make it better
 */
@Module
class PersonalModule(var view:PersonalContract.View){

    @Provides
    @UserScope
    fun provideView():PersonalContract.View = view
}