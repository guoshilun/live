package com.jk.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fansan.common.base.BaseViewModel
import com.fansan.common.callback.*
import com.fansan.common.ext.request
import com.fansan.common.ext.requestNoCheck
import com.fansan.common.network.apiService
import com.fansan.common.network.request.CertifyRequest
import com.fansan.common.pojo.*
import com.qiniu.android.storage.UploadManager

/**
 *@author  fansan
 *@version 2020/11/18
 */

class TakeOrderViewModel : BaseViewModel() {

    val levelSelected = UnPeekLiveData<DanBean>()
    val gameName = StringLiveData()
    val gameImg = StringLiveData()
    val recorderPath = StringLiveData()
    val recordUrl = StringLiveData()
    val recordDuration = IntLiveData()
    val recordDone = BooleanLiveData()
    val infoComplete = BooleanLiveData()
    val setPriceBean = UnPeekLiveData<SetPriceBean>()
    val orderGameData = UnPeekLiveData<AllGameBean>()
    val errorState = BooleanLiveData()
    val danListData = UnPeekLiveData<MutableList<DanBean>>()
    val updatePlayData = UnPeekLiveData<GetUpdatePlayDataBean>()
    val mQiniuTokenBean: UnPeekLiveData<QiNiuEntity> = UnPeekLiveData()
    val step2EditValue = MutableLiveData<String>("")
    val headImgUrl = StringLiveData()
    val getPriceStatus = BooleanLiveData()
    val priceDataArray = UnPeekLiveData<MutableList<SetPriceBean>>()
    val powerModifyPic = StringLiveData()
    val powerModifyLevel = UnPeekLiveData<DanBean>()
    val updateCertifyStatus = BooleanLiveData()
    val priceData = UnPeekLiveData<MutableList<PriceBean>>()
    val selectedPrice = UnPeekLiveData<PriceBean>()

    init {
        updatePlayData.postValue(GetUpdatePlayDataBean())
    }

    val certifyStatus = BooleanLiveData()

    val mQiniuManager: UploadManager by lazy {
        UploadManager()
    }

    fun getTakeOrderGame() {
        request({ apiService.getTakeOrderGame() }, {
            orderGameData.postValue(it)
        }, {
                    errorState.postValue(false)
                    loadingChange.toastShow.postValue(it.errorMsg)
                })
    }

    fun getPlayDanList(id: Int?) {
        request({ apiService.getPlayDanList(id) }, {
            danListData.postValue(it)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                }, true)
    }

    fun getUpdatePlayData(id: Int?) {
        request({ apiService.GetUpdatePlayData(id) }, {
            updatePlayData.postValue(it)
        }, {
                    errorState.postValue(false)
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

    fun addCertify(id: Int?) {
        val body = CertifyRequest(
            id,
            levelSelected.value?.id ?: 0,
            gameImg.value,
            recordUrl.value,
            recordDuration.value,
            step2EditValue.value ?: "",
            headImgUrl.value
        )
        requestNoCheck({ apiService.addPlayCertify(body) }, {
            if (it.code == 200) {
                certifyStatus.postValue(true)
            } else {
                certifyStatus.postValue(false)
            }
            loadingChange.toastShow.postValue(it.msg)
        }, {
                           loadingChange.toastShow.postValue(it.errorMsg)
                           certifyStatus.postValue(false)
                       }, true)
    }

    fun getPriceData(type: Int) {
        request({ apiService.getPriceList(type) }, {
            priceDataArray.postValue(it)
            getPriceStatus.postValue(true)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                    getPriceStatus.postValue(false)
                })
    }

    fun updateCertify(map: MutableMap<String, Any>) {
        requestNoCheck({ apiService.updatePlayCertify(map) }, {
            if (it.code == 200) {
                updateCertifyStatus.postValue(true)
            } else {
                loadingChange.toastShow.postValue(it.msg)
            }
        }, {
                           updateCertifyStatus.postValue(false)
                           loadingChange.toastShow.postValue(it.errorMsg)
                       }, true)
    }

    fun getPlayOrderPriceAllList(type: Int) {
        request({ apiService.getPlayOrderPriceAllList(type) }, {
            priceData.postValue(it)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                }, true)
    }
}
