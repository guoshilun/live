package com.jk.jkproject.ui.adapter;

import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.DanmuBean;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;

import java.util.List;

public class LiveRoomChatAdapter extends BaseQuickAdapter<DanmuBean, BaseViewHolder> {
  private OnItemclickListener onItemClickListener;
  
  public LiveRoomChatAdapter(List<DanmuBean> paramList) {
    super(R.layout.item_live_room_chat_list_layout, paramList);
  }
  
  protected void convert(BaseViewHolder paramBaseViewHolder, final DanmuBean bean) {
    String str1;
    if (TextUtils.isEmpty(bean.getData().getFrom().getNickName())) {
      str1 = bean.getData().getFrom().getUserName();
    } else {
      str1 = bean.getData().getFrom().getNickName();
    } 
    String str2 = str1;
    if (TextUtils.isEmpty(str1))
      str2 = "游客"; 
    String str3 = bean.getData().getContent();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str2);
    stringBuilder.append(":");
    paramBaseViewHolder.setText(R.id.tv_nickname, stringBuilder.toString()).setTextColor(R.id.tv_nickname, ContextCompat.getColor(this.mContext, R.color.color_999)).setText(R.id.tv_content, str3);
    if (!TextUtils.isEmpty(str2) && this.mContext != null && SPUtils.getUserId().equals(bean.getData().getFrom().getRid()))
      paramBaseViewHolder.setTextColor(R.id.tv_nickname, ContextCompat.getColor(this.mContext, R.color.tabSelectTextColor));
    paramBaseViewHolder.setOnClickListener(R.id.ll_chat_item, new View.OnClickListener() {
          public void onClick(View param1View) {
            if (OnClickUtils.isFastClick() && LiveRoomChatAdapter.this.onItemClickListener != null)
              LiveRoomChatAdapter.this.onItemClickListener.onClick(bean); 
          }
        });
  }
  
  public OnItemclickListener getOnItemclickListener() {
    return this.onItemClickListener;
  }
  
  public void setOnItemClickListener(OnItemclickListener paramOnItemClickListener) {
    this.onItemClickListener = paramOnItemClickListener;
  }
  
  public static interface OnItemclickListener {
    void onClick(DanmuBean param1DanmuBean);
  }
}