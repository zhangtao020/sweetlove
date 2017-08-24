package com.terry.see.seelive.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by terry on 2017/8/22.
 */
data class ItemComment(var voicetime: Int = 0, var status: Int = 0, var hate_count: Int = 0, var cmt_type: String = "",
                       var precid: Int = 0, var content: String = "", var like_count: Int = 0, var preuid: Int = 0, var passtime:String="",
                       var id:Int = 0,var u:ItemUser=ItemUser()):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readParcelable<ItemUser>(ItemUser::class.java.classLoader))

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(voicetime)
        dest?.writeInt(status)
        dest?.writeInt(hate_count)
        dest?.writeString(cmt_type)
        dest?.writeInt(precid)
        dest?.writeString(content)
        dest?.writeInt(like_count)
        dest?.writeInt(preuid)
        dest?.writeString(passtime)
        dest?.writeInt(id)
        dest?.writeParcelable(u,flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemComment> {
        override fun createFromParcel(parcel: Parcel): ItemComment {
            return ItemComment(parcel)
        }

        override fun newArray(size: Int): Array<ItemComment?> {
            return arrayOfNulls(size)
        }
    }

}