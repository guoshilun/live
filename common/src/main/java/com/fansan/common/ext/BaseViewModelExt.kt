package com.fansan.common.ext

import androidx.lifecycle.viewModelScope
import com.fansan.common.base.BaseViewModel
import com.fansan.common.callback.UnPeekLiveData
import com.fansan.common.network.ResultState
import com.fansan.common.network.interceptor.AppException
import com.fansan.common.network.interceptor.BaseResponse
import com.fansan.common.network.interceptor.ExceptionHandle
import com.fansan.common.network.paresException
import com.fansan.common.network.paresResult
import kotlinx.coroutines.*

/**
 *@author  fansan
 *@version 11/25/20
 */


fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    resultState: UnPeekLiveData<ResultState<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = ""
): Job {
    if (isShowDialog) resultState.value = ResultState.onAppLoading(loadingMessage)
    return viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
            resultState.paresResult(it)
        }.onFailure {
            resultState.paresException(it)
        }
    }
}


fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    resultState: UnPeekLiveData<ResultState<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = ""
): Job {
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) resultState.value = ResultState.onAppLoading(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            resultState.paresResult(it)
        }.onFailure {
            resultState.paresException(it)
        }
    }
}


fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = ""
): Job {
    if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
    return viewModelScope.launch {
        runCatching {
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            if (isShowDialog) loadingChange.dismissDialog.call()
            runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it) { t -> success(t) }
            }.onFailure { e ->
                e.message?.loge("fansangg")
                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            //网络请求异常 关闭弹窗
            if (isShowDialog) loadingChange.dismissDialog.call()
            it.message?.loge("fansangg")
            error(ExceptionHandle.handleException(it))
        }
    }
}


fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = ""
): Job {
    if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
    return viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
            if (isShowDialog) loadingChange.dismissDialog.call()
            success(it)
        }.onFailure {
            if (isShowDialog) loadingChange.dismissDialog.call()
            it.message?.loge("fansangg")
            error(ExceptionHandle.handleException(it))
        }
    }
}


suspend fun <T> executeResponse(
    response: BaseResponse<T>, success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        if (response.isSucces()) success(response.getResponseData())
        else throw AppException(
            response.getResponseCode(), response.getResponseMsg(), response.getResponseMsg()
        )
    }
}


fun <T> BaseViewModel.launch(
    block: () -> T, success: (T) -> Unit, error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            success(it)
        }.onFailure {
            error(it)
        }
    }
}