package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.adapter.LiveRoomWheatListAdapter;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.widget.SpaceItemWheatDecoration;
import com.jk.jkproject.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DialogLiveRoomWheatList extends BaseDialog {

    @BindView(R.id.tv_wheat_count)
    TextView tvWheatCount;
    @BindView(R.id.rv_wheat_list)
    RecyclerView rvWheatList;
    private DialogReturnListener listener;
    private Context mContext;
    private int mType;
    private List<LiveRoomWheatBaseInfo.DataBean> dataBeans = new ArrayList<>();
    private Unbinder bind;
    private LiveRoomWheatListAdapter mAdapter;

    public DialogLiveRoomWheatList(Context paramContext) {
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
        setContentView(R.layout.dialog_live_room_wheat_list);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.BOTTOM;
    }

    protected void initView() {
        mAdapter = new LiveRoomWheatListAdapter(mContext, dataBeans, 1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvWheatList.addItemDecoration(new SpaceItemWheatDecoration(mContext, 9));
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvWheatList.setNestedScrollingEnabled(false);
        rvWheatList.setLayoutManager(gridLayoutManager);
        rvWheatList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new LiveRoomWheatListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, int Type, String userId) {
                if (listener != null) {
                    listener.onClick(Type, userId);
                    dismiss();
                }
            }

            @Override
            public void onItemClick(int type, LiveRoomWheatBaseInfo.DataBean info) {

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
    }
}