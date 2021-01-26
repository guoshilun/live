package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc 随机pk邀请Dialog
 */
public class DialogLiveRoomPKInvitation extends BaseDialog {

    @BindView(R.id.tv_pk_record)
    TextView tvPkRecord;
    @BindView(R.id.sdv_header_1)
    SimpleDraweeView sdvHeader1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.sdv_header_2)
    SimpleDraweeView sdvHeader2;
    @BindView(R.id.sdv_header_3)
    SimpleDraweeView sdvHeader3;
    @BindView(R.id.tv_name_3)
    TextView tvName3;
    @BindView(R.id.tv_user_level_3)
    TextView tvUserLevel3;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_user_id_3)
    TextView tvUserId3;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;
    private int mType; //1.随机 2.好友 3.好友发起PK等待界面
    private LiveRoomWheatBaseInfo.DataBean info;
    private String roomId;
    private String userId;
    private DialogLiveRoomPunishmentClose mPKPunishClose;

    public DialogLiveRoomPKInvitation(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_pk_invitation);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.BOTTOM;
    }

    public void setData(int mType, LiveRoomWheatBaseInfo.DataBean info) {
        this.mType = mType;
        this.info = info;
        switch (mType) {
            case 1:
                sdvHeader1.setImageURI(SPUtils.getPicture());
                sdvHeader2.setImageResource(R.mipmap.icon_pk_vs);
                sdvHeader3.setImageResource(R.mipmap.icon_pk_random_pic);
                tvUserId.setVisibility(View.GONE);
                tvUserId3.setVisibility(View.GONE);
                tvName.setVisibility(View.GONE);
                tvUserLevel.setVisibility(View.GONE);
                tvName3.setVisibility(View.GONE);
                tvUserLevel3.setVisibility(View.GONE);
                tv1.setText("系统根据规则自动进行匹配");
                tv2.setText("成功后即可开始PK");
                tv3.setVisibility(View.VISIBLE);
                tv3.setTextColor(mContext.getResources().getColor(R.color.white));
                tvPkRecord.setText("随机PK");
                tv3.setBackgroundResource(R.drawable.bg_pk_round_btn);
                tv3.setText("发起PK");
                break;
            case 2:
            case 3:
                sdvHeader1.setImageURI(SPUtils.getPicture());
                sdvHeader2.setImageResource(R.mipmap.icon_pk_vs);
                sdvHeader3.setImageURI(info.getPicture());
                tvUserId.setVisibility(View.VISIBLE);
                tvUserId3.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvUserLevel.setVisibility(View.VISIBLE);
                tvName3.setVisibility(View.VISIBLE);
                tvUserLevel3.setVisibility(View.VISIBLE);
                tv1.setText("20s");
                tv2.setText("正在等待好友确认");
                tvName.setText(SPUtils.getNickname());
                tvName3.setText(info.getNickname());
                tvUserId.setText("JK账号:" + SPUtils.getUserId());
                tvUserId3.setText("JK账号:" + info.getUserId());
                tvUserLevel.setText(SPUtils.getAnchorGrade());
                tvUserLevel3.setText(info.getAnchorGrade());
                UserLevelSetUtils.setHostLevel(tvUserLevel, SPUtils.getAnchorGrade());
                UserLevelSetUtils.setHostLevel(tvUserLevel3, info.getAnchorGrade());
                tv3.setVisibility(View.GONE);
                tv3.setTextColor(mContext.getResources().getColor(R.color.white));
                tvPkRecord.setText("邀请中");
                break;
            case 4:
                tv2.setText("正在为您寻找PK对手");
                tv3.setText("匹配中...");
                tv3.setBackgroundResource(R.drawable.bg_pk_inviation_btn);
                tv3.setTextColor(mContext.getResources().getColor(R.color.color_9559FF));
                break;
        }
    }

    protected void initView() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.getScreenW(getContext()), ScreenUtils.dp2px(getContext(), 259));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick({R.id.sdv_header_1, R.id.sdv_header_2, R.id.sdv_header_3, R.id.tv_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sdv_header_1:
                break;
            case R.id.sdv_header_2:
                break;
            case R.id.sdv_header_3:
                break;
            case R.id.tv_3:
                if (tv3.getText().toString().trim().equals("发起PK")) {
                    tv1.setText("120s");
                    tv2.setText("正在为您寻找PK对手");
                    tv3.setText("匹配中...");
                    tv3.setBackgroundResource(R.drawable.bg_pk_inviation_btn);
                    tv3.setTextColor(mContext.getResources().getColor(R.color.color_9559FF));
                    IMLiveRoomManager.instance().sendRandomPk(roomId, userId);
                } else {
                    if (listener != null) {
                        listener.onClick(null);
                        dismiss();
                    }
                }
                break;
        }
    }

    public void setPKWaitTime(long remainTime) {
        long reTime = remainTime / 1000;
        tv1.setText(reTime + "s");
    }


    public void setRandom(String roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    public static interface DialogReturnListener {
        void onClick(PKTypeBaseInfo info);
    }

}