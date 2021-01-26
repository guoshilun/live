package com.jk.jkproject.utils.ext

/**
 * Boolean链式调用
 *
 *@author  fansan
 *@version 2020/3/30
 */

//协变中间类
sealed class BooleanExt<out T>

///Nothing是所有类型的子类型
object Otherwise : BooleanExt<Nothing>()

class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(func: () -> T): BooleanExt<T> = when {
    this -> {
        WithData(func())
    }

    else -> Otherwise
}

inline fun <T> Boolean.no(func: () -> T) = when {
    this -> Otherwise
    else -> {
        WithData(func())
    }
}

inline infix fun <T> BooleanExt<T>.otherwise(func: () -> T): T {
    return when (this) {
        is Otherwise -> func()
        is WithData<T> -> this.data
    }
}



