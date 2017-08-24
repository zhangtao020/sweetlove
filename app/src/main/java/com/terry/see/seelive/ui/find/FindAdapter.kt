package com.terry.see.seelive.ui.find

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.terry.see.seelive.R
import com.terry.see.seelive.bean.ItemRecommend
import com.terry.see.seelive.ui.detail.DetailActivity
import com.terry.see.seelive.util.AppDeviceUtil
import com.terry.see.seelive.util.GlideCircleTransform
import kotlinx.android.synthetic.main.fragment_find_item.view.*

/**
 * Created by terry on 2017/8/22.
 */
class FindAdapter(var activity: Activity, var mValues: MutableList<ItemRecommend> = mutableListOf()) : RecyclerView.Adapter<FindAdapter.FindViewHolder>() {


    fun clear(){
        mValues.clear()
    }

    fun buildData(values: MutableList<ItemRecommend>){
        if (values.isEmpty()) return

        mValues.addAll(values)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindViewHolder {
        return FindViewHolder(parent)
    }

    fun setPicViewLayoutParams(imgView: ImageView,scaleType: Float){
        val width = AppDeviceUtil.getScreenWidth(activity) - AppDeviceUtil.dpToPx(activity,40)
        var params = FrameLayout.LayoutParams(width,(width/scaleType).toInt())
        imgView.layoutParams = params
    }

    fun startIntentData(data:ItemRecommend,from:String){
        var intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("data",data)
        intent.putExtra("from",from)
        activity.startActivity(intent)
    }

    override fun onBindViewHolder(holder: FindViewHolder, position: Int) {

        var recommend = mValues[position]

        if (recommend.u.header.isNotEmpty()){
            Glide.with(activity).load(recommend.u.header[0]).centerCrop().
                    transform(GlideCircleTransform(activity)).into(holder.itemView.find_head_iv)
        }

        holder.itemView.find_name_tv.text= recommend.u.name + recommend.type
        holder.itemView.find_time_tv.text = recommend.passtime
        holder.itemView.find_content_tv.text = recommend.text
        holder.itemView.find_share_tv.text = recommend.forward.toString()
        holder.itemView.find_comment_tv.text = recommend.comment.toString()

        holder.itemView.find_comment_tv.setOnClickListener {
            startIntentData(recommend,"comment")
        }

        holder.itemView.setOnClickListener {
            startIntentData(recommend,"detail")
        }

        when (recommend.type){
            "video" ->
                if(recommend.video.thumbnail.isNotEmpty()){
                    holder.itemView.find_pic_container.visibility = View.VISIBLE
                    holder.itemView.find_video_start_iv.visibility = View.VISIBLE
                    setPicViewLayoutParams(holder.itemView.find_pic_iv,recommend.video.width/recommend.video.height.toFloat())
                    Glide.with(activity).load(recommend.video.thumbnail[0]).centerCrop().into(holder.itemView.find_pic_iv)
                }else{
                    holder.itemView.find_pic_container.visibility = View.GONE
                }
            "gif" ->
                if (recommend.gif.gif_thumbnail.isNotEmpty()){
                    holder.itemView.find_pic_container.visibility = View.VISIBLE
                    holder.itemView.find_video_start_iv.visibility = View.GONE
                    setPicViewLayoutParams(holder.itemView.find_pic_iv,recommend.gif.width/recommend.gif.height.toFloat())
                    Glide.with(activity).load(recommend.gif.images[0]).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.itemView.find_pic_iv)
                }else{
                    holder.itemView.find_pic_container.visibility = View.GONE
                    holder.itemView.find_video_start_iv.visibility = View.GONE
                }
            "image" ->
                if (recommend.image.thumbnail_small.isNotEmpty()){
                    holder.itemView.find_pic_container.visibility = View.VISIBLE
                    holder.itemView.find_video_start_iv.visibility = View.GONE

                    var height = recommend.image.height.toFloat()
                    if (recommend.image.height > 1000){
                        height = AppDeviceUtil.getScreenWidth(activity).toFloat() - AppDeviceUtil.dpToPx(activity,40)
                    }
                    setPicViewLayoutParams(holder.itemView.find_pic_iv,recommend.image.width/height)
                    Glide.with(activity).load(recommend.image.thumbnail_small[0]).centerCrop().into(holder.itemView.find_pic_iv)
                }else{
                    holder.itemView.find_pic_container.visibility = View.GONE
                    holder.itemView.find_video_start_iv.visibility = View.GONE
                }
            "text" -> {
                holder.itemView.find_pic_container.visibility = View.GONE
                holder.itemView.find_video_start_iv.visibility = View.GONE
            }
            else -> {
                holder.itemView.find_pic_container.visibility = View.GONE
                holder.itemView.find_video_start_iv.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    class FindViewHolder constructor(mView: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(mView.context).inflate(R.layout.fragment_find_item,mView,false))




//    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
//        val mIdView: TextView
//        val mContentView: TextView
//        var mItem: ItemRecommend? = null
//
//        init {
//            mIdView = mView.findViewById(R.id.id) as TextView
//            mContentView = mView.findViewById(R.id.content) as TextView
//        }
//
//        override fun toString(): String {
//            return super.toString() + " '" + mContentView.text + "'"
//        }
//    }
}
