package com.fansan.common.network


/**
 *@author  fansan
 *@version 2020/10/20
 */
 
class HttpRequestManger {

    companion object{
        val instance: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpRequestManger()
        }
    }
}