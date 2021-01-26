package com.jk.jkproject.ui.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class LiveGiftPagerAdapter extends FragmentStatePagerAdapter {
  private FragmentManager fragmentManager;
  
  private Context mContext;
  
  private final List<String> mFragmentTitles = new ArrayList<String>();
  
  private final List<Fragment> mFragments = new ArrayList<Fragment>();
  
  public LiveGiftPagerAdapter(Context paramContext, FragmentManager paramFragmentManager) {
    super(paramFragmentManager);
    this.mContext = paramContext;
    this.fragmentManager = paramFragmentManager;
  }
  
  public void addFragment(Fragment paramFragment, String paramString) {
    this.mFragments.add(paramFragment);
    this.mFragmentTitles.add(paramString);
  }
  
  public int getCount() {
    return this.mFragments.size();
  }
  
  public Fragment getItem(int paramInt) {
    return this.mFragments.get(paramInt);
  }
  
  public CharSequence getPageTitle(int paramInt) {
    return this.mFragmentTitles.get(paramInt);
  }
  
  public void removeTab(int paramInt) {
    for (byte b = 0; b < this.mFragments.size(); b++) {
      this.mFragments.remove(paramInt);
      this.mFragmentTitles.remove(paramInt);
    } 
  }
}
