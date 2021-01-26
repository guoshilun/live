package com.jk.order

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.afollestad.materialdialogs.MaterialDialog
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.init
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.AllGameBean
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.github.nukc.stateview.StateView
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.ActivityTakeOrderBinding
import kotlinx.android.synthetic.main.activity_take_order.*
import kotlinx.android.synthetic.main.item_game.*
import kotlinx.android.synthetic.main.item_game.recyclerview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 2020/11/18
 */

class TakeOrderActivity : BaseActivity<TakeOrderViewModel, ActivityTakeOrderBinding>() {

    private lateinit var mAdapter: GameAdapter
    private val mListData = mutableListOf<MutableList<AllGameBean.Game>>()
    private lateinit var mStateView: StateView
    private val mRejectReasonList: MutableList<AllGameBean.Reason> = mutableListOf()

    override fun initView(savedInstanceState: Bundle?) {
        mStateView = StateView.inject(recyclerview)
        mStateView.init {
            mViewModel.getTakeOrderGame()
        }
        back.click {
            finish()
        }
        mAdapter = GameAdapter()
        recyclerview.adapter = mAdapter
        dividerBuilder().asSpace().size(20.dp).showFirstDivider().showLastDivider().build()
            .addTo(recyclerview)

        mAdapter.childClick = { id ->
            mListData.forEach {
                it.forEach { game ->
                    if (game.id == id) {
                        if (game.state == 0) {
                            startActivity<TakeOrderStep1Activity>("id" to id, "game" to game.name)
                        } else if (game.state == 2) {
                            startActivity<TakeOrderModifyActivity>("id" to id,"game" to game.name,"type" to game.type)
                        }
                    }
                }
            }
        }
    }

    private fun showReject() {
        val reason = mRejectReasonList[0]
        MaterialDialog(this).show {
            title(text = reason.title)
            message(text = reason.content)
            positiveButton(text = "确定") {
                mViewModel.viewModelScope.launch {
                    delay(300)
                    if (mRejectReasonList.isNotEmpty()) {
                        showReject()
                    }
                }
                mRejectReasonList.remove(reason)
                it.dismiss()
            }
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_take_order).addBindingParam(BR.model, mViewModel)
    }

    override fun createObserver() {
        mViewModel.orderGameData.observeActivity(this) {
            mStateView.showContent()
            mListData.clear()
            mRejectReasonList.clear()
            mListData.add(it.mobileGame)
            mListData.add(it.clientGame)
            mListData.add(it.elseGame)
            mAdapter.setNewInstance(mListData)
            mAdapter.notifyDataSetChanged()
            mRejectReasonList.addAll(it.message)
            if (mRejectReasonList.isNotEmpty()) {
                showReject()
            }
        }

        mViewModel.errorState.observeActivity(this) {
            mStateView.showRetry()
        }

        mGlobalModel.refreshListEvent.observeActivity(this) {
            if (it) {
                mViewModel.getTakeOrderGame()
            }
        }
    }


}