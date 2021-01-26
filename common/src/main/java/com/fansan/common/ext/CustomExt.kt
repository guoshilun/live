package com.fansan.common.ext

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.Utils

import com.fansan.common.R
import com.github.nukc.stateview.StateView
import com.jk.jkproject.utils.ext.otherwise
import com.jk.jkproject.utils.ext.yes
import com.newer.library.adapter.base.BaseQuickAdapter
import java.math.BigDecimal
import java.math.RoundingMode
import java.nio.ByteBuffer
import kotlin.math.roundToInt

/**
 *@author  fansan
 *@version 2020/6/17
 */

fun String.formatDouble(): String? {
    return formatDouble(1) ?: this
}


/**
 * 字符串保留小数,0取整
 * @param scale Int 保留位
 * @return String?
 */

fun String.formatDouble(scale: Int): String? {
    return this.let {
        val num = BigDecimal(it.toDouble()).setScale(scale, RoundingMode.UP).toDouble()
        (num.roundToInt() - num == 0.0).yes {
            num.toLong().toString()
        }.otherwise {
            num.toString()
        }
    }
}

/**
 * 隐藏软键盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}


fun ViewPager2.init(
    fragmentList: MutableList<Fragment>,
    manager: FragmentManager,
    lifecycler: Lifecycle,
    canScroll: Boolean = false,
    defaultPage: Int = 0
): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = canScroll
    this.offscreenPageLimit = fragmentList.size
    //设置适配器
    adapter = object : FragmentStateAdapter(manager, lifecycler) {
        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getItemCount() = fragmentList.size
    }

    setCurrentItem(defaultPage, false)
    return this
}

private var <T : View>T.itemClickLastTime: Long
    get() = if (getTag(R.id.itemClickLastTime) != null) getTag(R.id.itemClickLastTime) as Long else 0
    set(value) {
        setTag(R.id.itemClickLastTime, value)
    }

private var <T : View> T.itemClickDelay: Long
    get() = if (getTag(R.id.itemClickDelay) != null) getTag(R.id.itemClickDelay) as Long else -1
    set(value) {
        setTag(R.id.itemClickDelay, value)
    }

private fun <T : View> T.itemClickEnable(): Boolean {
    var clickable = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - itemClickLastTime >= itemClickDelay) {
        clickable = true
    }
    itemClickLastTime = currentClickTime
    return clickable
}

private var <T : View>T.itemChildClickLastTime: Long
    get() = if (getTag(R.id.itemChildClickLastTime) != null) getTag(R.id.itemChildClickLastTime) as Long else 0
    set(value) {
        setTag(R.id.itemChildClickLastTime, value)
    }

private var <T : View> T.itemChildClickDelay: Long
    get() = if (getTag(R.id.itemChildClickDelay) != null) getTag(R.id.itemChildClickDelay) as Long else -1
    set(value) {
        setTag(R.id.itemChildClickDelay, value)
    }

private fun <T : View> T.itemChildClickEnable(): Boolean {
    var clickable = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - itemChildClickLastTime >= itemChildClickDelay) {
        clickable = true
    }
    itemChildClickLastTime = currentClickTime
    return clickable
}

/**
 * 给adapter拓展的，防止重复点击item
 */

fun BaseQuickAdapter<*, *>.noDoubleClickListener(
    interval: Long = 1000,
    action: (adapter: BaseQuickAdapter<*, *>, view: View, position: Int) -> Unit
) {


    setOnItemClickListener { adapter, view, position ->
        view.itemClickDelay = interval
        if (view.itemClickEnable()) {
            action(adapter, view, position)
        }
    }
}

/**
 * 给adapter拓展的，防止重复点击item
 */
fun BaseQuickAdapter<*, *>.childNoDoubleClickListener(
    interval: Long = 1000,
    action: (adapter: BaseQuickAdapter<*, *>, view: View, position: Int) -> Unit
) {

    setOnItemChildClickListener { adapter, view, position ->
        view.itemChildClickDelay = interval
        if (view.itemChildClickEnable()) {
            action(adapter, view, position)
        }
    }
}




fun <T> BaseQuickAdapter<T, *>.loadmoreTransaction(list: MutableList<T>, stateView: StateView?) {
    this.loadMoreModule.apply {
        isLoading.yes {
            list.isNotEmpty().yes {
                addData(list)
                loadMoreComplete()
            }.otherwise {
                loadMoreEnd()
            }
        }.otherwise {
            list.isEmpty().yes {
                stateView?.showEmpty()
                setList(list)
            }.otherwise {
                stateView?.showContent()
                setList(list)
            }
        }
    }

}


fun BaseQuickAdapter<*, *>.loadFailed(notLoadmoreBlock: () -> Unit) {
    this.loadMoreModule.apply {
        isLoading.yes {
            loadMoreFail()
        }.otherwise {
            notLoadmoreBlock.invoke()
        }
    }

}

fun BaseQuickAdapter<*, *>.loadmoreInit(recyclerView: RecyclerView,loadMoreRequest: () -> Unit) {
    recyclerView.adapter = this
    this.loadMoreModule.apply {
        isEnableLoadMore = true
        isEnableLoadMoreIfNotFullPage = true
        setOnLoadMoreListener {
            loadMoreRequest.invoke()
        }
    }
}

fun ByteArray.toBuffer(): ByteBuffer {
    return ByteBuffer.wrap(this)
}

fun ByteArray.getHeadType(): Int {
    return toBuffer().short.toInt()
}


fun String.md5(): String {
    return EncryptUtils.encryptMD5ToString(this)
}

fun StateView.init(request: () -> Unit) {
    showLoading()
    request.invoke()
    onRetryClickListener = object : StateView.OnRetryClickListener {
        override fun onRetryClick() {
            request.invoke()
        }

    }
}

fun StateView.show(list: MutableList<Nothing>) {
    list.isNullOrEmpty().yes {
        showEmpty()
    } otherwise {
        showContent()
    }
}
