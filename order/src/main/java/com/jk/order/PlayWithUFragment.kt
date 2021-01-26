package com.jk.order

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.TypedValue
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fansan.common.base.BaseFragment
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.color
import com.fansan.common.ext.startActivity
import com.fansan.common.ext.startActivityForResult
import com.fansan.common.pojo.PlayMenuBean
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.FragmentPlayWithUBinding
import kotlinx.android.synthetic.main.activity_take_order_step_2.*
import kotlinx.android.synthetic.main.fragment_play_with_u.*
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

/**
 *@author  fansan
 *@version 11/24/20
 */

class PlayWithUFragment : BaseFragment<PlayWithUViewModel, FragmentPlayWithUBinding>() {


    private val mFragmentHelper: FragmentContainerHelper by lazy {
        FragmentContainerHelper()
    }

    var mMediaPlayer: MediaPlayer? = null

    private val mFragmentList: MutableList<PlayWithUContentFragment> = mutableListOf()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_play_with_u).addBindingParam(
            BR.model, mViewModel
        )
    }

    override fun initView(savedInstanceState: Bundle?) {
        more.click {
            startActivityForResult<AllGameActivity>() {
                if (it != null) {
                    val backId = it.getIntExtra("id", 0)
                    mViewModel.titleData.value?.playMenu?.filterIndexed { index, playMenu ->
                            if (playMenu.id.toInt() == backId) {
                                viewpager.setCurrentItem(index, true)
                                true
                            } else false
                        }
                }
            }
        }
    }

    fun initMediaPlayer(url: String) {
        if (mMediaPlayer == null) mMediaPlayer = MediaPlayer()
        mMediaPlayer?.setDataSource(url)
        mMediaPlayer?.prepare()
    }

    fun releaseMediaPlayer() {
        try {
            mMediaPlayer?.stop()
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
            mMediaPlayer = null
        } catch (e: Exception) {
        }
    }

    fun init() {
        val bean = mViewModel.titleData.value
        bean?.playMenu?.forEach {
            mFragmentList.add(
                PlayWithUContentFragment.newInstance(
                    it.id, bean.playImgList as ArrayList<PlayMenuBean.PlayImg>
                )
            )
        }
        viewpager.isUserInputEnabled = false
        viewpager.offscreenPageLimit = 5
        viewpager.adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun getItemCount(): Int = mFragmentList.size

            override fun createFragment(position: Int): Fragment {
                return mFragmentList[position]
            }

        }
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mFragmentHelper.handlePageSelected(position)
            }
        })
        initIndicator()
    }

    private fun initIndicator() {
        val navigator = CommonNavigator(mActivity)
        navigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int = mFragmentList.size

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val title = GameTitle(mActivity)
                title.selectedColor = color(R.color.c_D53FDC)
                title.normalColor = color(R.color.c_666666)
                title.text = mViewModel.titleData.value?.playMenu?.get(index)?.name
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                title.click(200) {
                    mFragmentHelper.handlePageSelected(index)
                    viewpager.setCurrentItem(index, true)
                }

                return title
            }

            override fun getIndicator(context: Context?): IPagerIndicator {
                val linePagerIndicator = LinePagerIndicator(context)
                linePagerIndicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                linePagerIndicator.lineHeight = UIUtil.dip2px(
                    context, 2.0
                ).toFloat()
                linePagerIndicator.setColors(color(R.color.c_D53FDC))
                return linePagerIndicator
            }
        }

        indicator.navigator = navigator
        mFragmentHelper.attachMagicIndicator(indicator)
        mFragmentHelper.setInterpolator(OvershootInterpolator(1.0f))
        mFragmentHelper.setDuration(300)
    }

    override fun lazyLoadData() {
        mViewModel.getMenu()
    }

    override fun createObserver() {
        mViewModel.titleData.observeFragment(this) {
            init()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): PlayWithUFragment {
            val f = PlayWithUFragment()
            val b = Bundle()
            f.arguments = b
            return f
        }
    }
}