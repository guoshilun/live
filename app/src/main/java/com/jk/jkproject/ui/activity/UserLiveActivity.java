package com.jk.jkproject.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.LiveFoundRecommendAdapter;
import com.jk.jkproject.ui.entity.LiveClosePushInfo;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.entity.LiveHostClost;
import com.jk.jkproject.ui.widget.SpaceFoundItemDecoration;
import com.jk.jkproject.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/8/21 5:12 PM
 * @desc 主播下播用户端显示View
 */
public class UserLiveActivity extends BActivity {


    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_user_header)
    SimpleDraweeView sdvUserHeader;
    @BindView(R.id.tv_user_name_host)
    TextView tvUserNameHost;
    @BindView(R.id.tv_user_level1)
    TextView tvUserLevel1;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_follow_btn)
    TextView tvFollowBtn;
    @BindView(R.id.tv_host)
    TextView tvHost;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_live_stop)
    RelativeLayout rlLiveStop;
    private Unbinder bind;
    private int type;
    private int progress;
    private static int MESSAGE_TYPE = 291;

    private List<LiveHomeBeanInfo.DataBean> list;
    private LiveFoundRecommendAdapter mAdapter;
    private ProgressBar pd, progressBar;
    private LiveClosePushInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_stop);
        bind = ButterKnife.bind(this);
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        initData();
    }

    private void initData() {

    }

    private void initView() {
        rlLiveStop.setVisibility(View.VISIBLE);
        if (getIntent() != null) {
            type = getIntent().getIntExtra("type", -1);
            switch (type) {
                case 0:
                    info = (LiveClosePushInfo) getIntent().getSerializableExtra("info");
                    boolean isAttention = getIntent().getBooleanExtra("isAttention", false);
                    if (isAttention) {
                        info.setIsAttention(1);
                    }
                    stopPlay(info);
                    break;
                case 1:
                    String roomId = getIntent().getStringExtra("roomId");
                    AppApis.getHostInfo(roomId, this);
                    break;
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        ToastUtils.showShortToast(error);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_HOST_INFO)) {
            if (obj != null && obj instanceof LiveHostClost) {
                LiveHostClost info = (LiveHostClost) obj;
                if (info.getCode() == 200) {
                    stopPlay(info.getData());
                }
            }
        } else if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    tvFollowBtn.setText("已关注");
                    tvFollowBtn.setTextColor(getResources().getColor(R.color.color_FC5E8E));
                    tvFollowBtn.setBackgroundResource(R.drawable.bg_follow_white_btn);
                    tvFollowBtn.setEnabled(false);
                    ToastUtils.showShortToast("关注成功");
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        } else if (url.equals(Urls.GET_HOME_REMANNDE_LIST) && obj != null && obj instanceof LiveHomeBeanInfo) {
            LiveHomeBeanInfo info = (LiveHomeBeanInfo) obj;
            if (info.getCode() == 200) {
                if (mAdapter != null) {
                    mAdapter.setDatas(info.getData());
                }
            }
        }
    }

    //获取推荐数据
    private void setQuestRecommendData() {
        AppApis.getHomeRecommendedData(this);
    }

    private void setFollow(int paramInt, String paramString) {
        AppApis.getUpdateFollow(paramInt, paramString, this);
    }

    private void stopPlay(final LiveClosePushInfo info) {
        this.info = info;
        setQuestRecommendData();
        sdvUserHeader.setImageURI(info.getPicture());
        tvUserNameHost.setText(info.getNickName());
        tvUserLevel1.setText(info.getAnchorGrade());
        tvUserId.setText("ID " + info.getAccount());
        switch (info.getIsAttention()) {
            case 1:
                tvFollowBtn.setText("已关注");
                tvFollowBtn.setTextColor(getResources().getColor(R.color.color_FC5E8E));
                tvFollowBtn.setBackgroundResource(R.drawable.bg_follow_white_btn);
                tvFollowBtn.setEnabled(false);
                break;
            default:
                tvFollowBtn.setText("关注");
                tvFollowBtn.setTextColor(getResources().getColor(R.color.white));
                tvFollowBtn.setBackgroundResource(R.drawable.bg_follow_red_btn);
                tvFollowBtn.setEnabled(true);
                break;
        }
        tvFollowBtn.setOnClickListener(v -> setFollow(info.getIsAttention() == 0 ? 1 : 0, info.getAccount()));
        list = new ArrayList<>();
        mAdapter = new LiveFoundRecommendAdapter(this, list, R.layout.live_found_recommend);
        recyclerView.addItemDecoration(new SpaceFoundItemDecoration(this, 9));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) gridLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new LiveFoundRecommendAdapter.OnItemClickListener() {
            public void click(ProgressBar pb) {
                setStart(pb);
            }

            @Override
            public void positionClick(ProgressBar view) {
                setposition(view);
            }
        });
    }

    private void setStart(ProgressBar pb) {
        this.pd = pb;
        (new MyThread()).start();
    }

    private void setposition(ProgressBar view) {
        progressBar = view;
    }

    @OnClick(R.id.iv_return)
    public void onViewClicked() {
        finish();
    }

    private class MyThread extends Thread {
        private MyThread() {
        }

        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                if (progress == 100) {
                    setQuestRecommendData();
                    progress = 0;
                    return;
                }
                progress++;
                Message message = new Message();
                message.what = MESSAGE_TYPE;
                updateBarHandler.sendMessage(message);
            }
        }
    }

    public Handler updateBarHandler = new Handler() {
        public void handleMessage(Message param1Message) {
            if (MESSAGE_TYPE == param1Message.what) {
                if (pd != null) {
                    pd.setProgress(progress);
                }
                if (progressBar != null) {
                    progressBar.setProgress(progress);
                }
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
