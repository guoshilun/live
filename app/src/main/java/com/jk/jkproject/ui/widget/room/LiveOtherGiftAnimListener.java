package com.jk.jkproject.ui.widget.room;

import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.inter.LiveGiftActionListener;
import com.jk.jkproject.ui.inter.LiveRelease;

/**
 * Created by zhaotun on 16/6/7.
 * 其他礼物动画监听
 */
public interface LiveOtherGiftAnimListener extends LiveRelease {

    /**
     * 获取动画类型
     *
     * @return
     */
    String getAnimate();

    /**
     * 设置礼物管理类
     *
     * @param listener
     */
    void setLiveGiftActionListener(LiveGiftActionListener listener);

    /**
     * 是否是正在执行中
     *
     * @return
     */
    boolean isRunning();

    /**
     * 开始动画
     */
    void startAnim(LiveGiftInfoBean.DataBean info);
}
