package com.jk.jkproject.ui.entity;

import android.util.SparseArray;

import com.facebook.fresco.helper.Phoenix;
import com.jk.jkproject.R;

import java.util.List;

public class LiveGiftUtils {

    private static LiveGiftUtils instance;
    /**
     * 从服务端获取的所有礼物数据
     */
    private List<LiveGiftInfoBean.DataBean> mGiftList;
    /**
     * 解析本地的喜欢图片资源数据
     */
    private SparseArray<LiveGift> mapGiftLike;

    public static LiveGiftUtils get() {
        if (instance == null) {
            synchronized (LiveGiftUtils.class) {
                if (instance == null) {
                    instance = new LiveGiftUtils();
                }
            }
        }

        return instance;
    }

    /**
     * 根据礼物Id获取礼物信息
     *
     * @param gift_id
     * @return
     */
    public LiveGiftInfoBean.DataBean getGift(String gift_id) {
        if (mGiftList != null) {
            int size = mGiftList.size();
            for (int j = 0; j < size; j++) {
                LiveGiftInfoBean.DataBean giftInfo = mGiftList.get(j);
                if (gift_id.equals(giftInfo.getId())) {
                    return giftInfo;
                }
            }
        } else {
//            List<ConfigDBInfo> list = LitePal.where("keydictionaries = ?", "gift")
//                    .find(ConfigDBInfo.class);
//            if (null != list && list.size() > 0) {
//                LiveGiftBlock data = GsonUtils.get().fromJson(list.get(0).getValueDictionaries(), LiveGiftBlock.class);
//                if (data != null && data.getCode() == 200) {
//                    LiveGiftUtils.get().setGiftList(data.data);
//                }
//                if (mGiftList != null) {
//                    int size = mGiftList.size();
//                    for (int j = 0; j < size; j++) {
//                        LiveGiftInfoBean.DataBean giftInfo = mGiftList.get(j);
//                        if (gift_id == giftInfo.getId()) {
//                            return giftInfo;
//                        }
//                    }
//                }
//            }
            return null;
        }
        return null;
    }

    public List<LiveGiftInfoBean.DataBean> getGiftList() {
        return mGiftList;
    }

    public void setGiftList(List<LiveGiftInfoBean.DataBean> data) {
        mGiftList = data;
        if (mGiftList != null && mGiftList.size() > 0) {
            int size = mGiftList.size();
            for (int i = 0; i < size; i++) {
                LiveGiftInfoBean.DataBean giftInfo = mGiftList.get(i);
                // 礼物图片预加载
//                MLog.e("tag",giftInfo.image);
                Phoenix.prefetchToDiskCache(giftInfo.getG_icon());
            }
        }
    }

    public SparseArray<LiveGift> getMapGiftLike() {
        return mapGiftLike;
    }

    public LiveGift getGiftLike(int goods_id) {
        if (getMapGiftLike() != null && getMapGiftLike().size() > 0) {
            try {
                return getMapGiftLike().get(goods_id);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public LiveGift getGiftLikeByIndex(int index) {
        if (mapGiftLike == null) {
            setLiveGift();
        }
        if (getMapGiftLike() != null && getMapGiftLike().size() > 0 && index >= 0 && index < getMapGiftLike().size()) {
            try {
                return getMapGiftLike().valueAt(index);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public void release() {
        if (mapGiftLike != null) {
            mapGiftLike.clear();
            mapGiftLike = null;
        }
        instance = null;
    }

    /**
     * 直播是点心的初始化
     * 3800--3804
     */
    public void setLiveGift() {
        if (mapGiftLike == null) {
            mapGiftLike = new SparseArray<>();
            LiveGift gift1 = new LiveGift(3800, R.mipmap.icon_live_like_blue, "like");
            mapGiftLike.put(0, gift1);
            LiveGift gift2 = new LiveGift(3801, R.mipmap.icon_live_like_green, "like");
            mapGiftLike.put(1, gift2);
            LiveGift gift3 = new LiveGift(3802, R.mipmap.icon_live_like_pink, "like");
            mapGiftLike.put(2, gift3);
            LiveGift gift4 = new LiveGift(3803, R.mipmap.icon_live_like_skyblue, "like");
            mapGiftLike.put(3, gift4);
            LiveGift gift5 = new LiveGift(3804, R.mipmap.icon_live_like_skyblue_yellow, "like");
            mapGiftLike.put(4, gift5);
            // 礼物图片预加载
        }
    }
}
