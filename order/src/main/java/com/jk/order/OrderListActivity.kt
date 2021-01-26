package com.jk.order

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.childNoDoubleClickListener
import com.fansan.common.ext.init
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.OrderListBean
import com.fansan.common.pojo.ReasonBean
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.github.nukc.stateview.StateView
import com.jk.order.databinding.ActivityOrderlistBinding
import kotlinx.android.synthetic.main.activity_orderlist.*
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 2020/11/11
 */

class OrderListActivity : BaseActivity<OrderViewModel, ActivityOrderlistBinding>() {

    private val mAdapter: OrderListAdapter by lazy {
        OrderListAdapter()
    }

    private val userReasonList = mutableListOf(
        ReasonBean(1, "不想下单了"),
        ReasonBean(2, "我临时有事"),
        ReasonBean(3, "信息填错"),
        ReasonBean(4, "大神未响应"),
        ReasonBean(5, "大神无法提供服务")
    )
    private val playerReasonList = mutableListOf(
        ReasonBean(7, "当前有事，无法服务"),
        ReasonBean(8, "订单服务时间与其他时间冲突"),
        ReasonBean(9, "老板要求无法满足"),
        ReasonBean(10, "其他")
    )

    private var mStateView: StateView? = null


    override fun initView(savedInstanceState: Bundle?) {

        mStateView = StateView.inject(container)
        mStateView?.init {
            mViewModel.getOrderList()
        }
        mViewModel.getAuthentication()
        dividerBuilder().asSpace().size(10.dp).build().addTo(recyclerview)

        recyclerview.adapter = mAdapter

        mAdapter.setOnItemClickListener { _, _, position ->
            if (mAdapter.data[position].isHeader) {
                return@setOnItemClickListener
            }

            when (mAdapter.data[position].status) {
                1, 3, 4 -> {
                    startActivity<OrderDetailsActivity>(
                        "orderId" to mAdapter.data[position].orderId,
                        "type" to mViewModel.selectedIndex.value - 1
                    )
                }

                2 -> {
                    startActivity<OrderCancelActivity>(
                        "type" to mViewModel.selectedIndex.value - 1,
                        "orderId" to mAdapter.data[position].orderId
                    )
                }

                5 -> {
                    if (mViewModel.selectedIndex.value == 1) {
                        startActivity<RejectDetailsActivity>(
                            "status" to 1, "oid" to mAdapter.data[position].orderId
                        )
                    } else {
                        startActivity<OrderCancelActivity>(
                            "type" to mViewModel.selectedIndex.value - 1,
                            "orderId" to mAdapter.data[position].orderId
                        )
                    }
                }

                6 -> {
                    startActivity<OrderCancelActivity>(
                        "type" to mViewModel.selectedIndex.value - 1,
                        "orderId" to mAdapter.data[position].orderId
                    )
                }

                7 -> {
                    val status = if (mViewModel.selectedIndex.value == 1) {
                        2
                    } else {
                        3
                    }
                    startActivity<RejectDetailsActivity>(
                        "status" to status, "oid" to mAdapter.data[position].orderId
                    )
                }

                8 -> {
                    val status = if (mViewModel.selectedIndex.value == 1) {
                        6
                    } else {
                        4
                    }
                    startActivity<RejectDetailsActivity>(
                        "status" to status, "oid" to mAdapter.data[position].orderId
                    )
                }

                9 -> {
                    startActivity<RejectDetailsActivity>(
                        "status" to 6, "oid" to mAdapter.data[position].orderId
                    )
                }
            }
        }

        mAdapter.childNoDoubleClickListener { _, view, position ->
            when (view.id) {
                R.id.take -> {
                    when (mAdapter.data[position].status) {
                        1 -> {
                            if (mAdapter.type == 1) {
                                val p = ReasonPopup<ReasonBean>(this)
                                p.setWheelData(userReasonList)
                                p.mConfirmClick = { reason ->
                                    mViewModel.cancelOrder(
                                        reason?.id ?: 0,
                                        mAdapter.type,
                                        mAdapter.data[position].orderId
                                    )
                                    p.dismiss()
                                }
                                p.showPopupWindow()
                            } else {
                                startActivity<OrderDetailsActivity>(
                                    "type" to 1, "orderId" to mAdapter.data[position].orderId
                                )
                            }

                        }

                        2 -> {
                            if (mAdapter.type == 1) {
                                startActivity<OrderConfirmActivity>(
                                    "gameId" to mAdapter.data[position].playId,
                                    "userId" to mAdapter.data[position].userId
                                )
                            } else {
                                startActivity<OrderCancelActivity>(
                                    "type" to 1, "orderId" to mAdapter.data[position].orderId
                                )
                            }
                        }

                        3 -> {
                            startActivity<OrderDetailsActivity>(
                                "type" to mAdapter.type - 1,
                                "orderId" to mAdapter.data[position].orderId
                            )
                        }

                        4 -> {
                            if (mAdapter.type == 1) {
                                startActivity<OrderConfirmActivity>(
                                    "gameId" to mAdapter.data[position].playId,
                                    "userId" to mAdapter.data[position].userId
                                )
                            } else {
                                startActivity<OrderDetailsActivity>(
                                    "type" to mAdapter.type - 1,
                                    "orderId" to mAdapter.data[position].orderId
                                )
                            }
                        }

                        5 -> {
                            if (mAdapter.type == 1) {
                                startActivity<RejectDetailsActivity>(
                                    "status" to 1, "oid" to mAdapter.data[position].orderId
                                )
                             } else {
                                startActivity<OrderCancelActivity>(
                                    "type" to mViewModel.selectedIndex.value - 1,
                                    "orderId" to mAdapter.data[position].orderId
                                )
                             }
                        }

                        6 -> {
                            if (mAdapter.type == 1) {
                                startActivity<OrderConfirmActivity>(
                                    "gameId" to mAdapter.data[position].playId,
                                    "userId" to mAdapter.data[position].userId
                                )
                            } else {
                                /*startActivity<OrderDetailsActivity>(
                                    "type" to 1, "orderId" to mAdapter.data[position].orderId
                                )*/
                            }
                        }

                        7 -> {
                            val status = if (mAdapter.type == 1) {
                                2
                            } else {
                                3
                            }
                            startActivity<RejectDetailsActivity>(
                                "status" to status, "oid" to mAdapter.data[position].orderId
                            )
                        }

                        8 -> {
                            val status = if (mAdapter.type == 1) {
                                6
                            } else {
                                4
                            }
                            startActivity<RejectDetailsActivity>(
                                "status" to status, "oid" to mAdapter.data[position].orderId
                            )
                        }

                        9 -> {
                            startActivity<RejectDetailsActivity>(
                                "status" to 6, "oid" to mAdapter.data[position].orderId
                            )
                        }
                    }
                }

                R.id.rejectBtn -> {
                    val p = ReasonPopup<ReasonBean>(this)
                    p.setWheelData(playerReasonList)
                    p.mConfirmClick = { reason ->
                        mViewModel.cancelOrder(
                            reason?.id ?: 0, mAdapter.type, mAdapter.data[position].orderId
                        )
                        p.dismiss()
                    }
                    p.showPopupWindow()
                }
            }
        }

        refresh.setOnRefreshListener {
            mViewModel.getOrderList()
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_orderlist).addBindingParam(BR.model, mViewModel)
            .addBindingParam(BR.clickProxy, OrderClickProxy())
    }

