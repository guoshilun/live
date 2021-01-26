package com.jk.order

import android.os.Bundle
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.get
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.ReasonBean
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.ActivityRejectBinding
import kotlinx.android.synthetic.main.activity_reject.*

/**
 *@author  fansan
 *@version 12/9/20
 */

class RejectActivity : BaseActivity<OrderViewModel, ActivityRejectBinding>() {

    private var oid = ""

    // 1、已在约定时间陪玩 2、用户恶意捣乱诬陷 3、已在陪玩中 4、其他
    private val userReasonList = mutableListOf(
        ReasonBean(1, "已在约定时间陪玩"),
        ReasonBean(2, "用户恶意捣乱诬陷"),
        ReasonBean(3, "已在陪玩中"),
        ReasonBean(4, "其他"),
    )

    override fun initView(savedInstanceState: Bundle?) {

        oid = intent.getStringExtra("oid") ?: ""

        commit.click {
            if (mViewModel.selectedRefundReason.value == null) {
                showToast("请选择原因")
            } else {
                mViewModel.processRefund(oid, 0)
            }
        }

        reasonLayout.click {
            val p = ReasonPopup<ReasonBean>(this)
            p.mConfirmClick = {
                mViewModel.selectedRefundReason.postValue(it)
            }
            p.setWheelData(userReasonList)
            p.showPopupWindow()
            p.setTitle("选择原因")
        }

        back.click {
            finish()
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_reject).addBindingParam(BR.model, mViewModel)
    }

    override fun createObserver() {
            mViewModel.completeEvent.observeActivity(this){
                if (it){
                    mGlobalModel.needFinishEvent.postValue(true)
                    mGlobalModel.needFinishEvent.postValue(true)
                    startActivity<RejectDetailsActivity>("status" to 3,"oid" to oid)
                    finish()
                }
            }
    }
}