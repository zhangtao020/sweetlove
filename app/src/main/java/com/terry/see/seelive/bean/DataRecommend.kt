package com.terry.see.seelive.bean

/**
 * Created by terry on 2017/8/18.
 */
data class DataRecommend(var info:DataHeaderInfo= DataHeaderInfo(), var list:MutableList<ItemRecommend> = mutableListOf()) : BaseData()