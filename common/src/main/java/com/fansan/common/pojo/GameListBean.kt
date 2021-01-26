package com.fansan.common.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author  fansan
 *@version 12/5/20
 */

@Parcelize
class GameListBean(val play_id:Int = 0,val name:String = "",val order_price:Int = 0,val dan_name:String = ""):Parcelable {}