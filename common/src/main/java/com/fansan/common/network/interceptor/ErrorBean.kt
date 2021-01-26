package com.fansan.common.network.interceptor
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 2020/6/24
 */
 
@SuppressLint("ParcelCreator")
@Parcelize
data class ErrorBean(
    var code: Int = 0,
    var error: String = ""
) : Parcelable