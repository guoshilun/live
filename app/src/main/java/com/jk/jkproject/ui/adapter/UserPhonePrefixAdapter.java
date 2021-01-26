package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.LoginGetPhonePrefixActivity;
import com.jk.jkproject.ui.entity.PhonePrefixInfo;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.ViewHolder;

import java.util.List;

public class UserPhonePrefixAdapter extends CommonAdapter<PhonePrefixInfo.DataBean> {
  private final LoginGetPhonePrefixActivity cla;
  
  Context context;
  
  private List<PhonePrefixInfo.DataBean> datas;
  
  public UserPhonePrefixAdapter(Context paramContext, List<PhonePrefixInfo.DataBean> paramList, int paramInt, LoginGetPhonePrefixActivity paramLoginGetPhonePrefixActivity) {
    super(paramContext, paramList, paramInt);
    this.context = paramContext;
    this.datas = paramList;
    this.cla = paramLoginGetPhonePrefixActivity;
  }
  
  public void convert(ViewHolder paramViewHolder, final PhonePrefixInfo.DataBean testBean) {
    if (testBean != null) {
      TextView textView = (TextView)paramViewHolder.itemView.findViewById(R.id.tv_name);
      textView.setText(testBean.getName());
      textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              Intent intent = new Intent();
              intent.putExtra("phone_prefix", testBean.getPrefix());
              UserPhonePrefixAdapter.this.cla.setResult(-1, intent);
              UserPhonePrefixAdapter.this.cla.finish();
            }
          });
    } 
  }
}