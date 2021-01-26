package com.jk.weblib
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 2020/11/5
 */
 
@SuppressLint("ParcelCreator")
@Parcelize
data class EsportBean(
    var code: Int = 0,
    var `data`: Data = Data(),
    var msg: String = ""
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        var forecast_url: String = "",
        var list_url: String = "",
        var token: String = ""
    ) : Parcelable
}