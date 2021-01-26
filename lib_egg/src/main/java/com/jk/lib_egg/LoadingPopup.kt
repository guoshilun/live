package com.jk.lib_egg

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import razerdp.basepopup.BasePopupWindow

/**
 *@author  fansan
 *@version 2020/11/2
 */

class LoadingPopup(c: Context) : BasePopupWindow(c) {

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
        if (text.isEmpty()) {
            findViewById<TextView>(R.id.loading_tips).gone()
        } else {
            findViewById<TextView>(R.id.loading_tips).visible()
            findViewById<TextView>(R.id.loading_tips).text = text
        }
        return this
    }

    override fun showPopupWindow() {
        super.showPopupWindow()
    }
}