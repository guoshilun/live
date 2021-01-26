package com.fansan.common.pojo
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 12/1/20
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class SetPriceBean(
    var id: Int = 0,
    var limit_money: Int = 0,
    var max_value: Int = 0,
    var min_value: Int = 0
) : Parcelable