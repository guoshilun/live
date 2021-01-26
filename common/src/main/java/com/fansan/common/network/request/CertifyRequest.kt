package com.fansan.common.network.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author  fansan
 *@version 12/1/20
 */

@Parcelize
data class CertifyRequest(val play_id:Int?,val play_dan_id:Int,val img:String,val video:String,val video_duration:Int,val introduce:String,val picture:String):Parcelable {}