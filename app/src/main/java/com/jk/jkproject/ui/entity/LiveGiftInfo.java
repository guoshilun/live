package com.jk.jkproject.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.jk.jkproject.utils.GsonUtils;

public class LiveGiftInfo implements Parcelable, Comparable {
  public static final Creator<LiveGiftInfo> CREATOR = new Creator<LiveGiftInfo>() {
    @Override
    public LiveGiftInfo createFromParcel(Parcel in) {
      return new LiveGiftInfo(in);
    }

    @Override
    public LiveGiftInfo[] newArray(int size) {
      return new LiveGiftInfo[size];
    }
  };
  public int giftID;   // 礼物id
  public int price;      // 礼物价格
  public int type;      // 礼物类型
  public String icon;    // 礼物名称
  public String animateType; // 礼物动画
  public String image; // 礼物图片网络资源
  public String avatarPath = ""; // 本地图片地址

  public String name;  // 礼物名称
  public String desc;  // 礼物说明
  public String intro;  // 中奖倍数
  public int state;	 // 礼物状态 0:隐藏 1:正常 2:禁用
  public int remain_num; // 剩余免费次数
  public int remain_ttl; // 剩余倒计时 -1:不改变 0:不显示
  public int give_number=1; //赠送数量
  public boolean select_item = false; //选中item
  public int select_position; //选中礼物的位置
  public String select_tab = "shop";//选中的tab的位置
  public Long lastTime = 0L; //点击送礼最后的时间
  public LiveGiftInfo() {
  }
  protected LiveGiftInfo(Parcel in) {
    giftID = in.readInt();
    price = in.readInt();
    type = in.readInt();
    icon = in.readString();
    animateType = in.readString();
    image = in.readString();
    name = in.readString();
    desc = in.readString();
    state = in.readInt();
    remain_num = in.readInt();
    remain_ttl = in.readInt();
    give_number = in.readInt();
    select_item = in.readByte() != 0;
    select_position = in.readInt();
    lastTime = in.readLong();
    select_tab = in.readString();
    intro = in.readString();
    avatarPath = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(giftID);
    dest.writeInt(price);
    dest.writeInt(type);
    dest.writeString(icon);
    dest.writeString(animateType);
    dest.writeString(image);
    dest.writeString(name);
    dest.writeInt(state);
    dest.writeInt(remain_num);
    dest.writeInt(remain_ttl);
    dest.writeInt(give_number);
    dest.writeByte(select_item ? (byte) 1 : (byte) 0);
    dest.writeInt(select_position);
    dest.writeLong(lastTime);
    dest.writeString(select_tab);
    dest.writeString(intro);
    dest.writeString(avatarPath);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public String toString() {
    return GsonUtils.get().toJson(this);
  }

  @Override
  public int compareTo(@NonNull Object o) {
    if (this == o) {
      return 0;
    }
    if (o == null || getClass() != o.getClass()) {
      return 1;
    }
    LiveGiftInfo liveGiftInfo = (LiveGiftInfo) o;
    if (liveGiftInfo.giftID != this.giftID) {
      return 1;
    }
    if (liveGiftInfo.type != this.type) {
      return 1;
    }
    if (!liveGiftInfo.name.equals(this.name)) {
      return 1;
    }if (liveGiftInfo.remain_num != this.remain_num) {
      return 1;
    }if (liveGiftInfo.remain_ttl != this.remain_ttl) {
      return 1;
    }
    return 0;
  }

}
