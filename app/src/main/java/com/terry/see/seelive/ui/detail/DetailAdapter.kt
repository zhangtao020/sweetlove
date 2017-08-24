package com.terry.see.seelive.ui.detail

import android.app.Activity
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.terry.see.seelive.R
import com.terry.see.seelive.bean.DataComment
import com.terry.see.seelive.bean.ItemRecommend
import com.terry.see.seelive.util.AppDeviceUtil
import com.terry.see.seelive.util.GlideCircleTransform
import kotlinx.android.synthetic.main.fragment_find_item.view.*
import kotlinx.android.synthetic.main.activity_comment_item.view.*

/**
 * Created by terry on 2017/8/22.
 */
class DetailAdapter(var activity: Activity, var headerData: ItemRecommend) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val ITEM_TYPE_HEADER:Int = 1
        val ITEM_TYPE_COMMENT:Int = 2
    }

    fun setFragmentTransaction( transaction: FragmentTransaction){
        mTransaction = transaction
    }

    var mTransaction: FragmentTransaction?=null
    var mValues: MutableList<DataComment.InnerItemComment> = mutableListOf()

    fun buildData(values: DataComment?){

        if (values != null){
            if (values!!.hot != null && values!!.hot!!.list.isNotEmpty()){
                for (comment in values!!.hot!!.list){
                    comment.is_hot = true
                    mValues.add(comment)
                }
            }

            if (values!!.normal != null && values!!.normal!!.list.isNotEmpty()){
                mValues.addAll(values!!.normal!!.list)
            }
        }

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {

        when(position){
            0 -> return ITEM_TYPE_HEADER
            else -> return ITEM_TYPE_COMMENT
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType){
            ITEM_TYPE_HEADER -> return HeaderViewHolder(parent)
            else -> return CommentViewHolder(parent)
        }

    }

    fun setPicViewLayoutParams(imgView: ImageView,videoContainer:FrameLayout?, scaleType: Float){
        val width = AppDeviceUtil.getScreenWidth(activity) - AppDeviceUtil.dpToPx(activity, 40)
        var params = FrameLayout.LayoutParams(width, (width / scaleType).toInt())
        imgView.layoutParams = params

        if (videoContainer!=null){
            videoContainer!!.layoutParams = params
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)){
            ITEM_TYPE_HEADER -> bindHeaderView(holder as HeaderViewHolder)
            else ->  bindCommentView(holder as CommentViewHolder,position-1)
        }

    }

    override fun getItemCount(): Int {
        if (mValues.isEmpty())return 1
        return mValues.size + 1
    }

    fun bindCommentView(holder: CommentViewHolder,position: Int){
        val comment = mValues[position]

        if (position == 0){
            if (comment.is_hot) holder.itemView.comment_type_tv.text = "热门评论"
            else holder.itemView.comment_type_tv.text = "最新评论"
            holder.itemView.comment_type_tv.visibility = View.VISIBLE
        }else if ( mValues[position-1].is_hot && !comment.is_hot){
            holder.itemView.comment_type_tv.text = "最新评论"
            holder.itemView.comment_type_tv.visibility = View.VISIBLE
        }else{
            holder.itemView.comment_type_tv.visibility = View.GONE
        }

        if (comment.user.profile_image.isNotEmpty()){
            Glide.with(activity).load(comment.user.profile_image).centerCrop().
                    transform(GlideCircleTransform(activity)).into(holder.itemView.comment_header_iv)
        }

        if ("m" == comment.user.sex){
            var leftDw = activity.resources.getDrawable(R.mipmap.personal_sex_men)
            leftDw.setBounds(0,0,leftDw.intrinsicWidth,leftDw.intrinsicHeight)
            holder.itemView.comment_nick_tv.setCompoundDrawables(leftDw,null,null,null)
        }else{
            var leftDw = activity.resources.getDrawable(R.mipmap.personal_sex_women)
            leftDw.setBounds(0,0,leftDw.intrinsicWidth,leftDw.intrinsicHeight)
            holder.itemView.comment_nick_tv.setCompoundDrawables(leftDw,null,null,null)
        }
        holder.itemView.comment_nick_tv.text = comment.user.username

        if (comment.user.total_cmt_like_count > 1000){
            val count = (comment.user.total_cmt_like_count / 1000).toFloat()
            holder.itemView.comment_km_tv.text = "${count}k"
        }else{
            holder.itemView.comment_km_tv.text = comment.user.total_cmt_like_count.toString()
        }

        holder.itemView.comment_content_tv.text = comment.content

    }


    fun bindHeaderView(holder: HeaderViewHolder){
        if (headerData.u.header.isNotEmpty()){
            Glide.with(activity).load(headerData.u.header[0]).centerCrop().
                    transform(GlideCircleTransform(activity)).into(holder.itemView.find_head_iv)
        }

        holder.itemView.find_name_tv.text= headerData.u.name + headerData.type
        holder.itemView.find_time_tv.text = headerData.passtime
        holder.itemView.find_content_tv.text = headerData.text
        holder.itemView.find_comment_layout.visibility = View.GONE
        holder.itemView.find_bottom_line_iv.visibility = View.VISIBLE

        holder.itemView.find_video_start_iv.setOnClickListener {
            holder.itemView.find_video_start_iv.visibility = View.GONE
            holder.itemView.find_pic_iv.visibility = View.GONE
            if (mTransaction!=null)mTransaction!!.replace(R.id.find_video_container,VideoPlayFragment.newInstance(headerData.video.video[0])).commit()
        }

        when (headerData.type){
            "video" ->
                if(headerData.video.thumbnail.isNotEmpty()){
                    holder.itemView.find_pic_container.visibility = View.VISIBLE
                    holder.itemView.find_video_start_iv.visibility = View.VISIBLE
                    setPicViewLayoutParams(holder.itemView.find_pic_iv,holder.itemView.find_video_container,headerData.video.width/headerData.video.height.toFloat())
                    Glide.with(activity).load(headerData.video.thumbnail[0]).centerCrop().into(holder.itemView.find_pic_iv)
                }else{
                    holder.itemView.find_pic_container.visibility = View.GONE
                }
            "gif" ->
                if (headerData.gif.images.isNotEmpty()){
                    holder.itemView.find_pic_container.visibility = View.VISIBLE
                    holder.itemView.find_video_start_iv.visibility = View.GONE
                    setPicViewLayoutParams(holder.itemView.find_pic_iv,null,headerData.gif.width/headerData.gif.height.toFloat())
                    Glide.with(activity).load(headerData.gif.images[0]).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.itemView.find_pic_iv)
                }else{
                    holder.itemView.find_pic_container.visibility = View.GONE
                    holder.itemView.find_video_start_iv.visibility = View.GONE
                }
            "image" ->
                if (headerData.image.big.isNotEmpty()){
                    holder.itemView.find_pic_container.visibility = View.VISIBLE
                    holder.itemView.find_video_start_iv.visibility = View.GONE

                    val width = headerData.image.width
                    val height = headerData.image.height.toFloat()

                    setPicViewLayoutParams(holder.itemView.find_pic_iv,null, width/height)

                    var mGlide = Glide.with(activity).load(headerData.image.big[0])
                    if (height > 1000){
                        mGlide.override(width/2,height.toInt()/2)
                    }else if (height > 1500){
                        mGlide.override(width/2.5.toInt(),height.toInt()/2.5.toInt())
                    }
                    mGlide.into(holder.itemView.find_pic_iv)
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


    class HeaderViewHolder constructor(mView: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(mView.context).inflate(R.layout.fragment_find_item,mView,false))

    class CommentViewHolder constructor(mView: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(mView.context).inflate(R.layout.activity_comment_item,mView,false))

}