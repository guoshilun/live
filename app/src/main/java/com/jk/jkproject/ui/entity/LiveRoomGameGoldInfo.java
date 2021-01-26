package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class LiveRoomGameGoldInfo implements Serializable {

    private int front;  //正面压注总数量
    private int contrary;  //反面压注总数量
    private int maxBet;  //最大竞猜数量
    private int frontBet;  //个人正面下注数量
    private int contraryBet;  //个人反面下注数量
    private String stage; // 当前阶段  1  等待阶段 2 下注阶段  3 出奖阶段
    private int time; // 当前倒计时时间
    private String money; // 我的钻石数量
    private int isFirstOpen; //是否当天第一次打开 0 是 1 否

    public int getIsFirstOpen() {
        return isFirstOpen;
    }

    public void setIsFirstOpen(int isFirstOpen) {
        this.isFirstOpen = isFirstOpen;
    }

    @Override
    public String toString() {
        return "LiveRoomGameGoldInfo{" +
                "front=" + front +
                ", contrary=" + contrary +
                ", maxBet=" + maxBet +
                ", frontBet=" + frontBet +
                ", contraryBet=" + contraryBet +
                ", stage='" + stage + '\'' +
                ", time=" + time +
                ", money=" + money +
                '}';
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getContrary() {
        return contrary;
    }

    public void setContrary(int contrary) {
        this.contrary = contrary;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(int maxBet) {
        this.maxBet = maxBet;
    }

    public int getFrontBet() {
        return frontBet;
    }

    public void setFrontBet(int frontBet) {
        this.frontBet = frontBet;
    }

    public int getContraryBet() {
        return contraryBet;
    }

    public void setContraryBet(int contraryBet) {
        this.contraryBet = contraryBet;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
