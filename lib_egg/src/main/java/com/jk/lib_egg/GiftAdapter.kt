package com.jk.lib_egg

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jk.lib_egg.pojo.EggBean
import com.xiaote.ext.load

/**
 *@author  fansan
 *@version 2020/10/28
 */
 
class GiftAdapter :BaseQuickAdapter<EggBean.Data.GoldenMalletAward,BaseViewHolder>(R.layout.item_gift){

    override fun convert(helper: BaseViewHolder, item: EggBean.Data.GoldenMalletAward?) {
            helper.getView<ImageView>(R.id.image)
                .load(item?.g_icon)
            helper.setText(R.id.price,item?.g_amount.toString())

        if (item?.p_count != 0){
            helper.setGone(R.id.count,true)
            helper.setText(R.id.count,"X${item?.p_count}")
        }else{
            helper.setGone(R.id.count,false)
        }
    }

}