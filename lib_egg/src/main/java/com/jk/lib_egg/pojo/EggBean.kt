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
data class EggBean(
    var code: Int = 0,
    var `data`: Data = Data(),
    var msg: String = ""
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        var goldenMallet: String = "",
        var goldenMalletAwardList: List<GoldenMalletAward> = listOf(),
        var hammerMallet: String = "",
        var hammerMalletAwardList: List<GoldenMalletAward> = listOf()
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class GoldenMalletAward(
            var comment: String = "",
            var g_amount: Int = 0,
            var g_icon: String = "",
            var g_name: String = "",
            var probability1: Double = 0.0,
            var props_type: Int = 0,
            var type: Int = 0,
            var count: Int = 0,
            var icon: String = "",
            var id: Int = 0,
            var p_count: Int = 0,
            var p_effective_time: Int = 0,
            var p_type: Int = 0,
            var propsId: Int = 0,
            var propsType: Int = 0,
            var props_id: Int = 0
        ) : Parcelable
    }
}