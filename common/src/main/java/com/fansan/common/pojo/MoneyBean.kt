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
data class MoneyBean(
    var amount1: Int = 0,
    var amount2: Int = 0,
    var amount3: Int = 0
) : Parcelable