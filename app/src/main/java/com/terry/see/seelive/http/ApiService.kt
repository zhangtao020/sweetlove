package com.terry.see.seelive.http

import com.terry.see.seelive.bean.DataComment
import com.terry.see.seelive.bean.DataRecommend
import io.reactivex.Observable
import retrofit2.http.*
/**
 * Created by terry on 2017/8/18.
 */
interface ApiService{


    @GET
    fun recomment(@Url url:String):Observable<DataRecommend>

    @GET("topic/comment_list/{id}/0/budejie-android-6.7.9/{page}.json")
    fun commentForParameter(@Path("id") id:String,@Path("page") page:String):Observable<DataComment>

    @GET
    fun comment(@Url url:String):Observable<DataComment>
}