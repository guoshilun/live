package com.fansan.common.ext

import com.blankj.utilcode.util.LogUtils
import com.fansan.common.BuildConfig

const val TAG = "fansangg"

var showLog = BuildConfig.DEBUG

private enum class LEVEL {
    V, D, I, W, E,J
}

fun String.logv(tag: String = TAG) =
    log(LEVEL.V, tag, this)
fun String.logd(tag: String = TAG) =
    log(LEVEL.D, tag, this)
fun String.logi(tag: String = TAG) =
    log(LEVEL.I, tag, this)
fun String.logw(tag: String = TAG) =
    log(LEVEL.W, tag, this)
fun String.loge(tag: String = TAG) =
    log(LEVEL.E, tag, this)
fun String.logj(tag: String = TAG) = log(LEVEL.J, tag, this)

private fun log(level: LEVEL, tag: String, message: String) {
    if (!showLog) return
    when (level) {
        LEVEL.V -> LogUtils.vTag(tag, message)
        LEVEL.D -> LogUtils.dTag(tag, message)
        LEVEL.I -> LogUtils.iTag(tag, message)
        LEVEL.W -> LogUtils.wTag(tag, message)
        LEVEL.E -> LogUtils.eTag(tag, message)
        LEVEL.J -> LogUtils.json(tag, message)
    }
}