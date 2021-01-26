package com.jk.jkproject.ui.widget.room;

import com.jk.jkproject.ui.entity.DanmuBean;
import com.jk.jkproject.ui.inter.LiveAnimaActionListener;
import com.jk.jkproject.ui.inter.LiveEnterRoomAnimListener;
import com.jk.jkproject.utils.Constants;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by zhaotun on 16/6/7.
 * 直播动画队列
 */
public class LiveAnimationQueueActionManager implements LiveAnimaActionListener {

    // 土豪进入直播间动画队列
    private LinkedList<DanmuBean> danmuBeans = new LinkedList<>();
    // 全直播间公告消息动画队列
    private LinkedList<DanmuBean> annDanmus = new LinkedList<>();

    // 土豪进入直播间动画监听
    private ArrayList<LiveEnterRoomAnimListener> comboGiftAnimListeners = new ArrayList<>();
    // 全直播间公告消息动画监听
    private ArrayList<LiveEnterRoomAnimListener> comboNativeGiftAnimListeners = new ArrayList<>();
    private boolean isLast;

    public LiveAnimationQueueActionManager() {
    }

    public void clear() {
        if (danmuBeans != null) {
            danmuBeans.clear();
            danmuBeans = null;
        }

        if (annDanmus != null) {
            annDanmus.clear();
            annDanmus = null;
        }
    }

    /*****************************
     * 礼物 start
     *************************/

    @Override
    public void addGift(DanmuBean danmuBean) {
        if (danmuBean != null) {
            switch (danmuBean.getData().getFrom().getMsgType()) {
                case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_4://4 全直播间公告消息 （系统消息）
                    if (annDanmus != null) {
                        annDanmus.add(danmuBean);
                    }
                    pollAnnDanmus();
                    break;
                case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_3://3 土豪用户加入直播间消息 （系统消息）
                    if (danmuBeans != null) {
                        danmuBeans.add(danmuBean);
                    }
                    pollDanmuBeans();
                    break;
            }
        }
    }

    @Override
    public void pollDanmuBeans() {
        if (danmuBeans != null && danmuBeans.size() > 0 && comboGiftAnimListeners != null) {
            DanmuBean info = danmuBeans.poll();
            if (info != null) {
                boolean hasCombo = false;
                // 判断是否有空闲的进入房间动画通道
                if (!hasCombo) {
                    for (int i = 0, size = comboGiftAnimListeners.size(); i < size; i++) {
                        LiveEnterRoomAnimListener listener = comboGiftAnimListeners.get(i);
                        if (!listener.isRunning()) {
                            listener.startAnim(info);
                            hasCombo = true;
                            break;
                        }
                    }
                }
                // 都在执行动画,则重新加入队列的第一个
                if (!hasCombo) {
                    danmuBeans.add(0, info);
                }
            }
        }
    }

    @Override
    public void pollAnnDanmus() {
        if (annDanmus != null && annDanmus.size() > 0 && comboNativeGiftAnimListeners != null) {
            if (annDanmus.size() > 1) {
                isLast = false;
            } else {
                isLast = true;
            }
            DanmuBean info = annDanmus.poll();
            if (info != null) {
                boolean hasCombo = false;
                // 判断是否有空闲的进入房间动画通道
                if (!hasCombo) {
                    for (int i = 0, size = comboNativeGiftAnimListeners.size(); i < size; i++) {
                        LiveEnterRoomAnimListener listener = comboNativeGiftAnimListeners.get(i);
                        if (!listener.isRunning()) {
                            listener.startAnim(info, isLast);
                            hasCombo = true;
                            break;
                        }
                    }
                }
                // 都在执行动画,则重新加入队列的第一个
                if (!hasCombo) {
                    annDanmus.add(0, info);
                }
            }
        }
    }

    /**
     * 添加全直播间公告消息
     *
     * @param listener
     */
    public void addComboNativeGiftAnimListener(LiveEnterRoomAnimListener listener) {
        if (comboNativeGiftAnimListeners != null) {
            comboNativeGiftAnimListeners.add(listener);
        }
    }

    /**
     * 添加土豪进入直播间
     *
     * @param listener
     */
    public void addComboGiftAnimListener(LiveEnterRoomAnimListener listener) {
        if (comboGiftAnimListeners != null) {
            comboGiftAnimListeners.add(listener);
        }
    }

    /***************************** 本地礼物 end *************************/
    public void clearAllGift() {
        if (danmuBeans != null) {
            danmuBeans.clear();
        }
        if (annDanmus != null) {
            annDanmus.clear();
        }
    }
}
