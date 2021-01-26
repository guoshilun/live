package com.fansan.common.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blankj.utilcode.util.ConvertUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jk.jkproject.utils.ext.yes

/**
 *@author  fansan
 *@version 2020/6/18
 */

object CustomBindAdapter {

    @BindingAdapter(
        value = ["imageUrl", "placeHolder","errorImg", "radius", "centerCrop", "useCache"],
        requireAll = false
    )
    @JvmStatic
    fun imageUrl(
        view: ImageView,
        url: String?,
        placeholder: Drawable? = null,
        error: Drawable? = null,
        radius: Float = 0f,
        centerCrop: Boolean = false,
        useCache: Boolean = true
    ) {
        val options = RequestOptions()
        val transformations = mutableListOf<Transformation<Bitmap>>()
        if (centerCrop) {
            transformations.add(CenterCrop())
        }
        if (radius != 0f) {
            transformations.add(RoundedCorners(ConvertUtils.dp2px(radius)))
        }

        options.apply {
            placeholder?.let {
                placeholder(it)
            }
            error?.let {
                error(error)
            }
            transformations.isNotEmpty().yes {
                transform(*transformations.toTypedArray())
            }
        }

        Glide.with(view).load(url).apply(options).into(view)
    }

    @BindingAdapter(value = ["circleImageUrl", "placeHolder"])
    @JvmStatic
    fun circleImageUrl(view: ImageView, url: Any?, placeholde: Drawable) {
        Glide.with(view).load(url).apply(RequestOptions.bitmapTransform(CircleCrop()))
            .placeholder(placeholde).error(placeholde)
            .transition(DrawableTransitionOptions.withCrossFade(300)).into(view)
    }

    @BindingAdapter(value = ["verifyCompleteListener"])
    @JvmStatic
    fun verifyInputCompleteListener(view: EditText, callback: (() -> Unit)) {
        view.addTextChangedListener {
            if (it?.length == 6) {
                callback.invoke()
            }
        }
    }

    @BindingAdapter(value = ["progressColor"])
    @JvmStatic
    fun setRefreshProgressColor(view: SwipeRefreshLayout, color: Int) {
        view.setColorSchemeColors(color)
    }

    /* @BindingAdapter(value = ["animateHeight", "showOrHide"])
     @JvmStatic
     fun changeViewHeight(view: View, height: Float, show: Boolean) {
         if (show) {
             view.animateHeight(ConvertUtils.dp2px(height), 300)
         } else {
             view.animateHeight(0, 300)
         }
     }*/
}