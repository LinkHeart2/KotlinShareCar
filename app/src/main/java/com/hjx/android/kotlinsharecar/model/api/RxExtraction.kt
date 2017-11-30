package com.hjx.android.kotlinsharecar.model.api

import com.hjx.android.kotlinsharecar.model.entity.BaseEmtity
import io.reactivex.functions.Function

/**
 * Created by hjx on 2017-10-24.
 *You can make it better
 */
class RxExtraction<T> :Function<BaseEmtity<T>,BaseEmtity<T>>{
    override fun apply(t: BaseEmtity<T>): BaseEmtity<T>? {
        if(!t.isSuccess())
            throw RxError(t.status,t.message)
        return t
    }

}