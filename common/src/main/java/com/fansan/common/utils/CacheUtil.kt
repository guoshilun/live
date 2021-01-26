package com.fansan.common.utils

import com.fansan.common.Constants
import com.fansan.common.ext.get
import com.fansan.common.ext.saveKV


/**
 *@author  fansan
 *@version 2020/10/20
 */

object CacheUtil {

    @JvmStatic
    fun saveToken(token:String){
        token.saveKV(Constants.USER_TOKEN)
    }

    fun getToken():String? = get().getString(Constants.USER_TOKEN,"")

    @JvmStatic
    fun saveUserId(id:String){
        id.saveKV(Constants.USER_ID)
    }


    fun getUserId():String? = get().getString(Constants.USER_ID,"")
}