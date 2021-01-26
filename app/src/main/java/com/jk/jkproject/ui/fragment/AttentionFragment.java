package com.jk.jkproject.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.base.BFragment;

public class AttentionFragment extends BFragment {
  private static String tab_type = "tab_type";
  
  private String tabName = "";
  
  public static AttentionFragment newInstance(String paramString) {
    AttentionFragment attentionFragment = new AttentionFragment();
    Bundle bundle = new Bundle();
    bundle.putString(tab_type, paramString);
    attentionFragment.setArguments(bundle);
    return attentionFragment;
  }
  
  protected void initData() {
    super.initData();
    if (getArguments() != null)
      this.tabName = getArguments().getString(tab_type); 
    LogUtils.e(this.tabName);
  }
  
  protected void initView(View paramView) {
    super.initView(paramView);
  }
  
  protected boolean isBindEventBusHere() {
    return false;
  }
  
  protected int setLayoutId() {
    return 2131427423;
  }
}