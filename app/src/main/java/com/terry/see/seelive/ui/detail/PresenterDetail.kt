package com.terry.see.seelive.ui.detail

import com.terry.see.seelive.Keys
import com.terry.see.seelive.http.ApiService
import com.terry.see.seelive.ui.find.FindAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by terry on 2017/8/22.
 */
class PresenterDetail @Inject constructor() {

    lateinit var apiService: ApiService
        @Inject set

    fun requestRecommend(adapter: FindAdapter){

        apiService.recomment(Keys.recomend_url).filter { it.list.isNotEmpty() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.clear()
                    adapter.buildData(it.list)
                }
    }
}