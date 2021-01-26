package com.fansan.common.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fansan.common.BaseApp
import com.fansan.common.base.BaseViewModel
import java.lang.reflect.ParameterizedType



@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}


inline fun <reified VM : BaseViewModel> AppCompatActivity.getAppViewModel(): VM {
    (this.application as BaseApp).let {
            return it.getAppViewModelProvider().get(VM::class.java)
    }
}

inline fun <reified VM : BaseViewModel> Fragment.getAppViewModel(): VM {
    (this.requireActivity().application as BaseApp).let {
        return it.getAppViewModelProvider().get(VM::class.java)
    }
}






