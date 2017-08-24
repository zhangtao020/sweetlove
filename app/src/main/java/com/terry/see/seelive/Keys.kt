package com.terry.see.seelive

/**
 * Created by terry on 2017/8/18.
 */
object Keys {

    const val c_api_url = "http://c.api.budejie.com/"

    const val recomend_url = "http://c.api.budejie.com/topic/list/jingxuan/1/budejie-android-6.7.9/0-20.json?market=b-huawei&ver=6.7.9&visiting=&os=7.0&appname=baisibudejie&client=android&udid=865217031356615&mac=c8:94:bb:09:44:f9"

    fun comment_url(id:String):String{
        return c_api_url + "topic/comment_list/$id/0/budejie-android-6.7.9/0-20.json?"
    }
}