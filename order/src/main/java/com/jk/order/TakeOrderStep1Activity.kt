package com.jk.order

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.fansan.common.BaseActivity
import com.fansan.common.Constants
import com.fansan.common.GlideEngine
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.get
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.DanBean
import com.jk.jkproject.utils.ext.click
import com.jk.jkproject.utils.ext.gone
import com.jk.jkproject.utils.ext.visible
import com.jk.jkproject.utils.ext.yes
import com.jk.order.databinding.ActivityTakeOrderStep1Binding
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.qiniu.android.storage.UpCancellationSignal
import com.qiniu.android.storage.UpProgressHandler
import com.qiniu.android.storage.UploadOptions
import kotlinx.android.synthetic.main.activity_take_order_step_1.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 2020/11/18
 */

class TakeOrderStep1Activity : BaseActivity<TakeOrderViewModel, ActivityTakeOrderStep1Binding>() {

    private var mGameId: Int? = 0
    private var mPath = ""
    override fun initView(savedInstanceState: Bundle?) {
        mGameId = intent.get<Int>("id")
        mViewModel.gameName.postValue(intent.get<String>("game"))
        mViewModel.getUpdatePlayData(mGameId)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_take_order_step_1).addBindingParam(
            BR.clickProxy, Step1ClcikProxy()
        ).addBindingParam(BR.model, mViewModel)
    }

    override fun createObserver() {
        mViewModel.danListData.observeActivity(this) {
            if (it.isNotEmpty()) {
                val popup = ReasonPopup<DanBean>(this@TakeOrderStep1Activity)
                popup.setWheelData(it)
                popup.mConfirmClick = { dan ->
                    mViewModel.levelSelected.postValue(dan)
                    if (mViewModel.gameImg.value.isNotEmpty()) {
                        mViewModel.infoComplete.postValue(true)
                    }
                }
                popup.showPopupWindow()
                popup.setTitle("选择段位")
            }
        }

        mViewModel.updatePlayData.observeActivity(this) {
            if (it.dan_state == 1) {
                danLayout.visible()
            } else {
                danLayout.gone()
            }
        }

        mViewModel.gameImg.observeActivity(this) {
            if (mViewModel.updatePlayData.value?.dan_state == 1) {
                if (mViewModel.levelSelected.value != null) {
                    mViewModel.infoComplete.postValue(true)
                }
            } else {
                mViewModel.infoComplete.postValue(true)
            }
        }

        mViewModel.mQiniuTokenBean.observeActivity(this) {
            upLoadImg()
        }
    }


    private fun openGallery() {
        PictureSelector.create(this).openGallery(PictureMimeType.ofImage()).maxSelectNum(1)
            .isWeChatStyle(false).selectionMode(PictureConfig.SINGLE).isZoomAnim(true)
            .isPreviewImage(true).circleDimmedLayer(true)
            .imageEngine(GlideEngine.createGlideEngine()).isCamera(true).isGif(false)
            .showCropFrame(false).showCropGrid(false).rotateEnabled(false)
            .forResult(Constants.OPEN_PICTURE_CODE)
    }

    private fun takePicture() {
        PictureSelector.create(this).openCamera(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine()).showCropFrame(false).rotateEnabled(false)
            .forResult(Constants.OPEN_PICTURE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        (requestCode == Constants.OPEN_PICTURE_CODE).yes {
            val selectList = PictureSelector.obtainMultipleResult(data)
            if (!selectList.isNullOrEmpty()) {
                mPath = when {
                    selectList[0].isCut -> {
                        selectList[0].cutPath
                    }

                    selectList[0].isCompressed -> {
                        selectList[0].compressPath
                    }

                    else -> {
                        if (Build.VERSION.SDK_INT >= 29) {
                            selectList[0].androidQToPath
                        } else {
                            selectList[0].path
                        }
                    }
                }

                upLoadImg()
                //head.load(mAvatarPath)
            }
        }
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

    private fun upLoadImg() {
        if (mViewModel.mQiniuTokenBean.value == null) {
            mViewModel.getQiNiuToken()
        } else {
            mViewModel.viewModelScope.launch {
                delay(500)
                showLoading("上传中")
            }
            val opt = UploadOptions(null, null, true, UpProgressHandler { _, _ -> }, null)
            mViewModel.mQiniuManager.put(
                mPath,
                System.currentTimeMillis().toString(),
                mViewModel.mQiniuTokenBean.value?.qnToken,
                { key, info, _ ->
                    if (info.isOK) {
                        mViewModel.gameImg.postValue(mViewModel.mQiniuTokenBean.value?.url + "/" + key)
                        showToast("上传成功")
                        mViewModel.viewModelScope.launch {
                            delay(500)
                            dismissLoading()
                        }
                    } else {
                        showToast("上传失败，请重试")
                        dismissLoading()
                    }
                },
                opt
            )
        }
    }

    inner class Step1ClcikProxy() {

        fun chooseLevel() {
            mViewModel.getPlayDanList(mGameId)
        }

        fun back() {
            onBackPressed()
        }

        fun chooseImg() {
            val dialog = MaterialDialog(
                this@TakeOrderStep1Activity, BottomSheet(layoutMode = LayoutMode.WRAP_CONTENT)
            ).customView(
                R.layout.item_image_choose, horizontalPadding = false, noVerticalPadding = true
            ).cancelOnTouchOutside(true).cornerRadius(6.dp.toFloat())
            dialog.getCustomView().apply {
                findViewById<TextView>(R.id.takePicture).click {
                    takePicture()
                    dialog.dismiss()
                }

                findViewById<TextView>(R.id.chooseGallery).click {
                    openGallery()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        fun next() {
            startActivity<TakeOrderStep2Activity>(
                "id" to mGameId,
                "name" to mViewModel.gameName.value,
                "img" to mViewModel.gameImg.value,
                "dan" to mViewModel.levelSelected.value
            )
            finish()
        }
    }
}