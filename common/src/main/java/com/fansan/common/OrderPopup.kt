package com.fansan.common

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.launcher.ARouter
import com.fansan.common.databinding.OrderPopupBinding
import com.fansan.common.pojo.OrderPushBean
import com.jk.jkproject.utils.ext.click
import com.jk.jkproject.utils.ext.gone
import me.fansan.core.ext.util.dp
import razerdp.basepopup.BaseLazyPopupWindow
import razerdp.util.animation.AnimationHelper
import razerdp.util.animation.Direction
import razerdp.util.animation.TranslationConfig

/**
 *@author  fansan
 *@version 12/28/20
 */

class OrderPopup(c: Context, private val bean: OrderPushBean) : BaseLazyPopupWindow(c) {

    private var binding: OrderPopupBinding? = null
    var contactCallback: ((targetId: String, nickName: String) -> Unit)? = null

    /**
     *  Message bean = new Message();
    bean.setUserId(SPUtils.getUserId());
    bean.setTargetId(userInfo.getUserId());
    bean.setUserName(userInfo.getNickname());
    bean.setPicture(userInfo.getPicture());
    Intent intent3 = new Intent(this, LiveMessageActivity.class);
    intent3.putExtra("type", 3);
    intent3.putExtra("message", bean);
    startActivity(intent3);
     */

    init {
        setOutSideDismiss(true)
        setBackPressEnable(true)
        popupGravity = Gravity.TOP
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.order_popup)
    }

    override fun onViewCreated(contentView: View) {
        super.onViewCreated(contentView)
        binding = DataBindingUtil.bind(contentView)
        binding?.bean = bean
        binding?.executePendingBindings()

        when (bean.pushType) {
            1 -> {
                binding?.leftBtn?.gone()
                binding?.price?.setLeftView(context, R.drawable.me_diamon_blue, 16.dp, 12.dp)
                when (bean.status) {
                    3 -> {
                        binding?.rightBtn?.text = "联系大神"
                        binding?.priceLabel?.text = "实付："
                        binding?.root?.click {
                            contactCallback?.invoke(bean.userId, bean.nickname)
                            dismiss()
                        }
                    }

                    2 -> {
                        binding?.priceLabel?.text = "已退款："
                        if (bean.cancelType == 2) {
                            binding?.rightBtn?.text = "联系大神"
                            binding?.root?.click {
                                contactCallback?.invoke(bean.userId, bean.nickname)
                                dismiss()
                            }
                        } else if (bean.cancelType == 3) {
                            binding?.rightBtn?.text = "查看详情"
                            binding?.root?.click {
                                val b = Bundle()
                                b.putInt("type", 0)
                                b.putString("orderId", bean.orderId)
                                ARouter.getInstance().build("/order/cancel").with(b).navigation()
                                dismiss()
                            }
                        }
                    }

                    6 -> {
                        binding?.rightBtn?.text = "查看详情"
                        binding?.priceLabel?.text = "已退款："
                        binding?.root?.click {
                            val b = Bundle()
                            b.putInt("type", 0)
                            b.putString("orderId", bean.orderId)
                            ARouter.getInstance().build("/order/cancel").with(b).navigation()
                            dismiss()
                        }
                    }

                    7 -> {
                        binding?.rightBtn?.text = "查看详情"
                        binding?.priceLabel?.text = "实付："
                        binding?.root?.click {
                            val b = Bundle()
                            b.putInt("status", 2)
                            b.putString("oid", bean.orderId)
                            ARouter.getInstance().build("/order/reject").with(b).navigation()
                            dismiss()
                        }
                    }
                }
            }

            else -> {
                binding?.price?.setLeftView(context, R.drawable.me_diamon_yellow, 16.dp, 12.dp)
                when (bean.status) {
                    1 -> {
                        binding?.rightBtn?.text = "立即接单"
                        binding?.priceLabel?.text = "预计收入："
                        binding?.root?.click {
                            val b = Bundle()
                            b.putInt("type", 1)
                            b.putString("orderId", bean.orderId)
                            ARouter.getInstance().build("/order/details").with(b).navigation()
                            dismiss()
                        }
                    }

                    4 -> {
                        binding?.leftBtn?.gone()
                        binding?.rightBtn?.text = "查看详情"
                        binding?.priceLabel?.text = "收入："

                        binding?.root?.click {
                            val b = Bundle()
                            b.putInt("type", 1)
                            b.putString("orderId", bean.orderId)
                            ARouter.getInstance().build("/order/details").with(b).navigation()
                            dismiss()
                        }
                    }

                    5 -> {
                        binding?.leftBtn?.gone()
                        binding?.rightBtn?.text = "处理退款"
                        binding?.root?.click {
                            val b = Bundle()
                            b.putInt("type", 1)
                            b.putString("orderId", bean.orderId)
                            ARouter.getInstance().build("/order/cancel").with(b).navigation()
                            dismiss()
                        }
                        binding?.priceLabel?.text = "预计收入："
                    }
                }
            }
        }
    }

    override fun onCreateShowAnimation(): Animation {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.FROM_TOP).toShow()
    }

    override fun onCreateDismissAnimation(): Animation {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.TO_TOP).toShow()
    }
}