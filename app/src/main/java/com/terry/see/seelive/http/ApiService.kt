package com.terry.see.seelive.http

import com.terry.see.seelive.bean.DataRecommend
import io.reactivex.Observable
import retrofit2.http.*
/**
 * Created by terry on 2017/8/18.
 */
interface ApiService{


    @GET
    fun recomment(@Url url:String):Observable<DataRecommend>

}