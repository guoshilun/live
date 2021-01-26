package com.jk.order

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.fansan.common.pojo.OrderListBean
import com.jk.jkproject.utils.ext.gone
import com.jk.jkproject.utils.ext.visible
import com.newer.library.adapter.base.BaseSectionQuickAdapter
import com.newer.library.adapter.base.viewholder.BaseViewHolder

/**
 *@author  fansan
 *@version 2020/11/11
 */

class OrderListAdapter() : BaseSectionQuickAdapter<OrderListBean.Result, BaseViewHolder>(
    sectionHeadResId = R.layout.item_order_header
) {

    var type: Int = 1

    init {
        setNormalLayout(R.layout.item_order)
        addChildClickViewIds(R.id.take, R.id.rejectBtn)
    }

    override fun convertHeader(helper: BaseViewHolder, item: OrderListBean.Result) {
        helper.getView<TextView>(R.id.label).text = item.headName
    }

    override fun convert(holder: BaseViewHolder, item: OrderListBean.Result) {

        val takeBtn = holder.getView<TextView>(R.id.take)
        val rejectBen = holder.getView<TextView>(R.id.rejectBtn)
        val statusTv = when (item.status) {
            1 -> {
                if (type == 1) {
                    takeBtn.text = "取消订单"
                    takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                    takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                } else {
                    takeBtn.text = "立即接单"
                    takeBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    takeBtn.setBackgroundResource(R.drawable.shape_solid_ff5b99_10)
                }
                "待接单"
            }

            2 -> {
                if (type == 1) {
                    takeBtn.text = "再来一单"
                    takeBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    takeBtn.setBackgroundResource(R.drawable.shape_solid_ff5b99_10)
                } else {
                    takeBtn.text = "查看详情"
                    takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                    takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                }
                "取消"
            }

            3 -> {
                if (type == 1) {
                    takeBtn.text = "查看详情"
                    takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                    takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                } else {
                    takeBtn.text = "服务完成"
                    takeBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    takeBtn.setBackgroundResource(R.drawable.shape_solid_ff5b99_10)
                }

                "进行中"
            }

            4 -> {
                if (type == 1) {
                    takeBtn.text = "再来一单"
                    takeBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    takeBtn.setBackgroundResource(R.drawable.shape_solid_ff5b99_10)
                } else {
                    takeBtn.text = "查看余额"
                    takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                    takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                }
                "已完成"
            }

            5 -> {
                if (type == 1) {
                    takeBtn.text = "查看详情"
                    takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                    takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                } else {
                    takeBtn.text = "处理退款"
                    takeBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    takeBtn.setBackgroundResource(R.drawable.shape_solid_ff5b99_10)
                }
                "退款中"
            }

            6 -> {
                if (type == 1) {
                    takeBtn.text = "再来一单"
                    takeBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    takeBtn.setBackgroundResource(R.drawable.shape_solid_ff5b99_10)
                } else {
                    takeBtn.text = "查看详情"
                    takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                    takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                }
                "已退款"
            }

            7 -> {
                if (type == 1) {
                    takeBtn.text = "申诉"
                    takeBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    takeBtn.setBackgroundResource(R.drawable.shape_solid_ff5b99_10)
                    "退款被拒"
                } else {
                    takeBtn.text = "查看详情"
                    takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                    takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                    "已拒绝"
                }
            }

            8 -> {
                if (type == 1) {
                    takeBtn.text = "查看详情"
                    takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                    takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                } else {
                    takeBtn.text = "上传凭证"
                    takeBtn.setTextColor(Color.parseColor("#FFFFFF"))
                    takeBtn.setBackgroundResource(R.drawable.shape_solid_ff5b99_10)
                }
                "申诉中"
            }

            else -> {
                takeBtn.text = "查看详情"
                takeBtn.setTextColor(Color.parseColor("#FF5B99"))
                takeBtn.setBackgroundResource(R.drawable.shape_ff5b99_10)
                "已申诉"
            }
        }

        if (type == 2 && item.status == 1) {
            rejectBen.visible()
        } else {
            rejectBen.gone()
        }

        holder.setText(R.id.status, statusTv)
            .setText(R.id.date, TimeUtils.millis2String(item.c_time, "yyyy-MM-dd"))
            .setText(R.id.gameLabel, item.name).setText(R.id.username, item.nickname)
            .setText(R.id.gameLength, "${item.units}X${item.count}")
            .setText(R.id.price, "${item.sumPrice}")

        val head = holder.getView<ImageView>(R.id.head)
        Glide.with(context).load(item.picture).into(head)
    }

}