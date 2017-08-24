package com.terry.see.seelive.bean

/**
 * Created by terry on 2017/8/18.
 */
data class DataComment(var author_uid:Int,var hot:InnerDataComment?, var normal:InnerDataComment?) : BaseData(){


    data class InnerDataComment(var info: DataHeaderInfo = DataHeaderInfo(),var list:MutableList<InnerItemComment> = mutableListOf())

    data class InnerItemComment(var status:Int = 0,var ctime:String = "",var hate_count:Int = 0,var data_id:Int = 0,var content:String = "",
                                var like_count:Int = 0, var user:InnerItemUser = InnerItemUser(),var precmts:MutableList<InnerItemComment> = mutableListOf(),
                                var type:String ="",var id:Int = 0,var is_hot:Boolean = false)

    data class InnerItemUser(var username:String="",var profile_image:String = "",val personal_page:String="",val total_cmt_like_count:Int=0,
                             val is_vip:Boolean = false,val sex:String = "m",val id:Int = 0)
}