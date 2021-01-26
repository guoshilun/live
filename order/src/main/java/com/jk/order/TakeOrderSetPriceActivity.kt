package com.jk.order

import android.os.Bundle
import android.text.Html
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.finish
import com.fansan.common.ext.get
import com.fansan.common.ext.init
import com.fansan.common.ext.string
import com.fansan.common.pojo.PriceBean
import com.fansan.common.pojo.SetPriceBean
import com.github.nukc.stateview.StateView
import com.jk.order.databinding.ActivityTakeorderPriceBinding
import kotlinx.android.synthetic.main.activity_takeorder_price.*

/**
 *@author  fansan
 *@version 2020/11/23
 */

class TakeOrderSetPriceActivity :
    BaseActivity<TakeOrderViewModel, ActivityTakeorderPriceBinding>() {

    private var mStateView: StateView? = null
    private var mMaxMoney: Int? = 0
    private var mCurrentOrderNum: Int? = 0
    private var mCurrentPrice: Int? = 0
    private var mGameId: Int = 0
    private var mGameType:Int = 0

    override fun initView(savedInstanceState: Bundle?) {
        mGameId = intent.get("id") ?: 0
        mMaxMoney = intent.get("maxMoney")
        mCurrentOrderNum = intent.get("currentOrderNum")
        mCurrentPrice = intent.get("currentOrderPrice")
        mGameType = intent.get("type")?:0
        orderNum.text = mCurrentOrderNum.toString()
        val priceBean = PriceBean(limit_money = mCurrentPrice ?: 0)
        mViewModel.selectedPrice.postValue(priceBean)
        price.text = "$mCurrentPrice/局"
        mStateView = StateView.inject(content_container)
        mStateView?.init {
            mViewModel.getPriceData(mGameType)
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_takeorder_price).addBindingParam(
            BR.model, mViewModel
        ).addBindingParam(BR.clickProxy, SetPriceClickProxy())
    }

    override fun createObserver() {
        mViewModel.priceDataArray.observeActivity(this) {
            price1.text = Html.fromHtml(string(R.string.set_order_price).format(it[0].limit_money))
            price2.text = Html.fromHtml(string(R.string.set_order_price).format(it[1].limit_money))
            price3.text = Html.fromHtml(string(R.string.set_order_price).format(it[2].limit_money))
            price4.text = Html.fromHtml(string(R.string.set_order_price).format(it[3].limit_money))
            price5.text = Html.fromHtml(string(R.string.set_order_price).format(it[4].limit_money))
            price6.text = Html.fromHtml(string(R.string.set_order_price).format(it[5].limit_money))
        }

        mViewModel.getPriceStatus.observeActivity(this) {
            if (it) {
                mStateView?.showContent()
            } else {
                mStateView?.showRetry()
            }
        }

        mViewModel.updateCertifyStatus.observeActivity(this) {
            if (it) {
                mGlobalModel.refreshListEvent.postValue(true)
                finish()
            }
        }

        mViewModel.priceData.observeActivity(this){data ->
            val popup = ReasonPopup<PriceBean>(this@TakeOrderSetPriceActivity)
            popup.setWheelData(data)
            popup.mConfirmClick = {
                mViewModel.selectedPrice.postValue(it)
            }
            popup.showPopupWindow()
            popup.setTitle("选择价格")
        }
    }

    override fun onBackPressed() {
        val map = mutableMapOf<String, Any>(
            "type" to 3,
            "play_id" to mGameId,
            "order_price" to (mViewModel.selectedPrice.value?.limit_money ?: 0)
        )

        mViewModel.updateCertify(map)
    }

    inner class SetPriceClickProxy {

        fun back() {
            onBackPressed()
        }

        fun setPrice() {
            mViewModel.getPlayOrderPriceAllList(mGameType)
        }
    }

}