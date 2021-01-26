package com.jk.order

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ClipboardUtils
import com.blankj.utilcode.util.TimeUtils
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.callback.IntLiveData
import com.fansan.common.ext.get
import com.fansan.common.ext.init
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.ReasonBean
import com.github.nukc.stateview.StateView
import com.jk.jkproject.utils.ext.click
import com.jk.jkproject.utils.ext.gone
import com.jk.jkproject.utils.ext.visible
import com.jk.order.databinding.ActivityOrderDetailsBinding
import kotlinx.android.synthetic.main.activity_order_details.*
import kotlinx.android.synthetic.main.fragment_play_with_u.*
import kotlinx.android.synthetic.main.item_order.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 2020/11/16
 */

@Route(path = "/order/details")
class OrderDetailsActivity : BaseActivity<OrderViewModel, ActivityOrderDetailsBinding>() {

    private var type = IntLiveData()
    private var mStateView: StateView? = null
    private var mOrderId: String = ""
    private var countdown1Job : Job? = null
    private var countdown2Job :Job? = null

    //1 不想下单了 2.我临时有事 3.信息填错，从新下单 4 大神未响应 5 大神无法提供服务
    //7当前有事，无法服务 8.订单服务时间与其他时间冲突 9.老板要求无法满足 10其他
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

    override fun initView(savedInstanceState: Bundle?) {

        mStateView = StateView.inject(container)
        mOrderId = intent.get<String>("orderId") ?: ""
        mViewModel.orderId.value = mOrderId
        type.value = intent.get<Int>("type") ?: 0
        if (type.value == 0) {
            realPay.setLeftView(this, R.drawable.diamond, 12.dp, 9.dp)
        } else {
            realPay.setLeftView(this, R.drawable.me_diamon_yellow, 12.dp, 9.dp)
        }
        mStateView?.init {
            mViewModel.getOrderInfo()
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_order_details).addBindingParam(
            BR.model, mViewModel
        ).addBindingParam(BR.clickProxy, DetailsClickProxy()).addBindingParam(BR.type, type)
    }

    override fun createObserver() {
        mViewModel.orderInfoBean.observeActivity(this) {
            if (it.status == 1) {
                countdown()
            }else if (it.status == 3){
                countdown2()
            }

            if (it.status == 4) {
                doing.setImageResource(R.drawable.order_progress_done)
                complete.setImageResource(R.drawable.order_progress_done)
            }

            if (type.value == 0) {
                when (it.status) {
                    1 -> {
                        rightTv.text = "取消订单"
                        statusBtn.text = "提醒接单"
                        rightTv.click {
                            val p = ReasonPopup<ReasonBean>(this)
                            p.setWheelData(userReasonList)
                            p.mConfirmClick = { reason ->
                                mViewModel.cancelOrder(reason?.id ?: 0, 1, mOrderId)
                                p.dismiss()
                            }
                            p.showPopupWindow()
                        }
                    }
                    3 -> {
                        rightTv.visible()
                        rightTv.text = "退款"
                        rightTv.click {
                            val bean = mViewModel.orderInfoBean.value
                            startActivity<OrderRefundActivity>("data" to bean,"oid" to bean?.orderId)
                        }
                        doing.setImageResource(R.drawable.order_progress_done)
                        statusBtn.text = "确认完成"
                    }
                    4 -> {
                        rightTv.gone()
                        statusBtn.text = "再来一单"
                    }
                }
            } else {
                when (it.status) {
                    1 -> {
                        rightTv.text = "拒绝订单"
                        rightTv.click {
                            val p = ReasonPopup<ReasonBean>(this)
                            p.setWheelData(playerReasonList)
                            p.mConfirmClick = { reason ->
                                mViewModel.cancelOrder(reason?.id ?: 0, 2, mOrderId)
                                p.dismiss()
                            }
                            p.showPopupWindow()
                        }
                        statusBtn.text = "立即接单"
                    }
                    3 -> {
                        rightTv.gone()
                        doing.setImageResource(R.drawable.order_progress_done)
                        statusBtn.text = "服务完成"
                    }
                    4 -> {
                        rightTv.gone()
                        statusBtn.text = "查看余额"
                    }
                }
            }
        }

        mViewModel.requestStatusEvent.observeActivity(this) {
            if (it) {
                mStateView?.showContent()
            } else {
                mStateView?.showRetry()
            }
        }

        mViewModel.cancelEvent.observeActivity(this) {
            if (it) {
                showToast("订单已取消")
                startActivity<OrderCancelActivity>("type" to type.value, "orderId" to mOrderId)
                mGlobalModel.refreshListEvent.postValue(true)
                finish()
            }
        }

        mViewModel.completeEvent.observeActivity(this) {
            if (it) {
                mViewModel.getOrderInfo()
                mGlobalModel.refreshListEvent.postValue(true)
            }
        }

        mViewModel.receiveOrderStatus.observeActivity(this){
            if (it){
                mViewModel.getOrderInfo()
                mGlobalModel.refreshListEvent.postValue(true)
            }
        }

        mGlobalModel.needFinishEvent.observeActivity(this){
            if (it) finish()
        }
    }

