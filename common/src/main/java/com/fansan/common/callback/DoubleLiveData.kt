package com.fansan.common.callback

import androidx.lifecycle.MutableLiveData


/**
 * 作者　: fansan
 * 时间　: 2019/12/17
 * 描述　:自定义的Double类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class DoubleLiveData: UnPeekLiveData<Double>() {
    override fun getValue(): Double {
        return super.getValue() ?: 0.0
    }
}