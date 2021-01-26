package com.jk.lib_egg.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author  fansan
 *@version 2020/11/2
 */

@Parcelize
data class GameListBean(val code:Int,val msg:String,val data:MutableList<DataBean>):Parcelable{

    @Parcelize
    data class DataBean(val name:String,val icon:String):Parcelable
}