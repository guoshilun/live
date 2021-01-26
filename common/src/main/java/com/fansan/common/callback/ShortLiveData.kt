package com.fansan.common.callback

import androidx.lifecycle.MutableLiveData


/**
 * 作者　: fansan
 * 时间　: 2019/12/17
 * 描述　:自定义的Short类型 MutableLiveData
 */
class ShortLiveData : UnPeekLiveData<Short>() {
    override fun getValue(): Short {
        return super.getValue() ?: 0
    }
}