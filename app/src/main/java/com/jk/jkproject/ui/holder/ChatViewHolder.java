package com.jk.jkproject.ui.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.fresco.helper.Phoenix;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.DanmuBean;
import com.jk.jkproject.ui.inter.OnItemClickLitener;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.EmojiParseUtil;
import com.jk.jkproject.utils.EmoticonUtils;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;
import com.jk.jkproject.utils.emoji.AlineCenterImageSpan;
import com.jk.jkproject.utils.emoji.LHImageSpan;
import com.miliyo.danmaku.model.utils.DimensionUtil;

import static com.jk.jkproject.utils.Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_10;
import static com.jk.jkproject.utils.Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_14;
import static com.jk.jkproject.utils.Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_15;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    private Context context;

    private AlineCenterImageSpan imageSpan;

    private OnItemClickLitener listener;

    private NoLineClickSpan noLineClickSpan;

    private RelativeLayout rl_chat;

    private TextView tvUserLevel, tvHostLevel, hostType;

    private TextView userMsg;

    public ChatViewHolder(View paramView, OnItemClickLitener paramOnItemClickLitener) {
        super(paramView);
        this.listener = paramOnItemClickLitener;
        this.context = paramView.getContext();
        this.userMsg = (TextView) paramView.findViewById(R.id.livechat_msg_content);
        this.tvUserLevel = (TextView) paramView.findViewById(R.id.tv_user_level);
        this.tvHostLevel = (TextView) paramView.findViewById(R.id.tv_host_level);
        this.hostType = (TextView) paramView.findViewById(R.id.tv_mage);
        this.rl_chat = (RelativeLayout) paramView.findViewById(R.id.rl_chat);
        this.rl_chat.setBackgroundResource(R.drawable.bg_live_room_chat);
        this.noLineClickSpan = new NoLineClickSpan(paramOnItemClickLitener);
    }

    @SuppressLint({"ResourceAsColor"})
    private void showBaseMsg(final DanmuBean msg, int paramInt) {
        if (msg != null && msg.getData().getFrom() != null) {
            tvUserLevel.setVisibility(View.VISIBLE);
            String body = msg.getData().getContent();
            String imageUrl = "";
            String content;
            if (body.contains("---")) {
                String split[] = body.split("---");
                content = split[0] + "  ";
                imageUrl = split[1];
            } else {
                content = body + "  ";
            }
            String nickName = "   " + msg.getData().getFrom().getNickName() + ":";
//            setLevel(tvUserLevel, msg.getData().getFrom().getLevel());
            UserLevelSetUtils.setUserLevel(tvUserLevel, msg.getData().getFrom().getLevel());
            String emtpy = "       ";
            switch (msg.getData().getFrom().getUserType()) {
                case 3:
                    hostType.setVisibility(View.VISIBLE);
                    hostType.setBackgroundResource(R.drawable.bg_chat_red_btn);
                    hostType.setText("管");
                    emtpy = emtpy + "      ";
                    break;
                case 2:
                    hostType.setVisibility(View.VISIBLE);
                    hostType.setBackgroundResource(R.drawable.bg_chat_blue_btn);
                    hostType.setText("主");
                    emtpy = emtpy + "      ";
                    break;
                default:
                    hostType.setVisibility(View.GONE);
                    break;
            }
            String msgStr = emtpy + nickName + content;
            if (msg.getData().getFrom().getNickName().isEmpty()) {
                nickName = " 匿名：";
            }
            int nameColor = context.getResources().getColor(R.color.color_3AFFF8), contentColor = context.getResources().getColor(R.color.white);
            if (msg.getData().getFrom().getUserType() == 2) {
                nameColor = context.getResources().getColor(R.color.color_FF94DB);
            } else if (msg.getData().getFrom().getMsgType() == TYPE_LIVE_CHAT_SYSTEM_14) {
                contentColor = context.getResources().getColor(R.color.color_84FF50);
            }
            if (!imageUrl.equals("")) {
                contentColor = context.getResources().getColor(R.color.color_F9FD87);
            }
            SpannableString spannableString = EmojiParseUtil.getInstace().getExpressionString(context.getApplicationContext(), EmoticonUtils.get().getDefaultEmojiIds(), msgStr, 18);
            spannableString.setSpan(new ForegroundColorSpan(nameColor), emtpy.length(), emtpy.length() + nickName.length(), 33);
            spannableString.setSpan(noLineClickSpan, emtpy.length(), emtpy.length() + nickName.length(), 33);
            tvUserLevel.setText(msg.getData().getFrom().getLevel() + "");
            userMsg.setTextColor(contentColor);
            userMsg.setText((CharSequence) spannableString);
            userMsg.setMovementMethod(LinkMovementMethod.getInstance());
            userMsg.setOnClickListener(param1View -> {
                if (listener != null && !msg.getData().getFrom().getRid().equals("0") && OnClickUtils.isFastClick()) {
                    listener.onItemNameClick(msg.getData().getFrom().getRid());
                }
            });
            if (!imageUrl.trim().isEmpty()) {
                final int imgSize = DimensionUtil.dpToPx(context, 16);
                Phoenix.with(context)
                        .setUrl(imageUrl)
                        .setWidth(imgSize)
                        .setHeight(imgSize)
                        .setResult(bitmap -> {
                            final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                            spannableStringBuilder.append("我是礼物");
                            LHImageSpan imageSpan = new LHImageSpan(context, bitmap, imgSize);
                            spannableStringBuilder.setSpan(imageSpan,
                                    0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            if (userMsg != null) {
                                userMsg.append(spannableStringBuilder);
                            }
                        })
                        .load();
            }
        }
    }

    //等级划分
    private void setLevel(TextView tvUserLevel, String level) {
        int userLevel = Integer.parseInt(level);
        if (userLevel > 120) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_120);
        } else if (userLevel > 100) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_100);
        } else if (userLevel > 80) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_80);
        } else if (userLevel > 60) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_60);
        } else if (userLevel > 40) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_40);
        } else if (userLevel > 0) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_20);
        }
    }

    private void showBaseSystemMsg(final DanmuBean msg, int paramInt) {
        tvUserLevel.setVisibility(View.GONE);
        hostType.setVisibility(View.GONE);
        String str = "";
        if (msg.getData().getFrom().getMsgType() == TYPE_LIVE_CHAT_SYSTEM_15) {
            str = msg.getData().getContent();
        } else if (msg.getData().getFrom().getMsgType() == TYPE_LIVE_CHAT_SYSTEM_10) {
            str = "系统提示：" + msg.getData().getContent();
        } else if (msg.getData().getFrom().getMsgType() >= 31 && msg.getData().getFrom().getMsgType() <= 42) {
            if (msg.getData().getContent().contains("xxx")) {
                str = "系统提示：" + msg.getData().getContent().replace("xxx", msg.getData().getFrom().getNickName());
            }
        } else {
            str = "系统提示：" + msg.getData().getFrom().getNickName() + msg.getData().getContent();
        }
        int nameColor = context.getResources().getColor(R.color.color_3AFFF8), contentColor = context.getResources().getColor(R.color.color_FBFF7E);
        if (msg.getData().getFrom().getUserType() == 2) {
            nameColor = context.getResources().getColor(R.color.color_FF94DB);
        }
        SpannableString spannableString = EmojiParseUtil.getInstace().getExpressionString(this.context.getApplicationContext(), EmoticonUtils.get().getDefaultEmojiIds(), str, 18);
        if (msg.getData().getFrom().getMsgType() >= 31 && msg.getData().getFrom().getMsgType() <= 42) {
            if (msg.getData().getContent().contains("xxx")) {
                spannableString.setSpan(new ForegroundColorSpan(nameColor), "系统提示：".length() + msg.getData().getContent().indexOf("xxx"), "系统提示：".length() + msg.getData().getContent().indexOf("xxx") + msg.getData().getFrom().getNickName().length(), 33);
            }
        } else if (msg.getData().getFrom().getMsgType() != TYPE_LIVE_CHAT_SYSTEM_10) {
            spannableString.setSpan(new ForegroundColorSpan(nameColor), "系统提示：".length(), "系统提示：".length() + msg.getData().getFrom().getNickName().length(), 33);
        }
        userMsg.setTextColor(contentColor);
        userMsg.setText((CharSequence) spannableString);
        userMsg.setMovementMethod(LinkMovementMethod.getInstance());
        userMsg.setOnClickListener(param1View -> {
//            if (msg.getData().getFrom().getMsgType() != TYPE_LIVE_CHAT_SYSTEM_15) {
//                if (listener != null) {
//                    listener.onItemNameClick(msg.getData().getFrom().getRid());
//                }
//            }
        });

    }

    private void showNormalMsg(DanmuBean danmuBean) {
        if (danmuBean != null) {
            showBaseMsg(danmuBean, context.getResources().getColor(R.color.color_84FF50));
        }
    }

    private void showSystemMsg(DanmuBean danmuBean) {
        if (danmuBean != null && Build.VERSION.SDK_INT >= 23) {
            showBaseSystemMsg(danmuBean, context.getColor(R.color.color_FBFF7E));
        }
    }

    public void bind(DanmuBean danmuBean) {
        userMsg.setTag(danmuBean);
        switch (danmuBean.getData().getFrom().getMsgType()) {
            case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_0:
            case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_2:
            case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_3:
            case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_12:
            case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_14:
                showNormalMsg(danmuBean);
                break;
            default:
                showSystemMsg(danmuBean);
                break;
        }
    }

    public void release() {
        if (imageSpan != null) {
            imageSpan.release();
        }
        userMsg = null;
        listener = null;
        context = null;
        if (noLineClickSpan != null) {
            noLineClickSpan.release();
        }
    }
}