package com.jk.order

import com.bumptech.glide.Glide
import com.fansan.common.pojo.PlayUserListBean
import com.jk.order.databinding.ItemPlayWithUBinding
import com.newer.library.adapter.base.BaseQuickAdapter
import com.newer.library.adapter.base.module.LoadMoreModule
import com.newer.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 *@author  fansan
 *@version 11/24/20
 */

class PlayWithUAdapter:BaseQuickAdapter<PlayUserListBean,BaseDataBindingHolder<ItemPlayWithUBinding>>(R.layout.item_play_with_u),LoadMoreModule {

    init {
        addChildClickViewIds(R.id.recordLayout)
    }

    override fun convert(holder: BaseDataBindingHolder<ItemPlayWithUBinding>, item: PlayUserListBean) {
        holder.dataBinding?.bean = item
        holder.dataBinding?.executePendingBindings()
    }
}