package com.jk.jkproject.net.im.db;

import org.litepal.crud.LitePalSupport;

public class TeenTipsDB extends LitePalSupport {
    private long id ;
    private String u_id;
    private String time;

    @Override
    public String toString() {
        return "TeenTipsDB{" +
                "id=" + id +
                ", u_id='" + u_id + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
