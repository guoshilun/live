package com.jk.lib_egg

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import com.jk.lib_egg.pojo.EggBean
import com.xiaote.ext.load
import razerdp.basepopup.BasePopupWindow

/**
 *@author  fansan
 *@version 2020/10/29
 */

class ResultPopup(c: Context) :
    BasePopupWindow(c) {

    lateinit var mAdapter: GiftAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSingle: View
    private lateinit var mBottomLabel: View
    private lateinit var mIcon: ImageView
    private lateinit var mPrice: TextView
    private val mData:MutableList<EggBean.Data.GoldenMalletAward> = mutableListOf()
    var result:(() -> Unit)? = null

    init {
        popupGravity = Gravity.CENTER
        setOutSideDismiss(false)
        isOutSideTouchable = false
    }

    fun setDatas(datas: MutableList<EggBean.Data.GoldenMalletAward>){
        mData.clear()
        mData.addAll(datas)
        if (mData.size > 1) {
            val spanCount = if (mData.size > 5) 5 else mData.size
            mRecyclerView.layoutManager =
                GridLayoutManager(
                    context,
                    spanCount
                )
            mRecyclerView.visible()
            mSingle.gone()
            mAdapter.setNewData(mData)
        } else {
            mIcon.load(mData[0].g_icon)
            mPrice.text = mData[0].g_amount.toString()
        }
    }

    override fun onCreateContentView(): View {
        val contentView = createPopupById(R.layout.popup_result)
        mRecyclerView = contentView.findViewById(R.id.recyclerView)
        mSingle = contentView.findViewById(R.id.singleGift)
        mBottomLabel = contentView.findViewById(R.id.bottomLabel)
        mIcon = contentView.findViewById(R.id.image)
        mPrice = contentView.findViewById(R.id.price)
        mAdapter = GiftAdapter()
        mRecyclerView.adapter = mAdapter
        RecyclerViewDivider.with(context).size(5).asSpace().build().addTo(mRecyclerView)
        mBottomLabel.click {
            dismiss()
            result?.invoke()
        }
        return contentView
    }


    override fun showPopupWindow() {
        super.showPopupWindow()
    }

}