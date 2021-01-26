package com.fansan.common.pojo

import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 12/4/20
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class OrderInfoBean(
    var c_time: Long = 0,
    var cancelType: Int = 0,
    var cause: String? = "",
    var count: Int = 1,
    var gameName: String = "",
    var nickname: String = "",
    var orderId: String = "",
    var picture: String = "",
    var playId: String = "",
    var price: Int = 0,
    var remark: String = "",
    var status: Int = 1,
    var sumPrice: Int = 0,
    var tagId: String = "",
    var units: String = "",
    var r_time: Long = 0,
    var earnings: Int = 0,
    var handlingFee: Int = 0,
    var appealImages: String? = "",
    var certificate: Int = 0,
    var recOK: Int = 0,
    var description:String? = "",
    var appealCause:String? = "",
    var appealDescription:String? = "",
) : Parcelable