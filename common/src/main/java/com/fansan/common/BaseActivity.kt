package com.fansan.common

import android.content.res.Resources
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.BusUtils.Bus
import com.blankj.utilcode.util.ToastUtils
import com.fansan.common.base.BaseViewModel
import com.fansan.common.base.DataBindingConfig
import com.fansan.common.ext.getAppViewModel
import com.fansan.common.ext.getVmClazz
import com.fansan.common.pojo.OrderPushBean
import com.gyf.immersionbar.ImmersionBar
import me.jessyan.autosize.AutoSizeCompat

/**
 *@author  fansan
 *@version 2020/11/11
 */

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    lateinit var mViewModel: VM
    protected lateinit var immersionBar: ImmersionBar
    private var mProgressView: WaitProgressView? = null

    val mGlobalModel:GlobalAppViewModel by lazy {
        getAppViewModel()
    }

    abstract fun initView(savedInstanceState: Bundle?)

    fun showLoading(message: String = ""){
        if (mProgressView == null) {
            mProgressView = WaitProgressView(this)
        }
        if (mProgressView?.isShowing == true) {
            if (message.isNotEmpty()) mProgressView?.setText(message)
        }else{
            mProgressView?.setText(message)?.showPopupWindow()
        }
    }

    fun dismissLoading(){
        mProgressView?.dismiss()
    }

    fun showToast(message: String){
        ToastUtils.showShort(message)
    }

    open fun initWindow() {}

    private lateinit var mDatabind: DB

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        initWindow()
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel()
        initDataBind()
        init(savedInstanceState)
        addLoadingObserve(mGlobalModel)
    }


    private fun init(savedInstanceState: Bundle?) {
        registerUiChange()
        initView(savedInstanceState)
        createObserver()

    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }


    abstract fun createObserver()


    private fun registerUiChange() {
        mViewModel.loadingChange.showDialog.observeActivity(this, Observer {
            showLoading(
                if (it.isNullOrEmpty()) {
                    ""
                } else it
            )
        })

        mViewModel.loadingChange.dismissDialog.observeActivity(this, Observer {
            dismissLoading()
        })

        mViewModel.loadingChange.toastShow.observeActivity(this) {
            showToast(it)
        }
    }

    protected fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            viewModel.loadingChange.showDialog.observeActivity(this) {
                showLoading(
                    if (it.isNullOrEmpty()) "" else it
                )
            }
            viewModel.loadingChange.dismissDialog.observeActivity(this) {
                dismissLoading()
            }

            viewModel.loadingChange.toastShow.observeActivity(this) {
                showToast(it)
            }
        }
    }

    override fun getResources(): Resources {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            runOnUiThread {
                AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
            }
        } else {
            AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        }

        return super.getResources()
    }

    private fun initDataBind() {
        val config = getDataBindingConfig()
        mDatabind = DataBindingUtil.setContentView(this, config.getLayout())
        mDatabind.lifecycleOwner = this
        val parames = config.getBindingParams()
        parames.forEach { key, any ->
            mDatabind.setVariable(key, any)
        }
        immersionBar = ImmersionBar.with(this)
        immersionBar.fitsSystemWindows(true).statusBarColor(R.color.c_FFFFFF)
            .autoStatusBarDarkModeEnable(true, .2f).init()
    }


    override fun onStart() {
        super.onStart()
        BusUtils.register(this)
    }

    override fun onStop() {
        super.onStop()
        BusUtils.unregister(this)
    }

    @Bus(tag = "orderPush", threadMode = BusUtils.ThreadMode.MAIN)
    fun orderPushReceive(bean:OrderPushBean){
        val orderPopup = OrderPopup(this,bean)
        orderPopup.showPopupWindow()
    }
}