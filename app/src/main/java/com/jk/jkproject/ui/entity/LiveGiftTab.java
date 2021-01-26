package com.jk.jkproject.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class LiveGiftTab implements Parcelable {
  public static final Parcelable.Creator<LiveGiftTab> CREATOR = new Parcelable.Creator<LiveGiftTab>() {
      public LiveGiftTab createFromParcel(Parcel param1Parcel) {
        return new LiveGiftTab(param1Parcel);
      }
      
      public LiveGiftTab[] newArray(int param1Int) {
        return new LiveGiftTab[param1Int];
      }
    };
  
  public ArrayList<LiveGiftInfo> list;
  
  public String name = "";
  
  public String type = "";
  
  public LiveGiftTab() {}
  
  protected LiveGiftTab(Parcel paramParcel) {
    this.name = paramParcel.readString();
    this.type = paramParcel.readString();
    this.list = paramParcel.createTypedArrayList(LiveGiftInfo.CREATOR);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LiveGiftTab{name='");
    stringBuilder.append(this.name);
    stringBuilder.append('\'');
    stringBuilder.append(", list=");
    stringBuilder.append(this.list);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.name);
    paramParcel.writeString(this.type);
    paramParcel.writeTypedList(this.list);
  }
}
