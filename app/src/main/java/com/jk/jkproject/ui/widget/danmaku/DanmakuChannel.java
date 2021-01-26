package com.jk.jkproject.ui.widget.danmaku;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveFireworksInfo;
import com.jk.jkproject.ui.widget.room.LiveLevelView;
import com.jk.jkproject.utils.EmojiParseUtil;
import com.jk.jkproject.utils.EmoticonUtils;
import com.jk.jkproject.utils.LiveRoleUtils;
import com.jk.jkproject.utils.ScreenUtils;

public class DanmakuChannel extends RelativeLayout {
    public boolean isRunning = false;

    private DanmakuClickListener listener;

    public DanmakuEntity mEntity;

    public DanmakuChannel(Context paramContext) {
        super(paramContext);
        init();
    }

    public DanmakuChannel(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init();
    }

    public DanmakuChannel(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    @TargetApi(21)
    public DanmakuChannel(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
        super(paramContext, paramAttributeSet, paramInt1, paramInt2);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.danmaku_channel_layout, (ViewGroup) this, true);
        if (Build.VERSION.SDK_INT >= 21)
            setClipToOutline(false);
    }

    public void setDanmakuClickListener(DanmakuClickListener paramDanmakuClickListener) {
        this.listener = paramDanmakuClickListener;
    }

    public void setDanmakuEntity(DanmakuEntity paramDanmakuEntity) {
        this.mEntity = paramDanmakuEntity;
    }

    public void startAnimation(final DanmakuEntity entity, final DanmakuEndListener danmakuEndListener) {
        this.isRunning = true;
        setDanmakuEntity(entity);
        if (mEntity != null) {
            final View view = View.inflate(getContext(), R.layout.danmu_userchat_item, null);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.danmu_user_avatar);
            LiveLevelView levelView = (LiveLevelView) view.findViewById(R.id.danmu_user_level);
            TextView contentView = (TextView) view.findViewById(R.id.danmu_user_des);
            if (entity.getType() == DanmakuEntity.DANMAKU_TYPE_USERCHAT) {
                levelView.setLevel(entity.getLevel());
                String name = entity.getName() + "：";
                String content = entity.getText();
                String avatarImageUrl = entity.getAvatar();
                //匿名
                if (LiveRoleUtils.isAnonymousRole(entity.getRole())) {
                    name = "匿名" + "：";
                    avatarImageUrl = "res:///" + R.mipmap.profile_secret_user;
                }
                simpleDraweeView.setImageURI(avatarImageUrl);
                SpannableString spannableString = EmojiParseUtil.getInstace().getExpressionString(getContext(),
                        EmoticonUtils.get().getDefaultEmojiIds(),
                        name + content, 16);

                spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.white)), 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                contentView.setText(spannableString);

                view.setTag(R.id.tag_uid, entity.getUserId());
                view.setTag(R.id.tag_icon, entity.getAvatar());
                view.setTag(R.id.tag_role, entity.getRole());
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            String oid = (String) v.getTag(R.id.tag_uid);
                            String icon = (String) v.getTag(R.id.tag_icon);
                            int role = (int) v.getTag(R.id.tag_role);
                            // 不等于系统账号
                            if (!TextUtils.isEmpty(oid) && !TextUtils.isEmpty(icon) && !oid.equals("1000") && listener != null) {
                                listener.onDanmuIconClick(oid, icon, role);
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            } else if (entity.getType() == DanmakuEntity.DANMAKU_TYPE_FIREWORKS) {
                simpleDraweeView.setImageURI(Uri.parse("res:///" + R.mipmap.live_fireworks));
                levelView.setLevel(entity.getLevel());

                final LiveFireworksInfo info = entity.getLiveFireworksInfo();
                if (info.getRich_text() != null) {
                    RichTextParse.parse(contentView, info.getRich_text(), false);
                } else {
                    String desStr = getContext().getString(R.string.live_gift_fireworks);
                    String nameFrom = info.getFr_name();
                    String nameTo = info.getTo_name();
                    String format = String.format(desStr, nameFrom, nameTo);
//                SpannableString spannableString = new SpannableString(format);
                    SpannableString spannableString = EmojiParseUtil.getInstace().getExpressionString(getContext(),
                            EmoticonUtils.get().getDefaultEmojiIds(),
                            format, 16);

                    spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.white)), 0, nameFrom.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.white)), nameFrom.length() + 2, nameFrom.length() + 2 + nameTo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    contentView.setText(spannableString);
                }

                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onDanmakuClick(getContext(), entity);
                        }
                    }
                });
            } else {
                simpleDraweeView.setVisibility(View.GONE);
                levelView.setVisibility(View.GONE);
                contentView.setTextColor(ContextCompat.getColor(getContext(), R.color.live_yellow));

                if (entity.getRichText() != null) {
                    RichTextParse.parse(contentView, entity.getRichText(), false);
                } else {
                    SpannableString spannableString = EmojiParseUtil.getInstace().getExpressionString(getContext(),
                            EmoticonUtils.get().getDefaultEmojiIds(),
                            entity.getText(), 16);

                    contentView.setText(spannableString);
                }
            }
            view.measure(-1, -1);
            int measuredWidth = view.getMeasuredWidth();
            //int measuredHeight = view.getMeasuredHeight();
            int screenW = ScreenUtils.getScreenW(getContext());
            int width = measuredWidth > screenW ? measuredWidth : screenW;
            DanmakuChannel.this.getLayoutParams().width = width;
            // Animation anim = AnimationHelper.createTranslateAnim(getContext(), screenW, -measuredWidth);
            ObjectAnimator animator = AnimationHelper.createObjectAnim(getContext(), view, screenW, -measuredWidth);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    new Handler().post(new Runnable() {
                        public void run() {
                            view.clearAnimation();
                            DanmakuChannel.this.removeView(view);
                            if (danmakuEndListener != null) {
                                danmakuEndListener.endDanmu();
                            }
                        }
                    });
                    isRunning = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            addView(view);
            LogUtils.i("将弹幕对象添加到指定的弹道上");
            animator.start();
            LogUtils.i("在指定的弹道上，开始执行水平移动动画");
        }
    }
}
