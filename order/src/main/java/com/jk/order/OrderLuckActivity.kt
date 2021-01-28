package com.jk.order

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.init
import com.fansan.common.pojo.Order
import com.github.nukc.stateview.StateView
import com.jk.order.databinding.ActivityOrderLuckBinding
import kotlinx.android.synthetic.main.activity_order_luck.*

@Route(path = "/order/luck")
class OrderLuckActivity : BaseActivity<OrderLuckViewModel,ActivityOrderLuckBinding> (){
    private lateinit var mStateView: StateView
    private lateinit var mAdapter:OrderLuckAdapter
    override fun initView(savedInstanceState: Bundle?) {
        mStateView = StateView.inject(container)
        mStateView.init {
            mViewModel.getOrderLuckDetail()
        }
        mAdapter = OrderLuckAdapter()
        rv_order_mine.adapter = mAdapter
        mViewModel.getOrderLuckDetail()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_order_luck).addBindingParam(BR.bean,mViewModel.orderLuckBean)
    }

    override fun createObserver() {
        mViewModel.requestStatusEvent.observeActivity(this) {
            if (it) {
                mStateView.showContent()
            } else {
                mStateView.showRetry()
            }
        }
        mViewModel.orderLuckBean.observeActivity(this){
            it.orders?.let { orders ->
                mAdapter.setNewInstance(orders as MutableList<Order>)
            }
        }

    }
}