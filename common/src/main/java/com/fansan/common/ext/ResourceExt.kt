package com.fansan.common.ext

/**
 * author : fansan
 * Created on 2019/3/8 10:08 AM.
 * description:
 */
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.fansan.common.base.BaseViewModel

fun Context.color(id: Int) = ContextCompat.getColor(this, id)

fun Context.string(id: Int) = resources.getString(id)

fun Context.stringArray(id: Int): Array<String> = resources.getStringArray(id)

fun Context.drawable(id: Int) = ResourcesCompat.getDrawable(resources, id, null)

fun Context.dimenPx(id: Int) = resources.getDimensionPixelSize(id)


fun View.color(id: Int) = context.color(id)

fun View.string(id: Int) = context.string(id)

fun View.stringArray(id: Int) = context.stringArray(id)

fun View.drawable(id: Int) = context.drawable(id)

fun View.dimenPx(id: Int) = context.dimenPx(id)


fun Fragment.color(id: Int) = context?.color(id) ?: 0

fun Fragment.string(id: Int) = context?.string(id) ?: ""

fun Fragment.stringArray(id: Int) = context?.stringArray(id) ?: arrayOf()

fun Fragment.drawable(id: Int) = context?.drawable(id)

fun Fragment.dimenPx(id: Int) = context?.dimenPx(id) ?: 0


fun RecyclerView.ViewHolder.color(id: Int) = itemView.color(id)

fun RecyclerView.ViewHolder.string(id: Int) = itemView.string(id)

fun RecyclerView.ViewHolder.stringArray(id: Int) = itemView.stringArray(id)

fun RecyclerView.ViewHolder.drawable(id: Int) = itemView.drawable(id)

fun RecyclerView.ViewHolder.dimenPx(id: Int) = itemView.dimenPx(id)
