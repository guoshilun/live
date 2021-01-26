package com.jk.order

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter
import com.contrarywind.view.WheelView
import com.fansan.common.pojo.*
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.ReasonPopupBinding
import razerdp.basepopup.BasePopupWindow

/**
 *@author  fansan
 *@version 2020/11/17
 */

class ReasonPopup<T>(c: Context) : BasePopupWindow(c) {

    private var binding: ReasonPopupBinding? = null
    var mConfirmClick: ((T?) -> Unit)? = null
    var selectedReason: T? = null

    init {
        popupGravity = Gravity.BOTTOM
    }

    fun setWheelData(list: MutableList<T>) {
        val wheelView = findViewById<WheelView>(R.id.wheel)
        wheelView.setCyclic(false)
        selectedReason = list[0]
        val nameArray = list.map {
            when (it) {
                is DanBean -> {
                    (it as DanBean).dan_name
                }
                is SetPriceBean -> {
                    "${(it as SetPriceBean).limit_money}/局"
                }
                is GameListBean -> {
                    it.name
                }
                is ReasonBean -> {
                    it.text
                }
                is PriceBean -> {
                    "${it.limit_money}/局"
                }
                else -> {
                    "null"
                }
            }
        }
        wheelView.adapter = ArrayWheelAdapter(nameArray)
        wheelView.setOnItemSelectedListener {
            selectedReason = list[it]
        }
    }

    fun setTitle(title:String){
        findViewById<TextView>(R.id.title).text = title
    }

    override fun onViewCreated(contentView: View) {
        super.onViewCreated(contentView)
        binding = DataBindingUtil.bind(contentView)
        findViewById<View>(R.id.cancel).click {
            dismiss()
        }

        findViewById<View>(R.id.confirm).click {
            mConfirmClick?.invoke(selectedReason)
            dismiss()
        }
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.reason_popup)
    }

}