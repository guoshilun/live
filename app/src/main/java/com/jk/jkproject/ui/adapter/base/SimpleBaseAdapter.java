package com.jk.jkproject.ui.adapter.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
  protected int ITEM_VIEW_TYPE_ZERO;
  
  protected int VIEW_TYPE_COUNT;
  
  protected Context mContext;
  
  protected List<T> mData;
  
  public SimpleBaseAdapter(Context paramContext, List<T> paramList) {
    ArrayList<T> arrayList;
    this.VIEW_TYPE_COUNT = 1;
    this.ITEM_VIEW_TYPE_ZERO = 0;
    this.mContext = paramContext;
    if (paramList == null) {
      arrayList = new ArrayList();
    } else {
      arrayList = new ArrayList<T>(paramList);
    } 
    this.mData = arrayList;
  }
  
  public void add(int paramInt, T paramT) {
    this.mData.add(paramInt, paramT);
    notifyDataSetChanged();
  }
  
  public boolean add(T paramT) {
    boolean bool = this.mData.add(paramT);
    notifyDataSetChanged();
    return bool;
  }
  
  public boolean addAll(int paramInt, Collection<? extends T> paramCollection) {
    boolean bool = this.mData.addAll(paramInt, paramCollection);
    notifyDataSetChanged();
    return bool;
  }
  
  public boolean addAll(Collection<? extends T> paramCollection) {
    boolean bool = this.mData.addAll(paramCollection);
    notifyDataSetChanged();
    return bool;
  }
  
  public void clearData() {
    List<T> list = this.mData;
    if (list != null) {
      list.clear();
      notifyDataSetChanged();
    } 
  }
  
  public int getCount() {
    int i;
    List<T> list = this.mData;
    if (list == null) {
      i = 0;
    } else {
      i = list.size();
    } 
    return i;
  }
  
  public List<T> getDataList() {
    return this.mData;
  }
  
  public T getItem(int paramInt) {
    if (paramInt >= 0) {
      List<T> list = this.mData;
      if (list != null && paramInt < list.size())
        return this.mData.get(paramInt); 
    } 
    return null;
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public abstract int getItemResId(int paramInt);
  
  public int getItemViewType(int paramInt) {
    return this.ITEM_VIEW_TYPE_ZERO;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    ViewHolder viewHolder;
    if (paramView == null) {
      paramView = View.inflate(this.mContext, getItemResId(paramInt), null);
      viewHolder = new ViewHolder(paramView);
      paramView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder)paramView.getTag();
    } 
    onBindViewHolder(paramInt, paramView, viewHolder, getItem(paramInt));
    return paramView;
  }
  
  public int getViewTypeCount() {
    return this.VIEW_TYPE_COUNT;
  }
  
  public abstract void onBindViewHolder(int paramInt, View paramView, ViewHolder paramViewHolder, T paramT);
  
  public T remove(int paramInt) {
    T t = this.mData.remove(paramInt);
    notifyDataSetChanged();
    return t;
  }
  
  public boolean remove(T paramT) {
    boolean bool = this.mData.remove(paramT);
    notifyDataSetChanged();
    return bool;
  }
  
  public boolean removeAll(Collection<? extends T> paramCollection) {
    boolean bool = this.mData.removeAll(paramCollection);
    notifyDataSetChanged();
    return bool;
  }
  
  public void replaceAll(List<T> paramList) {
    if (paramList != null) {
      this.mData.clear();
      this.mData.addAll(paramList);
    } else {
      this.mData = paramList;
    } 
    notifyDataSetChanged();
  }
  
  public static class ViewHolder {
    private View convertView;
    
    private SparseArray<View> views = new SparseArray();
    
    public ViewHolder(View param1View) {
      this.convertView = param1View;
    }
    
    public <E extends View> E getView(int param1Int) {
      View view1 = (View)this.views.get(param1Int);
      View view2 = view1;
      if (view1 == null) {
        view2 = this.convertView.findViewById(param1Int);
        this.views.put(param1Int, view2);
      } 
      return (E)view2;
    }
  }
}
