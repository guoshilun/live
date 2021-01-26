package com.fansan.common.pojo
import android.annotation.SuppressLint
import android.os.Parcelable
import com.newer.library.adapter.base.entity.SectionEntity

import kotlinx.android.parcel.Parcelize


/**
 *@author  fansan
 *@version 12/5/20
 */

@SuppressLint("ParcelCreator")
@Parcelize
class OrderListBean(
    var currentResult: MutableList<Result> = mutableListOf(),
    var historyResult: MutableList<Result> = mutableListOf()
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Result(
        var headName:String = "",
        var c_time: Long = 0,
        var count: Int = 0,
        var name: String = "",
        var nickname: String = "",
        var orderId: String = "",
        var picture: String = "",
        var playId: Int = 0,
        var status: Int = 0,
        var sumPrice: Int = 0,
        var units: String = "",
        var userId: String = "",
        override val isHeader: Boolean
    ) : SectionEntity,Parcelable
}