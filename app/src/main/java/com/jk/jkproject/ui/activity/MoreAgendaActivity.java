package com.jk.jkproject.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoreAgendaActivity extends BActivity {
  private Unbinder bind;
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_more_agenda);
    this.bind = ButterKnife.bind((Activity)this);
  }
  
  @Nullable
  protected void onDestroy() {
    super.onDestroy();
    this.bind.unbind();
  }
}
