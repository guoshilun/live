package com.jk.jkproject.ui.widget.danmaku;

import com.blankj.utilcode.util.LogUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DanmakuActionManager {
  private DanmakuChannelListener listener;
  
  private List<DanmakuChannel> mDanmakuChannels;
  
  private Queue<DanmakuEntity> mDanmakuEntities;
  
  public DanmakuActionManager(List<DanmakuChannel> paramList) {
    this.mDanmakuChannels = paramList;
    this.mDanmakuEntities = new LinkedList<DanmakuEntity>();
  }
  
  public void addDanmu(DanmakuEntity paramDanmakuEntity) {
    this.mDanmakuEntities.add(paramDanmakuEntity);
    LogUtils.i("已将弹幕添加到弹幕队列");
    looperDan();
  }
  
  public void looperDan() {
    Queue<DanmakuEntity> queue = this.mDanmakuEntities;
    if (queue != null && queue.size() == 0) {
      DanmakuChannelListener danmakuChannelListener = this.listener;
      if (danmakuChannelListener != null) {
        danmakuChannelListener.endDanmu();
        return;
      } 
    } 
    for (byte b = 0; b < this.mDanmakuChannels.size(); b++) {
      if (!((DanmakuChannel)this.mDanmakuChannels.get(b)).isRunning && this.mDanmakuEntities.size() > 0) {
        DanmakuEntity danmakuEntity = this.mDanmakuEntities.poll();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("从弹幕队列里取出一个弹幕对象：");
        stringBuilder.append(danmakuEntity.getText());
        LogUtils.i(stringBuilder.toString());
        ((DanmakuChannel)this.mDanmakuChannels.get(b)).startAnimation(danmakuEntity, new DanmakuEndListener() {
              public void endDanmu() {
                if (DanmakuActionManager.this.mDanmakuEntities.size() > 0)
                  DanmakuActionManager.this.looperDan(); 
              }
            });
      } 
    } 
  }
  
  public void removeAll() {
    this.mDanmakuEntities.clear();
    this.mDanmakuChannels.clear();
    looperDan();
  }
  
  public void setDanmakuEndInter(DanmakuChannelListener paramDanmakuChannelListener) {
    this.listener = paramDanmakuChannelListener;
  }
}