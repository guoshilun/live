package com.jk.jkproject.ui.widget.danmaku;

import android.os.Parcel;
import android.os.Parcelable;

public class RichMessage implements Parcelable {
  public static final Parcelable.Creator<RichMessage> CREATOR = new Parcelable.Creator<RichMessage>() {
      public RichMessage createFromParcel(Parcel param1Parcel) {
        return new RichMessage(param1Parcel);
      }
      
      public RichMessage[] newArray(int param1Int) {
        return new RichMessage[param1Int];
      }
    };
  
  private String color;
  
  private String content;
  
  private String extend;
  
  private String gift_id;
  
  private String type;
  
  public RichMessage() {}
  
  protected RichMessage(Parcel paramParcel) {
    this.type = paramParcel.readString();
    this.content = paramParcel.readString();
    this.color = paramParcel.readString();
    this.extend = paramParcel.readString();
    this.gift_id = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getColor() {
    return this.color;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public String getExtend() {
    return this.extend;
  }
  
  public String getGift_id() {
    return this.gift_id;
  }
  
  public String getType() {
    return this.type;
  }
  
  public void setColor(String paramString) {
    this.color = paramString;
  }
  
  public void setContent(String paramString) {
    this.content = paramString;
  }
  
  public void setExtend(String paramString) {
    this.extend = paramString;
  }
  
  public void setGift_id(String paramInt) {
    this.gift_id = paramInt;
  }
  
  public void setType(String paramString) {
    this.type = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.type);
    paramParcel.writeString(this.content);
    paramParcel.writeString(this.color);
    paramParcel.writeString(this.extend);
    paramParcel.writeString(this.gift_id);
  }
}
