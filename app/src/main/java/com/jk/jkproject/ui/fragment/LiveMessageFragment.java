package com.jk.jkproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.ui.activity.SystemMessageActivity;
import com.jk.jkproject.ui.chat.ChatListBeanInfo;
import com.jk.jkproject.ui.chat.ChatMessageListAdapter;
import com.jk.jkproject.ui.chat.LiveMessageActivity;
import com.jk.jkproject.ui.chat.Message;
import com.jk.jkproject.ui.dialog.DialogConfirm;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;
import com.jk.jkproject.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.OnClick;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * @author Zick
 * @params
 * @date 2020/7/22 2:04 PM
 * @desc 消息
 */
public class LiveMessageFragment extends BFragment implements RefreshRecyclerViewListener, Observer {
    private static String tab_type = "tab_type";
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
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.img_share1)
    ImageView imgShare1;
    @BindView(R.id.iv_icon_1)
    ImageView ivIcon1;
    @BindView(R.id.iv_icon_2)
    ImageView ivIcon2;
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
    @BindView(R.id.iv_layout_network)
    ImageView ivLayoutNetwork;
    private int isHasData;

    private boolean isRefresh = false;
    private List<Message> list;

    private ChatMessageListAdapter mAdapter;

    private int page = 0;


    private String tabName = "";
    private Message bean;
    private DialogConfirm mConfirmDialog;


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

    public static LiveMessageFragment newInstance(String paramString) {
        LiveMessageFragment liveMessageFragment = new LiveMessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(tab_type, paramString);
        liveMessageFragment.setArguments(bundle);
        return liveMessageFragment;
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

    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            tabName = getArguments().getString(tab_type);
        }
    }

    protected void initView(View paramView) {
        super.initView(paramView);
        MessageNotifyCenter.getInstance().register(this);
        ivTitle.setVisibility(View.VISIBLE);
        tvRightTitle.setText("忽略未读");
        tvRightTitle.setClickable(false);
        tvRightTitle.setTextColor(getResources().getColor(R.color.color_999));
        tvRightTitle.setVisibility(View.VISIBLE);
        ivRightTitle.setVisibility(View.GONE);
        ivRightTitle.setImageResource(R.mipmap.icon_live_message);
        sdvIcon1.setImageResource(R.mipmap.live_icon_message_kefu);
        sdvIcon2.setImageResource(R.mipmap.live_icon_message_noti);
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
        tvTitle.setText("消息");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        list = new ArrayList<>();
        mAdapter = new ChatMessageListAdapter((Context) getActivity(), list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreFooterView(new DefaultLoadMoreFooterView(getContext()));
        mAdapter.setOnItemClickListener(bean -> {
            if (bean.getCount() > 0) {
                bean.setCount(0);
                mAdapter.notifyDataSetChanged();
                setIsAllRead(false);
            }
            Intent intent = new Intent(getContext(), LiveMessageActivity.class);
            intent.putExtra("type", 3);
            intent.putExtra("message", bean);
            startActivity(intent);
        });
        mAdapter.setOnLongItemClickListener(bean ->
                setDialogTips(1, bean)
        );
        recyclerView.setRecyclerViewListener(this);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.color_F1F5F8));
        mFlagShowNoNetworkToast = false;
        mFlagShowDefaultFailToast = false;
        getData(page);
    }

    //删除提示框
    private void setDialogTips(int mType, Message bean) {
        mConfirmDialog = new DialogConfirm(getContext(), mType);
        mConfirmDialog.show();
        mConfirmDialog.setDialogClickListener(type -> {
            switch (type) {
                case 1:
                    getRemoveConversation(bean);
                    break;
            }
        });
        mConfirmDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void getRemoveConversation(Message bean) {
        this.bean = bean;
        AppApis.getGetRemoveConversationInfo(bean.getTargetId(), this);
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        dismissLoading();
        if (recyclerView != null) {
            this.recyclerView.refreshComplete();
            this.recyclerView.loadMoreComplete();
        }
        if (Urls.GET_CHAT_LIST.equals(paramString1) && this.page == 1) {
            showFailView();
        }
    }

    public void onLoadMore(int paramInt1, int paramInt2) {
        if (isHasData == 1) {
            mAdapter.showLoadMoreView();
            getData(page);
        }
    }

    public void onRefresh() {
        isRefresh = true;
        page = 0;
        recyclerView.enableLoadMore();
        recyclerView.loadMoreComplete();
        getData(page);
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
        } else if (url.equals(Urls.GET_KEFU_INFO)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    JSONObject jSONObject1 = new JSONObject(jSONObject.getString("data"));
                    int state = jSONObject1.getInt("state");
                    Intent intent1 = new Intent(getContext(), LiveMessageActivity.class);
                    intent1.putExtra("type", 2);
                    intent1.putExtra("state", state);
                    startActivity(intent1);
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException1) {
                jSONException1.printStackTrace();
            }
        } else if (url.equals(Urls.GET_REMOVECONVERSATION_INFO)) {
            try {
                JSONObject object = new JSONObject(obj.toString());
                if (object.getInt("code") == 200) {
                    if (mAdapter != null && bean != null) {
                        mAdapter.remove(bean, true);
                    }
                    ToastUtils.showShortToast("删除成功");
                } else {
                    ToastUtils.showShortToast(object.getString("msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
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
//            mAdapter.showNoMoreDataView();
            recyclerView.loadMoreComplete();
            recyclerView.refreshComplete();
            ivLayoutNetwork.setImageResource(R.mipmap.icon_emply_message);
            networkNoTitle.setText("还没有消息哦");
            networkNoDes.setVisibility(View.GONE);
            btnNetworkNoReload.setVisibility(View.GONE);
            showFailView();
            return;
        }
        isHasData = info.getData().getUserList().size() > Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        if (!tvRightTitle.isClickable()) {
            for (int i = 0; i < info.getData().getUserList().size(); i++) {
                if (info.getData().getUserList().get(i).getCount() > 0) {
                    tvRightTitle.setTextColor(getResources().getColor(R.color.color_333));
                    tvRightTitle.setClickable(true);
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
                            if (dataBean.getMsgId().equals(dataBean2.getMsgId())) {
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

    protected int setLayoutId() {
        return R.layout.fragment_message;
    }

    @OnClick({R.id.iv_title, R.id.rl_1, R.id.rl_2, R.id.btn_network_no_reload, R.id.tv_right_title,R.id.layout_network_no_root})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                if (getActivity() != null) {
                    getActivity().finish();
                }
                break;
            case R.id.rl_1:
                AppApis.getKefuInfo(this);
                SPUtils.setKefuIsRead(false);
                ivIcon1.setVisibility(View.GONE);
                setIsAllRead(false);
                break;
            case R.id.rl_2:
                SPUtils.setSystemIsRead(false);
                ivIcon2.setVisibility(View.GONE);
                Intent intent = new Intent(getContext(), SystemMessageActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                setIsAllRead(false);
                break;
            case R.id.btn_network_no_reload:
            case R.id.layout_network_no_root:
                dismissFailView();
                showLoading();
                page = 0;
                getData(page);
                break;
            case R.id.tv_right_title:
                setRead();
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

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message param1Message) {
            Bundle bundle = (Bundle) param1Message.obj;
            switch (bundle.getInt("method")) {
                case IMEventType.ACTION_CHAT_MSG_PUSH:
                    Message message = (Message) bundle.getSerializable("mMessage");
                    if (message.getTargetId().equals("1") || message.getUserId().equals("1")) { //在线客服
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
                    break;
                case IMEventType.ACTION_SYSTEM_NOTI_MSG_PUSH:
                    SystemNotiBeanInfo.DataBean message1 = (SystemNotiBeanInfo.DataBean) bundle.getSerializable("bean");
                    if (message1 != null) {
                        SPUtils.setSystemIsRead(true);
                        ivIcon2.setVisibility(View.VISIBLE);
                        setIsRead(true);
                    }
                    break;
                default:
                    break;
            }
        }
    };


    //未读
    private void setIsRead(boolean isRead) {
        if (!tvRightTitle.isClickable()) {
            tvRightTitle.setTextColor(getResources().getColor(R.color.color_333));
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
            tvRightTitle.setTextColor(getResources().getColor(R.color.color_999));
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        MessageNotifyCenter.getInstance().unregister(this);
    }
}