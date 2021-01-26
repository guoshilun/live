package com.jk.jkproject.ui.entity;

public class DiamondTopUpBean extends BaseInfo {

    private String diamond;
    private String money;
    private boolean isClick;

    @Override
    public String toString() {
        return "DiamondTopUpBean{" +
                "diamond='" + diamond + '\'' +
                ", money='" + money + '\'' +
                ", isClick='" + isClick + '\'' +
                '}';
    }

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean getIsClick() {
        return isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }
}
