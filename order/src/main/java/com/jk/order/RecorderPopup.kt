package com.jk.order

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.*
import com.jk.jkproject.utils.ext.gone
import com.jk.jkproject.utils.ext.visible
import com.jk.order.databinding.PopupRecorderBinding
import kotlinx.coroutines.*
import razerdp.basepopup.BasePopupWindow
import java.io.File

/**
 *@author  fansan
 *@version 2020/11/19
 */

class RecorderPopup(c: Context) : BasePopupWindow(c) {

    init {
        setOutSideDismiss(true)
        popupGravity = Gravity.CENTER
    }

    private var mBinding: PopupRecorderBinding? = null

    private var mMediaPlayer: MediaPlayer? = null
    private var mMediaRecorder: MediaRecorder? = null
    private var mOutputPath = ""
    private var mRecordTime = 0L
    private var mTimeJob: Job? = null
    private var mRecorderDown: Job? = null
    private var mPlayingJob: Job? = null
    private lateinit var mTimeTextView: TextView
    var recorderComplete: ((String) -> Unit)? = null
    private var canTouch = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(contentView: View) {
        super.onViewCreated(contentView)
        mBinding = DataBindingUtil.bind(contentView)
        mBinding?.clickProxy = RecorderClickProxy()
        mTimeTextView = findViewById<TextView>(R.id.timeTv)

        val recorder = findViewById<View>(R.id.recorder)
        recorder.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (canTouch) {
                        canTouch = false
                        VibrateUtils.vibrate(100)
                        initRecorder()
                        mMediaRecorder?.start()
                        mRecordTime = System.currentTimeMillis()
                        updateTime()
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    mTimeJob?.cancel()
                    if ((mRecorderDown == null || mRecorderDown?.isActive == false) && mRecordTime != 0L ) {
                        if (System.currentTimeMillis() - mRecordTime < 3000) {
                            ToastUtils.showShort("录制时间过短")
                            mTimeTextView.text = "00:00"
                        } else {
                            showController()
                        }
                        mRecordTime = 0L
                        mRecorderDown = MainScope().launch {
                            delay(1500)
                            releaseRecorder()
                            canTouch = true
                            mRecordTime = 0L
                        }
                    }
                }
            }
            true
        }
    }

    private fun releaseRecorder() {
        if (mMediaRecorder != null) try {
            mMediaRecorder?.stop()
            mMediaRecorder?.reset()
            mMediaRecorder?.release()
            mMediaRecorder = null
        } catch (e: Exception) {

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

    private fun initRecorder() {
        if (mMediaRecorder == null) {
            mMediaRecorder = MediaRecorder()
        }
        mMediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mMediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mMediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        val audioFile = File(PathUtils.getInternalAppFilesPath(), "${TimeUtils.getNowMills()}_audio.aac")
        FileUtils.createFileByDeleteOldFile(audioFile)
        mOutputPath = audioFile.absolutePath
        mMediaRecorder?.setOutputFile(mOutputPath)
        mMediaRecorder?.prepare()
    }

    private fun showController() {
        findViewById<View>(R.id.controller).visible()
        findViewById<View>(R.id.recorderLayout).gone()
    }

    private fun hideController() {
        findViewById<View>(R.id.controller).gone()
        findViewById<View>(R.id.recorderLayout).visible()
        mTimeTextView.text = "00:00"
    }

    private fun playAudio() {
        if (mMediaPlayer != null && mMediaPlayer?.isPlaying == true) {
            val duration = ((mMediaPlayer?.duration ?: 0) / 1000)
            if (duration >= 10) {
                mTimeTextView.text = "00:$duration"
            } else {
                mTimeTextView.text = "00:0$duration"
            }
            mPlayingJob?.cancel()
            releaseMediaPlayer()
            findViewById<ImageView>(R.id.play).setImageResource(R.drawable.recorder_play)
        } else {
            if (mMediaPlayer == null) {
                mMediaPlayer = MediaPlayer()
            }
            LogUtils.eTag("fansan","mOutputPath = $mOutputPath")
            mMediaPlayer?.setDataSource(mOutputPath)
            mMediaPlayer?.prepareAsync()
            mMediaPlayer?.setOnPreparedListener {
                mMediaPlayer?.start()
                playingTime()
                findViewById<ImageView>(R.id.play).setImageResource(R.drawable.recorder_pause)
            }
            mMediaPlayer?.setOnCompletionListener {
                releaseMediaPlayer()
                findViewById<ImageView>(R.id.play).setImageResource(R.drawable.recorder_play)
            }
        }
    }

    private fun updateTime() {
        mTimeJob = MainScope().launch(Dispatchers.IO) {
            var recordTime = 0
            while (true) {
                delay(1000)
                recordTime += 1
                withContext(Dispatchers.Main) {
                    if (recordTime < 10) mTimeTextView.text = "00:0$recordTime"
                    else mTimeTextView.text = "00:$recordTime"
                    if (recordTime >= 15) {
                        releaseRecorder()
                        mRecordTime = 0L
                        showController()
                        mTimeJob?.cancel()
                    }
                }
            }
        }
    }

    private fun playingTime() {
        mPlayingJob = MainScope().launch(Dispatchers.IO) {
            while (mMediaPlayer?.isPlaying == true) {
                if ((mMediaPlayer?.currentPosition ?: 0) / 1000 < 10) {
                    withContext(Dispatchers.Main) {
                        mTimeTextView.text = "00:0${(mMediaPlayer?.currentPosition ?: 0) / 1000}"
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        mTimeTextView.text = "00:${(mMediaPlayer?.currentPosition ?: 0) / 1000}"
                    }
                }
            }
        }
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.popup_recorder)
    }

    inner class RecorderClickProxy() {

        fun reRecord() {
            if (mMediaPlayer != null && mMediaPlayer?.isPlaying == true) releaseMediaPlayer()
            hideController()
        }

        fun complete() {
            if (mMediaPlayer != null && mMediaPlayer?.isPlaying == true) releaseMediaPlayer()
            recorderComplete?.invoke(mOutputPath)
            hideController()
            dismiss()
        }

        fun playC() {
            playAudio()
        }
    }

    override fun onDismiss() {
        super.onDismiss()
        mTimeJob?.cancel()
        mPlayingJob?.cancel()
        releaseRecorder()
        releaseMediaPlayer()
        mRecordTime = 0L
        showController()
    }
}