package com.miliyo.danmaku.model.painter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

import com.miliyo.danmaku.model.DanMuModel;
import com.miliyo.danmaku.model.channel.DanMuChannel;
import com.miliyo.danmaku.model.utils.PaintUtils;

/**
 * Created by android_ls on 2016/12/7.
 */
public class DanMuPainter extends IDanMuPainter {

    protected static TextPaint paint;
    protected static RectF rectF;

    private boolean hide;

    private boolean hideAll;

    static {
        paint = PaintUtils.getPaint();
        rectF = new RectF();
    }

    public DanMuPainter() {
    }

    protected void layout(DanMuModel danMuView, DanMuChannel danMuChannel) {
    }

    private void onLayout(DanMuModel danMuView, DanMuChannel danMuChannel) {
        if (danMuView.isMoving()) {
            layout(danMuView, danMuChannel);
        }
    }

    protected void draw(Canvas canvas, DanMuModel danMuView, DanMuChannel danMuChannel) {
        if (danMuView.textBackground != null) {
            drawTextBackground(danMuView, canvas, danMuChannel);
        }
        if (danMuView.danmuEndBackground != null) {
            drawIntoSpecail(danMuView, canvas, danMuChannel);
        }
        if (danMuView.avatarCircle != null) {
            drawAvatarCircle(danMuView, canvas, danMuChannel);
        }
        if (danMuView.headTop != null) {
            drawHeadTop(danMuView, canvas, danMuChannel);
        }
        if (danMuView.avatar != null) {
            drawAvatar(danMuView, canvas, danMuChannel);
        }

        if (danMuView.avatarStrokes) {
            drawAvatarStrokes(danMuView, canvas, danMuChannel);
        }

        if (danMuView.love != null) {
            drawLove(danMuView, canvas, danMuChannel);
        }

        if (danMuView.headRight != null) {
            drawHeadRight(danMuView, canvas, danMuChannel);
        }

        if (danMuView.levelBitmap != null) {
            drawLevel(danMuView, canvas, danMuChannel);
        }

        if (!TextUtils.isEmpty(danMuView.levelText)) {
            drawLevelText(danMuView, canvas, danMuChannel);
        }
        if (!TextUtils.isEmpty(danMuView.userName)) {
            drawName(danMuView, canvas, danMuChannel);
        }

        if (!TextUtils.isEmpty(danMuView.text)) {
            drawText(danMuView, canvas, danMuChannel);
        }
    }

