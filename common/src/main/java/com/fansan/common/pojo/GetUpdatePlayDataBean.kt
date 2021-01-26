package com.fansan.common.pojo
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 11/30/20
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class GetUpdatePlayDataBean(
    var dan_name: String = "",
    var dan_state: Int = 0,
    var examples_img: String = "",
    var id: Int = 0,
    var img: String = "",
    var introduce: String = "",
    var is_top: Int = 0,
    var limit_money: Int = 0,
    var order_count: Int = 0,
    var order_price: Int = 0,
    var play_dan_id: Int = 0,
    var play_id: Int = 0,
    var state: Int = 0,
    var userId: String = "",
    var video: String = "",
    var video_duration:Int = 0,
    var units:String? = "",
    var examples_explain:String? = "",
) : Parcelable