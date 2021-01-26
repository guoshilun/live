package com.fansan.common.network.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author  fansan
 *@version 11/26/20
 */

@Parcelize
data class PlayUserListRequest(val play_id:String,val sort:Int,val sex:Int,val currentPage:Int,val currentSize:Int = 10):Parcelable {}