    //默认的边框
    protected void drawAvatarStrokes(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        float x = danMuView.getX() + danMuView.marginLeft + danMuView.avatarWidth / 2;
        float top = danMuView.headBgMarginTop + danMuView.getY() + danMuChannel.height / 2;
        paint.setColor(danMuView.headBordColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle((int) x, (int) top, danMuView.avatarHeight / 2, paint);
    }

    //爱心
    protected void drawLove(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {

    }

    //右下角
    protected void drawHeadRight(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        float top = danMuView.headBgMarginTop + (int) (danMuView.getY()) + (danMuChannel.height >> 1);
        float x = danMuView.getX() + danMuView.marginLeft + (danMuView.avatarWidth >> 1);
        rectF.set(x, top,
                (x + danMuView.headRightWidth),
                top + danMuView.headRightHeight);
        canvas.drawBitmap(danMuView.headRight, null, rectF, paint);
    }

    //头像顶的皇冠
    protected void drawHeadTop(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        float top = (int) (danMuView.getY()) + (danMuChannel.height >> 1) - (danMuView.headTopHeight >> 1);
        float x = danMuView.getX() + danMuView.marginLeft - danMuView.headBgMarginLeftOther;
        rectF.set((int) x, top,
                (int) (x + danMuView.headTopWidth),
                top + danMuView.headTopHeight);
        canvas.drawBitmap(danMuView.headTop, null, rectF, paint);
    }

    //头像
    protected void drawAvatar(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        float top = danMuView.headBgMarginTop + (int) (danMuView.getY()) + danMuChannel.height / 2 - danMuView.avatarHeight / 2;
        float x = danMuView.getX() + danMuView.marginLeft;
        rectF.set((int) x, top,
                (int) (x + danMuView.avatarWidth),
                top + danMuView.avatarHeight);
        canvas.drawBitmap(danMuView.avatar, null, rectF, paint);
    }

    //    //头像背景
    protected void drawAvatarCircle(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        //聊天弹幕
        if (danMuView.isChat) {

        } else {
            //系统的
            float top = danMuView.headBgMarginTop + (int) (danMuView.getY()) + danMuChannel.height / 2 - danMuView.avatarCircleWidth / 2;
            float x = danMuView.getX() + danMuView.marginLeft - danMuView.headBgMarginBottom;
            rectF.set((int) x, top,
                    (int) (x + danMuView.avatarCircleWidth),
                    top + danMuView.avatarCircleHight);
            canvas.drawBitmap(danMuView.avatarCircle, null, rectF, paint);
        }

    }

    protected void drawIntoSpecail(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        float top = (float) ((danMuView.getY() + danMuChannel.height + (danMuChannel.height - danMuView.avatarHeight) / 2 - danMuView.intoSpectialHeight) + danMuView.endMargin * 1.14);
        CharSequence text = danMuView.text;
        StaticLayout staticLayout = new StaticLayout(text,
                paint,
                (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        float x = (float) (danMuView.getX() + staticLayout.getWidth() + danMuView.marginLeft + danMuView.avatarWidth + danMuView.textBackgroundMarginLeft + danMuView.textMarginLeft + danMuView.textBackgroundPaddingRight - danMuView.headBgMarginTop + danMuView.endMargin * 0.4);

        rectF.set(x - danMuView.textBackgroundMarginLeft - danMuView.levelMarginLeft, top,
                (x + danMuView.intoSpecialWidth - danMuView.textBackgroundMarginLeft),
                top + danMuView.intoSpectialHeight);
        canvas.drawBitmap(danMuView.danmuEndBackground, null, rectF, paint);
    }

    protected void drawLevel(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        if (danMuView.isChat) {
//            CharSequence text = danMuView.userName;
//            StaticLayout staticLayout = new StaticLayout(text,
//                    paint,
//                    (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
//                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
//            float top = danMuView.getY() + danMuChannel.height + (danMuChannel.height - danMuView.avatarHeight) / 2 - danMuView.textBackgroundMarginHeight - staticLayout.getHeight() + danMuView.endMargin;
//            float x = danMuView.getX()
//                    + danMuView.marginLeft + danMuView.avatarWidth
//                    + staticLayout.getWidth()
//                    + danMuView.textMarginLeft * 2;
//            rectF.set((int) x, top,
//                    (int) (x + danMuView.levelBitmapWidth),
//                    top + danMuView.levelBitmapHeight);
//            canvas.drawBitmap(danMuView.levelBitmap, null, rectF, paint);
        } else {
            float top = danMuView.headBgMarginTop + (int) (danMuView.getY()) + danMuChannel.height / 2 - danMuView.levelBitmapHeight / 2;
            float x = danMuView.getX()
                    + danMuView.marginLeft
                    + danMuView.avatarWidth
                    + danMuView.levelMarginLeft;

            rectF.set((int) x, top,
                    (int) (x + danMuView.levelBitmapWidth),
                    top + danMuView.levelBitmapHeight);
            canvas.drawBitmap(danMuView.levelBitmap, null, rectF, paint);

        }
    }

    protected void drawLevelText(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
//        if (TextUtils.isEmpty(danMuView.levelText)) {
//            return;
//        }
//
//        paint.setTextSize(danMuView.levelTextSize);
//        paint.setColor(danMuView.levelTextColor);
//        paint.setStyle(Paint.Style.FILL);
//
//        float top = danMuView.headBgMarginTop + (int) danMuView.getY()
//                + danMuChannel.height / 2
//                - paint.ascent() / 2
//                - paint.descent() / 2;
//
//        float x = danMuView.getX()
//                + danMuView.marginLeft
//                + danMuView.avatarWidth
//                + danMuView.levelMarginLeft
//                + danMuView.levelBitmapWidth / 2;
//
//        canvas.drawText(danMuView.levelText.toString(), (int) x, top, paint);

    }

    protected void drawName(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        if (TextUtils.isEmpty(danMuView.userName)) {
            return;
        }
        paint.setTextSize(danMuView.textSize);
        paint.setColor(danMuView.userNameColor);
        paint.setStyle(Paint.Style.FILL);

        if (danMuView.isChat) {
            CharSequence text = danMuView.userName;
            StaticLayout staticLayout = new StaticLayout(text,
                    paint,
                    (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);

            float x = danMuView.getX()
                    + danMuView.marginLeft
                    + danMuView.avatarWidth
                    + danMuView.textMarginLeft;
            float top = danMuView.getY() + danMuChannel.height + (danMuChannel.height - danMuView.avatarHeight) / 2 - danMuView.textBackgroundMarginHeight - staticLayout.getHeight() - +danMuView.endMargin;
            canvas.save();
            canvas.translate((int) x, top);
            staticLayout.draw(canvas);
            canvas.restore();
        } else {


        }
    }

    protected void drawText(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        if (TextUtils.isEmpty(danMuView.text)) {
            return;
        }
        //聊天弹幕
        if (danMuView.isChat) {
            paint.setTextSize(danMuView.textSize);
            paint.setColor(danMuView.textColor);
            paint.setStyle(Paint.Style.FILL);
            CharSequence text = danMuView.text + "    ";
            StaticLayout staticLayout = new StaticLayout(text,
                    paint,
                    (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);

            float x = danMuView.getX()
                    + danMuView.marginLeft
                    + danMuView.avatarWidth
                    + danMuView.textMarginLeft;

            float top = danMuView.getY() + danMuChannel.height + (danMuChannel.height - danMuView.avatarHeight) / 2 - danMuView.textBackgroundMarginHeight + danMuView.endMargin;
            canvas.save();
            canvas.translate((int) x, top);
            staticLayout.draw(canvas);
            canvas.restore();
        } else {
            // 系统弹幕
            paint.setTextSize(danMuView.textSize);
            paint.setColor(danMuView.textColor);
            paint.setStyle(Paint.Style.FILL);

            CharSequence text = danMuView.text;
            StaticLayout staticLayout = new StaticLayout(text,
                    paint,
                    (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            float x = danMuView.getX()
                    + danMuView.marginLeft
                    + danMuView.avatarWidth
                    + danMuView.levelMarginLeft
                    + danMuView.levelBitmapWidth
                    + danMuView.textMarginLeft;
            float top = danMuView.headBgMarginTop + (int) (danMuView.getY())
                    + danMuChannel.height / 2
                    - staticLayout.getHeight() / 2;
            canvas.save();
            canvas.translate((int) x, top);
            staticLayout.draw(canvas);
            canvas.restore();
        }
    }

    protected void drawTextBackground(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        CharSequence text = danMuView.text+"    ";
        StaticLayout staticLayout = new StaticLayout(text,
                paint,
                (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);

        if (danMuView.isChat) {
            int textBackgroundHeight = danMuView.textBackgroundMarginHeight;
            float top = danMuView.headBgMarginTop / 4 + danMuView.getY() + danMuChannel.height + (danMuChannel.height - danMuView.avatarHeight) / 2 - danMuView.textBackgroundMarginHeight;

            float x = (float) (danMuView.getX()
                    + danMuView.marginLeft
                    + danMuView.avatarWidth
                    - danMuView.textBackgroundMarginLeft * 1.5);

            Rect rectF = new Rect((int) x,
                    (int) top,
                    (int) (x
                            + danMuView.levelBitmapWidth
                            + staticLayout.getWidth()),
                    (int) (top + textBackgroundHeight));

            danMuView.textBackground.setBounds(rectF);
            danMuView.textBackground.draw(canvas);
        } else {
            int textBackgroundHeight = staticLayout.getHeight()
                    + danMuView.textBackgroundPaddingTop
                    + danMuView.textBackgroundPaddingBottom;

            float top = danMuView.headBgMarginTop + danMuView.getY()
                    + (danMuChannel.height - textBackgroundHeight) / 2;

            float x = danMuView.getX()
                    + danMuView.marginLeft
                    + danMuView.avatarWidth
                    - danMuView.textBackgroundMarginLeft;
            Rect rectF = new Rect((int) x,
                    (int) top,
                    (int) (x + danMuView.levelMarginLeft
                            + danMuView.levelBitmapWidth
                            + danMuView.textMarginLeft
                            + danMuView.textBackgroundMarginLeft
                            + staticLayout.getWidth()
                            + danMuView.textBackgroundPaddingRight),
                    (int) (top + textBackgroundHeight));
            danMuView.textBackground.setBounds(rectF);
            danMuView.textBackground.draw(canvas);
        }
    }

    @Override
    public void requestLayout() {
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void hideNormal(boolean hide) {
        this.hide = hide;
    }

    @Override
    public void hideAll(boolean hideAll) {
        this.hideAll = hideAll;
    }

    @Override
    public void execute(Canvas canvas, DanMuModel danMuView, DanMuChannel danMuChannel) {
        if ((int) danMuView.getSpeed() == 0) {
            danMuView.setAlive(false);
        }

        onLayout(danMuView, danMuChannel);

        if (hideAll) {
            return;
        }

        if (danMuView.getPriority() == DanMuModel.NORMAL && hide) {
            return;
        }

        draw(canvas, danMuView, danMuChannel);
    }

}
