package com.jk.order

import android.os.Bundle
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.startActivity
import com.jk.order.databinding.ActivityTakeOrderStep1Binding
import com.jk.order.databinding.ActivityTakeOrderStep4Binding

/**
 *@author  fansan
 *@version 2020/11/18
 */

class TakeOrderStep4Activity : BaseActivity<TakeOrderViewModel, ActivityTakeOrderStep4Binding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mViewModel.infoComplete.postValue(true)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_take_order_step_4).addBindingParam(
            BR.clickProxy, Step4ClcikProxy()
        ).addBindingParam(BR.model,mViewModel)
    }

    override fun createObserver() {

    }

    inner class Step4ClcikProxy() {

        fun back() {
            finish()
        }

        fun next() {
            this@TakeOrderStep4Activity.mGlobalModel.refreshListEvent.postValue(true)
            finish()
        }
    }
}