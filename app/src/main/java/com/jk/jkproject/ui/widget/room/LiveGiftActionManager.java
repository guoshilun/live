package com.jk.jkproject.ui.widget.room;

import android.os.Bundle;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.ui.entity.LiveEntryInfo;
import com.jk.jkproject.ui.entity.LiveGift;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.entity.LiveGiftUtils;
import com.jk.jkproject.ui.inter.LiveChestGiftAnimListener;
import com.jk.jkproject.ui.inter.LiveComboGiftAnimListener;
import com.jk.jkproject.ui.inter.LiveEntryActionListener;
import com.jk.jkproject.ui.inter.LiveEntryAnimListener;
import com.jk.jkproject.ui.inter.LiveGiftActionListener;
import com.jk.jkproject.utils.Constants;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by zhaotun on 16/6/7.
 * 直播礼物动画管理类
 */
public class LiveGiftActionManager implements LiveGiftActionListener, LiveEntryActionListener {

    // 连击礼物
    private LinkedList<LiveGiftInfoBean.DataBean> comboGifts = new LinkedList<>();
    // 本地连击礼物
    private LinkedList<LiveGiftInfoBean.DataBean> comboNativeGifts = new LinkedList<>();
    // 其他礼物
    private LinkedList<LiveGiftInfoBean.DataBean> otherGifts = new LinkedList<>();
    // 宝箱礼物
    private LinkedList<LiveGiftInfoBean.DataBean> chestGifts = new LinkedList<>();
    // 连击动画监听
    private ArrayList<LiveComboGiftAnimListener> comboGiftAnimListeners = new ArrayList<>();
    // 本地连击动画监听
    private ArrayList<LiveComboGiftAnimListener> comboNativeGiftAnimListeners = new ArrayList<>();
    // 其他动画监听
    private ArrayList<LiveOtherGiftAnimListener> otherGiftAnimListeners = new ArrayList<>();
    // 宝箱动画监听
    private LiveChestGiftAnimListener chestGiftAnimListener;
    // 送过礼物的人
    private LinkedList<LiveEntryInfo> entryLists = new LinkedList<>();
    // 送过礼物的人进房控件动画监听
    private ArrayList<LiveEntryAnimListener> entryAnimListeners = new ArrayList<>();


    // 进房特效
    private LinkedList<LiveGiftInfoBean.DataBean> effects = new LinkedList<>();
    // 进房特效监听
//    private LiveSpecialEffectListener mEffectListener;

    // 小秘书特效
    private LinkedList<LiveGiftInfoBean.DataBean> secretary = new LinkedList<>();
    // 小秘书特效监听
//    private LiveSpecialSecretaryListener mSecretaryListener;


    public LiveGiftActionManager() {
    }

    public void clear() {
        if (comboGifts != null) {
            comboGifts.clear();
            comboGifts = null;
        }

        if (otherGifts != null) {
            otherGifts.clear();
            otherGifts = null;
        }

        if (chestGifts != null) {
            chestGifts.clear();
            chestGifts = null;
        }

        if (entryLists != null) {
            entryLists.clear();
            entryLists = null;
        }

        if (effects != null) {
            effects.clear();
            effects = null;
        }

        if (comboGiftAnimListeners != null) {
            comboGiftAnimListeners.clear();
            comboGiftAnimListeners = null;
        }

        if (otherGiftAnimListeners != null) {
            otherGiftAnimListeners.clear();
            otherGiftAnimListeners = null;
        }

        if (entryAnimListeners != null) {
            entryAnimListeners.clear();
            otherGiftAnimListeners = null;
        }

        if (chestGiftAnimListener != null) {
            chestGiftAnimListener = null;
        }

    }

    /**
     * 未识别的礼物提示
     */
    private void unrecognizedGift() {
        Bundle b = new Bundle();
        b.putInt("method", Constants.OBSERVABLE_ACTION_LIVE_UNRECOGNIZED_GIFT);
        MessageNotifyCenter.getInstance().doNotify(b);
    }


