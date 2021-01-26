package com.jk.jkproject.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jk.jkproject.utils.GsonUtils;
import java.util.ArrayList;

public class LiveGiftBlock extends BaseInfo implements Parcelable {
  public static final Parcelable.Creator<LiveGiftBlock> CREATOR = new Parcelable.Creator<LiveGiftBlock>() {
      public LiveGiftBlock createFromParcel(Parcel param1Parcel) {
        return new LiveGiftBlock(param1Parcel);
      }
      
      public LiveGiftBlock[] newArray(int param1Int) {
        return new LiveGiftBlock[param1Int];
      }
    };
  
  public ArrayList<LiveGiftInfo> data;
  
  public int[] list;
  
  public String ranking_url;
  
  public LiveGiftBlock() {}
  
  protected LiveGiftBlock(Parcel paramParcel) {
    this.list = paramParcel.createIntArray();
    this.data = paramParcel.createTypedArrayList(LiveGiftInfo.CREATOR);
    this.ranking_url = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    return GsonUtils.get().toJson(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeIntArray(this.list);
    paramParcel.writeTypedList(this.data);
    paramParcel.writeString(this.ranking_url);
  }
}