    override fun createObserver() {
        mViewModel.orderList.observeActivity(this) {
            val datas = mutableListOf<OrderListBean.Result>()
            if (refresh.isRefreshing) refresh.isRefreshing = false
            if (it.historyResult.isEmpty() && it.currentResult.isEmpty()) {
                mStateView?.showEmpty()
            } else {
                if (it.currentResult.isNotEmpty()) {
                    datas.add(OrderListBean.Result("进行中", isHeader = true))

                    datas.addAll(it.currentResult)
                }

                if (it.historyResult.isNotEmpty()) {
                    datas.add(OrderListBean.Result("历史订单", isHeader = true))
                    datas.addAll(it.historyResult)
                }

                mStateView?.showContent()
            }
            mAdapter.setNewInstance(datas)
        }

        mViewModel.orderRecordStatus.observeActivity(this) {
            if (!it) mStateView?.showRetry()
        }

        mViewModel.authentication.observeActivity(this) {

        }

        mViewModel.receiveOrderStatus.observeActivity(this) {
            if (it) {
                mViewModel.getOrderList()
            }
        }

        mViewModel.cancelEvent.observeActivity(this) {
            if (it) {
                mViewModel.getOrderList()
            }
        }

        mViewModel.completeEvent.observeActivity(this) {
            if (it) {
                mViewModel.getOrderList()
            }
        }

        mGlobalModel.refreshListEvent.observeActivity(this) {
            if (it) {
                mViewModel.getOrderList()
            }
        }
    }

    inner class OrderClickProxy {

        fun sendClick() {
            if (mViewModel.selectedIndex.value != 1) {
                mViewModel.selectedIndex.value = 1
                refresh.isRefreshing = true
                mViewModel.getOrderList()
                mAdapter.type = 1
            }
        }

        fun receiveClick() {
            if (mViewModel.selectedIndex.value != 2) {
                mViewModel.selectedIndex.value = 2
                mAdapter.type = 2
                refresh.isRefreshing = true
                mViewModel.getOrderList()
            }
        }

        fun back() {
            finish()
        }
    }

}