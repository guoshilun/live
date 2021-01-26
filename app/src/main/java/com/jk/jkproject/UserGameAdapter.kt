package com.jk.jkproject

import android.view.View
import com.fansan.common.utils.CacheUtil
import com.jk.jkproject.databinding.ItemUserGameBinding
import com.newer.library.adapter.base.BaseQuickAdapter
import com.newer.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 *@author  fansan
 *@version 12/3/20
 */

class UserGameAdapter :
    BaseQuickAdapter<UserGameListBean.DataBean, BaseDataBindingHolder<ItemUserGameBinding>>(R.layout.item_user_game) {

    init {
        addChildClickViewIds(R.id.takeOrder, R.id.recordLayout, R.id.img)
    }

    override fun convert(
        holder: BaseDataBindingHolder<ItemUserGameBinding>, item: UserGameListBean.DataBean
    ) {
        holder.dataBinding?.bean = item
        holder.dataBinding?.executePendingBindings()
        holder.setGone(R.id.takeOrder, CacheUtil.getUserId() == item.userId)
    }

}