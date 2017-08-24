package com.terry.see.seelive.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by terry on 2017/8/22.
 */
data class ItemVideo(var playfcount:Int = 0,var height:Int = 0,var width:Int=0,var video:MutableList<String> = mutableListOf(),
                     var download:MutableList<String> = mutableListOf(),var duration:Int = 0,var playcount:Int = 0,var thumbnail:MutableList<String> = mutableListOf()):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.createStringArrayList())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(playcount)
        dest?.writeInt(height)
        dest?.writeInt(width)
        dest?.writeStringList(video)
        dest?.writeStringList(download)
        dest?.writeInt(duration)
        dest?.writeInt(playcount)
        dest?.writeStringList(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemVideo> {
        override fun createFromParcel(parcel: Parcel): ItemVideo {
            return ItemVideo(parcel)
        }

        override fun newArray(size: Int): Array<ItemVideo?> {
            return arrayOfNulls(size)
        }
    }

}