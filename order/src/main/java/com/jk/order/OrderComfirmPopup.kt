package com.jk.order

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.ConfrimPayPopupBinding
import razerdp.basepopup.BasePopupWindow

/**
 *@author  fansan
 *@version 2020/11/17
 */
 
class OrderComfirmPopup(c:Context) :BasePopupWindow(c){

    private var binding: ConfrimPayPopupBinding? = null

    init {
        popupGravity = Gravity.CENTER
        setOutSideDismiss(false)
    }

    var confirmClick:(() -> Unit)? = null

    override fun onViewCreated(contentView: View) {
        super.onViewCreated(contentView)
        binding = DataBindingUtil.bind(contentView)
        findViewById<View>(R.id.cancel).click {
            dismiss()
        }

        findViewById<View>(R.id.confirm).click {
            confirmClick?.invoke()
        }
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.confrim_pay_popup)
    }

}