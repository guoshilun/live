package com.fansan.common

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet

/**
 *@author  fansan
 *@version 2020/10/27
 */

class DrawableTextView : AppCompatTextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        val drawables: Array<Drawable?> = DrawableUtils.get().getDrawable(context, attr)
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3])
    }

    fun setTopView(c: Context, id: Int, width: Int, height: Int) {
        val drawable = ResourcesCompat.getDrawable(c.resources, id, null)
        val drawables = arrayOfNulls<Drawable>(4)
        drawables[1] = drawable
        DrawableUtils.get().setDrawableBounds(drawables, width, height)
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3])
    }

    fun setLeftView(c: Context, id: Int, width: Int, height: Int) {
        val drawable = ResourcesCompat.getDrawable(c.resources,id,null)
        val drawables = arrayOfNulls<Drawable>(4)
        drawables[0] = drawable
        DrawableUtils.get().setDrawableBounds(drawables, width, height)
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3])
    }
}