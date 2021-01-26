package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.net.okhttp.ResponseListener;


public class SeekBarClickRelatelayout extends RelativeLayout implements ResponseListener {
    public OnCharmItemClick click;
    private LinearLayout otherInfo;
    private SimpleDraweeView otherHead;
    private TextView tvOtherName;
    private TextView tvOtherAttent;
    private String mOid;

    public SeekBarClickRelatelayout(Context context) {
        super(context);
        init(context);
    }

    public SeekBarClickRelatelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SeekBarClickRelatelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void relase() {
        if (mOid != null) {
            mOid = null;
        }
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_live_pk_seek_click, this, true);
        otherHead = findViewById(R.id.im_other_head);
        otherInfo = findViewById(R.id.liear_pk_other_info);
        tvOtherName = findViewById(R.id.tv_other_name);
        tvOtherAttent = findViewById(R.id.tv_attention_other);
        findViewById(R.id.linear_right).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.setOnCharmItemClick(1);
                }
            }
        });
        findViewById(R.id.linear_left).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.setOnCharmItemClick(0);
                }
            }
        });
        tvOtherAttent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getAttention(!tvOtherAttent.isSelected());
            }
        });
    }

    /**
     * 关注
     *
     * @param attention
     */
    private void getAttention(boolean attention) {
        if (attention) {
            // 当前为需要关注状态
//            AppApis.liveFollow(mOid, this);
        } else {
//            AppApis.liveUnfollow(mOid, this);
        }
    }

//    //设置对方信息
//    public void setOtherUserInfo(LiveCardInfo cardInfo) {
//        mOid = cardInfo.getUid();
////        LogUtils.e("cardInfo::" + cardInfo.getFollow_state());
//        otherHead.setImageURI(cardInfo.getLive_cover());
//        tvOtherName.setText(cardInfo.getNickname());
//        otherInfo.setVisibility(VISIBLE);
//        if (cardInfo.getFollow_state() == 1) {
//            updateAttention(true);
//            tvOtherAttent.setSelected(true);
//        } else {
//            tvOtherAttent.setSelected(false);
//            updateAttention(false);
//        }
//    }

    /**
     * 更新关注状态
     *
     * @param attention
     */
    private void updateAttention(boolean attention) {
        if (tvOtherAttent == null)
            return;
        if (attention) {
            tvOtherAttent.setVisibility(View.GONE);
//            tvOtherAttent.setText(R.string.live_room_followed);
            tvOtherAttent.setSelected(true);
        } else {
            tvOtherAttent.setVisibility(View.VISIBLE);
            tvOtherAttent.setSelected(false);
//            tvOtherAttent.setText(R.string.live_room_followed_pk);
        }
    }

    @Override
    public void onSuccess(String url, Object obj) {
//        if (url.equals(Urls.LIVE_FOLLOW)) {
//            // 关注
//            BaseInfo flw = (BaseInfo) obj;
//            if (flw != null && flw.getStatus() == 1) {
//                // 关注成功
//                updateAttention(true);
//            }
//            return;
//        }
//        if (url.equals(Urls.LIVE_UNFOLLOW)) {
//            // 取消关注
//            BaseInfo unflw = (BaseInfo) obj;
//            if (unflw != null && unflw.getStatus() == 1) {
//                // 取消关注成功
//                updateAttention(false);
//            }
//            return;
//        }
    }

    @Override
    public void onStartRequest() {

    }

    @Override
    public void onFailure(int code, String url, String error) {

    }

    public void setOnCharmClick(OnCharmItemClick click) {
        this.click = click;
    }

    public interface OnCharmItemClick {
        void setOnCharmItemClick(int id);//0 左边  、1 右边
    }
}
