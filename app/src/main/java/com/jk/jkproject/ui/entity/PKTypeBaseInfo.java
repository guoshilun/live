package com.jk.jkproject.ui.entity;

import androidx.annotation.IdRes;

public class PKTypeBaseInfo {

    @IdRes
    private int picture;
    private String name;
    private String describe;
    private String sgin;
    private int isSelect;//0关闭 1开启

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSgin() {
        return sgin;
    }

    public void setSgin(String sgin) {
        this.sgin = sgin;
    }

    public int isSelect() {
        return isSelect;
    }

    public void setSelect(int select) {
        isSelect = select;
    }
}
