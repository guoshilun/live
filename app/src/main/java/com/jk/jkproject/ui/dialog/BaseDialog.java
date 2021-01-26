package com.jk.jkproject.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.net.okhttp.ResponseListener;

public abstract class BaseDialog extends Dialog implements View.OnClickListener, ResponseListener {

  protected Context context;

  protected float mDimAmount = 0.6f;

  protected float mWidthScale = 0.9f;

  protected int gravity = Gravity.CENTER_VERTICAL;

  protected int animId = -1;

  protected int x = 0;

  protected int y = 0;

  protected int h = WindowManager.LayoutParams.WRAP_CONTENT;

  protected OnDialogClickListener mOnDialogClickListener;

  /**
   * Create a Dialog window that uses a custom dialog style.
   *
   * @param context The Context the Dialog is to run it.  In particular, it
   *                uses the window manager and theme in this context to
   *                present its UI.
   */
  public BaseDialog(Context context) {
    super(context, R.style.customDialog_roll_up_down);
    this.context = context;
  }

  public BaseDialog(Context context, int theme) {
    super(context, theme);
    this.context = context;
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    create(savedInstanceState);
    Window window = getWindow();
    WindowManager.LayoutParams params = window.getAttributes();
    params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
    window.setAttributes(params);
    initView();
    setWindow(x, y);
  }

  protected void setWindow(int x, int y) {
    Window dialogWindow = getWindow();
    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
    DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
    if (d.widthPixels > d.heightPixels) {
      lp.width = (int) (d.heightPixels * mWidthScale);
    } else {
      lp.width = (int) (d.widthPixels * mWidthScale);
    }
    lp.x = x;
    lp.y = y;
    lp.height = h;
    lp.gravity = gravity;
    lp.dimAmount = mDimAmount;
    lp.alpha = 1.0f;
    dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    if (animId != -1) {
      dialogWindow.setWindowAnimations(animId);
    }
  }

  protected void setAlpha(float alpha) {
    Window dialogWindow = getWindow();
    WindowManager.LayoutParams lp = dialogWindow.getAttributes();

    lp.dimAmount = mDimAmount;
    lp.alpha = alpha;
    dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    if (animId != -1) {
      dialogWindow.setWindowAnimations(animId);
    }
  }

  @Override
  public void onClick(View v) {

  }


  protected <E extends View> E getView(View v, int id) {
    try {
      if (v != null) {
        return (E) v.findViewById(id);
      } else {
        throw new NullPointerException("root view is null, can not find view");
      }
    } catch (Throwable e) {
      LogUtils.e("Could not cast View to concrete class");
      return null;
    }
  }

  protected <E extends View> E getView(int id) {
    return getView(getWindow().getDecorView(), id);
  }


  public void setOnDialogClickListener(OnDialogClickListener listener) {
    this.mOnDialogClickListener = listener;
  }

  protected abstract void create(Bundle b);

  protected abstract void initView();

  public interface OnDialogClickListener {

    void onClick(DialogInterface dialog, int i);
  }

  @Override
  public void onSuccess(String url, Object obj) {
    if (this == null || obj == null) {
      return;
    }
  }

  @Override
  public void onFailure(int code, String url, String error) {
    if (this == null) {
      return;
    }
  }

  @Override
  public void onStartRequest() {

  }

}
