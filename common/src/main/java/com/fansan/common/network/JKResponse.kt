package com.fansan.common.network

import com.fansan.common.network.interceptor.BaseResponse

/**
 *@author  fansan
 *@version 2020/10/20
 */

class JKResponse<T>(private val msg: String, private val code: Int, private val data: T) :
    BaseResponse<T>() {

    override fun isSucces(): Boolean = code == 200

    override fun getResponseData(): T = data

    override fun getResponseCode(): Int = code

    override fun getResponseMsg(): String = msg
}