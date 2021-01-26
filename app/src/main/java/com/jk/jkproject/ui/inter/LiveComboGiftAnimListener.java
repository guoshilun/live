package com.jk.jkproject.ui.inter;

import com.jk.jkproject.ui.entity.LiveGiftInfoBean;

/**
 * Created by zhaotun on 16/6/7.
 * 连击礼物动画监听
 */
public interface LiveComboGiftAnimListener extends LiveRelease {

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
   * 是否是连击
   *
   * @param info
   * @return
   */
  boolean isCombo(LiveGiftInfoBean.DataBean info);

  /**
   * 是否是正在执行中
   *
   * @return
   */
  boolean isRunning();

  /**
   * 增加个数
   */
  void addTimes(LiveGiftInfoBean.DataBean info);

  /**
   * 开始动画
   *
   * @param info
   */
  void startAnim(LiveGiftInfoBean.DataBean info);
}
