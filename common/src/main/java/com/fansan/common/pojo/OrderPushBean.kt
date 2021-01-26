package com.fansan.common.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author  fansan
 *@version 12/28/20
 */

@Parcelize
class OrderPushBean(val userId:String,val gameName:String,val orderId:String,val count:Int,val unit:String,val amount:String,val msg:String,val status:Int,val pushType:Int,val cancelType:Int,val nickname:String):Parcelable