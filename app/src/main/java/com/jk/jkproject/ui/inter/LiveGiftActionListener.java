package com.jk.jkproject.ui.inter;

import com.jk.jkproject.ui.entity.LiveGiftInfoBean;

public interface LiveGiftActionListener {
  void addGift(LiveGiftInfoBean.DataBean paramLiveGiftResponse);
  
  void addNativeGift(LiveGiftInfoBean.DataBean paramLiveGiftResponse);
  
  void addSpecialEffect(LiveGiftInfoBean.DataBean paramLiveRoomEffectInfo);
  
  void addSpecialSecretary(LiveGiftInfoBean.DataBean paramLiveRoomSecretaryInfo);
  
  void pollChestGift();
  
  void pollComboGift();
  
  void pollComboNativeGift();
  
  void pollOtherGift();
  
  void pollSpecialEffect();
  
  void pollSpecialSecretary();
}
