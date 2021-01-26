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
import com.blankj.utilcode.util.LogUtils
import com.fansan.common.BaseActivity
import com.fansan.common.Constants
import com.fansan.common.GlideEngine
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.get
import com.fansan.common.ext.noDoubleClickListener
import com.fansan.common.ext.startActivity
import com.fansan.common.pojo.ReasonBean
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.jk.jkproject.utils.ext.click
import com.jk.jkproject.utils.ext.yes
import com.jk.order.databinding.ActivityAppealBinding
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.qiniu.android.storage.UpProgressHandler
import com.qiniu.android.storage.UploadOptions
import kotlinx.android.synthetic.main.activity_appeal.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 12/10/20
 */

class AppealActivity : BaseActivity<OrderViewModel, ActivityAppealBinding>() {

    private var mReasonBean: String = ""
    private var mOid: String = ""
    private lateinit var mAdapter: AddImgAdapter
    private var mPath = ""
    private val mPathList = mutableListOf<String>()
    private val mUrlsList = mutableListOf<String>()
    private var mType: Int = 0

    override fun initView(savedInstanceState: Bundle?) {
        mReasonBean = intent.get("reason") ?: ""
        mType = intent.get("type") ?: 0
        reasonValue.text = mReasonBean
        mOid = intent.get("oid") ?: ""
        mAdapter = AddImgAdapter()
        mPathList.add("add")
        mAdapter.setNewInstance(mPathList)
        dividerBuilder().asSpace().size(20.dp).showLastDivider().build().addTo(recyclerview)
        recyclerview.adapter = mAdapter
        mAdapter.noDoubleClickListener { adapter, view, position ->
            if (mAdapter.data[position] == "add") {
                chooseImg()
            } else {

            }
        }

        commit.click {
            if (mViewModel.selectedRefundReason == null) {
                showToast("请选择原因")
            } else {
                mPathList.remove("add")
                if (mPathList.isEmpty()) {
                    mViewModel.submitAppeal(
                        mType, mUrlsList.joinToString(","), mOid
                    )
                } else {
                    upLoadImg()
                }
            }
        }

        back.click {
            finish()
        }
    }


    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_appeal).addBindingParam(BR.model, mViewModel)
    }

    override fun createObserver() {
        mViewModel.mQiniuTokenBean.observeActivity(this) {
            upLoadImg()
        }

        mViewModel.completeEvent.observeActivity(this) {
            if (it) {
                mGlobalModel.needFinishEvent.postValue(true)
                mGlobalModel.refreshListEvent.postValue(true)
                startActivity<RejectDetailsActivity>("status" to 6, "oid" to mOid)
                finish()
            }
        }
    }

    private fun chooseImg() {
        val dialog = MaterialDialog(
            this, BottomSheet(layoutMode = LayoutMode.WRAP_CONTENT)
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


    private fun openGallery() {
        PictureSelector.create(this).openGallery(PictureMimeType.ofImage()).maxSelectNum(6 - mPathList.size)
            .isWeChatStyle(false).selectionMode(PictureConfig.MULTIPLE).isZoomAnim(true)
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
                selectList.forEachIndexed { index, localMedia ->
                    mPath = when {
                        selectList[index].isCut -> {
                            selectList[index].cutPath
                        }

                        selectList[index].isCompressed -> {
                            selectList[index].compressPath
                        }

                        else -> {
                            if (Build.VERSION.SDK_INT >= 29) {
                                selectList[index].androidQToPath
                            } else {
                                selectList[index].path
                            }
                        }
                    }
                    mPathList.remove("add")
                    mPathList.add(mPath)
                    if (mPathList.size < 6) {
                        mPathList.add("add")
                    }
                }

                mAdapter.notifyDataSetChanged()
                //head.load(mAvatarPath)
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
                mPathList.remove("add")
                mPathList.forEachIndexed { index, s ->
                    val opt = UploadOptions(null, null, true, UpProgressHandler { _, _ -> }, null)
                    mViewModel.mQiniuManager.put(
                        s,
                        System.currentTimeMillis().toString(),
                        mViewModel.mQiniuTokenBean.value?.qnToken,
                        { key, info, _ ->
                            if (info.isOK) {
                                LogUtils.eTag("fansan", "上传成功 $index")
                                mUrlsList.add(mViewModel.mQiniuTokenBean.value?.url + "/" + key)
                                if (mUrlsList.size == mPathList.size) {
                                    showToast("上传成功")
                                    mViewModel.viewModelScope.launch {
                                        delay(500)
                                        dismissLoading()
                                        mViewModel.submitAppeal(
                                            mType, mUrlsList.joinToString(","), mOid
                                        )
                                    }
                                }
                            } else {
                                LogUtils.eTag("fansan", info.error)
                                showToast("上传失败，请重试")
                            }
                        },
                        opt
                    )
                }
            }
        }
    }
}