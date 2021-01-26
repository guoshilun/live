package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.ui.entity.LiveRoomContributionInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;
import java.util.ArrayList;
import java.util.List;

public class LiveRoomVIPListAdapter extends BaseWrapperRecyclerAdapter<LiveRoomContributionInfo.DataBean.ListBean, LiveRoomVIPListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {
  private Context context;
  
  private List<LiveRoomContributionInfo.DataBean.ListBean> list = new ArrayList<LiveRoomContributionInfo.DataBean.ListBean>();
  
  private LayoutInflater mLayoutInflater;
  
  private OnItemClickListener onItemClickListener;
  
  public LiveRoomVIPListAdapter(Context paramContext, List<LiveRoomContributionInfo.DataBean.ListBean> paramList) {
    this.context = paramContext;
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    appendToList(paramList);
  }
  
  public OnItemClickListener getOnItemClickListener() {
    return this.onItemClickListener;
  }
  
  public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, int paramInt) {
    this.list = getList();
    if (this.list.size() > 0) {
      LiveRoomContributionInfo.DataBean.ListBean listBean = this.list.get(paramInt);
      paramItemViewHolder.tvUserName.setText(listBean.getNickName());
      paramItemViewHolder.sdvHeader.setImageURI(listBean.getPicture());
    } 
  }
  
  public void onClick(View paramView, int paramInt) {
    if (FuncUtils.isFastDoubleClick())
      return; 
  }
  
  public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
    return new ItemViewHolder(this.mLayoutInflater.inflate(2131427484, paramViewGroup, false));
  }
  
  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
    this.onItemClickListener = paramOnItemClickListener;
  }
  
  public class ItemViewHolder extends ClickableViewHolder {
    @BindView(2131296748)
    SimpleDraweeView sdvHeader;
    
    @BindView(2131297051)
    TextView tvUserName;
    
    public ItemViewHolder(View param1View) {
      super(param1View);
      this.sdvHeader = (SimpleDraweeView)param1View.findViewById(2131296748);
      this.tvUserName = (TextView)param1View.findViewById(2131297051);
      addOnItemViewClickListener();
      addOnViewClickListener(param1View);
    }
  }
  
  public static interface OnItemClickListener {
    void terminationClick(View param1View, String param1String);
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\LiveRoomVIPListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */