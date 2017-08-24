package com.terry.see.seelive.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by terry on 2017/8/22.
 */
data class ItemUser(var header:MutableList<String> = mutableListOf(),var uid:String="",var is_vip:Boolean=false,var sex:String="m",
                    var name:String = ""):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeStringList(header)
        dest?.writeString(uid)
        dest?.writeByte(if (is_vip) 1.toByte() else 0.toByte())
        dest?.writeString(sex)
        dest?.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemUser> {
        override fun createFromParcel(parcel: Parcel): ItemUser {
            return ItemUser(parcel)
        }

        override fun newArray(size: Int): Array<ItemUser?> {
            return arrayOfNulls(size)
        }
    }

}