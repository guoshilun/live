package com.jk.order

import android.media.MediaPlayer
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.get
import com.fansan.common.ext.init
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.DanBean
import com.github.nukc.stateview.StateView
import com.jk.order.databinding.ActivityTakeorderModifyBinding
import kotlinx.android.synthetic.main.activity_takeorder_modify.*
import kotlinx.coroutines.*

/**
 *@author  fansan
 *@version 2020/11/23
 */

class TakeOrderModifyActivity : BaseActivity<TakeOrderViewModel, ActivityTakeorderModifyBinding>() {

    private var mStateView: StateView? = null
    private var mGameId: Int = 0
    private var mMediaPlayer: MediaPlayer? = null
    private var mType :Int = 0

    override fun initView(savedInstanceState: Bundle?) {
        mGameId = intent.get<Int>("id") ?: 0
        mType = intent.get<Int>("type") ?: 0
        mViewModel.gameName.postValue(intent.get<String>("game"))
        mStateView = StateView.inject(content_container)
        mStateView?.init {
            mViewModel.getUpdatePlayData(mGameId)
        }
    }

    private fun initMediaPlayer() {
        if (mMediaPlayer == null) mMediaPlayer = MediaPlayer()
        mMediaPlayer?.setDataSource(mViewModel.updatePlayData.value?.video)
        mMediaPlayer?.prepare()
        mMediaPlayer?.setOnPreparedListener {
            mViewModel.recordDuration.postValue((mMediaPlayer?.duration ?: 0) / 1000)
            record.text = "${(mMediaPlayer?.duration ?: 0) / 1000}S"
        }
    }

    private fun playingTime() {
        mViewModel.viewModelScope.launch(Dispatchers.IO) {
            try {
                while (mMediaPlayer?.isPlaying == true) {
                    withContext(Dispatchers.Main) {
                        record.text = "${(mMediaPlayer?.currentPosition ?: 0) / 1000}S"
                    }
                }
            } finally {
                withContext(NonCancellable) {
                    mViewModel.recordDuration.postValue(mViewModel.recordDuration.value)
                }
            }
        }
    }

    private fun releaseMediaPlayer() {
        try {
            mMediaPlayer?.stop()
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
            mMediaPlayer = null
        } catch (e: Exception) {
        }
    }

    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_takeorder_modify).addBindingParam(
            BR.model, mViewModel
        ).addBindingParam(BR.clickProxy, ModifyClickProxy())
    }

    override fun createObserver() {
        mViewModel.updatePlayData.observeActivity(this) {
            mStateView?.showContent()
            mViewModel.viewModelScope.launch {
                delay(500)
                if (mMediaPlayer == null) initMediaPlayer()
            }
        }

        mGlobalModel.refreshListEvent.observeActivity(this) {
            if (it) {
                mViewModel.getUpdatePlayData(mGameId)
            }
        }

        mViewModel.updateCertifyStatus.observeActivity(this) {
            if (it) {
                val bean = mViewModel.updatePlayData.value
                if (bean?.is_top == 0) {
                    bean.is_top = 1
                } else {
                    bean?.is_top = 0
                }
                mViewModel.updatePlayData.postValue(bean)
            }
        }
    }

    inner class ModifyClickProxy {

        fun back() {
            finish()
        }

        fun setDefaultSkill() {
            val bean = mViewModel.updatePlayData.value
            val top = if (bean?.is_top == 0) {
                1
            } else {
                0
            }
            val map = mutableMapOf<String, Any>("type" to 4, "play_id" to mGameId, "is_top" to top)
            mViewModel.updateCertify(map)
        }

        fun setPrice() {
            startActivity<TakeOrderSetPriceActivity>(
                "id" to mGameId,
                "maxMoney" to mViewModel.updatePlayData.value?.limit_money,
                "currentOrderNum" to mViewModel.updatePlayData.value?.order_count,
                "currentOrderPrice" to mViewModel.updatePlayData.value?.order_price,
                "type" to mType
            )
        }

        fun gameModify() {
            val dan = DanBean(
                mViewModel.updatePlayData.value?.dan_name ?: "",
                mViewModel.updatePlayData.value?.play_dan_id ?: 0
            )
            startActivity<TakeOrderPowerModifyActivity>(
                "id" to mGameId,
                "img" to mViewModel.updatePlayData.value?.img,
                "dan" to dan,
                "showLevel" to ((mViewModel.updatePlayData.value?.dan_state ?: 0) == 1),
                "exam" to mViewModel.updatePlayData.value?.examples_img
            )
        }

        fun infoModify() {
            startActivity<InfoModifyActivity>(
                "id" to mGameId,
                "recordUrl" to mViewModel.updatePlayData.value?.video,
                "recordDuration" to mViewModel.updatePlayData.value?.video_duration,
                "introduce" to mViewModel.updatePlayData.value?.introduce
            )
        }

        fun playVoice() {
            if (mMediaPlayer != null && mMediaPlayer?.isPlaying == true) {
                mMediaPlayer?.stop()
                mMediaPlayer?.prepareAsync()
            } else {
                mMediaPlayer?.start()
                playingTime()
            }
        }
    }
}