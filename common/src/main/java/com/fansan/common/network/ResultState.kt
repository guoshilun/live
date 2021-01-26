package com.fansan.common.network
import com.fansan.common.callback.UnPeekLiveData
import com.fansan.common.network.interceptor.AppException
import com.fansan.common.network.interceptor.BaseResponse
import com.fansan.common.network.interceptor.ExceptionHandle

/**
 * 作者　: fansan
 * 时间　: 2020/4/9
 * 描述　: 自定义结果集封装类
 */
sealed class ResultState<out T> {
    companion object {
        fun <T> onAppSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onAppLoading(loadingMessage:String): ResultState<T> = Loading(loadingMessage)
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)
    }

    data class Loading(val loadingMessage:String) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: AppException) : ResultState<Nothing>()
}


/**
 * 处理返回值
 * @param result 请求结果
 */
fun <T> UnPeekLiveData<ResultState<T>>.paresResult(result: BaseResponse<T>) {
    setValue(if (result.isSucces()) ResultState.onAppSuccess(result.getResponseData()) else
                 ResultState.onAppError(AppException(result.getResponseCode(), result.getResponseMsg())))
}

/**
 * 不处理返回值 直接返回请求结果
 * @param result 请求结果
 */
fun <T> UnPeekLiveData<ResultState<T>>.paresResult(result: T) {
    setValue(ResultState.onAppSuccess(result))
}

/**
 * 异常转换异常处理
 */
fun <T> UnPeekLiveData<ResultState<T>>.paresException(e: Throwable) {
    setValue(ResultState.onAppError(ExceptionHandle.handleException(e)))
}

