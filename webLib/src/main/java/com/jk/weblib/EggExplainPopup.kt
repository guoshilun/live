package com.jk.weblib

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.just.agentweb.AgentWeb
import razerdp.basepopup.BaseLazyPopupWindow

/**
 *@author  fansan
 *@version 2020/11/6
 */

class EggExplainPopup(c: Context, private val url: String) : BaseLazyPopupWindow(c) {

    lateinit var mAgentWeb: AgentWeb

    init {
        popupGravity = Gravity.CENTER
        setOutSideDismiss(false)
        isOutSideTouchable = false
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.egg_explain)
    }

    override fun onViewCreated(contentView: View) {
        super.onViewCreated(contentView)

        mAgentWeb = AgentWeb.with(context).setAgentWebParent(
            findViewById(R.id.rootView), ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        ).useDefaultIndicator(Color.parseColor("#F891F0")).interceptUnkownUrl().createAgentWeb()
            .ready().go(url)

        findViewById<View>(R.id.close).setOnClickListener {
            dismiss()
        }
    }

}