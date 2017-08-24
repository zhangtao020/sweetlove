package com.terry.see.seelive.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by terry on 2017/8/22.
 */
data class ItemRecommend(var status:Int = 0, var comment:Int = 0, var bookmark:Int = 0, var text:String = "", var shareurl:String = "", var down:Int = 0, var forward:Int = 0,
                         var passtime:String = "", var type:String = "", var id:String = "", var top_comments:MutableList<ItemComment> = mutableListOf(), var u:ItemUser= ItemUser(),
                         var video:ItemVideo = ItemVideo(),var gif:ItemGif = ItemGif(),var image:ItemImage = ItemImage()):Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(ItemComment.CREATOR),
            parcel.readParcelable(ItemUser::class.java.classLoader),
            parcel.readParcelable(ItemVideo::class.java.classLoader),
            parcel.readParcelable(ItemGif::class.java.classLoader),
            parcel.readParcelable(ItemImage::class.java.classLoader))

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(status)
        dest?.writeInt(comment)
        dest?.writeInt(bookmark)
        dest?.writeString(text)
        dest?.writeString(shareurl)
        dest?.writeInt(down)
        dest?.writeInt(forward)
        dest?.writeString(passtime)
        dest?.writeString(type)
        dest?.writeString(id)
        dest?.writeTypedList(top_comments)
        dest?.writeParcelable(u,flags)
        dest?.writeParcelable(video,flags)
        dest?.writeParcelable(gif,flags)
        dest?.writeParcelable(image,flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemRecommend> {
        override fun createFromParcel(parcel: Parcel): ItemRecommend {
            return ItemRecommend(parcel)
        }

        override fun newArray(size: Int): Array<ItemRecommend?> {
            return arrayOfNulls(size)
        }
    }

}