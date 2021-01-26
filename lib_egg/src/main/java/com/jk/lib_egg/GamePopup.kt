package com.jk.lib_egg

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.jk.lib_egg.pojo.GameListBean
import com.xiaote.ext.load
import razerdp.basepopup.BasePopupWindow
import razerdp.util.animation.AnimationHelper
import razerdp.util.animation.TranslationConfig

/**
 *@author  fansan
 *@version 2020/10/29
 */

class GamePopup(c: Context) : BasePopupWindow(c) {

    init {
        popupGravity = Gravity.BOTTOM
        setOutSideDismiss(true)
        isOutSideTouchable = false
    }

    var coinClick: (() -> Unit)? = null
    var eggClick: (() -> Unit)? = null

    private var mProgress: View? = null
    private var mProgressBar: View? = null
    private var mContent: View? = null
    private var mCoinImg: ImageView? = null
    private var mEggImg: ImageView? = null
    private var mCoinName: TextView? = null
    private var mEggName: TextView? = null

    override fun onCreateContentView(): View {
        val contentView = createPopupById(R.layout.popup_game)
        contentView.findViewById<View>(R.id.coin).click {
                coinClick?.invoke()
            }
        contentView.findViewById<View>(R.id.egg).click {
                eggClick?.invoke()
            }

        mCoinImg = contentView.findViewById(R.id.coinIcon)
        mEggImg = contentView.findViewById(R.id.eggIcon)
        mCoinName = contentView.findViewById(R.id.coinName)
        mEggName = contentView.findViewById(R.id.eggName)

        mProgress = contentView.findViewById(R.id.progress)
        mProgressBar = contentView.findViewById(R.id.progressbar)
        mProgress?.visible()
        mContent = contentView.findViewById(R.id.content)
        mContent?.gone()

        return contentView
    }

    fun updateGame(dataBean: MutableList<GameListBean.DataBean>?) {
        if (dataBean.isNullOrEmpty()) {
            Toast.makeText(context,"isNullOrEmpty",Toast.LENGTH_SHORT).show()
            mProgress?.visible()
            mProgressBar?.gone()
            mContent?.gone()
        } else {
            mCoinName?.text = dataBean[0].name
            mEggName?.text = dataBean[1].name
            mCoinImg?.load(dataBean[0].icon, error = R.drawable.coin)
            mEggImg?.load(dataBean[1].icon, error = R.drawable.egg)
            mProgress?.gone()
            mContent?.visible()
        }

    }

    override fun onCreateShowAnimation(): Animation {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.FROM_BOTTOM)
            .toShow()
    }



    override fun onCreateDismissAnimation(): Animation {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.TO_BOTTOM)
            .toShow()
    }
}