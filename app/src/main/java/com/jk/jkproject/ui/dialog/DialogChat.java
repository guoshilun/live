package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.chat.ChatListBeanInfo;
import com.jk.jkproject.ui.chat.ChatMessageListAdapter;
import com.jk.jkproject.ui.chat.LiveMessageActivity;
import com.jk.jkproject.ui.chat.Message;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class DialogChat extends BaseDialog implements RefreshRecyclerViewListener {


    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_left_name)
    TextView tvLeftName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_title)
    ImageView ivRightTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.sdv_icon_1)
    SimpleDraweeView sdvIcon1;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.sdv_icon_2)
    SimpleDraweeView sdvIcon2;
    @BindView(R.id.img_share1)
    ImageView imgShare1;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.live_new_recycler_view)
    WrapperRecyclerView recyclerView;
    @BindView(R.id.network_no_title)
    TextView networkNoTitle;
    @BindView(R.id.network_no_des)
    TextView networkNoDes;
    @BindView(R.id.btn_network_no_reload)
    Button btnNetworkNoReload;
    @BindView(R.id.layout_network_no_root)
    LinearLayout layoutNetworkNoRoot;
    @BindView(R.id.loading_cpb_progress)
    CircularProgressBar loadingCpbProgress;
    @BindView(R.id.layout_loading_root)
    LinearLayout layoutLoadingRoot;
    @BindView(R.id.iv_icon_1)
    ImageView ivIcon1;
    @BindView(R.id.iv_icon_2)
    ImageView ivIcon2;
    private Unbinder bind;

    private int isHasData;

    private boolean isRefresh = false;

    private List<Message> list;

    private DialogReturnListener listener;

    private ChatMessageListAdapter mAdapter;

    private Context mContext;

    private int mType;

    private int page = 0;

    private String titleName;
    private String uid;

    private String userName;

    public DialogChat(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }

    private void dismissFailView() {
        if (layoutNetworkNoRoot != null) {
            layoutNetworkNoRoot.setVisibility(View.GONE);
        }
    }

    private void dismissLoading() {
        if (layoutLoadingRoot != null) {
            layoutLoadingRoot.setVisibility(View.GONE);
        }
    }

    private void getData(int paramInt) {
        AppApis.getChatList(this);
    }

    public void setMessageType(Message message) {
        if (message.getTargetId().equals("0")) { //在线客服
            SPUtils.setKefuIsRead(true);
            ivIcon1.setVisibility(View.VISIBLE);
            setIsRead(true);
        } else {
            if (mAdapter != null) {
                boolean addResult = true;
                for (int j = 0; j < mAdapter.getList().size(); j++) {
                    Message dataBean2 = mAdapter.getList().get(j);
                    if (message.getUserId().equals(dataBean2.getTargetId())) {
                        if (LiveMessageActivity.mTargetId.equals(message.getUserId())) {
                            dataBean2.setCount(0);
                        } else {
                            dataBean2.setCount(1);
                            setIsRead(true);
                        }
                        dataBean2.setContent(message.getContent());
                        dataBean2.setSentTime(message.getSentTime());
                        addResult = false;
                        break;
                    }
                }
                if (addResult || mAdapter.getList().size() == 0) {
                    String send = message.getUserId();
                    message.setUserId(message.getTargetId());
                    message.setTargetId(send);
                    setIsRead(true);
                    mAdapter.add(0, message, false);
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void setSystem(SystemNotiBeanInfo.DataBean message) {
        if (message != null && ivIcon2 != null) {
            SPUtils.setSystemIsRead(true);
            ivIcon2.setVisibility(View.VISIBLE);
            setIsRead(true);
        }
    }

    @RequiresApi(api = 23)
    private void init() {
        ivTitle.setVisibility(View.VISIBLE);
        tvRightTitle.setVisibility(View.VISIBLE);
        tvRightTitle.setText("忽略未读");
        tvRightTitle.setClickable(false);
        tvRightTitle.setTextColor(mContext.getResources().getColor(R.color.color_999));
        tvLeftName.setVisibility(View.GONE);
        sdvIcon1.setImageResource(R.mipmap.live_icon_message_kefu);
        sdvIcon2.setImageResource(R.mipmap.live_icon_message_noti);
        tvTitle.setText("消息");
        if (SPUtils.getKefuIsRead()) {
            ivIcon1.setVisibility(View.VISIBLE);
        } else {
            ivIcon1.setVisibility(View.GONE);
        }
        if (SPUtils.getSystemIsRead()) {
            ivIcon2.setVisibility(View.VISIBLE);
        } else {
            ivIcon2.setVisibility(View.GONE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        list = new ArrayList<>();
        mAdapter = new ChatMessageListAdapter(mContext, list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreFooterView(new DefaultLoadMoreFooterView(getContext()));
        mAdapter.setOnItemClickListener(new ChatMessageListAdapter.OnItemClickListener() {
            public void onClick(Message bean) {
                if (listener != null) {
                    if (bean.getCount() > 0) {
                        bean.setCount(0);
                        mAdapter.notifyDataSetChanged();
                    }
                    setIsAllRead(false);
                    listener.onClick(3, bean);
                    dismiss();
                }
            }
        });
        recyclerView.setRecyclerViewListener(this);
        recyclerView.setBackgroundColor(mContext.getResources().getColor(R.color.color_F1F5F8));
        getData(page);
    }

    private void showFailView() {
        if (layoutNetworkNoRoot != null) {
            layoutNetworkNoRoot.setVisibility(View.VISIBLE);
        }
    }

    private void showLoading() {
        if (layoutLoadingRoot != null) {
            layoutLoadingRoot.setVisibility(View.VISIBLE);
        }
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.fragment_message);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 1.0F;
        this.mDimAmount = 0.6F;
        this.h = 528;
        this.gravity = Gravity.BOTTOM;
    }

    protected void initView() {
        if (Build.VERSION.SDK_INT >= 23) {
            init();
        }
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        dismissLoading();
        this.recyclerView.refreshComplete();
        this.recyclerView.loadMoreComplete();
        showFailView();
    }

    public void onLoadMore(int paramInt1, int paramInt2) {
        if (this.isHasData == 1) {
            this.mAdapter.showLoadMoreView();
            getData(this.page);
        }
    }

    public void onRefresh() {
        this.isRefresh = true;
        this.page = 0;
        this.recyclerView.enableLoadMore();
        this.recyclerView.loadMoreComplete();
        getData(this.page);
    }

    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(getContext(), 528));
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        dismissLoading();
        if (Urls.GET_CHAT_LIST.equals(url)) {
            if (obj != null && obj instanceof ChatListBeanInfo) {
                ChatListBeanInfo chatInfo = (ChatListBeanInfo) obj;
                if (chatInfo.getCode() == 200) {
                    setData(chatInfo);
                } else {
                    recyclerView.refreshComplete();
                    recyclerView.loadMoreComplete();
                }
            } else {
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        } else if (url.equals(Urls.GET_CLEARALLCOUNT_INFO)) {
            try {
                JSONObject object = new JSONObject(obj.toString());
                if (object.getInt("code") == 200) {
                    setIsAllRead(false);
                    ToastUtils.showShortToast("忽略未读消息成功");
                } else {
                    LogUtils.e("忽略未读消息成功=", object.getString("msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setData(ChatListBeanInfo info) {
        if (info == null || info.getData() == null || info.getData().getConversationList() == null || info.getData().getConversationList().size() == 0) {
            if (info.getData().getUserList() != null) {
                isHasData = info.getData().getUserList().size() > Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            if (isRefresh) {
                mAdapter.clear(true);
            }
            mAdapter.showNoMoreDataView();
            recyclerView.loadMoreComplete();
            recyclerView.refreshComplete();
            return;
        }
        isHasData = info.getData().getUserList().size() > Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        if (!tvLeftName.isClickable()) {
            for (int i = 0; i < info.getData().getUserList().size(); i++) {
                if (info.getData().getUserList().get(i).getCount() > 0) {
                    tvLeftName.setTextColor(mContext.getResources().getColor(R.color.color_333));
                    tvLeftName.setClickable(true);
                    break;
                }
            }
        }
        //当触发下拉刷新时
        if (isRefresh) {
            isRefresh = false;
            recyclerView.refreshComplete();
            if (mAdapter != null) {
                mAdapter.clear(true);
                if (list.size() > 0) {
                    list.clear();
                    list = info.getData().getUserList();
                }
                mAdapter.addAll(info.getData().getUserList(), true);
            }

            recyclerView.getRecyclerView().scrollToPosition(0);

            if (info.getData().getUserList().size() > 0 && isHasData == 1) {
                page++;
            }
        } else {
            // 第一次加载数据
            if (mAdapter != null && mAdapter.getBasicItemCount() == 0) {
                mAdapter.addAll(info.getData().getUserList(), true);
                if (info.getData().getUserList().size() > 0 && isHasData == 1) {
                    page++;
                }
            } else {
                //加载更多时
                int size = info.getData().getUserList().size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        Message dataBean = info.getData().getUserList().get(i);
                        boolean addResult = true;
                        for (int j = 0; j < mAdapter.getList().size(); j++) {
                            Message dataBean2 = mAdapter.getList().get(j);
                            if (dataBean.getMsgId() == dataBean2.getMsgId()) {
                                addResult = false;
                                break;
                            }
                        }

                        if (addResult) {
                            mAdapter.add(dataBean);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }
                if (info.getData().getUserList().size() > 0 && isHasData == 1) {
                    recyclerView.loadMoreComplete();
                    mAdapter.showNoMoreDataView();
                    page++;
                } else {
                    mAdapter.showNoMoreDataView();
                    recyclerView.loadMoreComplete();
                }
            }
        }
    }

    @OnClick({R.id.rl_1, R.id.rl_2, R.id.btn_network_no_reload, R.id.iv_right_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right_title:
                setRead();
//                if (listener != null) {
//                    listener.onClick(4, null);
//                }
                break;
            case R.id.rl_1:
                if (listener != null) {
                    SPUtils.setKefuIsRead(false);
                    ivIcon1.setVisibility(View.GONE);
                    listener.onClick(2, null);
                    setIsAllRead(false);
                    dismiss();
                }
                break;
            case R.id.rl_2:
                if (listener != null) {
                    SPUtils.setSystemIsRead(false);
                    ivIcon2.setVisibility(View.GONE);
                    listener.onClick(1, null);
                    setIsAllRead(false);
                    dismiss();
                }
                break;
            case R.id.btn_network_no_reload:
                dismissFailView();
                showLoading();
                page = 0;
                getData(page);
                break;
        }
    }

    public void setDialogClickListener(DialogReturnListener listener) {
        this.listener = listener;
    }

    public static interface DialogReturnListener {
        void onClick(int type, Message bean);
    }

    //未读
    private void setIsRead(boolean isRead) {
        if (!tvRightTitle.isClickable()) {
            tvRightTitle.setTextColor(mContext.getResources().getColor(R.color.color_333));
            tvRightTitle.setClickable(true);
        }
    }

    //已读
    private void setIsAllRead(boolean isRead) {
        if (SPUtils.getSystemIsRead() || SPUtils.getKefuIsRead()) {
            return;
        }
        if (mAdapter != null && mAdapter.getList() != null) {
            for (int i = 0; i < mAdapter.getList().size(); i++) {
                if (mAdapter.getList().get(i).getCount() > 0) {
                    return;
                }
            }
        }
        if (tvRightTitle.isClickable()) {
            tvRightTitle.setTextColor(mContext.getResources().getColor(R.color.color_999));
            tvRightTitle.setClickable(false);
        }
    }

    //设置全部已读
    private void setRead() {
        String read = "";
        if (SPUtils.getSystemIsRead()) {
            ivIcon2.setVisibility(View.GONE);
            SPUtils.setSystemIsRead(false);
        }
        if (SPUtils.getKefuIsRead()) {
            ivIcon1.setVisibility(View.GONE);
            SPUtils.setKefuIsRead(false);
        }
        if (mAdapter != null && mAdapter.getList() != null) {
            for (int i = 0; i < mAdapter.getList().size(); i++) {
                if (mAdapter.getList().get(i).getCount() > 0) {
                    mAdapter.getList().get(i).setCount(0);
                    read = read + "," + mAdapter.getList().get(i).getTargetId();
                }
            }
            mAdapter.notifyDataSetChanged();
        }
        if (read.isEmpty()) {
            return;
        }
        AppApis.getGetClearAllCountInfo(read.substring(1), this);
    }
}
