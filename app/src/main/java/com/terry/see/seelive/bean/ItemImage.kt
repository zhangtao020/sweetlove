package com.terry.see.seelive.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by terry on 2017/8/22.
 */
data class ItemImage(var height:Int = 0, var width:Int=0, var big:MutableList<String> = mutableListOf(),
                     var download_url:MutableList<String> = mutableListOf(), var thumbnail_small:MutableList<String> = mutableListOf()):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(height)
        parcel.writeInt(width)
        parcel.writeStringList(big)
        parcel.writeStringList(download_url)
        parcel.writeStringList(thumbnail_small)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemImage> {
        override fun createFromParcel(parcel: Parcel): ItemImage {
            return ItemImage(parcel)
        }

        override fun newArray(size: Int): Array<ItemImage?> {
            return arrayOfNulls(size)
        }
    }

}