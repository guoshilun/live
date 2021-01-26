package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.HomeTabInfo;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.ViewHolder;

import java.util.List;

public class TeamTabRVAdapter extends CommonAdapter<HomeTabInfo> {
  Context context;
  
  private List<HomeTabInfo> datas;
  
  private OnItemClickListener onItemClickListener;
  
  public TeamTabRVAdapter(Context paramContext, List<HomeTabInfo> paramList, int paramInt) {
    super(paramContext, paramList, paramInt);
    this.context = paramContext;
    this.datas = paramList;
  }
  
  public void convert(ViewHolder paramViewHolder, final HomeTabInfo testBean) {
    if (testBean != null) {
      TextView textView = (TextView)paramViewHolder.itemView.findViewById(R.id.tv_name);
      textView.setText(testBean.getTitleName());
      if (testBean.isSelected()) {
        textView.setBackgroundColor(this.context.getResources().getColor(R.color.tabSelectTextColor));
        textView.setTextColor(this.context.getResources().getColor(R.color.white));
      } else {
        textView.setBackgroundColor(this.context.getResources().getColor(R.color.white));
        textView.setTextColor(this.context.getResources().getColor(R.color.tabSelectTextColor));
      } 
      textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              if (TeamTabRVAdapter.this.onItemClickListener != null)
                TeamTabRVAdapter.this.onItemClickListener.click(testBean); 
              TeamTabRVAdapter.this.setSelect(testBean);
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
  
  public void setSelect(HomeTabInfo paramHomeTabInfo) {
    for (byte b = 0; b < this.datas.size(); b++) {
      HomeTabInfo homeTabInfo = this.datas.get(b);
      if (paramHomeTabInfo == this.datas.get(b)) {
        homeTabInfo.setSelected(true);
      } else {
        homeTabInfo.setSelected(false);
      } 
    } 
    notifyDataSetChanged();
  }
  
  public static interface OnItemClickListener {
    void click(HomeTabInfo param1HomeTabInfo);
  }
}
