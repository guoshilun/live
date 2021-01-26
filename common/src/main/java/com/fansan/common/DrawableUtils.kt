package com.fansan.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet

/**
 *@author  fansan
 *@version 2020/10/15
 */

class DrawableUtils {

    private var leftDrawableWidth = 0
    private var leftDrawableHeight: Int = 0
    private var topDrawableWidth = 0
    private var topDrawableHeight: Int = 0
    private var rightDrawableWidth = 0
    private var rightDrawableHeight: Int = 0
    private var bottomDrawableWidth = 0
    private var bottomDrawableHeight: Int = 0
    private val defaultSize:Int = -0x1


    fun getDrawable(context: Context, attrs: AttributeSet): Array<Drawable?> {
        val drawables = arrayOfNulls<Drawable>(4)
        val array = context.obtainStyledAttributes(attrs, R.styleable.DrawableTextView)
        drawables[0] = array.getDrawable(R.styleable.DrawableTextView_leftDrawable)
        leftDrawableWidth = array.getDimensionPixelSize(
            R.styleable.DrawableTextView_leftDrawableWidth, defaultSize
        )
        leftDrawableHeight = array.getDimensionPixelSize(
            R.styleable.DrawableTextView_leftDrawableHeight, defaultSize
        )
        drawables[1] = array.getDrawable(R.styleable.DrawableTextView_topDrawable)
        topDrawableWidth =
            array.getDimensionPixelSize(R.styleable.DrawableTextView_topDrawableWidth, defaultSize)
        topDrawableHeight = array.getDimensionPixelSize(
            R.styleable.DrawableTextView_topDrawableHeight, defaultSize
        )
        drawables[2] = array.getDrawable(R.styleable.DrawableTextView_rightDrawable)
        rightDrawableWidth = array.getDimensionPixelSize(
            R.styleable.DrawableTextView_rightDrawableWidth, defaultSize
        )
        rightDrawableHeight = array.getDimensionPixelSize(
            R.styleable.DrawableTextView_rightDrawableHeight, defaultSize
        )
        drawables[3] = array.getDrawable(R.styleable.DrawableTextView_bottomDrawable)
        bottomDrawableWidth = array.getDimensionPixelSize(
            R.styleable.DrawableTextView_bottomDrawableWidth, defaultSize
        )
        bottomDrawableHeight = array.getDimensionPixelSize(
            R.styleable.DrawableTextView_bottomDrawableHeight, defaultSize
        )
        array.recycle()
        setDrawableBounds(drawables)
        return drawables
    }

    fun setDrawableBounds(drawable: Array<Drawable?>) {
        if (drawable[0] != null) drawable[0]!!.setBounds(
            0,
            0,
            retestDrawableWidth(drawable[0], leftDrawableWidth),
            retestDrawableHeight(drawable[0], leftDrawableHeight)
        )
        if (drawable[1] != null) drawable[1]!!.setBounds(
            0,
            0,
            retestDrawableWidth(drawable[1], topDrawableWidth),
            retestDrawableHeight(drawable[1], topDrawableHeight)
        )
        if (drawable[2] != null) drawable[2]!!.setBounds(
            0,
            0,
            retestDrawableWidth(drawable[2], rightDrawableWidth),
            retestDrawableHeight(drawable[2], rightDrawableHeight)
        )
        if (drawable[3] != null) drawable[3]!!.setBounds(
            0,
            0,
            retestDrawableWidth(drawable[3], bottomDrawableWidth),
            retestDrawableHeight(drawable[3], bottomDrawableHeight)
        )
    }

    fun setDrawableBounds(drawable: Array<Drawable?>, width: Int, height: Int) {
        if (drawable[0] != null) drawable[0]!!.setBounds(
            0, 0, retestDrawableWidth(drawable[0], width), retestDrawableHeight(drawable[0], height)
        )
        if (drawable[1] != null) drawable[1]!!.setBounds(
            0, 0, retestDrawableWidth(drawable[1], width), retestDrawableHeight(drawable[1], height)
        )
        if (drawable[2] != null) drawable[2]!!.setBounds(
            0, 0, retestDrawableWidth(drawable[2], width), retestDrawableHeight(drawable[2], height)
        )
        if (drawable[3] != null) drawable[3]!!.setBounds(
            0, 0, retestDrawableWidth(drawable[3], width), retestDrawableHeight(drawable[3], height)
        )
    }

    /**
     * 重新测量图标的宽度
     * @param drawable  图标
     * @param drawableWidth  图标宽度
     * @return
     */
    private fun retestDrawableWidth(drawable: Drawable?, drawableWidth: Int): Int {
        var finalDrawableWidth = drawableWidth
        if (drawable != null) {
            if (drawableWidth == defaultSize) {
                finalDrawableWidth = drawable.intrinsicWidth
            }
            return finalDrawableWidth
        }
        return 0
    }

    /**
     * 重新测量图标的高度
     * @param drawable  图标
     * @param drawableHeight  图标高度
     * @return
     */
    private fun retestDrawableHeight(drawable: Drawable?, drawableHeight: Int): Int {
        var finalDrawableHeight = drawableHeight
        if (drawable != null) {
            if (drawableHeight == defaultSize) {
                finalDrawableHeight = drawable.intrinsicHeight
            }
            return finalDrawableHeight
        }
        return 0
    }

    companion object {
        fun get(): DrawableUtils {
            return DrawableUtils()
        }
    }
}