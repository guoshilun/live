package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.jk.jkproject.ui.entity.LiveRoomBannedTime;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.ViewHolder;
import java.util.List;

public class LiveRoomBannedTimeRVAdapter extends CommonAdapter<LiveRoomBannedTime.DataBean> {
  Context context;
  
  private List<LiveRoomBannedTime.DataBean> datas;
  
  private OnItemClickListener onItemClickListener;
  
  public LiveRoomBannedTimeRVAdapter(Context paramContext, List<LiveRoomBannedTime.DataBean> paramList, int paramInt) {
    super(paramContext, paramList, paramInt);
    this.context = paramContext;
    this.datas = paramList;
  }
  
  public void convert(ViewHolder paramViewHolder, final LiveRoomBannedTime.DataBean testBean) {
    if (testBean != null) {
      TextView textView = (TextView)paramViewHolder.itemView.findViewById(2131296954);
      textView.setText(testBean.getName());
      if (testBean.isSelected()) {
        textView.setBackgroundColor(this.context.getResources().getColor(2131099710));
      } else {
        textView.setBackgroundColor(this.context.getResources().getColor(2131099712));
      } 
      textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              if (LiveRoomBannedTimeRVAdapter.this.onItemClickListener != null)
                LiveRoomBannedTimeRVAdapter.this.onItemClickListener.click(testBean); 
              LiveRoomBannedTimeRVAdapter.this.setSelect(testBean);
            }
          });
    } 
  }
  
  public OnItemClickListener getOnItemClickListener() {
    return this.onItemClickListener;
  }
  
  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
    this.onItemClickListener = paramOnItemClickListener;
  }
  
  public void setSelect(LiveRoomBannedTime.DataBean paramDataBean) {
    for (byte b = 0; b < this.datas.size(); b++) {
      LiveRoomBannedTime.DataBean dataBean = this.datas.get(b);
      if (paramDataBean == this.datas.get(b)) {
        dataBean.setSelected(true);
      } else {
        dataBean.setSelected(false);
      } 
    } 
    notifyDataSetChanged();
  }
  
  public static interface OnItemClickListener {
    void click(LiveRoomBannedTime.DataBean param1DataBean);
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\LiveRoomBannedTimeRVAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */