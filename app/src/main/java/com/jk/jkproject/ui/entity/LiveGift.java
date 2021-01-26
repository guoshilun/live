package com.jk.jkproject.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class LiveGift implements Parcelable {
  public static final Parcelable.Creator<LiveGift> CREATOR = new Parcelable.Creator<LiveGift>() {
      public LiveGift createFromParcel(Parcel param1Parcel) {
        return new LiveGift(param1Parcel);
      }
      
      public LiveGift[] newArray(int param1Int) {
        return new LiveGift[param1Int];
      }
    };
  
  public static final String GLOBAL_BROADCAST = "broadcast";
  
  public static final String LIVE_ACHIEVEMENT_ANIMATE_3Q4Q = "achieve_success";
  
  public static final String LIVE_ACHIEVEMENT_ANIMATE_BABY = "with_child";
  
  public static final String LIVE_ACHIEVEMENT_ANIMATE_GIRLFRIEND = "girl_friend";
  
  public static final String LIVE_ACHIEVEMENT_ANIMATE_MENWIFT = "marriage";
  
  public static final String LIVE_EFFECT_ANIMATE_BARRAGE = "effect_barrage";
  
  public static final String LIVE_EFFECT_ANIMATE_CAR = "effect_roadster";
  
  public static final String LIVE_EFFECT_ANIMATE_CAR2 = "effect_batmobile";
  
  public static final String LIVE_EFFECT_ANIMATE_DBATMOBILE = "effect_batmobile";
  
  public static final String LIVE_EFFECT_ANIMATE_DRAGON = "effect_dragon";
  
  public static final String LIVE_EFFECT_ANIMATE_PLANE = "effect_airplane";
  
  public static final String LIVE_EFFECT_ANIMATE_PLANE2 = "effect_dragon";
  
  public static final String LIVE_EFFECT_ANIMATE_SVGA = "svga";
  
  public static final String LIVE_GIFT_ANIMATE_1314 = "1314";
  
  public static final String LIVE_GIFT_ANIMATE_666 = "666";
  
  public static final String LIVE_GIFT_ANIMATE_BATTER = "batter";
  
  public static final String LIVE_GIFT_ANIMATE_CAR = "car";
  
  public static final String LIVE_GIFT_ANIMATE_CONCERT = "concert";
  
  public static final String LIVE_GIFT_ANIMATE_CROWN = "crown";
  
  public static final String LIVE_GIFT_ANIMATE_CRUISE = "cruise";
  
  public static final String LIVE_GIFT_ANIMATE_FEATHER300 = "feather300";
  
  public static final String LIVE_GIFT_ANIMATE_FIREWORKS = "fireworks";
  
  public static final String LIVE_GIFT_ANIMATE_GOLDMAN = "goldman";
  
  public static final String LIVE_GIFT_ANIMATE_LIKE = "like";
  
  public static final String LIVE_GIFT_ANIMATE_PLANE = "plane";
  
  public static final String LIVE_GIFT_ANIMATE_PUMPKIN = "pumpkin";
  
  public static final String LIVE_GIFT_ANIMATE_RING = "ring";
  
  public static final String LIVE_GIFT_ANIMATE_ROSE520 = "rose520";
  
  public static final String LIVE_GIFT_ANIMATE_SMOKE = "smoke";
  
  public static final int LIVE_GIFT_ID_BROAD_CAST = 1100;
  
  public static final int LIVE_GIFT_ID_FEATHER500 = 3814;
  
  public static final int LIVE_GIFT_ID_HOT_TICKET = 3800;
  
  public static final int LIVE_GIFT_ID_ROSE = 3001;
  
  public static final int LIVE_GIFT_ID_ROSE520 = 3813;
  
  private String animate;
  
  private int goods_id;
  
  private String name;
  
  private int price;
  
  private int res_id;
  
  public LiveGift() {}
  
  public LiveGift(int paramInt1, int paramInt2, String paramString) {
    this.goods_id = paramInt1;
    this.res_id = paramInt2;
    this.animate = paramString;
  }
  
  protected LiveGift(Parcel paramParcel) {
    this.goods_id = paramParcel.readInt();
    this.res_id = paramParcel.readInt();
    this.animate = paramParcel.readString();
    this.name = paramParcel.readString();
    this.price = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getAnimate() {
    return this.animate;
  }
  
  public int getGoods_id() {
    return this.goods_id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getPrice() {
    return this.price;
  }
  
  public int getRes_id() {
    return this.res_id;
  }
  
  public void setAnimate(String paramString) {
    this.animate = paramString;
  }
  
  public void setGoods_id(int paramInt) {
    this.goods_id = paramInt;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setPrice(int paramInt) {
    this.price = paramInt;
  }
  
  public void setRes_id(int paramInt) {
    this.res_id = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LiveGift{goods_id=");
    stringBuilder.append(this.goods_id);
    stringBuilder.append(", res_id=");
    stringBuilder.append(this.res_id);
    stringBuilder.append(", name='");
    stringBuilder.append(this.name);
    stringBuilder.append('\'');
    stringBuilder.append(", animate='");
    stringBuilder.append(this.animate);
    stringBuilder.append('\'');
    stringBuilder.append(", price=");
    stringBuilder.append(this.price);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.goods_id);
    paramParcel.writeInt(this.res_id);
    paramParcel.writeString(this.animate);
    paramParcel.writeString(this.name);
    paramParcel.writeInt(this.price);
  }
}
