package com.jk.order

import android.view.View
import android.widget.ImageView
import com.blankj.utilcode.util.ConvertUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zhpan.bannerview.BaseViewHolder

/**
 *@author  fansan
 *@version 2020/10/12
 */

class BannerViewHolder(view: View) : BaseViewHolder<String>(view) {

    override fun bindData(data: String, position: Int, pageSize: Int) {
        val opt = RequestOptions()
        opt.apply {
            transform(RoundedCorners(ConvertUtils.dp2px(5f)))
        }
        Glide.with(itemView).load(data).apply(opt).into(itemView as ImageView)
    }

}