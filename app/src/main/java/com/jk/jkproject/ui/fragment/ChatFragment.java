package com.jk.jkproject.ui.fragment;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.ui.widget.MsgListView.MsgListView;
import com.jk.jkproject.ui.widget.ResizeLayout;

import butterknife.BindView;
import butterknife.Unbinder;

public class ChatFragment extends BFragment {
    private static String tab_type = "tab_type";
    @BindView(R.id.message_list)
    MsgListView listView;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.et_msg)
    EditText etMsg;
    @BindView(R.id.ll_msg_send)
    LinearLayout llMsgSend;
    @BindView(R.id.iv_live_room_gift)
    ImageView ivLiveRoomGift;
    @BindView(R.id.tt_layout_bottom)
    LinearLayout ttLayoutBottom;
    @BindView(R.id.rzlay)
    ResizeLayout rzlay;
    Unbinder unbinder;


    private String tabName = "";


    public static ChatFragment newInstance(String paramString) {
        ChatFragment chatFragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString(tab_type, paramString);
        chatFragment.setArguments(bundle);
        return chatFragment;
    }

    protected void initData() {
        super.initData();
        if (getArguments() != null)
            this.tabName = getArguments().getString(tab_type);
    }

    protected void initView(View paramView) {
        super.initView(paramView);
        this.listView.setOverScrollMode(2);
        this.listView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
                param1MotionEvent.getAction();
                return false;
            }
        });
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    protected int setLayoutId() {
        return R.layout.fragment_chat;
    }


}