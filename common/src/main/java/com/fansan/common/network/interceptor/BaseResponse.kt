package com.fansan.common.network.interceptor

/**
 * 作者　: fansan
 * 时间　: 2019/12/17
 * 描述　: 服务器返回数据的基类
 */
abstract class BaseResponse<T> {

    abstract fun isSucces(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String

}