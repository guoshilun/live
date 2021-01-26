package com.fansan.common.base

import androidx.lifecycle.ViewModel
import com.fansan.common.callback.StringLiveData
import com.fansan.common.callback.UnPeekLiveData

/**
 * 作者　: fansan
 * 时间　: 2019/12/12
 * 描述　: ViewModel的基类
 */
open class BaseViewModel : ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { StringLiveData() }
        //隐藏
        val dismissDialog by lazy { UnPeekLiveData.Builder<String>().setAllowNullValue(true).create() }

        val toastShow by lazy { StringLiveData() }
    }

}