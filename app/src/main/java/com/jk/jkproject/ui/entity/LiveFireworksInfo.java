package com.jk.jkproject.ui.entity;

import com.jk.jkproject.ui.widget.danmaku.RichMessage;
import java.io.Serializable;
import java.util.ArrayList;

public class LiveFireworksInfo implements Serializable {
  private String animate;
  
  private String chest_key;
  
  private int chest_ttl;
  
  private String content;
  
  private String content_lg;
  
  private String content_sm;
  
  private String fr_head;
  
  private String fr_name;
  
  private String fr_uid;
  
  private int gift_id;
  
  private int glory_level;
  
  private String live_id;
  
  private String pull_url;
  
  private ArrayList<RichMessage> rich_text;
  
  private int role;
  
  private long room_id;
  
  private String to_name;
  
  private String to_uid;
  
  private int total_glamour;
  
  public String getAnimate() {
    return this.animate;
  }
  
  public String getChest_key() {
    return this.chest_key;
  }
  
  public int getChest_ttl() {
    return this.chest_ttl;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public String getContent_lg() {
    return this.content_lg;
  }
  
  public String getContent_sm() {
    return this.content_sm;
  }
  
  public String getFr_head() {
    return this.fr_head;
  }
  
  public String getFr_name() {
    return this.fr_name;
  }
  
  public String getFr_uid() {
    return this.fr_uid;
  }
  
  public int getGift_id() {
    return this.gift_id;
  }
  
  public int getGlory_level() {
    return this.glory_level;
  }
  
  public String getLive_id() {
    return this.live_id;
  }
  
  public String getPull_url() {
    return this.pull_url;
  }
  
  public ArrayList<RichMessage> getRich_text() {
    return this.rich_text;
  }
  
  public int getRole() {
    return this.role;
  }
  
  public long getRoom_id() {
    return this.room_id;
  }
  
  public String getTo_name() {
    return this.to_name;
  }
  
  public String getTo_uid() {
    return this.to_uid;
  }
  
  public int getTotal_glamour() {
    return this.total_glamour;
  }
  
  public void setAnimate(String paramString) {
    this.animate = paramString;
  }
  
  public void setChest_key(String paramString) {
    this.chest_key = paramString;
  }
  
  public void setChest_ttl(int paramInt) {
    this.chest_ttl = paramInt;
  }
  
  public void setContent(String paramString) {
    this.content = paramString;
  }
  
  public void setContent_lg(String paramString) {
    this.content_lg = paramString;
  }
  
  public void setContent_sm(String paramString) {
    this.content_sm = paramString;
  }
  
  public void setFr_head(String paramString) {
    this.fr_head = paramString;
  }
  
  public void setFr_name(String paramString) {
    this.fr_name = paramString;
  }
  
  public void setFr_uid(String paramString) {
    this.fr_uid = paramString;
  }
  
  public void setGift_id(int paramInt) {
    this.gift_id = paramInt;
  }
  
  public void setGlory_level(int paramInt) {
    this.glory_level = paramInt;
  }
  
  public void setLive_id(String paramString) {
    this.live_id = paramString;
  }
  
  public void setPull_url(String paramString) {
    this.pull_url = paramString;
  }
  
  public void setRich_text(ArrayList<RichMessage> paramArrayList) {
    this.rich_text = paramArrayList;
  }
  
  public void setRole(int paramInt) {
    this.role = paramInt;
  }
  
  public void setRoom_id(long paramLong) {
    this.room_id = paramLong;
  }
  
  public void setTo_name(String paramString) {
    this.to_name = paramString;
  }
  
  public void setTo_uid(String paramString) {
    this.to_uid = paramString;
  }
  
  public void setTotal_glamour(int paramInt) {
    this.total_glamour = paramInt;
  }
}
