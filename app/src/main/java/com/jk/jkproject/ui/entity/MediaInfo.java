package com.jk.jkproject.ui.entity;

import android.text.TextUtils;
import com.jk.jkproject.utils.GsonUtils;

public class MediaInfo extends ViewSelectBean {

  private String resource;//" : "http://...", //原始路径
  private String thumb;//"    : "http://...", //缩略图
  private String text = "";//"     : "", //描述
  private int type = 0;//1, // 0:文本 1:图片 2:音频 3:视频 4:图文

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getThumb() {
    return thumb;
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return GsonUtils.get().toJson(this);
  }

  @Override
  public boolean equals(Object o) {
    MediaInfo mi = (MediaInfo) o;
    return type == mi.getType() && TextUtils.equals(resource, mi.getResource()) &&
            TextUtils.equals(thumb, mi.getThumb()) && TextUtils.equals(text, mi.getText());
  }
}

