package com.jk.jkproject.ui.entity;

public class EmoticonInfo extends BaseInfo{
  private String description;
  
  private int id;
  
  private String pic_url;
  
  private String thumb_url;
  
  public String getDescription() {
    return this.description;
  }
  
  public int getId() {
    return this.id;
  }
  
  public String getPic_url() {
    return this.pic_url;
  }
  
  public String getThumb_url() {
    return this.thumb_url;
  }
  
  public void setDescription(String paramString) {
    this.description = paramString;
  }
  
  public void setId(int paramInt) {
    this.id = paramInt;
  }
  
  public void setPic_url(String paramString) {
    this.pic_url = paramString;
  }
  
  public void setThumb_url(String paramString) {
    this.thumb_url = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("EmoticonInfo [id=");
    stringBuilder.append(this.id);
    stringBuilder.append(", pic_url=");
    stringBuilder.append(this.pic_url);
    stringBuilder.append(", thumb_url=");
    stringBuilder.append(this.thumb_url);
    stringBuilder.append(", description=");
    stringBuilder.append(this.description);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}
