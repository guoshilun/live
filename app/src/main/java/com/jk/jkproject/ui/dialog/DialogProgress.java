package com.jk.jkproject.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.jk.jkproject.R;

public class DialogProgress extends Dialog {
  private TextView mTxtTitle;
  
  public DialogProgress(Context paramContext) {
    super(paramContext, R.style.customDialog);
    setContentView(R.layout.dialog_progress);
    this.mTxtTitle = (TextView)findViewById(R.id.progress_message);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setCanceledOnTouchOutside(false);
  }
  
  public void setMsg(String paramString) {
    this.mTxtTitle.setText(paramString);
  }
}