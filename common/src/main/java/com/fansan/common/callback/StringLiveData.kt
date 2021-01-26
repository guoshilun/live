package com.fansan.common.callback

import androidx.lifecycle.MutableLiveData


/**
 * 作者　: fansan
 * 时间　: 2019/12/17
 * 描述　:自定义的String类型 MutableLiveData
 */
class StringLiveData : UnPeekLiveData<String>() {

    override fun getValue(): String {
        return super.getValue() ?: ""
    }

}