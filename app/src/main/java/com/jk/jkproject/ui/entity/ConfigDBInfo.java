package com.jk.jkproject.ui.entity;

import org.litepal.crud.LitePalSupport;

public class ConfigDBInfo extends LitePalSupport {
  private String keyDictionaries;
  
  private String valueDictionaries;
  
  public String getKeyDictionaries() {
    return this.keyDictionaries;
  }
  
  public String getValueDictionaries() {
    return this.valueDictionaries;
  }
  
  public void setKeyDictionaries(String paramString) {
    this.keyDictionaries = paramString;
  }
  
  public void setValueDictionaries(String paramString) {
    this.valueDictionaries = paramString;
  }
}
