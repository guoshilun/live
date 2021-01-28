package com.jk.order

import com.fansan.common.base.BaseViewModel
import com.fansan.common.callback.BooleanLiveData
import com.fansan.common.callback.UnPeekLiveData
import com.fansan.common.ext.request
import com.fansan.common.network.apiService
import com.fansan.common.pojo.OrderLuckBean

class OrderLuckViewModel:BaseViewModel() {
    val orderLuckBean:UnPeekLiveData<OrderLuckBean> = UnPeekLiveData()
    val requestStatusEvent = BooleanLiveData()
    fun getOrderLuckDetail(){
        request({ apiService.luckOrder(hashMapOf())},{
            orderLuckBean.postValue(it)
            requestStatusEvent.postValue(true)
        },{
            loadingChange.toastShow.postValue(it.errorMsg)
            requestStatusEvent.postValue(false)
        },true)
    }
}