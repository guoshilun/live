package com.jk.order

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.databinding.library.baseAdapters.BR
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BusUtils
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.get
import com.fansan.common.ext.init
import com.fansan.common.pojo.GameListBean
import com.github.nukc.stateview.StateView
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.ActivityOrderconfirmBinding
import kotlinx.android.synthetic.main.activity_orderconfirm.*

/**
 *@author  fansan
 *@version 2020/11/12
 */

@Route(path = "/order/orderconfirm")
class OrderConfirmActivity : BaseActivity<OrderViewModel, ActivityOrderconfirmBinding>() {

    private var mStateView: StateView? = null
    private var mGameId: Int = 0
    private var mNickName: String = ""
    private var mUserID = ""

    override fun initView(savedInstanceState: Bundle?) {
        mGameId = intent.get("gameId") ?: 0
        mNickName = intent.get("name") ?: ""
        mUserID = intent.get("userId") ?: ""
        mStateView = StateView.inject(contentLayout)
        mStateView?.init {
            mViewModel.confirmOrder(mGameId, mUserID)
        }
        mGlobalModel.getMoney()
        preFixEditText.setEdtHint("备注：", "填写额外对大神的要求，最多50字")
        preFixEditText.setFixText("备注：", R.color.c_666666)
        preFixEditText.addTextChangedListener {
            mViewModel.remark.postValue(it.toString().replace("备注：", ""))
        }

        back.click {
            finish()
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_orderconfirm).addBindingParam(
            BR.model, mViewModel
        ).addBindingParam(BR.clickProxy, ConfirmClickProxy())
            .addBindingParam(BR.global, mGlobalModel)
    }

    override fun createObserver() {
        mViewModel.confirmOrderInfoBean.observeActivity(this) {

        }

        mViewModel.requestStatusEvent.observeActivity(this) {
            if (it) mStateView?.showContent()
        }

        mGlobalModel.moneyData.observeActivity(this) {
            setBtn(it.amount1)
        }

        mViewModel.orderNum.observeActivity(this) {
            setBtn(it)
        }

        mViewModel.submitResult.observeActivity(this) {
            if (it) {
                /*startActivity<OrderDetailsActivity>(
                    "type" to 0, "orderId" to mViewModel.orderId.value
                )*/
                BusUtils.post("jumpMsg")
                mGlobalModel.refreshListEvent.postValue(true)
                finish()
            }
        }

        mViewModel.gameListData.observeActivity(this) { bean ->
            val p = ReasonPopup<GameListBean>(this)
            p.setWheelData(bean)
            p.mConfirmClick = {
                mViewModel.orderNum.postValue(1)
                val data = mViewModel.confirmOrderInfoBean.value
                data?.playId = it?.play_id ?: 0
                data?.dan_name = it?.dan_name ?: ""
                data?.name = it?.name ?: ""
                data?.price = it?.order_price ?: 0
                mViewModel.confirmOrderInfoBean.postValue(data)
            }
            p.showPopupWindow()
            p.setTitle("选择游戏")
        }
    }

    private fun setBtn(it: Int) {
        val price = it * (mViewModel.confirmOrderInfoBean.value?.price ?: 0)
        val totalMoney = mGlobalModel.moneyData.value?.amount1
        if (totalMoney == 0 || price > totalMoney ?: 0) {
            commit.text = "充值"
            commit.click {
                ARouter.getInstance().build("/jk/charge").navigation(this, 999)
            }
        } else {
            commit.text = "提交订单"
            commit.click {
                mViewModel.submitOrder(mUserID, mGameId)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 999) {
            mGlobalModel.getMoney()
        }
    }

    inner class ConfirmClickProxy {

        fun plusOrder() {
            var num = mViewModel.orderNum.value
            num++
            mViewModel.orderNum.postValue(num)
        }

        fun minOrder() {
            var num = mViewModel.orderNum.value
            if (num >= 2) {
                num--
                mViewModel.orderNum.postValue(num)
            }

        }

        fun chooseGame() {
            mViewModel.chooseGame(mUserID)
        }
    }

}