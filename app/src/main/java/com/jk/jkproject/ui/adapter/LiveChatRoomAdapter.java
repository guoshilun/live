package com.jk.jkproject.ui.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.DanmuBean;
import com.jk.jkproject.ui.holder.ChatViewHolder;
import com.jk.jkproject.ui.inter.OnItemClickLitener;

import java.util.ArrayList;

public class LiveChatRoomAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private ChatViewHolder chatViewHolder;

    private OnItemClickLitener listener;

    private LayoutInflater mInflater;

    private ArrayList<DanmuBean> msgList;

    public LiveChatRoomAdapter(Context paramContext, ArrayList<DanmuBean> paramArrayList, OnItemClickLitener paramOnItemClickLitener) {
        this.mInflater = LayoutInflater.from(paramContext);
        this.msgList = paramArrayList;
        this.listener = paramOnItemClickLitener;
    }

    @Override
    public int getItemCount() {
        return msgList.size() > 0 ? msgList.size() : 0;
    }

    public void onBindViewHolder(ChatViewHolder paramChatViewHolder, int paramInt) {
        paramChatViewHolder.bind(msgList.get(paramInt));
    }

    public ChatViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        this.chatViewHolder = new ChatViewHolder(this.mInflater.inflate(R.layout.livechat_item_layout, paramViewGroup, false), this.listener);
        return this.chatViewHolder;
    }

    public void release() {
        if (chatViewHolder != null) {
            chatViewHolder.release();
            chatViewHolder = null;
        }
        mInflater = null;
        if (msgList != null) {
            msgList.clear();
            msgList = null;
        }
        listener = null;
    }
}