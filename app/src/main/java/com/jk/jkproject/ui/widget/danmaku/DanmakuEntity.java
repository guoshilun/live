package com.jk.jkproject.ui.widget.danmaku;

import com.jk.jkproject.ui.entity.BubbleBean;
import com.jk.jkproject.ui.entity.IntentLive;
import com.jk.jkproject.ui.entity.LiveFireworksInfo;
import java.io.Serializable;
import java.util.ArrayList;

public class DanmakuEntity implements Serializable {
  public static final int DANMAKU_TYPE_EFFECT_BARRAGE = 4;
  
  public static final int DANMAKU_TYPE_FIREWORKS = 2;
  
  public static final int DANMAKU_TYPE_INTENT_LIVE = 3;
  
  public static final int DANMAKU_TYPE_SYSTEM = 0;
  
  public static final int DANMAKU_TYPE_USERCHAT = 1;
  
  private String achieve_icon;
  
  private String avatar;
  
  private String bg_barrage;
  
  private String bg_head;
  
  private BubbleBean effect;
  
  private IntentLive intentLive;
  
  private boolean isChat = true;
  
  private int level;
  
  private LiveFireworksInfo liveFireworksInfo;
  
  private String name;
  
  private ArrayList<RichMessage> richText;
  
  private int role;
  
  private String text;
  
  private int type;
  
  private String userId;
  
  public String getAchieve_icon() {
    return this.achieve_icon;
  }
  
  public String getAvatar() {
    return this.avatar;
  }
  
  public String getBg_barrage() {
    return this.bg_barrage;
  }
  
  public String getBg_head() {
    return this.bg_head;
  }
  
  public BubbleBean getEffect() {
    return this.effect;
  }
  
  public IntentLive getIntentLive() {
    return this.intentLive;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public LiveFireworksInfo getLiveFireworksInfo() {
    return this.liveFireworksInfo;
  }
  
  public String getName() {
    return this.name;
  }
  
  public ArrayList<RichMessage> getRichText() {
    return this.richText;
  }
  
  public int getRole() {
    return this.role;
  }
  
  public String getText() {
    return this.text;
  }
  
  public int getType() {
    return this.type;
  }
  
  public String getUserId() {
    return this.userId;
  }
  
  public boolean isChat() {
    return this.isChat;
  }
  
  public void setAchieve_icon(String paramString) {
    this.achieve_icon = paramString;
  }
  
  public void setAvatar(String paramString) {
    this.avatar = paramString;
  }
  
  public void setBg_barrage(String paramString) {
    this.bg_barrage = paramString;
  }
  
  public void setBg_head(String paramString) {
    this.bg_head = paramString;
  }
  
  public void setChat(boolean paramBoolean) {
    this.isChat = paramBoolean;
  }
  
  public void setEffect(BubbleBean paramBubbleBean) {
    this.effect = paramBubbleBean;
  }
  
  public void setIntentLive(IntentLive paramIntentLive) {
    this.intentLive = paramIntentLive;
  }
  
  public void setLevel(int paramInt) {
    this.level = paramInt;
  }
  
  public void setLiveFireworksInfo(LiveFireworksInfo paramLiveFireworksInfo) {
    this.liveFireworksInfo = paramLiveFireworksInfo;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setRichText(ArrayList<RichMessage> paramArrayList) {
    this.richText = paramArrayList;
  }
  
  public void setRole(int paramInt) {
    this.role = paramInt;
  }
  
  public void setText(String paramString) {
    this.text = paramString;
  }
  
  public void setType(int paramInt) {
    this.type = paramInt;
  }
  
  public void setUserId(String paramString) {
    this.userId = paramString;
  }
}
