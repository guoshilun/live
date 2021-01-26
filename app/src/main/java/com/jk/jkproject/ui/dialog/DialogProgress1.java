package com.jk.jkproject.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.jk.jkproject.R;

public class DialogProgress1 extends Dialog {
  private final ProgressBar progressBar;

  public DialogProgress1(Context paramContext) {
    super(paramContext, R.style.customDialog);
    setContentView(R.layout.dialog_progress_1);
    progressBar = (ProgressBar) findViewById(R.id.progress_bar);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setCanceledOnTouchOutside(false);
  }
  
  public void setMsg(String paramString) {
//    this.mTxtTitle.setText(paramString);
  }
}