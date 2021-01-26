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
data class AllGameBean(
    var clientGame: MutableList<Game> = mutableListOf(),
    var elseGame: MutableList<Game> = mutableListOf(),
    var mobileGame: MutableList<Game> = mutableListOf(),
    var message:MutableList<Reason> = mutableListOf()
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Game(
        var icon: String = "", var id: Int = 0, var name: String = "",var state:Int = 0,var type:Int = 0
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Reason(
        var title: String = "", var content: String = ""
    ) : Parcelable
}