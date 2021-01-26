package com.fansan.common.pojo
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 11/25/20
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class PlayMenuBean(
    var playImgList: List<PlayImg> = listOf(),
    var playMenu: List<PlayMenu> = listOf()
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class PlayImg(
        var big_img: String = "",
        var id: String = ""
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class PlayMenu(
        var id: String = "",
        var name: String = ""
    ) : Parcelable
}