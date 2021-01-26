package com.jk.lib_egg.pojo
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 2020/11/2
 */
 
 @SuppressLint("ParcelCreator")
@Parcelize
data class GameResultBean(
    var code: Int = 0,
    var `data`: List<EggBean.Data.GoldenMalletAward> = listOf(),
    var msg: String = ""
) : Parcelable