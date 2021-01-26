package com.jk.jkproject.ui.inter;

import com.jk.jkproject.ui.entity.DanmuBean;

/**
 * Created by zhaotun on 16/6/7.
 * 进入房间动画监听
 */
public interface LiveEnterRoomAnimListener extends LiveRelease {

  /**
   * 获取动画类型
   *
   * @return
   */
  String getAnimate();

  /**
   * 设置动画管理类
   *
   * @param listener
   */
  void setAnimaActionListener(LiveAnimaActionListener listener);

  /**
   * 是否是正在执行中
   *
   * @return
   */
  boolean isRunning();


  /**
   * 开始动画
   *
   * @param info
   */
  void startAnim(DanmuBean info);

  void startAnim(DanmuBean info,boolean isLast);
}
