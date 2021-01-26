package com.jk.jkproject.service;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.jk.jkproject.ui.widget.danmaku.DanmakuActionManager;
import com.jk.jkproject.ui.widget.danmaku.DanmakuChannel;
import com.jk.jkproject.ui.widget.danmaku.DanmakuChannelListener;
import com.jk.jkproject.ui.widget.danmaku.DanmakuEntity;
import com.jk.jkproject.utils.ScreenUtils;
import java.util.LinkedList;

public class FloatViewService extends Service {
  private static final String TAG = "FloatViewService";
  
  private DanmakuActionManager danmakuActionManager;
  
  private LinearLayout mFloatLayout;
  
  private DanmakuChannel mFloatView;
  
  private WindowManager mWindowManager;
  
  private WindowManager.LayoutParams wmParams;
  
  @SuppressLint({"InflateParams"})
  private void createFloatView() {
    this.wmParams = new WindowManager.LayoutParams();
    Application application = getApplication();
    getApplication();
    this.mWindowManager = (WindowManager)application.getSystemService("window");
    WindowManager.LayoutParams layoutParams = this.wmParams;
    layoutParams.type = 2002;
    layoutParams.format = 1;
    layoutParams.flags = 8;
    layoutParams.gravity = 51;
    layoutParams.x = 0;
    layoutParams.y = ScreenUtils.dp2px((Context)getApplication(), 75.0F);
    layoutParams = this.wmParams;
    layoutParams.width = -2;
    layoutParams.height = -2;
    this.mFloatLayout = (LinearLayout)LayoutInflater.from((Context)getApplication()).inflate(2131427419, null);
    this.mWindowManager.addView((View)this.mFloatLayout, (ViewGroup.LayoutParams)this.wmParams);
    this.mFloatView = (DanmakuChannel)this.mFloatLayout.findViewById(2131296336);
    this.mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    this.mFloatView.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            Toast.makeText((Context)FloatViewService.this, "一百块都不给我！", 0).show();
          }
        });
    LinkedList<DanmakuChannel> linkedList = new LinkedList();
    linkedList.add(this.mFloatView);
    this.danmakuActionManager = new DanmakuActionManager(linkedList);
    this.danmakuActionManager.setDanmakuEndInter(new DanmakuChannelListener() {
          public void endDanmu() {
            FloatViewService.this.stopSelf();
          }
        });
  }
  
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onCreate() {
    super.onCreate();
    createFloatView();
  }
  
  public void onDestroy() {
    super.onDestroy();
    LinearLayout linearLayout = this.mFloatLayout;
    if (linearLayout != null) {
      WindowManager windowManager = this.mWindowManager;
      if (windowManager != null)
        windowManager.removeView((View)linearLayout); 
    } 
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    DanmakuEntity danmakuEntity = (DanmakuEntity)paramIntent.getSerializableExtra("danmu");
    this.danmakuActionManager.addDanmu(danmakuEntity);
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkproject\service\FloatViewService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */