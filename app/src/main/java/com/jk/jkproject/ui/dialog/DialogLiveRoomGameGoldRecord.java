package com.jk.jkproject.ui.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.info.GameLogProto;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.ui.adapter.LiveRoomGameGoldItemAdapter;
import com.jk.jkproject.ui.entity.LiveRoomGameGoldBaseInfo;
import com.jk.jkproject.ui.entity.PKRecordBaseInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc 金币游戏记录
 */
public class DialogLiveRoomGameGoldRecord extends BaseDialog implements Observer {


    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_pk_record)
    TextView tvPkRecord;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.iv_title)
    TextView ivTitle;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;
    private int mType;
    private LiveRoomGameGoldItemAdapter mAdapter;
    //    private List<LiveRoomGameGoldBaseInfo.DataBean> list;
    private List<GameLogProto.GameLogResult> list;
    private View mHeaderView;
    TextView tvSession, tvWinCount, tvEarnings;


    public DialogLiveRoomGameGoldRecord(Context paramContext, int mType) {
        super(paramContext);
        this.mContext = paramContext;
        this.mType = mType;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_game_gold_record);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.CENTER;
        MessageNotifyCenter.getInstance().addObserver(this);
    }

    private void getData() {
        IMLiveRoomManager.instance().sendGameGoldLogReq();
    }


    protected void initView() {
        list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            LiveRoomGameGoldBaseInfo.DataBean info = new LiveRoomGameGoldBaseInfo.DataBean();
//            info.setGold((i * 200000) + "");
//            info.setRank(i);
//            info.setWin(i % 2 == 0);
//            list.add(info);
//        }
        mAdapter = new LiveRoomGameGoldItemAdapter(mContext, list);
        mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView(context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((type, info) -> {
            switch (type) {
                case 1:
                    break;
                case 2:
//                    ToastUtils.showLongToast("点击邀请");
                    if (listener != null) {
                        listener.onClick(1, info);
                    }
                    dismiss();
                    break;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 333), ScreenUtils.dp2px(getContext(), 443));
        getData();
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick({R.id.iv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                if (listener != null) {
                    listener.onClick(1, null);
                    dismiss();
                }
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            android.os.Message message = android.os.Message.obtain();
            message.obj = arg;
            handler.sendMessage(message);
        }
    }


    public static interface DialogReturnListener {
        void onClick(int type, PKRecordBaseInfo.DataBean info);
    }

    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(android.os.Message param1Message) {
            Bundle bundle = (Bundle) param1Message.obj;
            switch (bundle.getInt("method")) {
                case IMEventType.ACTION_LINK_GAMELOGHANDLER:
                    if (isShowing() && mAdapter != null) {
                        LiveRoomGameGoldBaseInfo info = (LiveRoomGameGoldBaseInfo) bundle.getSerializable("info");
                        mAdapter.clear();
                        mAdapter.addAll(info.getData(), true);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void dismiss() {
        super.dismiss();
//        MessageNotifyCenter.getInstance().unregister(this);
    }
}