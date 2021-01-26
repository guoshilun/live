package com.fansan.common

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.jk.jkproject.utils.ext.gone
import com.jk.jkproject.utils.ext.otherwise
import com.jk.jkproject.utils.ext.visible
import com.jk.jkproject.utils.ext.yes
import razerdp.basepopup.BasePopupWindow

/**
 *@author  fansan
 *@version 2020/6/23
 */


class WaitProgressView(context: Context) : BasePopupWindow(context) {

    init {
        popupGravity = Gravity.CENTER
        isOutSideTouchable = false
        setOutSideDismiss(false)
        setBackPressEnable(true)
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.layout_custom_progress_dialog_view)
    }

    fun setText(text: String): BasePopupWindow {
        text.isEmpty().yes {
            findViewById<TextView>(R.id.loading_tips).gone()
        }.otherwise {
            findViewById<TextView>(R.id.loading_tips).visible()
            findViewById<TextView>(R.id.loading_tips).text = text
        }
        return this
    }

}
