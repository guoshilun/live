package com.jk.order

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.newer.library.adapter.base.BaseQuickAdapter
import com.newer.library.adapter.base.viewholder.BaseViewHolder

/**
 *@author  fansan
 *@version 12/10/20
 */

class AddImgAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_add_img) {

    override fun convert(holder: BaseViewHolder, item: String) {
        val img = holder.getView<ImageView>(R.id.img)
        if (item == "add") {
            img.setImageResource(R.drawable.add_img)
        } else {
            Glide.with(context).load(item).into(img)
        }
    }
}