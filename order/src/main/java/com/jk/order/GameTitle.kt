package com.jk.order

import android.content.Context
import com.blankj.utilcode.util.ConvertUtils
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

/**
 *@author  fansan
 *@version 11/24/20
 */

class GameTitle(c: Context) : ColorTransitionPagerTitleView(c){

    override fun onSelected(index: Int, totalCount: Int) {
        super.onSelected(index, totalCount)
        paint.isFakeBoldText = true
        paint.textSize = ConvertUtils.sp2px(14f).toFloat()
    }

    override fun onDeselected(index: Int, totalCount: Int) {
        super.onDeselected(index, totalCount)
        paint.isFakeBoldText = false
        paint.textSize = ConvertUtils.sp2px(12f).toFloat()
    }
}