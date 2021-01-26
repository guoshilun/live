package com.jk.order

import android.os.Bundle
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.finish
import com.fansan.common.ext.init
import com.fansan.common.pojo.AllGameBean
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.github.nukc.stateview.StateView
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.ActivityAllgameBinding
import kotlinx.android.synthetic.main.activity_allgame.*
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 11/30/20
 */

class AllGameActivity : BaseActivity<PlayWithUViewModel, ActivityAllgameBinding>() {

    private val mAdapter: GameAdapter by lazy {
        GameAdapter()
    }

    private lateinit var mStateView:StateView

    private val mListData = mutableListOf<MutableList<AllGameBean.Game>>()

    override fun initView(savedInstanceState: Bundle?) {
        mStateView = StateView.inject(recyclerview)
        mStateView.init {
            mViewModel.getAllGame()
        }
        dividerBuilder().size(20.dp).showFirstDivider().asSpace().build().addTo(recyclerview)
        recyclerview.adapter = mAdapter

        mAdapter.childClick = {
            finish("id" to it)
        }

        back.click {
            finish()
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_allgame).addBindingParam(BR.model, mViewModel)
    }

    override fun createObserver() {
        mViewModel.errorStatus.observeActivity(this) {
            if (it) mStateView.showRetry()
        }

        mViewModel.allGameData.observeActivity(this) {
            mStateView.showContent()
            mListData.clear()
            mListData.add(it.mobileGame)
            mListData.add(it.clientGame)
            mListData.add(it.elseGame)
            mAdapter.setNewInstance(mListData)
        }
    }

}