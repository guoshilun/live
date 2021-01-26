package com.fansan.common

import com.fansan.common.base.BaseViewModel
import com.fansan.common.callback.BooleanLiveData
import com.fansan.common.callback.UnPeekLiveData
import com.fansan.common.ext.request
import com.fansan.common.network.apiService
import com.fansan.common.pojo.MoneyBean

/**
 *@author  fansan
 *@version 12/1/20
 */

class GlobalAppViewModel:BaseViewModel() {

    val refreshListEvent = BooleanLiveData()
    val moneyData = UnPeekLiveData<MoneyBean>()
    val needFinishEvent = BooleanLiveData()

    fun getMoney(){
        request({ apiService.getMoney()}, {
            moneyData.postValue(it)
        }, {
            loadingChange.toastShow.postValue(it.errorMsg)
        })
    }
}