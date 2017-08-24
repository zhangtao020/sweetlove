package com.terry.see.seelive.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by terry on 2017/8/22.
 */
data class ItemGif(var height:Int = 0, var width:Int=0, var images:MutableList<String> = mutableListOf(),
                   var download_url:MutableList<String> = mutableListOf(), var gif_thumbnail:MutableList<String> = mutableListOf()):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(height)
        dest?.writeInt(width)
        dest?.writeStringList(images)
        dest?.writeStringList(download_url)
        dest?.writeStringList(gif_thumbnail)
    }

    override fun describeContents(): Int {
        return  0
    }

    companion object CREATOR : Parcelable.Creator<ItemGif> {
        override fun createFromParcel(parcel: Parcel): ItemGif {
            return ItemGif(parcel)
        }

        override fun newArray(size: Int): Array<ItemGif?> {
            return arrayOfNulls(size)
        }
    }

}