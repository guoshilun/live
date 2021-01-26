package com.jk.order

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.callback.IntLiveData
import com.fansan.common.ext.get
import com.fansan.common.ext.init
import com.fansan.common.ext.startActivity
import com.github.nukc.stateview.StateView
import com.jk.jkproject.utils.ext.click
import com.jk.jkproject.utils.ext.gone
import com.jk.jkproject.utils.ext.visible
import com.jk.order.databinding.ActivityOrderCancelBinding
import kotlinx.android.synthetic.main.activity_order_cancel.*
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 2020/11/17
 */

@Route(path = "/order/cancel")
class OrderCancelActivity : BaseActivity<OrderViewModel, ActivityOrderCancelBinding>() {


    private val type = IntLiveData()
    private var mOrderId: String = ""
    private var mStateView: StateView? = null

    override fun initView(savedInstanceState: Bundle?) {
        mStateView = StateView.inject(this)
        immersionBar.reset().statusBarView(View.inflate(this, R.layout.status_bar, null))
            .transparentBar().init()
        type.value = intent.get<Int>("type") ?: 0
        mOrderId = intent.get<String>("orderId") ?: ""
        mViewModel.orderId.value = mOrderId

        if (type.value == 0) {
            realPay.setLeftView(this, R.drawable.diamond, 12.dp, 9.dp)
        } else {
            realPay.setLeftView(this, R.drawable.me_diamon_yellow, 12.dp, 9.dp)
        }

        mStateView?.init {
            mViewModel.getOrderInfo()
        }

        back.click {
            finish()
        }

    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_order_cancel).addBindingParam(
            BR.model, mViewModel
        ).addBindingParam(BR.type, type)
    }

    override fun createObserver() {

        mViewModel.orderInfoBean.observeActivity(this) {
            mStateView?.showContent()

            when (it.status) {
                2 -> {
                    cancelLabel.text = "订单已取消"
                }
                5 -> {
                    cancelLabel.text = "用户申请退款"
                    refundBtn.visible()
                    markLayout.gone()
                    refundBtn.click { view ->
                        startActivity<RejectDetailsActivity>(
                            "status" to 5, "oid" to it.orderId
                        )
                    }

                }
                6 -> {
                    cancelLabel.text = "已退款"
                }
            }
        }

        mViewModel.requestStatusEvent.observeActivity(this) {
            if (!it) {
                mStateView?.showRetry()
            }
        }

        mGlobalModel.needFinishEvent.observeActivity(this){
            if (it) finish()
        }

    }

}