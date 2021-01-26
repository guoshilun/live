package com.jk.order

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jk.jkproject.utils.ext.click
import com.jk.order.databinding.PopupChooseGenderBinding
import razerdp.basepopup.BasePopupWindow

/**
 *@author  fansan
 *@version 11/24/20
 */

class SortGenderPopup(c:Context):BasePopupWindow(c) {

    var resultCallback :((Int) -> Unit)? = null

    override fun onViewCreated(contentView: View) {
        super.onViewCreated(contentView)
        DataBindingUtil.bind<PopupChooseGenderBinding>(contentView)
        findViewById<View>(R.id.unlimited).click {
            resultCallback?.invoke(0)
            dismiss()
        }

        findViewById<View>(R.id.male).click {
            resultCallback?.invoke(1)
            dismiss()
        }

        findViewById<View>(R.id.female).click {
            resultCallback?.invoke(2)
            dismiss()
        }

    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.popup_choose_gender)
    }
}