    /*****************************
     * 礼物 start
     *************************/
    int count = 0;

    @Override
    public void addGift(LiveGiftInfoBean.DataBean info) {
        if (info != null) {
//            if (info.getType() == 0) { //连击动画
            if (comboGifts != null) {
                comboGifts.add(info);
            }
            pollComboGift();
            if (!TextUtils.isEmpty(info.getSvgaAnimate())) {
                info.setAnimateType(LiveGift.LIVE_EFFECT_ANIMATE_SVGA);
                if (otherGifts != null) {
                    otherGifts.add(info);
                }
                pollOtherGift();
            }
        }
    }

    @Override
    public void pollComboGift() {
        if (comboGifts != null && comboGifts.size() > 0 && comboGiftAnimListeners != null) {
            LiveGiftInfoBean.DataBean info = comboGifts.poll();
            if (info != null) {
                LiveGiftInfoBean.DataBean gift = LiveGiftUtils.get().getGift(info.getId());
                if (gift == null) {
                    // 未识别的连击礼物,开启下一个
                    pollComboGift();
                } else {
                    boolean hasCombo = false;
                    // 判断是否有连击
                    for (int i = 0, size = comboGiftAnimListeners.size(); i < size; i++) {
                        LiveComboGiftAnimListener listener = comboGiftAnimListeners.get(i);
                        if (listener != null && listener.isCombo(info)) {
                            // 连击
                            listener.addTimes(info);
                            hasCombo = true;
                            break;
                        }
                    }
                    // 判断是否有空闲的连击礼物动画通道
                    if (!hasCombo) {
                        for (int i = 0, size = comboGiftAnimListeners.size(); i < size; i++) {
                            LiveComboGiftAnimListener listener = comboGiftAnimListeners.get(i);
                            if (!listener.isRunning()) {
                                listener.startAnim(info);
                                hasCombo = true;
                                break;
                            }
                        }
                    }
                    // 都在执行动画,则重新加入队列的第一个
                    if (!hasCombo) {
                        comboGifts.add(0, info);
                    }
                }
            } else {
                pollComboGift();
            }
        }
    }

    @Override
    public void pollOtherGift() {
        if (otherGifts != null && otherGifts.size() > 0) {
            LiveGiftInfoBean.DataBean info = otherGifts.poll();
            if (info != null) {
                // 进场动画走的也是大礼物的通道，
                LiveGiftInfoBean.DataBean gift = LiveGiftUtils.get().getGift(info.getId());
                if (gift != null && !TextUtils.isEmpty(info.getSvgaAnimate())) {
//                    gift.animateType = LiveGift.LIVE_EFFECT_ANIMATE_SVGA;
                    gift.setAnimateType(LiveGift.LIVE_EFFECT_ANIMATE_SVGA);
                }
                if (gift == null) {
                    // 未识别的礼物,开启下一个
//                    unrecognizedGift();
                    pollOtherGift();
                } else {
                    boolean next = true;
                    boolean isRunning = false;
                    boolean isHasRunning = false;
                    for (int i = 0, size = otherGiftAnimListeners.size(); i < size; i++) {
                        LiveOtherGiftAnimListener listener = otherGiftAnimListeners.get(i);
                        if (listener != null) {
                            next = false;
                            if (listener.isRunning()) {
                                LogUtils.e("tag", "isHasRunning:::" + isHasRunning);
                                isHasRunning = true;
                                break;
                            }
                        }
                    }
                    if (isHasRunning) {
                        otherGifts.add(0, info);
                    } else {
                        for (int i = 0, size = otherGiftAnimListeners.size(); i < size; i++) {
                            LiveOtherGiftAnimListener listener = otherGiftAnimListeners.get(i);
                            if (listener != null && TextUtils.equals(info.getAnimateType(), listener.getAnimate())) {
                                next = false;
                                isRunning = listener.isRunning();
                                if (isRunning) {
                                    otherGifts.add(0, info);
                                } else {
                                    listener.startAnim(info);
                                }
                                break;
                            }
                        }
                    }
                    if (next && !isRunning) {
                        // 未识别的礼物,开始下一个
//                        unrecognizedGift();
                        pollOtherGift();
                    }
                }
            } else {
                pollOtherGift();
            }
        }
    }

