package com.hjx.android.kotlinsharecar.di.components

import com.hjx.android.kotlinsharecar.di.modules.PersonalModule
import com.hjx.android.kotlinsharecar.di.scopes.UserScope
import com.hjx.android.kotlinsharecar.view.activity.PersonalActivity
import dagger.Component

/**
 * Created by hjx on 2017-10-24.
 *You can make it better
 */
@UserScope
@Component(modules = arrayOf(PersonalModule::class),dependencies = arrayOf(NetComponent::class))
interface PersonalComponent{
    fun inject(activity:PersonalActivity)
}