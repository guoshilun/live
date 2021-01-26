package com.fansan.common.callback

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStore


/**
 *@author  fansan
 *@version 2020/10/16
 * 1.一条消息能被多个观察者消费
 * 2.消息被所有观察者消费完毕后才开始阻止倒灌
 * 3.可以通过 clear 方法手动将消息从内存中移除
 */

open class ProtectedUnPeekLiveData<T>:LiveData<T>(){
    protected var isAllowNullValue = false
    private val observers: HashMap<ViewModelStore, Boolean> = HashMap()
    private val stores: HashMap<Observer<in T>, ViewModelStore> = HashMap()

    fun observeActivity(
        activity: AppCompatActivity?, observer: Observer<in T>
    ) {
        var store: ViewModelStore? = null
        var owner: LifecycleOwner? = null

        activity?.let {
            owner = it
            store = it.viewModelStore
        }

        observe(store, owner, observer)
    }

    fun observeFragment(fragment: Fragment?, observer: Observer<in T>) {
        var owner: LifecycleOwner? = null
        var store: ViewModelStore? = null
        fragment?.let {
            owner = it.viewLifecycleOwner
            store = it.viewModelStore
        }

        observe(store, owner, observer)
    }

    private fun observe(
        store: ViewModelStore?, owner: LifecycleOwner?, observer: Observer<in T>
    ) {
        if (store != null && observers[store] == null) {
            observers[store] = true
            stores[observer] = store
        }

        super.observe(owner!!, Observer { t ->
            if (store != null) {
                if (observers[store] == false) {
                    observers[store] = true
                    if (t != null || isAllowNullValue) {
                        observer.onChanged(t)
                    }
                }
            }
        })
    }

    override fun removeObserver(observer: Observer<in T>) {
        if (observer == null) {
            return
        }
        val store = stores[observer]
        if (store != null) {
            for ((key) in observers) {
                if (store == key) {
                    observers.remove(key)
                    stores.remove(observer)
                    break
                }
            }
        }
        super.removeObserver(observer)
    }

    /**
     * 重写的 setValue 方法，默认不接收 null
     * 可通过 Builder 配置允许接收
     * 可通过 Builder 配置消息延时清理的时间
     *
     *
     * override setValue, do not receive null by default
     * You can configure to allow receiving through Builder
     * And also, You can configure the delay time of message clearing through Builder
     *
     * @param value
     */
    override fun setValue(value: T?) {
        if (value != null || isAllowNullValue) {
            for (entry in observers.entries) {
                entry.setValue(false)
            }
            super.setValue(value)
        }
    }

    protected fun clear() {
        super.setValue(null)
    }
}