package com.fansan.common.callback

import androidx.lifecycle.MutableLiveData


/**
 * 作者　: fansan
 * 时间　: 2019/12/17
 * 描述　:自定义的Boolean类型 MutableLiveData
 */
class IntLiveData : UnPeekLiveData<Int>() {

    override fun getValue(): Int {
        return super.getValue() ?: 0
    }
}