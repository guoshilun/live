package com.fansan.common.pojo
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 11/26/20
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class PlayUserListBean(
    var count: Int = 0,
    var dan_name: String = "",
    var introduce: String = "",
    var name: String = "",
    var nickname: String = "",
    var order_price: Int = 0,
    var picture: String = "",
    var play_id: Int = 0,
    var sex: Int = 0,
    var units: String = "",
    var userId: String = "",
    var video: String = "",
    var video_duration: String = "",
    var events_one_buy:Int = 0 //是否开通一元购 0.未开通 1.开通
) : Parcelable

