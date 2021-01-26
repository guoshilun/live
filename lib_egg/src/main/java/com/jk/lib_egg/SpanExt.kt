package com.jk.lib_egg

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView

/**
 * @author fansan
 * @version 2020-01-09
 * SpannableString拓展
 */

/**
 * 将一段文字中指定range的文字改变大小
 * @param range 要改变大小的文字的范围
 * @param scale 缩放值，大于1，则比其他文字大；小于1，则比其他文字小；默认是1.5
 */
fun CharSequence.toSizeSpan(range: IntRange, scale: Float = 1.5f): CharSequence {
    return SpannableString(this).apply {
        setSpan(
            RelativeSizeSpan(scale), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将一段文字中指定range的文字改变大小
 * @param range 要改变大小的文字的范围
 * @param size dimen
 */
fun CharSequence.toSizeSpan(range: IntRange, size: Int): CharSequence {
    return SpannableString(this).apply {
        setSpan(AbsoluteSizeSpan(size), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字改变前景色
 * @param range 要改变前景色的文字的范围
 * @param color 要改变的颜色，默认是红色
 */
fun CharSequence.toColorSpan(range: IntRange, color: Int = Color.RED): CharSequence {
    return SpannableString(this).apply {
        setSpan(
            ForegroundColorSpan(color), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将一段文字中指定range的文字改变背景色
 * @param range 要改变背景色的文字的范围
 * @param color 要改变的颜色，默认是红色
 */
fun CharSequence.toBackgroundColorSpan(range: IntRange, color: Int = Color.RED): CharSequence {
    return SpannableString(this).apply {
        setSpan(
            BackgroundColorSpan(color), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将一段文字中指定range的文字添加删除线
 * @param range 要添加删除线的文字的范围
 */
fun CharSequence.toStrikeThrougthSpan(range: IntRange): CharSequence {
    return SpannableString(this).apply {
        setSpan(StrikethroughSpan(), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字添加颜色和点击事件
 * @param range 目标文字的范围
 */
fun CharSequence.toClickSpan(
    range: IntRange,
    color: Int = Color.RED,
    isUnderlineText: Boolean = false,
    clickAction: () -> Unit
): CharSequence {
    return SpannableString(this).apply {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                clickAction()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = color
                ds.isUnderlineText = isUnderlineText
            }
        }
        setSpan(clickableSpan, range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字添加style效果
 * @param range 要添加删除线的文字的范围
 */
fun CharSequence.toStyleSpan(style: Int = Typeface.BOLD, range: IntRange): CharSequence {
    return SpannableString(this).apply {
        setSpan(StyleSpan(style), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}


/*fun CharSequence.toTagSpan(
    textColor: Int,
    backgroundColor: Int,
    size: Int,
    radius: Float,
    rightMargin: Int,
    leftMargin: Int,
    textLeftPadding: Int,
    textRightPadding: Int,
    topPadding: Int,
    bottomPadding: Int,
    paintStyle: Paint.Style,
    range: IntRange
): CharSequence {
    return SpannableString(this).apply {
        setSpan(
            TagSpan(
                textColor,
                backgroundColor,
                size,
                radius,
                rightMargin,
                leftMargin,
                textLeftPadding,
                textRightPadding,
                topPadding,
                bottomPadding,
                paintStyle
            ), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}*/

/**
 * 设置自定义字体
 * @param range 范围
 */
/*fun CharSequence.toFontSpan(typeface: Typeface?, range: IntRange): CharSequence {
    return SpannableString(this).apply {
        setSpan(
            CustomTypeFace(typeface), range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}*/

/** TextView的扩展 **/
fun TextView.sizeSpan(str: String = "", range: IntRange, scale: Float = 1.5f): TextView {
    text = (if (str.isEmpty()) text else str).toSizeSpan(range, scale)
    return this
}


fun TextView.sizeSpan(str: String = "", range: IntRange, size: Int): TextView {
    text = (if (str.isEmpty()) text else str).toSizeSpan(range, size)
    return this
}

fun TextView.appendSizeSpan(str: String = "", scale: Float = 1.5f): TextView {
    append(str.toSizeSpan(0..str.length, scale))
    return this
}

fun TextView.appendSizeSpan(str: String = "", size: Int): TextView {
    append(str.toSizeSpan(0..str.length, size))
    return this
}

/*fun TextView.fontSpan(str: String = "", range: IntRange, typeface: Typeface?): TextView {
    text = (if (str.isEmpty()) text else str).toFontSpan(typeface, range)
    return this
}

fun TextView.appendFontSpan(str: String = "", typeface: Typeface?): TextView {
    append(str.toFontSpan(typeface, 0..str.length))
    return this
}*/

fun TextView.colorSpan(str: String = "", range: IntRange, color: Int = Color.RED): TextView {
    text = (if (str.isEmpty()) text else str).toColorSpan(range, color)
    return this
}

fun TextView.appendColorSpan(str: String = "", color: Int = Color.RED): TextView {
    append(str.toColorSpan(0..str.length, color))
    return this
}


/*fun TextView.appendTagSpan(
    str: String,
    textColor: Int,
    backgroundColor: Int,
    size: Int,
    radius: Float,
    rightMargin: Int,
    leftMargin: Int,
    textLeftPadding: Int,
    textRightPadding: Int,
    topPadding: Int,
    bottomPadding: Int,
    paintStyle: Paint.Style
): TextView {
    append(
        str.toTagSpan(
            textColor,
            backgroundColor,
            size,
            radius,
            rightMargin,
            leftMargin,
            textLeftPadding,
            textRightPadding,
            topPadding,
            bottomPadding,
            paintStyle,
            0..str.length
        )
    )

    return this
}*/

fun TextView.backgroundColorSpan(
    str: String = "", range: IntRange, color: Int = Color.RED
): TextView {
    text = (if (str.isEmpty()) text else str).toBackgroundColorSpan(range, color)
    return this
}

fun TextView.appendBackgroundColorSpan(str: String = "", color: Int = Color.RED): TextView {
    append(str.toBackgroundColorSpan(0..str.length, color))
    return this
}

fun TextView.strikeThrougthSpan(str: String = "", range: IntRange): TextView {
    text = (if (str.isEmpty()) text else str).toStrikeThrougthSpan(range)
    return this
}

fun TextView.appendStrikeThrougthSpan(str: String = ""): TextView {
    append(str.toStrikeThrougthSpan(0..str.length))
    return this
}

fun TextView.clickSpan(
    str: String = "",
    range: IntRange,
    color: Int = Color.RED,
    isUnderlineText: Boolean = false,
    clickAction: () -> Unit
): TextView {
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT  // 透明点击背景
    text =
        (if (str.isEmpty()) text else str).toClickSpan(range, color, isUnderlineText, clickAction)
    return this
}

fun TextView.appendClickSpan(
    str: String = "",
    color: Int = Color.RED,
    isUnderlineText: Boolean = false,
    clickAction: () -> Unit
): TextView {
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT  // 透明点击背景
    append(str.toClickSpan(0..str.length, color, isUnderlineText, clickAction))
    return this
}

fun TextView.styleSpan(str: String = "", range: IntRange, style: Int = Typeface.BOLD): TextView {
    text = (if (str.isEmpty()) text else str).toStyleSpan(style = style, range = range)
    return this
}

fun TextView.appendStyleSpan(str: String = "", style: Int = Typeface.BOLD): TextView {
    append(str.toStyleSpan(style = style, range = 0..str.length))
    return this
}
