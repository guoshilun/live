package com.fansan.common.callback

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer


/**
 *@author  fansan
 *@version 2020/10/16
 */

open class UnPeekLiveData<T> : ProtectedUnPeekLiveData<T>() {

    public override fun setValue(value: T?) {
        super.setValue(value)
    }

    public override fun postValue(value: T?) {
        super.postValue(value)
    }

    public fun call(){
        super.postValue(null)
    }

    @Deprecated("", ReplaceWith("observeActivity/observeFragment"))
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    class Builder<T> {
        /**
         * 是否允许传入 null value
         */
        private var isAllowNullValue = false
        fun setAllowNullValue(allowNullValue: Boolean): Builder<T> {
            isAllowNullValue = allowNullValue
            return this
        }

        fun create(): UnPeekLiveData<T> {
            val liveData: UnPeekLiveData<T> = UnPeekLiveData()
            liveData.isAllowNullValue = isAllowNullValue
            return liveData
        }
    }

}