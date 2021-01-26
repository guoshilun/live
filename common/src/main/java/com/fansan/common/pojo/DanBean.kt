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
data class DanBean(
    var dan_name: String = "",
    var id: Int = 0
) : Parcelable