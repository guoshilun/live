package com.miliyo.danmaku.control.dispatcher;

import android.content.Context;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;

import com.miliyo.danmaku.DanMuChannelInfo;
import com.miliyo.danmaku.model.DanMuModel;
import com.miliyo.danmaku.model.channel.DanMuChannel;
import com.miliyo.danmaku.model.utils.PaintUtils;

import java.util.Random;

/**
 * Created by android_ls on 2016/12/7.
 */
public class DanMuDispatcher implements IDanMuDispatcher {

    private Context context;
    protected TextPaint paint;
    private Random random = new Random();

    public DanMuDispatcher(Context context) {
        this.context = context;
        paint = PaintUtils.getPaint();
    }

    private int start = 0;
    private DanMuChannelInfo lastInfo = new DanMuChannelInfo();

    @Override
    public synchronized void dispatch(DanMuModel danMuView, DanMuChannel[] danMuChannels, DanMuChannelInfo danmuInfo) {
        if (lastInfo == null) {
            lastInfo = new DanMuChannelInfo();
        }
        if (!danMuView.isAttached() && danMuChannels != null && danmuInfo != null) {
            Log.e("tag", "start:" + start + "  danmuinfo:" + danmuInfo.toString() + "\nlast:" + lastInfo.toString() + "text:" + danMuView.text.toString());

            //第一次
            if ((danmuInfo.lastChannel == 0 && danmuInfo.danmuSize == 0 && lastInfo.danmuSize == 0 && lastInfo.lastChannel == 0)) {
                danMuView.selectChannel(0);
                lastInfo.lastChannel = 0;
                lastInfo.danmuSize++;
                start = 0;
            } else if (danmuInfo.danmuSize == 0 && danmuInfo.lastChannel == 0) {
                start = 0;
                if (lastInfo.lastChannel == 0) {
                    danMuView.selectChannel(1);
                    lastInfo.lastChannel = 1;
                    lastInfo.danmuSize++;
                } else if (lastInfo.lastChannel == 1) {
                    danMuView.selectChannel(0);
                    lastInfo.lastChannel = 0;
                    lastInfo.danmuSize++;
                }
            } else if (danmuInfo.danmuSize == 0 && danmuInfo.lastChannel == -1) {
                if (start == 0) {
                    danMuView.selectChannel(0);
                    lastInfo.lastChannel = 0;
                    lastInfo.danmuSize++;
                    start++;
                } else {
                    if (lastInfo.lastChannel == 1) {
                        danMuView.selectChannel(0);
                        lastInfo.lastChannel = 0;
                        lastInfo.danmuSize++;
                    } else if (lastInfo.lastChannel == 0) {
                        danMuView.selectChannel(1);
                        lastInfo.lastChannel = 1;
                        lastInfo.danmuSize++;
                        start = 0;
                    }
                }
            }

            if (danmuInfo.danmuSize > 0) {
                start = 0;
//                if (danmuInfo.lastChannel == 0 && lastInfo.lastChannel == 0) {
//                    danMuView.selectChannel(0);
//                    lastInfo.lastChannel = 0;
//                    lastInfo.danmuSize++;
//                }
//
//                else
                if (lastInfo.lastChannel == 0) {
                    danMuView.selectChannel(1);
                    lastInfo.lastChannel = 1;
                    lastInfo.danmuSize++;
                } else if (lastInfo.lastChannel == 1) {
                    danMuView.selectChannel(0);
                    lastInfo.lastChannel = 0;
                    lastInfo.danmuSize++;
                }
            }
//            DanMuChannel danMuChannel;
//            if (danMuChannels.length == 1) {
//                danMuChannel = danMuChannels[0];
//            } else {
            DanMuChannel danMuChannel = danMuChannels[danMuView.getChannelIndex()];
//            }
            if (danMuChannel == null) {
                return;
            }
            measure(danMuView, danMuChannel);
        }
    }

    private int selectChannelRandomly(DanMuChannel[] danMuChannels, int size) {
        return random.nextInt(danMuChannels.length);
    }

    private void measure(DanMuModel danMuView, DanMuChannel danMuChannel) {
        if (danMuView.isMeasured()) {
            return;
        }
        CharSequence text = danMuView.text + danMuView.userName;
        if (!TextUtils.isEmpty(text)) {
            paint.setTextSize(danMuView.textSize);
            StaticLayout staticLayout = new StaticLayout(text,
                    paint,
                    (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);

            float textWidth = danMuView.getX()
                    + danMuView.marginLeft
                    + danMuView.avatarWidth
                    + danMuView.levelMarginLeft
                    + danMuView.levelBitmapWidth
                    + danMuView.textMarginLeft
                    + staticLayout.getWidth()
                    + danMuView.textBackgroundPaddingRight + danMuView.intoSpecialWidth;
            danMuView.setWidth((int) textWidth);

            float textHeight = staticLayout.getHeight()
                    + danMuView.textBackgroundPaddingTop
                    + danMuView.textBackgroundPaddingBottom;
            if (danMuView.avatar != null && danMuView.avatarHeight > textHeight) {
                danMuView.setHeight((int) (danMuView.getY() + danMuView.avatarHeight));
            } else {
                danMuView.setHeight((int) (danMuView.getY() + textHeight));
            }
        }

        if (danMuView.getDisplayType() == DanMuModel.RIGHT_TO_LEFT) {
            danMuView.setStartPositionX(danMuChannel.width);
        } else if (danMuView.getDisplayType() == DanMuModel.LEFT_TO_RIGHT) {
            danMuView.setStartPositionX(-danMuView.getWidth());
        }

        danMuView.setMeasured(true);
        danMuView.setStartPositionY(danMuChannel.topY);
        danMuView.setAlive(true);
    }

    public void release() {
        context = null;
        if (lastInfo != null) {
            lastInfo = null;
        }
    }
}
