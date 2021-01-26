package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class LiveHostClost extends BaseInfo implements Serializable {

    private LiveClosePushInfo data;

    @Override
    public String toString() {
        return "LiveHostClost{" +
                "data=" + data +
                '}';
    }

    public LiveClosePushInfo getData() {
        return data;
    }

    public void setData(LiveClosePushInfo data) {
        this.data = data;
    }
}
