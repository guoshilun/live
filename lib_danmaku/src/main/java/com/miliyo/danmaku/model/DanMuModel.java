package com.miliyo.danmaku.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.miliyo.danmaku.view.OnDanMuTouchCallBackListener;
import com.miliyo.danmaku.view.OnDanMuViewTouchListener;

/**
 * Created by android_ls on 2016/12/7.
 */
public class DanMuModel implements OnDanMuViewTouchListener {

    public final static int RIGHT_TO_LEFT = 1;

    public final static int LEFT_TO_RIGHT = 2;

    /**
     * priority
     */
    public final static int SYSTEM = 100;

    public final static int NORMAL = 50;
    public boolean isChat = false;
    /**
     * 弹幕左边内间距
     */
    public int paddingLeft;

    /**
     * 弹幕右边内间距
     */
    public int paddingRight;

    /**
     * 弹幕距离左边的外边距
     */
    public int marginLeft;

    /**
     * 弹幕距离右边的外边距
     */
    public int marginRight;


    /**
     * 用户图像
     */
    public Bitmap avatar;

    /**
     * 用户图像宽
     */
    public int avatarWidth;

    /**
     * 用户图像高
     */
    public int avatarHeight;

    /**
     * 用户头像上的爱心
     */
    public Bitmap love;

    /**
     * 用户头像上的爱心宽
     */
    public int loveWidth;

    /**
     * 用户头像上的爱心高
     */
    public int loveHeight;
    /**
     * 弹幕头像外边框
     */
    public Bitmap avatarCircle;
    /**
     * 弹幕头像外边框高
     */
    public int avatarCircleHight;
    /**
     * 弹幕头像外边框宽
     */
    public int avatarCircleWidth;
    /**
     * 头像右下角
     */
    public Bitmap headRight;

    /**
     * 用户头像上右下角宽
     */
    public int headRightWidth;

    /**
     * 用户头像上右下角高
     */
    public int headRightHeight;


    /**
     * 用户图像描边（默认是白色的描边）
     */
    public boolean avatarStrokes = true;


    /**
     * 用户等级标签
     */
    public Bitmap levelBitmap;

    /**
     * 用户等级标签宽
     */
    public int levelBitmapWidth;

    /**
     * 用户等级标签高
     */
    public int levelBitmapHeight;

    /**
     * 用户等级标签距离左边的外边距
     */
    public int levelMarginLeft;
    /**
     * 用户等级标签距离左边的外边距
     */
    public int levelMarginTop;


    /**
     * 用户等级标签文本
     */
    public CharSequence levelText;

    /**
     * 用户等级标签文本，字体大小
     */
    public float levelTextSize;

    /**
     * 用户等级标签文本，字体颜色
     */
    public int levelTextColor;


    /**
     * 弹幕文本内容（支持富文本）
     */
    public CharSequence text;

    /**
     * 弹幕文本内容（支持富文本），字体大小
     */
    public float textSize;

    /**
     * 弹幕文本内容（支持富文本），字体颜色
     */
    public int textColor;

    /**
     * 弹幕文本内容（支持富文本），距离左边的外边距
     */
    public int textMarginLeft;


    /**
     * 弹幕文本背景图
     */
    public Drawable textBackground;

    /**
     * 弹幕文本背景图，距离左边的外边距
     */
    public int textBackgroundMarginLeft;

    /**
     * 弹幕文本背景图，距离上边的内边距
     */
    public int textBackgroundPaddingTop;

    /**
     * 弹幕文本背景图，距离下边的内边距
     */
    public int textBackgroundPaddingBottom;

    /**
     * 弹幕文本背景图，距离左边的内边距
     */
    public int textBackgroundPaddingLeft;

    /**
     * 弹幕文本背景图，距离右边的内边距
     */
    public int textBackgroundPaddingRight;

    /**
     * 头像头部
     */
    public Bitmap headTop;
    /**
     * 统一的topmarin
     */
    public int topMarin;

    /**
     * 用户头像头部
     */
    public int headTopWidth;

    /**
     * 用户头像头部
     */
    public int headTopHeight;

    /**
     * 用户头像上下左右边距
     */

    public int headBgMargin;
    /**
     * 用户头像上边距
     */

    public int headBgMarginTop;
    /**
     * 用户头像上边距
     */

    public int headBgMarginBottom;
    /**
     * 用户头像左边距
     */
    public int headBgMarginLeft;
    /**
     * 用户头像左边距
     */
    public int headBgMarginLeftOther;
    /**
     * 头上顶的皇冠Marin
     */
    public int headTopMarin;
    /**
     * 姓名
     */
    public String userName;
    /**
     * 姓名颜色
     */
    public int userNameColor;

    /**
     * 弹幕尾部
     */
    public Bitmap danmuEndBackground;


    /**
     * 弹幕潜力股 宽
     */
    public int intoSpecialWidth;

    /**
     * 弹幕潜力股 高
     */
    public int intoSpectialHeight;

    /**
     * 弹幕文本背景图，高
     */
    public int textBackgroundMarginHeight;

    /**
     * 头像外面边框颜色
     */
    public int headBordColor;


    /**
     * 尾部的margin
     */

    public float endMargin;



    private float startPositionX = -1;
    private float startPositionY = -1;
    private float speed;

    private int width;
    private int height;
    private boolean enableTouch;
    private int channelIndex;
    private boolean isMoving = true;
    private boolean isAlive = true;
    private OnDanMuTouchCallBackListener onTouchCallBackListener;
    private int displayType;
    private boolean attached;
    private int priority = NORMAL;

    private boolean isMeasured;

    public DanMuModel() {
    }

    public float getX() {
        return this.startPositionX;
    }

    public float getY() {
        return this.startPositionY;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
//        Log.i("danmu", "-----------isAlive = "+ isAlive +"---------------");

        if (!isAlive) {
            release();
        }

        this.isAlive = isAlive;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public OnDanMuTouchCallBackListener getOnTouchCallBackListener() {
        return this.onTouchCallBackListener;
    }

    public void setOnTouchCallBackListener(OnDanMuTouchCallBackListener onTouchCallBackListener) {
        this.onTouchCallBackListener = onTouchCallBackListener;
    }

    public void enableTouch(boolean enableTouch) {
        this.enableTouch = enableTouch;
    }

    public boolean enableTouch() {
        return this.enableTouch;
    }

    public void selectChannel(int index) {
        this.channelIndex = index;
    }

    public int getChannelIndex() {
        return this.channelIndex;
    }

    public void enableMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean isMoving() {
        return this.isMoving;
    }

    public boolean onTouch(float x, float y) {
        if (x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public void release() {
//        Log.i("danmu", "---------release()-------------");

        this.avatar = null;
        this.levelBitmap = null;
        this.textBackground = null;
        this.avatarCircle = null;
        this.headRight = null;
        this.headTop = null;
        this.onTouchCallBackListener = null;
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public void setStartPositionX(float startPositionX) {
        this.startPositionX = startPositionX;
    }

    public void setStartPositionY(float startPositionY) {
        this.startPositionY = startPositionY;
    }

    public boolean isAttached() {
        return attached;
    }

    public void setAttached(boolean attached) {
        this.attached = attached;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority != NORMAL && priority != SYSTEM) {
            throw new IllegalArgumentException("there's no such number of priority");
        }
        this.priority = priority;
    }

    public boolean isMeasured() {
        return isMeasured;
    }

    public void setMeasured(boolean measured) {
        isMeasured = measured;
    }

}
