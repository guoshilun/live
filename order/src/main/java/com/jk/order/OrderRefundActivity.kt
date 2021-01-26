package com.jk.order

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.blankj.utilcode.util.ActivityUtils
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.get
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.OrderInfoBean
import com.fansan.common.pojo.ReasonBean
import com.jk.order.databinding.ActivityRefundBinding

/**
 *@author  fansan
 *@version 2020/11/17
 */

class OrderRefundActivity : BaseActivity<OrderViewModel, ActivityRefundBinding>() {


    private val userReasonList = mutableListOf(
        ReasonBean(1, "不想打了"),
        ReasonBean(2, "协商一致退款"),
        ReasonBean(3, "大神没时间"),
        ReasonBean(4, "大神水平太差"),
        ReasonBean(5, "大神为开打"),
        ReasonBean(6, "实际服务这与接单大神不服")
    )

    override fun initView(savedInstanceState: Bundle?) {
        val bean = intent.get<OrderInfoBean>("data")
        mViewModel.orderInfoBean.postValue(bean)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_refund).addBindingParam(BR.model, mViewModel)
            .addBindingParam(BR.clickProxy, RefundClickProxy())
    }

    override fun createObserver() {
        mViewModel.completeEvent.observeActivity(this){
            if (it){
                startActivity<RejectDetailsActivity>("status" to 1,"oid" to intent.get<String>("oid"))
                mGlobalModel.refreshListEvent.postValue(true)
                mGlobalModel.needFinishEvent.postValue(true)
                finish()
            }
        }
    }

    inner class RefundClickProxy {

        fun reasonClick() {
            val popup = ReasonPopup<ReasonBean>(this@OrderRefundActivity)
            popup.setWheelData(userReasonList)
            popup.mConfirmClick = {
                mViewModel.selectedRefundReason.postValue(it)
            }
            popup.showPopupWindow()
        }

        fun commit() {
            if (mViewModel.selectedRefundReason.value == null) {
                showToast("请选择原因")
            } else {
                mViewModel.submitRefund()
            }
        }
    }

}