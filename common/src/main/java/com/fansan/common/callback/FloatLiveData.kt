package com.fansan.common.callback

import androidx.lifecycle.MutableLiveData


/**
 * 作者　: fansan
 * 时间　: 2019/12/17
 * 描述　:自定义的Float类型 UnPeekNotNullLiveData  做了防止数据倒灌处理 提供了默认值，避免取值的时候还要判空
 */
class FloatLiveData: UnPeekLiveData<Float>() {
    override fun getValue(): Float {
        return super.getValue()?:0f
    }
}