    private fun countdown() {
        var deadline = mViewModel.orderInfoBean.value?.r_time ?: 0L
       countdown1Job = mViewModel.viewModelScope.launch {
            while (deadline >= 1000) {
                val diffTime = TimeUtils.millis2String(deadline, "mm分ss秒")
                details.text =
                    if (type.value == 0) "${diffTime}后，若大神扔未接单，系统将自动取消，订单金额原路返还" else "${diffTime}后,若规定时间内未接单，订单将自动取消"
                deadline -= 1000
                delay(1000)
            }
            //mViewModel.getOrderInfo()
        }
    }

    private fun countdown2() {
        var deadline = mViewModel.orderInfoBean.value?.r_time ?: 0L
        countdown2Job =  mViewModel.viewModelScope.launch {
            if (countdown1Job?.isActive == true) countdown1Job?.cancel()
            while (deadline >= 1000) {
                val diffTime = getGapTime(deadline)
                details.text = "订单有效时间为${diffTime},请在有效时间内完成订单"
                deadline -= 1000
                delay(1000)
            }
            //mViewModel.getOrderInfo()
        }
    }

    private fun getGapTime(time: Long): String {
        val hours = time / (1000 * 60 * 60)
        val minutes = (time - hours * (1000 * 60 * 60)) / (1000 * 60)
        val second = (time - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000
        var diffTime = ""
        diffTime = if (minutes < 10) {
            "$hours:0$minutes"
        } else {
            "$hours:$minutes"
        }
        diffTime = if (second < 10) {
            "$diffTime:0$second"
        } else {
            "$diffTime:$second"
        }
        return diffTime
    }

    inner class DetailsClickProxy {

        fun btnClick() {
            when (mViewModel.orderInfoBean.value?.status) {
                1 -> {
                    if (type.value == 0) {
                        mViewModel.remindOrder()
                    } else {
                        mViewModel.receiveOrder(mViewModel.orderId.value)
                    }
                }


                3 -> {
                    if (type.value == 0) {
                        mViewModel.reqComplete(mViewModel.orderId.value)
                    } else {
                        mViewModel.recComplete(mViewModel.orderId.value)
                    }
                }

                4 -> {
                    if (type.value == 0) {
                        val gameid = (mViewModel.orderInfoBean.value?.playId ?:"0").toInt()
                        val userId = mViewModel.orderInfoBean.value?.tagId ?: ""
                        startActivity<OrderConfirmActivity>(
                            "gameId" to gameid, "userId" to userId
                        )
                        finish()
                    } else {
                        //查看余额
                    }
                }

            }

            /*if (type.value == 0 && mViewModel.orderInfoBean.value?.status == 1) {

            } else if (type.value == 0 && mViewModel.orderInfoBean.value?.status == 3) {
                val popup = OrderComfirmPopup(this@OrderDetailsActivity)
                popup.confirmClick = {
                    mViewModel.reqComplete(mOrderId)
                }
                popup.showPopupWindow()
            } else if (type.value == 0 && mViewModel.orderInfoBean.value?.status == 4) {
                val gameid = mViewModel.orderInfoBean.value?.playId ?: 0
                val userId = mViewModel.orderInfoBean.value?.tagId ?: ""
                startActivity<OrderConfirmActivity>(
                    "gameId" to gameid, "userId" to userId
                )
                finish()
            }*/
        }

        fun copyOrderId() {
            ClipboardUtils.copyText(mViewModel.orderId.value)
            mViewModel.loadingChange.toastShow.postValue("复制成功")
        }

        fun contact() {

        }

        fun back(){
            finish()
        }
    }

}