package com.jk.order

import com.fansan.common.pojo.Order
import com.jk.order.databinding.ItemOrderLuckMineBinding
import com.newer.library.adapter.base.BaseQuickAdapter
import com.newer.library.adapter.base.viewholder.BaseDataBindingHolder

class OrderLuckAdapter: BaseQuickAdapter<Order, BaseDataBindingHolder<ItemOrderLuckMineBinding>>(R.layout.item_order_luck_mine) {
    override fun convert(holder: BaseDataBindingHolder<ItemOrderLuckMineBinding>, item: Order) {
        holder.dataBinding?.bean = item
        holder.dataBinding?.executePendingBindings()
    }
}