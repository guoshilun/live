package com.jk.jkproject.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.base.BFragment;

/**
*@params
*@date 2020/7/9 10:09 AM
*@author Zick
*@desc 战队页面弃用
*/
public class AgendaFragment extends BFragment {
  private static String tab_type = "tab_type";
  
  private String tabName = "";
  
  public static AgendaFragment newInstance(String paramString) {
    AgendaFragment agendaFragment = new AgendaFragment();
    Bundle bundle = new Bundle();
    bundle.putString(tab_type, paramString);
    agendaFragment.setArguments(bundle);
    return agendaFragment;
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
