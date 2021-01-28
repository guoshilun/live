package com.jk.order

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.fansan.common.base.BaseFragment
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.*
import com.fansan.common.pojo.PlayMenuBean
import com.github.nukc.stateview.StateView
import com.jk.jkproject.utils.ext.rotate
import com.jk.order.databinding.FragmentPlayWithUContentBindingImpl
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.BaseBannerAdapter
import kotlinx.android.synthetic.main.fragment_play_with_u_content.*
import kotlinx.coroutines.*
import razerdp.basepopup.BasePopupWindow

/**
 *@author  fansan
 *@version 11/24/20
 */

class PlayWithUContentFragment :
    BaseFragment<PlayWithUViewModel, FragmentPlayWithUContentBindingImpl>() {

    private lateinit var mBannerViewPager: BannerViewPager<String, BannerViewHolder>
    private val bannerUrls = mutableListOf<String>()
    private lateinit var mAdapter: PlayWithUAdapter
    private var mID: String? = "0"
    protected var mJob: Job? = null
    private var mStateview:StateView? = null
    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_play_with_u_content).addBindingParam(
            BR.model, mViewModel
        ).addBindingParam(BR.clickProxy, ContentClickProxy())
    }

    override fun initView(savedInstanceState: Bundle?) {
        mAdapter = PlayWithUAdapter()
        mBannerViewPager =
            mDatabind.root.findViewById<BannerViewPager<String, BannerViewHolder>>(R.id.banner)
        val imgBean = arguments?.getSerializable("imgBean") as ArrayList<PlayMenuBean.PlayImg>
        mID = arguments?.getString("id", "0")
        if (mID == "0") {
            imgBean.forEach {
                bannerUrls.add(it.big_img)
            }
        } else {
            imgBean.forEach {
                if (it.id == mID) {
                    bannerUrls.add(it.big_img)
                }
            }
        }
        mStateview = StateView.inject(recyclerview)
        mBannerViewPager.adapter = object : BaseBannerAdapter<String, BannerViewHolder>() {
            override fun createViewHolder(
                parent: ViewGroup, itemView: View, viewType: Int
            ): BannerViewHolder {
                return BannerViewHolder(itemView)
            }

            override fun onBind(
                holder: BannerViewHolder?, data: String, position: Int, pageSize: Int
            ) {
                holder?.bindData(data, position, pageSize)
            }

            override fun getLayoutId(viewType: Int): Int {
                return R.layout.item_banner_img
            }

        }

        mBannerViewPager.setIndicatorSliderRadius(ConvertUtils.dp2px(2f), ConvertUtils.dp2px(4f))
            .setIndicatorSliderColor(color(R.color.c_FFFFFF), color(R.color.c_C26DED))
            .setLifecycleRegistry(lifecycle).create(bannerUrls)
        recyclerview.adapter = mAdapter

        refresh.setOnRefreshListener {
            mViewModel.mListRefreshing.value = true
            mViewModel.getList(mID ?: "0")
        }

        mAdapter.loadmoreInit(recyclerview) {
            mViewModel.getList(mID ?: "0")
        }

        mAdapter.childNoDoubleClickListener { _, view, position ->
            if (view.id == R.id.recordLayout) {
                mJob?.cancel()
                (parentFragment as PlayWithUFragment).releaseMediaPlayer()
                (parentFragment as PlayWithUFragment).initMediaPlayer(mAdapter.data[position].video)
                (parentFragment as PlayWithUFragment).mMediaPlayer?.start()
                mJob = mViewModel.viewModelScope.launch(Dispatchers.IO) {
                    try {
                        while ((parentFragment as PlayWithUFragment).mMediaPlayer?.isPlaying == true) {
                            withContext(Dispatchers.Main) {
                                (view.findViewById(R.id.record) as TextView).text =
                                    "${((parentFragment as PlayWithUFragment).mMediaPlayer?.currentPosition ?: 0) / 1000}S"
                            }
                        }
                    } finally {
                        LogUtils.eTag("finally", "finally --- ${Thread.currentThread().name}")
                        withContext(NonCancellable) {
                            withContext(Dispatchers.Main) {
                                LogUtils.eTag(
                                    "finally", "Dispatchers.Main --- ${Thread.currentThread().name}"
                                )
                                (view.findViewById(R.id.record) as TextView).text =
                                    "${mAdapter.data[position].video_duration}S"
                            }
                        }
                    }
                }
            }
        }

        mAdapter.noDoubleClickListener { _, _, position ->
            val b = Bundle()
            b.putString("u_id",mAdapter.data[position].userId)
//            ARouter.getInstance().build("/jk/homepage").with(b).navigation(mActivity)
            ARouter.getInstance().build("/order/luck").with(b).navigation(mActivity)
        }
    }

    override fun lazyLoadData() {
        mStateview?.init {
            mViewModel.mListRefreshing.value = true
            mViewModel.getList(mID ?: "0")
        }
    }

    override fun createObserver() {
        mViewModel.sortType.observeFragment(this) {
            mViewModel.mListRefreshing.value = true
            mViewModel.getList(mID ?: "0")
            mJob?.cancel()
            (parentFragment as PlayWithUFragment).releaseMediaPlayer()
        }

        mViewModel.genderType.observeFragment(this) {
            mViewModel.mListRefreshing.value = true
            mViewModel.getList(mID ?: "0")
            mJob?.cancel()
            (parentFragment as PlayWithUFragment).releaseMediaPlayer()
        }

        mViewModel.mListRefreshing.observeFragment(this) {
            if (refresh.isRefreshing != it) refresh.isRefreshing = it
        }

        mViewModel.playUserListData.observeFragment(this) {
            mAdapter.loadmoreTransaction(it, mStateview)
        }

        mViewModel.playUserListLoadFiled.observeFragment(this) {
            mAdapter.loadFailed {
                mStateview?.showRetry()
            }
        }
    }

    inner class ContentClickProxy {
        fun smartSortClick() {
            mViewModel.sortType.postValue(1)
        }

        fun priceSortClick() {
            if (mViewModel.sortType.value == 2) {
                mViewModel.sortType.postValue(3)
                priceSortLabel.text = "价格降序"
                priceSortArrow.rotate(0f, 180f)
            } else {
                mViewModel.sortType.postValue(2)
                priceSortLabel.text = "价格升序"
                priceSortArrow.rotate(180f, 0f)
            }
        }

        fun genderSortClick() {
            val sortGenderPopup = SortGenderPopup(mActivity)
            sortGenderPopup.resultCallback = {
                genderSortLabel.text = when (it) {
                    0 -> "性别不限"
                    1 -> "只看男生"
                    2 -> "只看女生"
                    else -> "性别不限"
                }
                mViewModel.genderType.postValue(it)
            }
            sortGenderPopup.showPopupWindow(genderSort)
            sortGenderPopup.onDismissListener = object : BasePopupWindow.OnDismissListener() {
                override fun onDismiss() {
                    genderSortArrow.rotate(180f, 0f)
                }
            }
            genderSortArrow.rotate(0f, 180f)
        }
    }

    override fun onPause() {
        super.onPause()
        mJob?.cancel()
        (parentFragment as PlayWithUFragment).releaseMediaPlayer()
    }

    companion object {

        @JvmStatic
        fun newInstance(
            id: String, imgBean: ArrayList<PlayMenuBean.PlayImg>?
        ): PlayWithUContentFragment {
            val f = PlayWithUContentFragment()
            val b = Bundle()
            b.putString("id", id)
            b.putSerializable("imgBean", imgBean)
            f.arguments = b
            return f
        }
    }
}