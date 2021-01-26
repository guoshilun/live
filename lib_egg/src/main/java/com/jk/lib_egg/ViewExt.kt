package com.jk.lib_egg

/**
 * author : fansan
 * Created on 2019/3/8 10:10 AM.
 * description:
 */
import android.animation.IntEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * 设置View的高度
 */
fun View.height(height: Int): View {
    val params = layoutParams
    params.height = height
    layoutParams = params
    return this
}


/**
 * 设置View高度，限制在min和max范围之内
 * @param h
 * @param min 最小高度
 * @param max 最大高度
 */
fun View.limitHeight(h: Int, min: Int, max: Int): View {
    val params = layoutParams
    when {
        h < min -> params.height = min
        h > max -> params.height = max
        else -> params.height = h
    }
    layoutParams = params
    return this
}

/**
 * 设置View的宽度
 */
fun View.width(width: Int): View {
    val params = layoutParams
    params.width = width
    layoutParams = params
    return this
}

/**
 * 设置View宽度，限制在min和max范围之内
 * @param w
 * @param min 最小宽度
 * @param max 最大宽度
 */
fun View.limitWidth(w: Int, min: Int, max: Int): View {
    val params = layoutParams
    when {
        w < min -> params.width = min
        w > max -> params.width = max
        else -> params.width = w
    }
    layoutParams = params
    return this
}

/**
 * 设置View的宽度和高度
 * @param width 要设置的宽度
 * @param height 要设置的高度
 */
fun View.widthAndHeight(width: Int, height: Int): View {
    val params = layoutParams
    params.width = width
    params.height = height
    layoutParams = params
    return this
}

/**
 * 设置View的margin
 * @param leftMargin 默认保留原来的
 * @param topMargin 默认是保留原来的
 * @param rightMargin 默认是保留原来的
 * @param bottomMargin 默认是保留原来的
 */
fun View.margin(leftMargin: Int = Int.MAX_VALUE,
                topMargin: Int = Int.MAX_VALUE,
                rightMargin: Int = Int.MAX_VALUE,
                bottomMargin: Int = Int.MAX_VALUE): View {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    if (leftMargin != Int.MAX_VALUE) params.leftMargin = leftMargin
    if (topMargin != Int.MAX_VALUE) params.topMargin = topMargin
    if (rightMargin != Int.MAX_VALUE) params.rightMargin = rightMargin
    if (bottomMargin != Int.MAX_VALUE) params.bottomMargin = bottomMargin
    layoutParams = params
    return this
}

/**
 * 设置宽度，带有过渡动画
 * @param targetValue 目标宽度
 * @param duration 时长
 * @param action 可选行为
 */
fun View.animateWidth(targetValue: Int, duration: Long = 400, action: ((Float) -> Unit)? = null) {
    ValueAnimator.ofInt(width, targetValue).apply {
        addUpdateListener {
            width(it.animatedValue as Int)
            action?.invoke((it.animatedFraction))
        }
        setDuration(duration)
        start()
    }
}

/**
 * 设置高度，带有过渡动画
 * @param targetValue 目标高度
 * @param duration 时长
 * @param action 可选行为
 */
fun View.animateHeight(targetValue: Int, duration: Long = 400, action: ((Float) -> Unit)? = null) {
    ValueAnimator.ofInt(height, targetValue).apply {
        addUpdateListener {
            height(it.animatedValue as Int)
            action?.invoke((it.animatedFraction))
        }
        setDuration(duration)
        start()
    }
}

/**
 * 设置宽度和高度，带有过渡动画
 * @param targetWidth 目标宽度
 * @param targetHeight 目标高度
 * @param duration 时长
 * @param action 可选行为
 */
fun View.animateWidthAndHeight(targetWidth: Int,
                               targetHeight: Int,
                               duration: Long = 400,
                               action: ((Float) -> Unit)? = null) {
    val startHeight = height
    val evaluator = IntEvaluator()
    ValueAnimator.ofInt(width, targetWidth).apply {
        addUpdateListener {
            widthAndHeight(
                it.animatedValue as Int,
                evaluator.evaluate(it.animatedFraction, startHeight, targetHeight)
            )
            action?.invoke((it.animatedFraction))
        }
        setDuration(duration)
        start()
    }
}

/**
 * 设置点击监听, 并实现事件节流
 */

