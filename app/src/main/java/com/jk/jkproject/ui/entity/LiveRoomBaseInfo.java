package com.jk.jkproject.ui.entity;

public class LiveRoomBaseInfo extends BaseInfo {
  private DataBean data;
  
  public DataBean getData() {
    return this.data;
  }
  
  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  
  public static class DataBean {
    private String TeamName;
    
    private String TypeName;
    
    private boolean attention;
    
    private long downcastTime;
    
    private int followers;
    
    private String headUrl;
    
    private int heat;
    
    private int live_type;
    
    private String nickName;
    
    private int payTime;
    
    private String play_url = "";
    
    private String pull_flow_url = "";
    
    private String r_msg;
    
    private String r_name;
    
    private String r_notice;
    
    private int r_state;
    
    private String roomId;
    
    private String u_id;
    
    public long getDowncastTime() {
      return this.downcastTime;
    }
    
    public int getFollowers() {
      return this.followers;
    }
    
    public String getHeadUrl() {
      return this.headUrl;
    }
    
    public int getHeat() {
      return this.heat;
    }
    
    public int getLive_type() {
      return this.live_type;
    }
    
    public String getNickName() {
      return this.nickName;
    }
    
    public int getPayTime() {
      return this.payTime;
    }
    
    public String getPlay_url() {
      return this.play_url;
    }
    
    public String getPull_flow_url() {
      return this.pull_flow_url;
    }
    
    public String getR_msg() {
      return this.r_msg;
    }
    
    public String getR_name() {
      return this.r_name;
    }
    
    public String getR_notice() {
      return this.r_notice;
    }
    
    public int getR_state() {
      return this.r_state;
    }
    
    public String getRoomId() {
      return this.roomId;
    }
    
    public String getTeamName() {
      return this.TeamName;
    }
    
    public String getTypeName() {
      return this.TypeName;
    }
    
    public String getU_id() {
      return this.u_id;
    }
    
    public boolean isAttention() {
      return this.attention;
    }
    
    public void setAttention(boolean param1Boolean) {
      this.attention = param1Boolean;
    }
    
    public void setDowncastTime(long param1Long) {
      this.downcastTime = param1Long;
    }
    
    public void setFollowers(int param1Int) {
      this.followers = param1Int;
    }
    
    public void setHeadUrl(String param1String) {
      this.headUrl = param1String;
    }
    
    public void setHeat(int param1Int) {
      this.heat = param1Int;
    }
    
    public void setLive_type(int param1Int) {
      this.live_type = param1Int;
    }
    
    public void setNickName(String param1String) {
      this.nickName = param1String;
    }
    
    public void setPayTime(int param1Int) {
      this.payTime = param1Int;
    }
    
    public void setPlay_url(String param1String) {
      this.play_url = param1String;
    }
    
    public void setPull_flow_url(String param1String) {
      this.pull_flow_url = param1String;
    }
    
    public void setR_msg(String param1String) {
      this.r_msg = param1String;
    }
    
    public void setR_name(String param1String) {
      this.r_name = param1String;
    }
    
    public void setR_notice(String param1String) {
      this.r_notice = param1String;
    }
    
    public void setR_state(int param1Int) {
      this.r_state = param1Int;
    }
    
    public void setRoomId(String param1String) {
      this.roomId = param1String;
    }
    
    public void setTeamName(String param1String) {
      this.TeamName = param1String;
    }
    
    public void setTypeName(String param1String) {
      this.TypeName = param1String;
    }
    
    public void setU_id(String param1String) {
      this.u_id = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("DataBean{heat=");
      stringBuilder.append(this.heat);
      stringBuilder.append(", TypeName='");
      stringBuilder.append(this.TypeName);
      stringBuilder.append('\'');
      stringBuilder.append(", payTime=");
      stringBuilder.append(this.payTime);
      stringBuilder.append(", nickName='");
      stringBuilder.append(this.nickName);
      stringBuilder.append('\'');
      stringBuilder.append(", r_msg='");
      stringBuilder.append(this.r_msg);
      stringBuilder.append('\'');
      stringBuilder.append(", headUrl='");
      stringBuilder.append(this.headUrl);
      stringBuilder.append('\'');
      stringBuilder.append(", r_state=");
      stringBuilder.append(this.r_state);
      stringBuilder.append(", live_type=");
      stringBuilder.append(this.live_type);
      stringBuilder.append(", TeamName='");
      stringBuilder.append(this.TeamName);
      stringBuilder.append('\'');
      stringBuilder.append(", roomId='");
      stringBuilder.append(this.roomId);
      stringBuilder.append('\'');
      stringBuilder.append(", r_name='");
      stringBuilder.append(this.r_name);
      stringBuilder.append('\'');
      stringBuilder.append(", u_id='");
      stringBuilder.append(this.u_id);
      stringBuilder.append('\'');
      stringBuilder.append(", pull_flow_url='");
      stringBuilder.append(this.pull_flow_url);
      stringBuilder.append('\'');
      stringBuilder.append(", play_url='");
      stringBuilder.append(this.play_url);
      stringBuilder.append('\'');
      stringBuilder.append(", followers=");
      stringBuilder.append(this.followers);
      stringBuilder.append(", r_notice='");
      stringBuilder.append(this.r_notice);
      stringBuilder.append('\'');
      stringBuilder.append(", attention=");
      stringBuilder.append(this.attention);
      stringBuilder.append(", downcastTime=");
      stringBuilder.append(this.downcastTime);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
}