    @Override
    public void pollChestGift() {
//        if (chestGifts != null && chestGifts.size() > 0) {
//            LiveGiftInfoBean.DataBean info = chestGifts.poll();
//            if (info != null && !TextUtils.isEmpty(info.getKey())) {
//                if (chestGiftAnimListener != null) {
//                    if (chestGiftAnimListener.isRunning()) {
//                        chestGiftAnimListener.addTimes(info);
//                    } else {
//                        chestGiftAnimListener.startAnim(info);
//                    }
//                }
//            } else {
//                pollChestGift();
//            }
//        }
    }

    /**
     * 添加连击动画监听
     *
     * @param listener
     */
    public void addComboGiftAnimListener(LiveComboGiftAnimListener listener) {
        if (comboGiftAnimListeners != null) {
            comboGiftAnimListeners.add(listener);
        }
    }

    /**
     * 添加其他动画监听
     *
     * @param listener
     */
    public void addOtherGiftAnimListener(LiveOtherGiftAnimListener listener) {
        if (otherGiftAnimListeners != null) {
            otherGiftAnimListeners.add(listener);
        }
    }

    /**
     * 添加宝箱动画监听
     *
     * @param listener
     */
    public void setChestGiftAnimListener(LiveChestGiftAnimListener listener) {
        chestGiftAnimListener = listener;
    }

    /***************************** 礼物 end *************************/


    /*****************************
     * 送过礼物的人进房间 start
     *************************/

    @Override
    public void addEntry(LiveEntryInfo info) {
        if (info != null) {
            if (entryLists != null) {
                entryLists.add(info);
            }
            pollEntry();
        }
    }

    @Override
    public void pollEntry() {
        if (entryLists != null && entryLists.size() > 0 && entryAnimListeners != null) {
            LiveEntryInfo info = entryLists.poll();
            if (info != null) {
                boolean isNext = true;
                for (int i = 0, size = entryAnimListeners.size(); i < size; i++) {
                    LiveEntryAnimListener listener = entryAnimListeners.get(i);
                    if (listener != null && !listener.isRunning()) {
                        listener.startAnim(info);
                        isNext = false;
                        break;
                    }
                }
                if (isNext) {
                    entryLists.add(0, info);
                }
            } else {
                pollEntry();
            }
        }
    }

    /**
     * 添加送过礼物的人进房动画监听
     *
     * @param listener
     */
    public void addEntryAnimListener(LiveEntryAnimListener listener) {
//        if (entryAnimListeners != null) {
//            entryAnimListeners.add(listener);
//        }
    }

    /***************************** 送过礼物的人进房间 end *************************/


    /*****************************
     * 进房特效 start
     *************************/

    @Override
    public void addSpecialEffect(LiveGiftInfoBean.DataBean info) {
//        if (info != null) {
//            if (effects != null) {
//                effects.add(info);
//            }
//            pollSpecialEffect();
//        }
    }

    @Override
    public void pollSpecialEffect() {
//        if (effects != null && effects.size() > 0 && mEffectListener != null) {
//            LiveRoomEffectInfo info = effects.poll();
//            if (info != null && TextUtils.isEmpty(info.getDesc()) && !TextUtils.isEmpty(info.getBar_text())) {
//                info.setDesc(info.getBar_text());
//            }
//            if (info != null && !TextUtils.isEmpty(info.getDesc())) {
//                if (mEffectListener.isRunning()) {
//                    effects.add(0, info);
//                } else {
//                    mEffectListener.startAnim(info);
//                }
//            } else {
//                pollSpecialEffect();
//            }
//        }
    }

    /**
     * 添加进房特效动画监听
     *
     * @param listener
     */
//    public void addEffectAnimListener(LiveSpecialEffectListener listener) {
//        mEffectListener = listener;
//    }

