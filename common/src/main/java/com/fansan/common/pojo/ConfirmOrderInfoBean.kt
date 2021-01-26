package com.fansan.common.pojo
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 12/3/20
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ConfirmOrderInfoBean(
    var anchorGrade: String = "",
    var dan_name: String = "",
    var name: String = "",
    var nickname: String = "",
    var picture: String = "",
    var playId: Int = 0,
    var price: Int = 0,
    var sex: Int = 0,
    var type: Int = 0,
    var units: String = "",
    var userGrade: String = ""
) : Parcelable