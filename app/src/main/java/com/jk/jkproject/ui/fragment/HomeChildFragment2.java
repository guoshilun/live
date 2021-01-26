package com.jk.jkproject.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.base.BFragment;

public class HomeChildFragment2 extends BFragment {
  private static String tab_type = "tab_type";
  
  private int tabName;
  
  public static HomeChildFragment2 newInstance(int paramInt) {
    HomeChildFragment2 homeChildFragment2 = new HomeChildFragment2();
    Bundle bundle = new Bundle();
    bundle.putInt(tab_type, paramInt);
    homeChildFragment2.setArguments(bundle);
    return homeChildFragment2;
  }
  
  protected void initData() {
    super.initData();
    if (getArguments() != null)
      this.tabName = getArguments().getInt(tab_type); 
    LogUtils.e(Integer.valueOf(this.tabName));
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


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\fragment\HomeChildFragment2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */