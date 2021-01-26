package com.jk.jkproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jk.jkproject.base.AppApplication;

public class NetworkUtils {
  public static final int NETWORN_MOBILE = 2;
  
  public static final int NETWORN_NONE = 0;
  
  public static final int NETWORN_WIFI = 1;
  
  public static int getNetworkState(Context paramContext) {
    if (paramContext == null)
      return 0; 
    ConnectivityManager connectivityManager = (ConnectivityManager)AppApplication.getInstance().getSystemService("connectivity");
    NetworkInfo.State state = connectivityManager.getNetworkInfo(1).getState();
    if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)
      return 1; 
    state = connectivityManager.getNetworkInfo(0).getState();
    return (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) ? 2 : 0;
  }
  
  public static boolean hasNetwork(Context paramContext) {
    if (paramContext == null)
      return false; 
    ConnectivityManager connectivityManager = (ConnectivityManager)AppApplication.getInstance().getSystemService("connectivity");
    if (connectivityManager == null)
      return false; 
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    return !(networkInfo == null || !networkInfo.isAvailable());
  }
}