    /**************************** 进房特效 end *************************/


    /*****************************
     * 小秘书特效 start
     *************************/

    @Override
    public void addSpecialSecretary(LiveGiftInfoBean.DataBean info) {
//        if (info != null) {
//            if (secretary != null) {
//                secretary.add(info);
//            }
//
//            pollSpecialSecretary();
//        }
    }

    @Override
    public void pollSpecialSecretary() {
//        if (secretary != null && secretary.size() > 0 && mSecretaryListener != null) {
//            LiveRoomSecretaryInfo info = secretary.poll();
//            if (info != null && !TextUtils.isEmpty(info.getContent())) {
//                if (mSecretaryListener.isRunning()) {
//                    secretary.add(0, info);
//                } else {
//                    mSecretaryListener.startAnim(info);
//                }
//            } else {
//                pollSpecialSecretary();
//            }
//        }
    }

    /**
     * 添加小秘书特效动画监听
     *
     * @param listener
     */
//    public void addSecretaryAnimListener(LiveSpecialSecretaryListener listener) {
//        mSecretaryListener = listener;
//    }

    /**************************** 小秘书特效 end *************************/


    /*****************************
     * 本地礼物 start
     *************************/

    @Override
    public void addNativeGift(LiveGiftInfoBean.DataBean info) {
//        if (info != null) {
//            if (comboNativeGifts != null) {
//                comboNativeGifts.add(info);
//            }
//            pollComboNativeGift();
//        }
    }

    @Override
    public void pollComboNativeGift() {
//        if (comboNativeGifts != null && comboNativeGifts.size() > 0 && comboNativeGiftAnimListeners != null) {
//            LiveGiftInfoBean.DataBean info = comboNativeGifts.poll();
//            if (info != null) {
//                LiveGiftInfoBean.DataBean gift = LiveGiftUtils.get().getGift(info.getId());
//                if (gift == null) {
//                    // 未识别的连击礼物,开启下一个
//                    unrecognizedGift();
//
//                    pollComboNativeGift();
//                } else {
//                    boolean hasCombo = false;
//                    // 判断是否有连击
//                    for (int i = 0, size = comboNativeGiftAnimListeners.size(); i < size; i++) {
//                        LiveComboGiftAnimListener listener = comboNativeGiftAnimListeners.get(i);
//                        if (listener != null && listener.isCombo(info)) {
//                            // 连击
//                            listener.addTimes(info);
//                            hasCombo = true;
//                            LogUtils.e("hasCombo=", hasCombo);
//                            break;
//                        }
//                    }
//
//                    // 判断是否有空闲的连击礼物动画通道
//                    if (!hasCombo) {
//                        for (int i = 0, size = comboNativeGiftAnimListeners.size(); i < size; i++) {
//                            LiveComboGiftAnimListener listener = comboNativeGiftAnimListeners.get(i);
//                            if (!listener.isRunning()) {
//                                listener.startAnim(info);
//                                hasCombo = true;
//                                break;
//                            }
//                        }
//                    }
//
//                    // 都在执行动画,则重新加入队列的第一个
//                    if (!hasCombo) {
//                        comboNativeGifts.add(0, info);
//                    }
//                }
//            } else {
//                pollComboNativeGift();
//            }
//        }
    }

    /**
     * 添加连击动画监听
     *
     * @param listener
     */
    public void addComboNativeGiftAnimListener(LiveComboGiftAnimListener listener) {
        if (comboNativeGiftAnimListeners != null) {
            comboNativeGiftAnimListeners.add(listener);
        }
    }

    /***************************** 本地礼物 end *************************/
    public void clearAllGift() {
        if (comboGifts != null) {
            comboGifts.clear();
        }
        if (comboNativeGifts != null) {
            comboNativeGifts.clear();
        }
        if (otherGifts != null) {
            otherGifts.clear();
        }
        if (chestGifts != null) {
            chestGifts.clear();
        }
    }
}
