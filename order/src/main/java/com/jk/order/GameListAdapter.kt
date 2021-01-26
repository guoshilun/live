package com.jk.order

import com.fansan.common.pojo.AllGameBean
import com.jk.order.databinding.ItemGameListBinding
import com.newer.library.adapter.base.BaseQuickAdapter
import com.newer.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 *@author  fansan
 *@version 2020/11/18
 */

class GameListAdapter :
    BaseQuickAdapter<AllGameBean.Game, BaseDataBindingHolder<ItemGameListBinding>>(R.layout.item_game_list) {
    override fun convert(
        holder: BaseDataBindingHolder<ItemGameListBinding>, item: AllGameBean.Game
    ) {
        holder.dataBinding?.gamebean = item
        holder.dataBinding?.executePendingBindings()
    }

}