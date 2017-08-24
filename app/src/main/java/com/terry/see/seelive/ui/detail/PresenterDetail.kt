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

    fun requestComment(adapter: DetailAdapter,id:String){

        apiService.comment(Keys.comment_url(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.buildData(it)
                }
    }

    fun requestComment(adapter: DetailAdapter,id:String,page:String){

        apiService.commentForParameter(id,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.buildData(it)
                }
    }
}