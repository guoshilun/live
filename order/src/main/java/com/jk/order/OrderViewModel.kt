package com.jk.order

import androidx.lifecycle.MutableLiveData
import com.fansan.common.base.BaseViewModel
import com.fansan.common.callback.BooleanLiveData
import com.fansan.common.callback.IntLiveData
import com.fansan.common.callback.StringLiveData
import com.fansan.common.callback.UnPeekLiveData
import com.fansan.common.ext.request
import com.fansan.common.ext.requestNoCheck
import com.fansan.common.network.apiService
import com.fansan.common.pojo.*
import com.qiniu.android.storage.UploadManager

/**
 *@author  fansan
 *@version 2020/11/11
 */

class OrderViewModel : BaseViewModel() {

    val selectedIndex = IntLiveData()
    val selectedRefundReason = UnPeekLiveData<ReasonBean>()
    val refundMark = MutableLiveData<String>()
    val confirmOrderInfoBean = UnPeekLiveData<ConfirmOrderInfoBean>()
    val requestStatusEvent = BooleanLiveData()
    val orderNum = IntLiveData()
    val totalPrice = IntLiveData()
    val remark = StringLiveData()
    val submitResult = BooleanLiveData()
    val orderId = StringLiveData()
    val orderInfoBean = UnPeekLiveData<OrderInfoBean>()
    val gameListData = UnPeekLiveData<MutableList<GameListBean>>()
    val orderRecordStatus = BooleanLiveData()
    val orderList = UnPeekLiveData<OrderListBean>()
    val authentication = IntLiveData()
    val completeEvent = BooleanLiveData()
    val cancelEvent = BooleanLiveData()
    val receiveOrderStatus = BooleanLiveData()
    val mQiniuTokenBean: UnPeekLiveData<QiNiuEntity> = UnPeekLiveData()

    val mQiniuManager: UploadManager by lazy {
        UploadManager()
    }

    init {
        orderNum.postValue(1)
        selectedIndex.value = 1
    }


    fun confirmOrder(gameId: Int, userId: String) {
        request({ apiService.placeAnOrder(userId, gameId) }, {
            confirmOrderInfoBean.postValue(it)
            requestStatusEvent.postValue(true)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                    requestStatusEvent.postValue(false)
                })
    }

    fun submitOrder(userId: String, gameId: Int) {
        val map = mutableMapOf<String, Any>(
            "receiveUserId" to userId,
            "playId" to gameId,
            "count" to orderNum.value,
            "remark" to remark.value
        )
        request({ apiService.submitOrder(map) }, {
            submitResult.postValue(true)
            orderId.value = it.orderId
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                    submitResult.postValue(false)
                }, true)
    }

    fun getOrderInfo() {
        request({ apiService.getOrderInfo(orderId.value) }, {
            requestStatusEvent.postValue(true)
            orderInfoBean.postValue(it)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                    requestStatusEvent.postValue(false)
                })
    }

    fun remindOrder() {
        requestNoCheck({ apiService.remindOrder(orderId.value) }, {
            loadingChange.toastShow.postValue(it.msg)
        }, {
                           loadingChange.toastShow.postValue(it.errorMsg)
                       }, true)
    }

    fun chooseGame(id: String) {
        request({ apiService.getGameList(id) }, {
            gameListData.postValue(it)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                }, true)
    }

    fun getOrderList() {
        request({ apiService.orderRecord(selectedIndex.value) }, {
            orderList.postValue(it)
            orderRecordStatus.postValue(true)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                    orderRecordStatus.postValue(false)
                })
    }

    fun getAuthentication() {
        request({ apiService.getAuthentication() }, {
            authentication.postValue(it.status)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                })
    }

    fun reqComplete(orderId: String) {
        requestNoCheck({
                           apiService.reqComplete(orderId)

                       }, {
                           if (it.code == 200) {
                               completeEvent.postValue(true)
                           } else {
                               loadingChange.toastShow.postValue(it.msg)
                               completeEvent.postValue(false)
                           }
                       }, {
                           completeEvent.postValue(false)
                           loadingChange.toastShow.postValue(it.errorMsg)
                       }, true)
    }

    fun cancelOrder(cause: Int, type: Int, orderId: String) {
        requestNoCheck({ apiService.cancelOrder(cause, type, orderId) }, {
            if (it.code == 200) {
                cancelEvent.postValue(true)
            } else {
                cancelEvent.postValue(false)
                loadingChange.toastShow.postValue(it.msg)
            }
        }, {
                           loadingChange.toastShow.postValue(it.errorMsg)
                           cancelEvent.postValue(false)
                       }, true)
    }

    fun receiveOrder(orderId: String) {
        requestNoCheck({ apiService.receiveOrder(orderId) }, {
            if (it.code == 200) {
                receiveOrderStatus.postValue(true)
            } else {
                receiveOrderStatus.postValue(false)
            }
            loadingChange.toastShow.postValue(it.msg)
        }, {
                           loadingChange.toastShow.postValue(it.errorMsg)
                           receiveOrderStatus.postValue(false)
                       }, true)
    }

    fun recComplete(orderId: String) {
        requestNoCheck({
                           apiService.recComplete(orderId)
                       }, {
                           if (it.code == 200) {
                               completeEvent.postValue(true)
                           } else {
                               completeEvent.postValue(false)
                           }
                           loadingChange.toastShow.postValue(it.msg)
                       }, {
                           completeEvent.postValue(false)
                           loadingChange.toastShow.postValue(it.errorMsg)
                       }, true)
    }

    fun submitRefund() {
        requestNoCheck({
                           apiService.submitRefund(
                               orderInfoBean.value?.orderId ?: "",
                               selectedRefundReason.value?.id ?: 0,
                               refundMark.value ?: ""
                           )
                       }, {
                           if (it.code == 200) {
                               completeEvent.postValue(true)
                           } else {
                               completeEvent.postValue(false)
                               loadingChange.toastShow.postValue(it.msg)
                           }
                       }, {
                           completeEvent.postValue(true)
                           loadingChange.toastShow.postValue(it.errorMsg)

                       }, true)
    }

    fun processRefund(oid: String, type: Int) {
        requestNoCheck({
                           apiService.processRefund(
                               oid, type, selectedRefundReason.value?.id ?: 0, refundMark.value?:""
                           )
                       }, {
                           if (it.code == 200) {
                               completeEvent.postValue(true)
                           } else {
                               completeEvent.postValue(false)
                           }
                           loadingChange.toastShow.postValue(it.msg)
                       }, {
                           completeEvent.postValue(false)
                           loadingChange.toastShow.postValue(it.errorMsg)
                       }, true)
    }

    /**
     * 获取七牛token
     */
    fun getQiNiuToken() {
        request({ apiService.getQiNiuToken() }, {
            mQiniuTokenBean.postValue(it)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                }, true)
    }

    fun submitAppeal(type: Int, urls: String, oid: String) {
        requestNoCheck({ apiService.submitAppeal(type, refundMark.value ?: "", urls, oid) }, {
            if (it.code == 200) {
                completeEvent.postValue(true)
            } else {
                completeEvent.postValue(false)
            }
            loadingChange.toastShow.postValue(it.msg)
        }, {
                           loadingChange.toastShow.postValue(it.errorMsg)
                           completeEvent.postValue(false)
                       }, true)
    }

    fun appealInfo(oid:String){
        request({
                apiService.appealInfo(oid)
                },{
                  orderInfoBean.postValue(it)
            requestStatusEvent.postValue(true)
        },{
            loadingChange.toastShow.postValue(it.errorMsg)
            requestStatusEvent.postValue(false)
        })
    }
}