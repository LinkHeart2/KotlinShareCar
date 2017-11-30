package com.hjx.android.kotlinsharecar.presenter

import com.hjx.android.kotlinsharecar.app.AppSp
import com.hjx.android.kotlinsharecar.model.api.ApiService
import com.hjx.android.kotlinsharecar.model.api.RxError
import com.hjx.android.kotlinsharecar.model.api.RxExtraction
import com.hjx.android.kotlinsharecar.model.api.RxSchedulers
import javax.inject.Inject

/**
 * Created by hjx on 2017-10-24.
 *You can make it better
 */
class PersonalPresenter @Inject constructor(var apiService: ApiService,var view:PersonalContract.View) :PersonalContract.Presenter{

    override fun logout() {
        apiService.doLogout(AppSp.getMemberID(),AppSp.getAccessToken())
                .compose(RxSchedulers.io_main())
                .map(RxExtraction<String>())
                .subscribe(
                        {
                            view.onLogoutSuccess()
                        },
                        {
                            error ->
                            if (error is RxError)
                                view.showMsg(error.msg)
                            else
                                error.printStackTrace()
                        }
                )
    }

}