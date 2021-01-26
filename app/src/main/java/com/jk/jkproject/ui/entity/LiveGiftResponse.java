package com.jk.jkproject.ui.entity;

import com.jk.jkproject.ui.widget.danmaku.RichMessage;
import com.jk.jkproject.utils.GsonUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class LiveGiftResponse extends LiveChestInfo {
  private String achieve_icon;
  
  private String animate;
  
  private String animate_combo;
  
  private String bg_barrage;
  
  private String bg_head;
  
  private int combo;
  
  private int combo_force;
  
  private int combo_simulate;
  
  private ErrorContent content;
  
  private long create_time;
  
  private int crit_diamond;
  
  private int crit_times;
  
  private HashMap<String, CritTimes> crit_times_map;
  
  private String desc;
  
  private int diamond_remain;
  
  private String giftKey;
  
  public ArrayList<LiveGiftInfo> gift_list;
  
  public ArrayList<LiveGiftTab> gift_tabs;
  
  private int glamour_take;
  
  private int glory_level;
  
  private int goods_id;
  
  private String head;
  
  private String image;
  
  private int is_combo;
  
  private String live_id;
  
  private int multiple;
  
  private String nickname;
  
  private String oid;
  
  private int remain_num;
  
  private ArrayList<RichMessage> richMessages;
  
  private int role;
  
  private int sex;
  
  private String svgaAnimate;
  
  private String svgaUrl;
  
  private int total_glamour;
  
  private String uid;
  
  public String getAchieve_icon() {
    return this.achieve_icon;
  }
  
  public String getAnimate() {
    return this.animate;
  }
  
  public String getAnimate_combo() {
    return this.animate_combo;
  }
  
  public String getBg_barrage() {
    return this.bg_barrage;
  }
  
  public String getBg_head() {
    return this.bg_head;
  }
  
  public int getCombo() {
    return this.combo;
  }
  
  public int getCombo_force() {
    return this.combo_force;
  }
  
  public int getCombo_simulate() {
    return this.combo_simulate;
  }
  
  public ErrorContent getContent() {
    return this.content;
  }
  
  public long getCreate_time() {
    return this.create_time;
  }
  
  public int getCrit_diamond() {
    return this.crit_diamond;
  }
  
  public int getCrit_times() {
    return this.crit_times;
  }
  
  public HashMap<String, CritTimes> getCrit_times_map() {
    return this.crit_times_map;
  }
  
  public String getDesc() {
    return this.desc;
  }
  
  public int getDiamond_remain() {
    return this.diamond_remain;
  }
  
  public String getGiftKey() {
    return this.giftKey;
  }
  
  public int getGlamour_take() {
    return this.glamour_take;
  }
  
  public int getGlory_level() {
    return this.glory_level;
  }
  
  public int getGoods_id() {
    return this.goods_id;
  }
  
  public String getHead() {
    return this.head;
  }
  
  public String getImage() {
    return this.image;
  }
  
  public int getIs_combo() {
    return this.is_combo;
  }
  
  public String getLive_id() {
    return this.live_id;
  }
  
  public int getMultiple() {
    return this.multiple;
  }
  
  public String getNickname() {
    return this.nickname;
  }
  
  public String getOid() {
    return this.oid;
  }
  
  public int getRemain_num() {
    return this.remain_num;
  }
  
  public ArrayList<RichMessage> getRichMessages() {
    return this.richMessages;
  }
  
  public int getRole() {
    return this.role;
  }
  
  public int getSex() {
    return this.sex;
  }
  
  public String getSvgaAnimate() {
    return this.svgaAnimate;
  }
  
  public String getSvgaUrl() {
    return this.svgaUrl;
  }
  
  public int getTotal_glamour() {
    return this.total_glamour;
  }
  
  public String getUid() {
    return this.uid;
  }
  
  public void setAchieve_icon(String paramString) {
    this.achieve_icon = paramString;
  }
  
  public void setAnimate(String paramString) {
    this.animate = paramString;
  }
  
  public void setAnimate_combo(String paramString) {
    this.animate_combo = paramString;
  }
  
  public void setBg_barrage(String paramString) {
    this.bg_barrage = paramString;
  }
  
  public void setBg_head(String paramString) {
    this.bg_head = paramString;
  }
  
  public void setCombo(int paramInt) {
    this.combo = paramInt;
  }
  
  public void setCombo_force(int paramInt) {
    this.combo_force = paramInt;
  }
  
  public void setCombo_simulate(int paramInt) {
    this.combo_simulate = paramInt;
  }
  
  public void setContent(ErrorContent paramErrorContent) {
    this.content = paramErrorContent;
  }
  
  public void setCreate_time(long paramLong) {
    this.create_time = paramLong;
  }
  
  public void setCrit_diamond(int paramInt) {
    this.crit_diamond = paramInt;
  }
  
  public void setCrit_times(int paramInt) {
    this.crit_times = paramInt;
  }
  
  public void setCrit_times_map(HashMap<String, CritTimes> paramHashMap) {
    this.crit_times_map = paramHashMap;
  }
  
  public void setDesc(String paramString) {
    this.desc = paramString;
  }
  
  public void setDiamond_remain(int paramInt) {
    this.diamond_remain = paramInt;
  }
  
  public void setGiftKey(String paramString) {
    this.giftKey = paramString;
  }
  
  public void setGlamour_take(int paramInt) {
    this.glamour_take = paramInt;
  }
  
  public void setGlory_level(int paramInt) {
    this.glory_level = paramInt;
  }
  
  public void setGoods_id(int paramInt) {
    this.goods_id = paramInt;
  }
  
  public void setHead(String paramString) {
    this.head = paramString;
  }
  
  public void setImage(String paramString) {
    this.image = paramString;
  }
  
  public void setIs_combo(int paramInt) {
    this.is_combo = paramInt;
  }
  
  public void setLive_id(String paramString) {
    this.live_id = paramString;
  }
  
  public void setMultiple(int paramInt) {
    this.multiple = paramInt;
  }
  
  public void setNickname(String paramString) {
    this.nickname = paramString;
  }
  
  public void setOid(String paramString) {
    this.oid = paramString;
  }
  
  public void setRemain_num(int paramInt) {
    this.remain_num = paramInt;
  }
  
  public void setRichMessages(ArrayList<RichMessage> paramArrayList) {
    this.richMessages = paramArrayList;
  }
  
  public void setRole(int paramInt) {
    this.role = paramInt;
  }
  
  public void setSex(int paramInt) {
    this.sex = paramInt;
  }
  
  public void setSvgaAnimate(String paramString) {
    this.svgaAnimate = paramString;
  }
  
  public void setSvgaUrl(String paramString) {
    this.svgaUrl = paramString;
  }
  
  public void setTotal_glamour(int paramInt) {
    this.total_glamour = paramInt;
  }
  
  public void setUid(String paramString) {
    this.uid = paramString;
  }
  
  public String toString() {
    return GsonUtils.get().toJson(this);
  }
}
