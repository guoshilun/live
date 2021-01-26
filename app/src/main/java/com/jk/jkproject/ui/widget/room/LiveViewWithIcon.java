package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.JoinRoomBeanInfo;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.StringUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import cn.iwgang.countdownview.CountdownView;

public class LiveViewWithIcon extends RelativeLayout {
    private CountdownView gameCountDown;

    private SimpleDraweeView ivIcon;

    private ImageView ivLove;

    private int num = 0;

    private OnListener onClickListener;

    private RelativeLayout rl_icon;

    private TextView tvDesLeft;

    private TextView tvDesRight;

    private TextView tvHot;

    private TextView tvName;
    private JoinRoomBeanInfo info;

    public LiveViewWithIcon(Context paramContext) {
        super(paramContext);
        init(paramContext);
    }

    public LiveViewWithIcon(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init(paramContext);
    }

    public LiveViewWithIcon(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init(paramContext);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_view_live_view_with_icon, this, true);
        ivIcon = (SimpleDraweeView) findViewById(R.id.iv_icon);
        ivLove = (ImageView) findViewById(R.id.iv_love);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvHot = (TextView) findViewById(R.id.tv_hot);
        rl_icon = findViewById(R.id.rl_icon);
        gameCountDown = (CountdownView) findViewById(R.id.game_countdown_room);
        tvDesLeft = (TextView) findViewById(R.id.tv_user_name_host);
        tvDesRight = (TextView) findViewById(R.id.tv_des_right);
    }

    public OnListener getOnClickListener() {
        return this.onClickListener;
    }

    public void setGame() {
        this.ivIcon.setVisibility(VISIBLE);
        this.tvName.setText("全民战主播");
        this.tvDesRight.setVisibility(GONE);
        this.tvDesLeft.setVisibility(GONE);
    }

    public void setIvIcon(boolean flag) {
        if (info != null) {
            if (ivLove != null) {
                if (flag) {
                    ivLove.setImageResource(R.mipmap.live_icon_room_found);
                    this.ivLove.setEnabled(false);
                    info.setIsAttention(1);
                } else {
                    ivLove.setImageResource(R.mipmap.live_icon_room_unfound);
                    this.ivLove.setEnabled(true);
                    info.setIsAttention(0);
                }
            }
        }
    }

    public void setLevel(int grade, long experience) {
        tvDesLeft.setText(grade + "");
        UserLevelSetUtils.setHostLevel(tvDesLeft, grade + "", true);
        tvHot.setText("需" + StringUtils.ReadSize(Integer.parseInt(experience + "")) + "升级");
    }

    public void setLiveRoomData(final JoinRoomBeanInfo info) {
        this.info = info;
        ivIcon.setImageURI(info.getPicture());
        tvName.setText(info.getNickname());
        tvDesLeft.setText(info.getAnchorGrade() + "");
        UserLevelSetUtils.setHostLevel(tvDesLeft, info.getAnchorGrade() + "", true);
        tvHot.setText("需" + StringUtils.ReadSize(info.getNeedExperience()) + "升级");
        if (!SPUtils.getIsHost()) {
            if (info.getIsAttention() != 1) {
                ivLove.setImageResource(R.mipmap.live_icon_room_unfound);
                ivLove.setEnabled(true);
                ivLove.setOnClickListener(param1View -> {
                    if (onClickListener != null)
                        onClickListener.onClick(info.getUserId(), false);
                });
            } else {
                ivLove.setImageResource(R.mipmap.live_icon_room_found);
                ivLove.setEnabled(false);
            }
        } else {
            ivLove.setVisibility(GONE);
        }
        rl_icon.setOnClickListener(param1View -> {
            if (onClickListener != null)
                onClickListener.onClick(info.getUserId(), true);
        });
    }

    public void setOnClickListener(OnListener paramOnListener) {
        this.onClickListener = paramOnListener;
    }

    public static interface OnListener {
        void onClick(String param1String, boolean param1Boolean);
    }
}
