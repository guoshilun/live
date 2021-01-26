package com.fansan.common.base

import android.util.SparseArray
import androidx.lifecycle.ViewModel


/**
 *@author  fansan
 *@version 2020/10/16
 */

class DataBindingConfig(private val layout: Int) {

    private val bindingParams: SparseArray<Any> = SparseArray<Any>()

    fun getLayout(): Int {
        return layout
    }

    fun getBindingParams(): SparseArray<*> {
        return bindingParams
    }

    fun addBindingParam(variableId: Int, `object`: Any): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, `object`)
        }
        return this
    }

}