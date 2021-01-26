package com.jk.order

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.afollestad.materialdialogs.MaterialDialog
import com.alibaba.android.arouter.facade.annotation.Route
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.callback.IntLiveData
import com.fansan.common.ext.get
import com.fansan.common.ext.startActivity
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.github.nukc.stateview.StateView
import com.jk.jkproject.utils.ext.click
import com.jk.jkproject.utils.ext.gone
import com.jk.order.databinding.ActivityRefundDetailsBinding
import kotlinx.android.synthetic.main.activity_refund_details.*
import kotlinx.android.synthetic.main.activity_refund_details.label
import kotlinx.android.synthetic.main.activity_refund_details.recyclerview
import kotlinx.android.synthetic.main.item_add_img.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 12/9/20
 */

@Route(path = "/order/reject")
class RejectDetailsActivity : BaseActivity<OrderViewModel, ActivityRefundDetailsBinding>() {

    private val statusType = IntLiveData() // 1.用户退款中 2.大神不同意 3.等待用户处理 4.用户申诉 5.处理退款申请 6.凭证已上传
    private var mStateView: StateView? = null
    private var countdownJob: Job? = null
    private lateinit var mAdapter: AddImgAdapter

    override fun initView(savedInstanceState: Bundle?) {
        mStateView = StateView.inject(this)
        mStateView?.showLoading()
        val status = intent.get<Int>("status") ?: 0
        val oid = intent.get<String>("oid") ?: ""
        mViewModel.orderId.value = oid
        statusType.value = status
        when (status) {
            1 -> {
                cancelLabel.text = "等待大神处理"
                mViewModel.getOrderInfo()
            }

            2 -> {
                cancelLabel.text = "大神不同意退款"
                mViewModel.getOrderInfo()
            }

            3 -> {
                cancelLabel.text = "请等待用户处理"
                label.text = "用户修改申请后，您需要重新处理"
                mViewModel.getOrderInfo()
            }

            4 -> {
                cancelLabel.text = "用户已申诉"
                label.text = "请上传凭证由客服判断"
                mAdapter = AddImgAdapter()
                mViewModel.getOrderInfo()
                recyclerview.adapter = mAdapter
                dividerBuilder().asSpace().size(20.dp).showLastDivider().build().addTo(recyclerview)
            }

            5 -> {
                cancelLabel.text = "请处理退款申请"
                mViewModel.getOrderInfo()
            }

            6 -> {
                cancelLabel.text = "凭证已上传"
                label.text = "1-3个工作日会有结果"
                mAdapter = AddImgAdapter()
                mViewModel.getOrderInfo()
                recyclerview.adapter = mAdapter
                dividerBuilder().asSpace().size(20.dp).showLastDivider().build().addTo(recyclerview)
            }

        }

        agree.click {
            MaterialDialog(this).show {
                title(text = "同意退款")
                message(text = "同意后钱款将退回用户账户")
                positiveButton(text = "确定") {
                    mViewModel.processRefund(oid, 1)
                    dismiss()
                }
                negativeButton(text = "取消") {
                    dismiss()
                }
            }
        }

        reject.click {
            if (status == 5) {
                startActivity<RejectActivity>("oid" to oid)
            }else if (status == 4){
                startActivity<AppealActivity>(
                    "reason" to mViewModel.orderInfoBean.value?.cause, "oid" to oid, "type" to 2
                )
            }
        }

        appeal.click {
            startActivity<AppealActivity>(
                "reason" to mViewModel.orderInfoBean.value?.cause, "oid" to oid, "type" to 1
            )
        }

        back.click {
            finish()
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

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_refund_details).addBindingParam(
            BR.model, mViewModel
        ).addBindingParam(BR.type, statusType)
    }

    override fun createObserver() {
        mViewModel.orderInfoBean.observeActivity(this) {
            mStateView?.showContent()
            if (statusType.value == 1 || statusType.value == 2 || statusType.value == 5) {
                countdown()
            }

            if (::mAdapter.isInitialized) {
                if ((it.appealImages ?: "").isNotEmpty()) {
                    val imgList = it.appealImages?.split(",")
                    mAdapter.setNewInstance(imgList?.toMutableList())
                }
            }
        }

        mViewModel.completeEvent.observeActivity(this) {
            if (it) {
                mGlobalModel.needFinishEvent.postValue(true)
                mGlobalModel.refreshListEvent.postValue(true)
                val oid = mViewModel.orderInfoBean.value?.orderId
                startActivity<OrderCancelActivity>("type" to 1, "orderId" to oid)
                finish()
            }
        }

        mGlobalModel.needFinishEvent.observeActivity(this) {
            if (it) finish()
        }
    }


    private fun countdown() {
        var deadline = mViewModel.orderInfoBean.value?.r_time ?: 0L
        countdownJob = mViewModel.viewModelScope.launch {
            while (deadline >= 1000) {
                val diffTime = getGapTime(deadline)
                if (statusType.value == 1 || statusType.value == 5) {
                    label.text = "还剩${diffTime}"
                } else {
                    label.text = "还剩${diffTime},若超时未处理钱款将打给大神"
                }
                deadline -= 1000
                delay(1000)
            }
            //mViewModel.getOrderInfo()
        }
    }
}