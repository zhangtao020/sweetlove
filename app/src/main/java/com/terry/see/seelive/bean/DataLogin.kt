package com.terry.see.seelive.bean

/**
 * Created by terry on 2017/8/18.
 */
data class DataLogin(var uid: Long, var nickname: String, var avatar: String,
                     var loginstatus: Int, var msg: String, var token: String) : BaseData()
