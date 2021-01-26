package com.jk.order

import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.afollestad.materialdialogs.MaterialDialog
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.drawable
import com.fansan.common.ext.get
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.ActivityInfoModifyBinding
import com.qiniu.android.storage.UpProgressHandler
import com.qiniu.android.storage.UploadOptions
import kotlinx.android.synthetic.main.activity_info_modify.record
import kotlinx.android.synthetic.main.activity_take_order_step_2.*
import kotlinx.coroutines.*
import razerdp.basepopup.BasePopupWindow

/**
 *@author  fansan
 *@version 11/24/20
 */

class InfoModifyActivity : BaseActivity<TakeOrderViewModel, ActivityInfoModifyBinding>() {
    private var mMediaPlayer: MediaPlayer? = null
    private var mGameId: Int = 0
    private var mAnimDrawable:AnimationDrawable? = null
    override fun initView(savedInstanceState: Bundle?) {
        mGameId = intent.get("id") ?: 0
        mViewModel.recordUrl.value = intent.get("recordUrl") ?: ""
        mViewModel.recordDuration.postValue(intent.get("recordDuration"))
        mViewModel.step2EditValue.postValue(intent.get("introduce"))
        initMediaPlayer()
        initAnim()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_info_modify).addBindingParam(
            BR.model, mViewModel
        ).addBindingParam(BR.clickProxy, InfoClickProxy())
    }

    override fun createObserver() {

        mViewModel.recordDone.observeActivity(this) {
            upLoadRecord()
        }

        mViewModel.mQiniuTokenBean.observeActivity(this) {
            upLoadRecord()
        }

        mViewModel.updateCertifyStatus.observeActivity(this) {
            mGlobalModel.refreshListEvent.postValue(true)
            finish()
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
                    stopAnim()
                }
            }
        }
    }

    private fun initAnim(){
        mAnimDrawable = AnimationDrawable()
        drawable(R.drawable.wave_1)?.let { mAnimDrawable?.addFrame(it, 300) }
        drawable(R.drawable.wave_2)?.let { mAnimDrawable?.addFrame(it, 300) }
        drawable(R.drawable.wave_3)?.let { mAnimDrawable?.addFrame(it, 300) }
        mAnimDrawable?.isOneShot = false
    }

    private fun setAnim(){
        wave.setImageDrawable(mAnimDrawable)
        mAnimDrawable?.start()
    }

    private fun stopAnim(){
        wave.setImageResource(R.drawable.recorder_wave)
        mAnimDrawable?.stop()
    }

    private fun upLoadRecord() {
        if (mViewModel.mQiniuTokenBean.value == null) {
            mViewModel.getQiNiuToken()
        } else {
            mViewModel.viewModelScope.launch {
                delay(500)
                showLoading("上传中")
            }
            val opt = UploadOptions(null, null, true, UpProgressHandler { _, _ -> }, null)
            mViewModel.mQiniuManager.put(
                mViewModel.recorderPath.value,
                System.currentTimeMillis().toString(),
                mViewModel.mQiniuTokenBean.value?.qnToken,
                { key, info, _ ->
                    if (info.isOK) {
                        mViewModel.recordUrl.value =
                            mViewModel.mQiniuTokenBean.value?.url + "/" + key
                        dismissLoading()
                        showToast("上传成功")
                        releaseMediaPlayer()
                        initMediaPlayer()
                    } else {
                        showToast("上传失败，请重试")
                        dismissLoading()
                    }
                },
                opt
            )
        }
    }

    private fun initMediaPlayer() {
        if (mMediaPlayer == null) mMediaPlayer = MediaPlayer()
        mMediaPlayer?.setDataSource(mViewModel.recordUrl.value)
        mMediaPlayer?.prepare()
        mMediaPlayer?.setOnPreparedListener {
            mViewModel.recordDuration.postValue((mMediaPlayer?.duration ?: 0) / 1000)
            record.text = "${(mMediaPlayer?.duration ?: 0) / 1000}S"
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

    override fun onBackPressed() {
        MaterialDialog(this).show {
            message(text = "确定要退出吗?")
            positiveButton(text = "确定") {
                super.onBackPressed()
                dismiss()
            }
            negativeButton(text = "取消") {
                dismiss()
            }
        }
    }

    inner class InfoClickProxy() {

        fun back() {
            onBackPressed()
        }

        fun setVoice() {
            releaseMediaPlayer()
            val p = RecorderPopup(this@InfoModifyActivity)
            p.recorderComplete = {
                mViewModel.recorderPath.postValue(it)
                mViewModel.recordDone.postValue(true)
            }
            p.onDismissListener = object : BasePopupWindow.OnDismissListener() {
                override fun onDismiss() {
                    initMediaPlayer()
                }
            }
            p.showPopupWindow()
        }

        fun playVoice() {
            if (mMediaPlayer != null && mMediaPlayer?.isPlaying == true) {
                mMediaPlayer?.stop()
                mMediaPlayer?.prepareAsync()
                stopAnim()
            } else {
                mMediaPlayer?.start()
                playingTime()
                setAnim()
            }
        }

        fun commit() {
            val map = mutableMapOf<String, Any>(
                "type" to 1,
                "play_id" to mGameId,
                "video" to mViewModel.recordUrl.value,
                "video_duration" to mViewModel.recordDuration.value,
                "introduce" to (mViewModel.step2EditValue.value ?: "")
            )
            mViewModel.updateCertify(map)
        }
    }


}