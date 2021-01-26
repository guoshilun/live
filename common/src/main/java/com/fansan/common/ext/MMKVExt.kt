package com.fansan.common.ext

import android.os.Parcelable
import com.fansan.common.utils.CacheUtil
import com.tencent.mmkv.MMKV

/**
 *@author  fansan
 *@version 2020/10/20
 */


fun Parcelable.saveKV(key:String){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode(key,this)
}

fun ByteArray.saveKV(key:String){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode(key,this)
}

fun Int.saveKV(key:String){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode(key,this)
}

fun String.saveKV(key:String){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode(key,this)
}


fun Double.saveKV(key:String){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode(key,this)
}


fun Float.saveKV(key:String){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode(key,this)
}

fun Boolean.saveKV(key:String){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode(key,this)
}


fun Long.saveKV(key:String){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode(key,this)
}

fun CacheUtil.get():MMKV{
    return MMKV.defaultMMKV()
}