private var <T : View>T.clickLastTime: Long
    get() = if (getTag(R.id.clickLastTime) != null) getTag(R.id.clickLastTime) as Long else 0
    set(value) {
        setTag(R.id.clickLastTime, value)
    }

private var <T : View> T.clickDelay: Long
    get() = if (getTag(R.id.clickDelay) != null) getTag(R.id.clickDelay) as Long else -1
    set(value) {
        setTag(R.id.clickDelay, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var clickable = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - clickLastTime >= clickDelay) {
        clickable = true
    }
    clickLastTime = currentClickTime
    return clickable
}

fun <T : View> T.click(delay: Long = 500, block: (T) -> Unit) {
    clickDelay = delay
    setOnClickListener {
        if (clickEnable()) {
            block(this)
        }
    }
}


/**
 * 设置长按监听
 */
fun View.longClick(action: (view: View) -> Boolean) {
    setOnLongClickListener {
        action(it)
    }
}


/*** 可见性相关 ****/
fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

val View.isGone: Boolean
    get() {
        return visibility == View.GONE
    }

val View.isVisible: Boolean
    get() {
        return visibility == View.VISIBLE
    }

val View.isInvisible: Boolean
    get() {
        return visibility == View.INVISIBLE
    }

/**
 * 切换View的可见性
 */
fun View.toggleVisibility() {
    visibility = if (visibility == View.GONE) View.VISIBLE else View.GONE
}


/**
 * 获取View的截图, 支持获取整个RecyclerView列表的长截图
 * 注意：调用该方法时，请确保View已经测量完毕，如果宽高为0，则将抛出异常
 */
fun View.toBitmap(): Bitmap {
    if (measuredWidth == 0 || measuredHeight == 0) {
        throw RuntimeException("调用该方法时，请确保View已经测量完毕，如果宽高为0，则抛出异常以提醒！")
    }
    return when (this) {
        is RecyclerView -> {
            this.scrollToPosition(0)
            this.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )

            val bmp = Bitmap.createBitmap(width, measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmp)

            //draw default bg, otherwise will be black
            if (background != null) {
                background.setBounds(0, 0, width, measuredHeight)
                background.draw(canvas)
            } else {
                canvas.drawColor(Color.WHITE)
            }
            this.draw(canvas)
            //恢复高度
            this.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST)
            )
            bmp //return
        }
        else -> {
            val screenshot =
                Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(screenshot)
            if (background != null) {
                background.setBounds(0, 0, width, measuredHeight)
                background.draw(canvas)
            } else {
                canvas.drawColor(Color.WHITE)
            }
            draw(canvas) // 将 view 画到画布上
            screenshot //return
        }
    }
}

fun View.rotate(starF: Float, endF: Float, duration: Long = 200) {

    val rotation = ObjectAnimator.ofFloat(this, "Rotation", starF, endF)
    rotation.duration = duration
    rotation.start()
}


/**
 * 给TextView的drawable设置大小
 * @param width Drawable的宽度
 * @param height Drawable的高度
 */
fun TextView.sizeDrawable(width: Int,
                          height: Int,
                          leftDrawable: Int = 0,
                          topDrawable: Int = 0,
                          rightDrawable: Int = 0,
                          bottomDrawable: Int = 0): TextView {
    val rect = Rect(0, 0, width, height)
    setCompoundDrawables(
        findDrawable(leftDrawable, 0, this)?.apply { bounds = rect },
        findDrawable(topDrawable, 1, this)?.apply { bounds = rect },
        findDrawable(rightDrawable, 2, this)?.apply { bounds = rect },
        findDrawable(bottomDrawable, 3, this)?.apply { bounds = rect })
    return this
}


private fun findDrawable(drawableRes: Int, index: Int, textView: TextView): Drawable? {
    if (drawableRes != 0) return textView.drawable(drawableRes)
    if (textView.compoundDrawables.isNotEmpty()) return textView.compoundDrawables[index]
    return null
}

/**
 * @param size 会同时作用于Drawable宽高
 */
fun TextView.sizeDrawable(size: Int,
                          leftDrawable: Int = 0,
                          topDrawable: Int = 0,
                          rightDrawable: Int = 0,
                          bottomDrawable: Int = 0): TextView {
    sizeDrawable(size, size, leftDrawable, topDrawable, rightDrawable, bottomDrawable)
    return this
}


// 所有子View
inline val ViewGroup.children
    get() = (0 until childCount).map { getChildAt(it) }