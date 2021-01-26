package com.jk.order

import com.fansan.common.base.BaseViewModel
import com.fansan.common.callback.BooleanLiveData
import com.fansan.common.callback.IntLiveData
import com.fansan.common.callback.UnPeekLiveData
import com.fansan.common.ext.request
import com.fansan.common.network.ApiService
import com.fansan.common.network.apiService
import com.fansan.common.network.request.PlayUserListRequest
import com.fansan.common.pojo.AllGameBean
import com.fansan.common.pojo.PlayMenuBean
import com.fansan.common.pojo.PlayUserListBean

/**
 *@author  fansan
 *@version 11/24/20
 */

class PlayWithUViewModel : BaseViewModel() {

    val titleData = UnPeekLiveData<PlayMenuBean>()
    var listPage = 0
    val mListRefreshing = BooleanLiveData()
    val sortType = IntLiveData()
    val genderType = IntLiveData()
    val playUserListData = UnPeekLiveData<MutableList<PlayUserListBean>>()
    val playUserListLoadFiled = BooleanLiveData()
    val allGameData = UnPeekLiveData<AllGameBean>()
    val errorStatus = BooleanLiveData()

    fun getMenu() {
        request({ apiService.getPlayMenuList() }, {
            titleData.postValue(it)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                })
    }

    fun getList(id: String) {
        if (mListRefreshing.value) {
            listPage = 0
        }
        val request = PlayUserListRequest(id, sortType.value, genderType.value, listPage)
        request({ apiService.getPlayUserList(request) }, {
            playUserListData.postValue(it)
            listPage++
            mListRefreshing.postValue(false)
        }, {
                    loadingChange.toastShow.postValue(it.errorMsg)
                    mListRefreshing.postValue(false)
                    playUserListLoadFiled.postValue(true)
                })
    }

    fun getAllGame() {
        request({ apiService.getAllGame() }, {
            allGameData.postValue(it)
        }, {
            errorStatus.postValue(true)
            loadingChange.toastShow.postValue(it.errorMsg)
                })
    }
}