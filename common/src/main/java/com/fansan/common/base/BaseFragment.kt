package com.fansan.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.fansan.common.WaitProgressView
import com.fansan.common.ext.getVmClazz

/**
 *@author  fansan
 *@version 11/24/20
 */

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>:Fragment() {

    protected lateinit var mDatabind: DB
    private var mProgressView: WaitProgressView? = null

    //是否第一次加载
    protected var isFirst: Boolean = true

    lateinit var mViewModel: VM

    lateinit var mActivity: FragmentActivity

    private val config: DataBindingConfig by lazy {
        getDataBindingConfig()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = createViewModel()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as FragmentActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        initView(savedInstanceState)
        createObserver()
        onVisible()
        registorDefUIChange()
        initData()
    }

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mDatabind = DataBindingUtil.inflate(inflater, config.getLayout(), container, false)
        mDatabind.lifecycleOwner = this
        val parames = config.getBindingParams()
        parames.forEach { key, any ->
            mDatabind.setVariable(key, any)
        }
        return mDatabind.root
    }


    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    /**
     * 创建观察者
     */
    abstract fun createObserver()

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lazyLoadData()
            isFirst = false
        }
    }

    fun showLoading(message: String = ""){
        if (mProgressView == null) {
            mProgressView = WaitProgressView(mActivity)
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

    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}


    private fun registorDefUIChange() {
        mViewModel.loadingChange.showDialog.observeFragment(this, Observer {
            showLoading(
                if (it.isNullOrEmpty()) "" else it
            )
        })

        mViewModel.loadingChange.dismissDialog.observeFragment(this, Observer {
            dismissLoading()
        })

        mViewModel.loadingChange.toastShow.observeFragment(this){
            showToast(it)
        }
    }

    protected fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeFragment(this) {
                showLoading(
                    if (it.isNullOrEmpty()) "" else it
                )
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeFragment(this) {
                dismissLoading()
            }
        }
    }
}