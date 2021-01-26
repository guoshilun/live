package com.jk.jkproject.utils;

import com.jk.jkproject.ui.entity.BaseInfo;
import java.util.List;

public class AppConfigUrl extends BaseInfo {
  private List<AppReplacesBean> app_replaces;
  
  private ConfirmBindBean confirm_bind;
  
  private int enable_call_rooms;
  
  private int enable_live_room_slide;
  
  private int hide_phone_login;
  
  private int live_chat_frequency_all;
  
  private int live_chat_frequency_grade;
  
  private int live_chat_frequency_limit;
  
  private UrlsBean urls;
  
  public List<AppReplacesBean> getApp_replaces() {
    return this.app_replaces;
  }
  
  public ConfirmBindBean getConfirm_bind() {
    return this.confirm_bind;
  }
  
  public int getEnable_call_rooms() {
    return this.enable_call_rooms;
  }
  
  public int getEnable_live_room_slide() {
    return this.enable_live_room_slide;
  }
  
  public int getHide_phone_login() {
    return this.hide_phone_login;
  }
  
  public int getLive_chat_frequency_all() {
    return this.live_chat_frequency_all;
  }
  
  public int getLive_chat_frequency_grade() {
    return this.live_chat_frequency_grade;
  }
  
  public int getLive_chat_frequency_limit() {
    return this.live_chat_frequency_limit;
  }
  
  public UrlsBean getUrls() {
    return this.urls;
  }
  
  public void setApp_replaces(List<AppReplacesBean> paramList) {
    this.app_replaces = paramList;
  }
  
  public void setConfirm_bind(ConfirmBindBean paramConfirmBindBean) {
    this.confirm_bind = paramConfirmBindBean;
  }
  
  public void setEnable_call_rooms(int paramInt) {
    this.enable_call_rooms = paramInt;
  }
  
  public void setEnable_live_room_slide(int paramInt) {
    this.enable_live_room_slide = paramInt;
  }
  
  public void setHide_phone_login(int paramInt) {
    this.hide_phone_login = paramInt;
  }
  
  public void setLive_chat_frequency_all(int paramInt) {
    this.live_chat_frequency_all = paramInt;
  }
  
  public void setLive_chat_frequency_grade(int paramInt) {
    this.live_chat_frequency_grade = paramInt;
  }
  
  public void setLive_chat_frequency_limit(int paramInt) {
    this.live_chat_frequency_limit = paramInt;
  }
  
  public void setUrls(UrlsBean paramUrlsBean) {
    this.urls = paramUrlsBean;
  }
  
  public static class AppDictionariesBean {}
  
  public static class AppReplacesBean {
    private String from;
    
    private String to;
    
    public String getFrom() {
      return this.from;
    }
    
    public String getTo() {
      return this.to;
    }
    
    public void setFrom(String param1String) {
      this.from = param1String;
    }
    
    public void setTo(String param1String) {
      this.to = param1String;
    }
  }
  
  public static class ConfirmBindBean {
    private String btn_no;
    
    private String btn_yes;
    
    private String content;
    
    private String link;
    
    private String title;
    
    public String getBtn_no() {
      return this.btn_no;
    }
    
    public String getBtn_yes() {
      return this.btn_yes;
    }
    
    public String getContent() {
      return this.content;
    }
    
    public String getLink() {
      return this.link;
    }
    
    public String getTitle() {
      return this.title;
    }
    
    public void setBtn_no(String param1String) {
      this.btn_no = param1String;
    }
    
    public void setBtn_yes(String param1String) {
      this.btn_yes = param1String;
    }
    
    public void setContent(String param1String) {
      this.content = param1String;
    }
    
    public void setLink(String param1String) {
      this.link = param1String;
    }
    
    public void setTitle(String param1String) {
      this.title = param1String;
    }
  }
  
  public static class UrlsBean {
    private String agreement;
    
    private String call_profile;
    
    private String charge_help;
    
    private String faq_app;
    
    private String faq_glamour;
    
    private String guide;
    
    private String live_duration;
    
    private String live_share;
    
    private String pay_charge;
    
    private String pay_vip;
    
    private String ranking;
    
    private String shop_effect1;
    
    private String treaty;
    
    private String vip_banner;
    
    private String vip_desc;
    
    public String getAgreement() {
      return this.agreement;
    }
    
    public String getCall_profile() {
      return this.call_profile;
    }
    
    public String getCharge_help() {
      return this.charge_help;
    }
    
    public String getFaq_app() {
      return this.faq_app;
    }
    
    public String getFaq_glamour() {
      return this.faq_glamour;
    }
    
    public String getGuide() {
      return this.guide;
    }
    
    public String getLive_duration() {
      return this.live_duration;
    }
    
    public String getLive_share() {
      return this.live_share;
    }
    
    public String getPay_charge() {
      return this.pay_charge;
    }
    
    public String getPay_vip() {
      return this.pay_vip;
    }
    
    public String getRanking() {
      return this.ranking;
    }
    
    public String getShop_effect1() {
      return this.shop_effect1;
    }
    
    public String getTreaty() {
      return this.treaty;
    }
    
    public String getVip_banner() {
      return this.vip_banner;
    }
    
    public String getVip_desc() {
      return this.vip_desc;
    }
    
    public void setAgreement(String param1String) {
      this.agreement = param1String;
    }
    
    public void setCall_profile(String param1String) {
      if (param1String == null) {
        this.call_profile = "";
      } else {
        this.call_profile = param1String;
      } 
    }
    
    public void setCharge_help(String param1String) {
      this.charge_help = param1String;
    }
    
    public void setFaq_app(String param1String) {
      this.faq_app = param1String;
    }
    
    public void setFaq_glamour(String param1String) {
      this.faq_glamour = param1String;
    }
    
    public void setGuide(String param1String) {
      this.guide = param1String;
    }
    
    public void setLive_duration(String param1String) {
      this.live_duration = param1String;
    }
    
    public void setLive_share(String param1String) {
      this.live_share = param1String;
    }
    
    public void setPay_charge(String param1String) {
      this.pay_charge = param1String;
    }
    
    public void setPay_vip(String param1String) {
      this.pay_vip = param1String;
    }
    
    public void setRanking(String param1String) {
      this.ranking = param1String;
    }
    
    public void setShop_effect1(String param1String) {
      this.shop_effect1 = param1String;
    }
    
    public void setTreaty(String param1String) {
      this.treaty = param1String;
    }
    
    public void setVip_banner(String param1String) {
      this.vip_banner = param1String;
    }
    
    public void setVip_desc(String param1String) {
      this.vip_desc = param1String;
    }
  }
}
