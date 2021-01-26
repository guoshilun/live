package com.jk.order

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fansan.common.pojo.AllGameBean
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.jk.jkproject.utils.ext.gone
import com.jk.order.databinding.ItemGameBinding
import com.newer.library.adapter.base.BaseQuickAdapter
import com.newer.library.adapter.base.viewholder.BaseDataBindingHolder
import me.fansan.core.ext.util.dp

/**
 *@author  fansan
 *@version 2020/11/18
 */

class GameAdapter :
    BaseQuickAdapter<MutableList<AllGameBean.Game>, BaseDataBindingHolder<ItemGameBinding>>(R.layout.item_game) {

    var childClick: ((Int) -> Unit)? = null

    override fun convert(holder: BaseDataBindingHolder<ItemGameBinding>, item: MutableList<AllGameBean.Game>) {
        holder.dataBinding?.executePendingBindings()

        val rv = holder.getView<RecyclerView>(R.id.recyclerview)
        val adapter = GameListAdapter()
        rv.adapter = adapter
        adapter.setOnItemClickListener { _, _, position ->
            childClick?.invoke(item[position].id)
        }
        when (holder.layoutPosition) {
            0 -> {
                holder.getView<TextView>(R.id.categoryName).text = "手游"
            }
            1 -> {
                holder.getView<TextView>(R.id.categoryName).text = "端游"
            }
            2 -> {
                holder.getView<TextView>(R.id.categoryName).text = "其他"
                if (item.isEmpty()){
                    holder.itemView.gone()
                }
            }
        }

        adapter.setNewInstance(item)
        if (rv.itemDecorationCount <= 0) {
            context.dividerBuilder().size(13.dp).asSpace().build().addTo(rv)
        }
    }

}