package com.miliyo.danmaku.control.dispatcher;

import com.miliyo.danmaku.DanMuChannelInfo;
import com.miliyo.danmaku.model.DanMuModel;
import com.miliyo.danmaku.model.channel.DanMuChannel;

/**
 * Created by android_ls on 2016/12/7.
 */
public interface IDanMuDispatcher {

    void dispatch(DanMuModel iDanMuView, DanMuChannel[] danMuChannels, DanMuChannelInfo size);

}
