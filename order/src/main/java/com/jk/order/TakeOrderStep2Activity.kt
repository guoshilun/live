package com.jk.order

import android.graphics.drawable.AnimationDrawable
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.afollestad.materialdialogs.MaterialDialog
import com.fansan.common.BaseActivity
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.drawable
import com.fansan.common.ext.get
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.DanBean
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.ActivityTakeOrderStep1Binding
import com.jk.order.databinding.ActivityTakeOrderStep2Binding
import com.qiniu.android.storage.UpProgressHandler
import com.qiniu.android.storage.UploadOptions
import kotlinx.android.synthetic.main.activity_take_order_step_2.*
import kotlinx.coroutines.*
import me.fansan.core.ext.util.dp
import java.io.File
import java.io.FileInputStream

/**
 *@author  fansan
 *@version 2020/11/18
 */

class TakeOrderStep2Activity : BaseActivity<TakeOrderViewModel, ActivityTakeOrderStep2Binding>() {

    private var mMediaPlayer: MediaPlayer? = null
    private var mGameId: Int? = 0
    private var mAnimDrawable:AnimationDrawable? = null

    override fun initView(savedInstanceState: Bundle?) {

        mGameId = intent.get<Int>("id")
        mViewModel.gameName.postValue(intent.get<String>("name"))
        mViewModel.gameImg.postValue(intent.get<String>("img"))
        mViewModel.levelSelected.postValue(intent.get<DanBean>("dan"))
        initAnim()

        recordLayout.click {
            if (mViewModel.recordDone.value) {
                if (mMediaPlayer != null && mMediaPlayer?.isPlaying == true) {
                    mMediaPlayer?.stop()
                    mMediaPlayer?.prepareAsync()
                    stopAnim()
                } else {
                    mMediaPlayer?.start()
                    playingTime()
                    setAnim()
                }
            } else {
                val p = RecorderPopup(this)
                p.recorderComplete = {
                    mViewModel.recorderPath.postValue(it)
                    mViewModel.recordDone.postValue(true)
                }
                p.showPopupWindow()
            }
        }
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_take_order_step_2).addBindingParam(
            BR.clickProxy, Step2ClcikProxy()
        ).addBindingParam(BR.model, mViewModel)
    }

    override fun createObserver() {
        mViewModel.recordDone.observeActivity(this) {
            if (it) {
                initMediaPlayer()
                if (mViewModel.step2EditValue.value!!.isNotEmpty()){
                    mViewModel.infoComplete.postValue(true)
                }else{
                    mViewModel.infoComplete.postValue(false)
                }
            } else {
                record.text = "录音"
                releaseMediaPlayer()
                mViewModel.infoComplete.postValue(false)
            }
        }

        mViewModel.step2EditValue.observe(this) {
            if (it.isNotEmpty()) {
                if (mViewModel.recordDone.value) {
                    mViewModel.infoComplete.postValue(true)
                }
            }else{
                mViewModel.infoComplete.postValue(false)
            }
        }

        mViewModel.mQiniuTokenBean.observeActivity(this) {
            upLoadRecord()
        }
    }

    private fun initMediaPlayer() {
        mViewModel.viewModelScope.launch {
            delay(800)
            try {
                if (mMediaPlayer == null) mMediaPlayer = MediaPlayer()
                mMediaPlayer?.setDataSource(mViewModel.recorderPath.value)
                mMediaPlayer?.prepare()
                mMediaPlayer?.setOnPreparedListener {
                    mViewModel.recordDuration.postValue((mMediaPlayer?.duration ?: 0) / 1000)
                    record.text = "${(mMediaPlayer?.duration ?: 0) / 1000}S"
                }
            } catch (e: Exception) {
            }
        }

    }


    private fun playingTime() {
        lifecycleScope.launch(Dispatchers.IO) {
            while (mMediaPlayer?.isPlaying == true) {
                withContext(Dispatchers.Main) {
                    record.text = "${(mMediaPlayer?.currentPosition ?: 0) / 1000}S"
                }
            }
            stopAnim()
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
                        mViewModel.recordUrl.value = mViewModel.mQiniuTokenBean.value?.url + "/" + key
                        dismissLoading()
                        showToast("上传成功")
                        startActivity<TakeOrderStep3Activity>(
                            "id" to mGameId,
                            "name" to mViewModel.gameName.value,
                            "img" to mViewModel.gameImg.value,
                            "dan" to mViewModel.levelSelected.value,
                            "recordUrl" to mViewModel.recordUrl.value,
                            "introduce" to mViewModel.step2EditValue.value,
                            "recordDuration" to mViewModel.recordDuration.value
                        )
                        finish()
                    } else {
                        showToast("上传失败，请重试")
                        dismissLoading()
                    }
                },
                opt
            )
        }
    }

    private fun releaseMediaPlayer() {
        try {
            stopAnim()
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

    inner class Step2ClcikProxy() {

        fun deleteRecord() {
            mViewModel.recordDone.postValue(false)
            mViewModel.recorderPath.postValue("")
            record.text = "录音"
            stopAnim()
        }

        fun back() {
            onBackPressed()
        }

        fun next() {
            upLoadRecord()
        }
    }
}