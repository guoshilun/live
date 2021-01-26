package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.LiveRoomWheatListAdapter;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.widget.pagescroll.HorizontalPageLayoutManager;
import com.jk.jkproject.ui.widget.pagescroll.PagingScrollHelper;
import com.jk.jkproject.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DialogLiveRoomPKList extends BaseDialog {

    @BindView(R.id.tv_wheat_count)
    TextView tvWheatCount;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_wheat_list)
    RecyclerView rvWheatList;
    @BindView(R.id.ll_emply)
    LinearLayout llEmply;

    private DialogReturnListener listener;
    private Context mContext;
    private int mType;
    private List<LiveRoomWheatBaseInfo.DataBean> dataBeans = new ArrayList<>();
    private Unbinder bind;
    private LiveRoomWheatListAdapter mAdapter;

    public DialogLiveRoomPKList(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }

    public void setBeanInfo(LiveRoomWheatBaseInfo.DataBean info) {
        if (mAdapter != null) {
            mAdapter.add(info, true);
        }
    }

    //用户取消更新列表
    public void setUpdataUserId(String userId) {
        if (mAdapter != null) {
            for (int i = 0; i < mAdapter.getList().size(); i++) {
                if (mAdapter.getList().get(i).getUserId().equals(userId)) {
                    mAdapter.remove(i, true);
                }
            }
        }
    }

    public void setList(List<LiveRoomWheatBaseInfo.DataBean> wheatBeans) {
        if (mAdapter != null) {
            mAdapter.clear(true);
            mAdapter.addAll(wheatBeans);
        }
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_pk_list);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.BOTTOM;
    }

    public void getData() {
        AppApis.getPKFriendList(this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_PK_FRIEDN_LIST)) {
            if (obj != null && obj instanceof LiveRoomWheatBaseInfo) {
                LiveRoomWheatBaseInfo info = (LiveRoomWheatBaseInfo) obj;
                if (mAdapter != null && info.getCode() == 200 && info.getData() != null) {
                    tvWheatCount.setVisibility(View.VISIBLE);
                    rvWheatList.setVisibility(View.VISIBLE);
                    llEmply.setVisibility(View.GONE);
                    tvWheatCount.setText(info.getData().size() + "");
                    if (info.getData().size() > 0) {
                        mAdapter.clear();
                        mAdapter.addAll(info.getData(), true);
                    } else {
                        llEmply.setVisibility(View.VISIBLE);
                        rvWheatList.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    protected void initView() {
        tvTitle.setText("邀请好友PK");
        mAdapter = new LiveRoomWheatListAdapter(mContext, dataBeans, 2);
        PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(1, 4);
        rvWheatList.setAdapter(mAdapter);
        ((DefaultItemAnimator) rvWheatList.getItemAnimator()).setSupportsChangeAnimations(false);
        pagingScrollHelper.setUpRecycleView(rvWheatList);
        rvWheatList.setLayoutManager(horizontalPageLayoutManager);
        pagingScrollHelper.updateLayoutManger();
        mAdapter.setOnItemClickListener(new LiveRoomWheatListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, int Type, String userId) {

            }

            @Override
            public void onItemClick(int type, LiveRoomWheatBaseInfo.DataBean info) {
                if (listener != null) {
                    listener.onClick(type, info);
                    dismiss();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.getScreenW(getContext()), ScreenUtils.dp2px(getContext(), 259));
    }


    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public static interface DialogReturnListener {
        void onClick(int type, String userId);

        void onClick(int type, LiveRoomWheatBaseInfo.DataBean info);
    }
}