package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveGift;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.inter.LiveGiftActionListener;
import com.jk.jkproject.utils.CleanDataUtils;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;


public class LiveViewForSvgaGift extends RelativeLayout implements LiveOtherGiftAnimListener {
    private SVGAImageView svgaImageView;
    private boolean isRunning = false;
    private static SVGAParser parser;
    private LiveGiftActionListener mLiveGiftAction;
    private Context context;
    private String svgaUrl = "";

    public LiveViewForSvgaGift(Context context) {
        super(context);
        init(context);
    }

    public LiveViewForSvgaGift(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LiveViewForSvgaGift(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public static SVGAParser instance(Context context) {
        synchronized (SVGAParser.class) {
            if (parser == null) {
                parser = new SVGAParser(context);
            }
            return parser;
        }
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_view_live_view_for_svga_gift, this, true);
        svgaImageView = findViewById(R.id.svga);
        svgaImageView.setCallback(new SVGACallback() {
            @Override
            public void onPause() {
                isRunning = false;
                LogUtils.e("svga=onPause");
            }

            @Override
            public void onFinished() {
                isRunning = false;
//                CleanDataUtils.clearAllCache(context);
                LogUtils.e("svga=onFinished");
            }

            @Override
            public void onRepeat() {
                LogUtils.e("svga=onRepeat");
                if (svgaImageView != null) {
                    svgaImageView.stopAnimation();
                    svgaImageView.pauseAnimation();
                    svgaImageView.setVisibility(GONE);
                }
                isRunning = false;
                if (mLiveGiftAction != null) {
                    CleanDataUtils.clearAllCache(context);
                    mLiveGiftAction.pollOtherGift();
                }
            }

            @Override
            public void onStep(int frame, double percentage) {
//                LogUtils.e("svga=", "frame:" + frame + "    percentage:" + percentage);
            }
        });
        svgaImageView.setClearsAfterStop(true);
    }

    @Override
    public void release() {
        if (context != null) {
            context = null;
        }
        if (svgaImageView != null) {
            svgaImageView = null;
        }
        if (parser != null) {
            parser = null;
        }
        isRunning = false;
    }

    @Override
    public String getAnimate() {
        return LiveGift.LIVE_EFFECT_ANIMATE_SVGA;
    }

    @Override
    public void setLiveGiftActionListener(LiveGiftActionListener listener) {
        mLiveGiftAction = listener;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void startAnim(LiveGiftInfoBean.DataBean info) {
        isRunning = true;
        loadAnimation(info.getSvgaAnimate());
    }

    private void loadAnimation(final String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        try {
            instance(context).decodeFromURL(new URL(url), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    LogUtils.e("svgaUrl=", url);
                    if (svgaImageView != null && videoItem != null) {
                        svgaImageView.setVideoItem(videoItem);
                        svgaImageView.setVisibility(VISIBLE);
                        svgaImageView.startAnimation();
                        isRunning = true;
                    }
                }

                @Override
                public void onError() {
                    isRunning = false;
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
