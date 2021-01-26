package com.jk.jkproject.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LiveGiftInfoBean extends BaseInfo implements Parcelable {
    private List<DataBean> data;

    protected LiveGiftInfoBean(Parcel in) {
        data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<LiveGiftInfoBean> CREATOR = new Creator<LiveGiftInfoBean>() {
        @Override
        public LiveGiftInfoBean createFromParcel(Parcel in) {
            return new LiveGiftInfoBean(in);
        }

        @Override
        public LiveGiftInfoBean[] newArray(int size) {
            return new LiveGiftInfoBean[size];
        }
    };

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> paramList) {
        this.data = paramList;
    }


    @Override
    public String toString() {
        return "LiveGiftInfoBean{" +
                "data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt) {
        paramParcel.writeTypedList(this.data);
    }


    public static class DataBean implements Parcelable {
        private int g_amount;

        private String g_cartoon;

        private String g_continuous_send;

        private int g_experience;

        private int g_gain_amount;

        private int g_gain_hot;

        public int getP_id() {
            return p_id;
        }

        public void setP_id(int p_id) {
            this.p_id = p_id;
        }

        private int p_id = -1;

        private String g_icon;

        private String g_name;
        private String picture;
        private String username;

        private int is_combo;//0.单送礼物 1:连送礼物
        private int g_type;//	类型: 0.普通礼物 1.连送礼物 2.豪华礼物
        private int type;// 0.没有svga 1:有svga
        private int combo = 1; //连接次数
        private String id;

        private String select_count;
        private String userId;
        private String animateType = LiveGift.LIVE_GIFT_ANIMATE_BATTER;
        private String svgaAnimate;//svga礼物链接


        public boolean select_item;

        private int select_position;

        private long expire_time;
        private int p_count;


        public long getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(long expire_time) {
            this.expire_time = expire_time;
        }

        public int getP_count() {
            return p_count;
        }

        public void setP_count(int p_count) {
            this.p_count = p_count;
        }

        public DataBean(Parcel in) {
            g_amount = in.readInt();
            g_cartoon = in.readString();
            g_continuous_send = in.readString();
            g_experience = in.readInt();
            g_gain_amount = in.readInt();
            g_gain_hot = in.readInt();
            g_icon = in.readString();
            g_name = in.readString();
            is_combo = in.readInt();
            id = in.readString();
            select_count = in.readString();
            select_item = in.readByte() != 0;
            select_position = in.readInt();
            type = in.readInt();
            picture = in.readString();
            expire_time = in.readLong();
            username = in.readString();
            userId = in.readString();
            g_type = in.readInt();
            animateType = in.readString();
            svgaAnimate = in.readString();
            combo = in.readInt();
            p_count = in.readInt();
            p_id = in.readInt();
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIs_combo() {
            return is_combo;
        }

        public void setIs_combo(int is_combo) {
            this.is_combo = is_combo;
        }

        public DataBean() {
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

            dest.writeInt(g_amount);
            dest.writeString(g_cartoon);
            dest.writeString(g_continuous_send);
            dest.writeInt(g_experience);
            dest.writeInt(g_gain_amount);
            dest.writeInt(g_gain_hot);
            dest.writeString(g_icon);
            dest.writeString(g_name);
            dest.writeInt(is_combo);
            dest.writeString(id);
            dest.writeString(select_count);
            dest.writeByte((byte) (select_item ? 1 : 0));
            dest.writeInt(select_position);
            dest.writeInt(type);
            dest.writeString(picture);
            dest.writeString(username);
            dest.writeString(userId);
            dest.writeInt(g_type);
            dest.writeString(animateType);
            dest.writeString(svgaAnimate);
            dest.writeInt(combo);
            dest.writeLong(expire_time);
            dest.writeInt(p_count);
            dest.writeInt(p_id);
        }

        public int getG_amount() {
            return this.g_amount;
        }

        public String getG_cartoon() {
            return this.g_cartoon;
        }

        public String getG_continuous_send() {
            return this.g_continuous_send;
        }

        public int getG_experience() {
            return this.g_experience;
        }

        public int getG_gain_amount() {
            return this.g_gain_amount;
        }

        public int getG_gain_hot() {
            return this.g_gain_hot;
        }

        public String getG_icon() {
            return this.g_icon;
        }

        public String getG_name() {
            return this.g_name;
        }

        public int getCombo() {
            return combo;
        }

        public void setCombo(int combo) {
            this.combo = combo;
        }

        public String getAnimateType() {
            return animateType;
        }

        public void setAnimateType(String animateType) {
            this.animateType = animateType;
        }

        public String getSvgaAnimate() {
            return svgaAnimate;
        }

        public void setSvgaAnimate(String svgaAnimate) {
            this.svgaAnimate = svgaAnimate;
        }

        public int getG_type() {
            return g_type;
        }

        public void setG_type(int g_type) {
            this.g_type = g_type;
        }

        public String getId() {
            return this.id;
        }

        public String getSelect_count() {
            return this.select_count;
        }

        public int getSelect_position() {
            return this.select_position;
        }

        public boolean isSelect_item() {
            return this.select_item;
        }

        public void setG_amount(int param1Int) {
            this.g_amount = param1Int;
        }

        public void setG_cartoon(String param1String) {
            this.g_cartoon = param1String;
        }

        public void setG_continuous_send(String param1String) {
            this.g_continuous_send = param1String;
        }

        public void setG_experience(int param1Int) {
            this.g_experience = param1Int;
        }

        public void setG_gain_amount(int param1Int) {
            this.g_gain_amount = param1Int;
        }

        public void setG_gain_hot(int param1Int) {
            this.g_gain_hot = param1Int;
        }

        public void setG_icon(String param1String) {
            this.g_icon = param1String;
        }

        public void setG_name(String param1String) {
            this.g_name = param1String;
        }


        public void setId(String param1Int) {
            this.id = param1Int;
        }

        public void setSelect_count(String param1String) {
            this.select_count = param1String;
        }

        public void setSelect_item(boolean param1Boolean) {
            this.select_item = param1Boolean;
        }

        public void setSelect_position(int param1Int) {
            this.select_position = param1Int;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "g_amount=" + g_amount +
                    ", g_cartoon='" + g_cartoon + '\'' +
                    ", g_continuous_send='" + g_continuous_send + '\'' +
                    ", g_experience=" + g_experience +
                    ", g_gain_amount=" + g_gain_amount +
                    ", g_gain_hot=" + g_gain_hot +
                    ", g_icon='" + g_icon + '\'' +
                    ", g_name='" + g_name + '\'' +
                    ", picture='" + picture + '\'' +
                    ", username='" + username + '\'' +
                    ", is_combo=" + is_combo +
                    ", g_type=" + g_type +
                    ", type=" + type +
                    ", combo=" + combo +
                    ", id=" + id +
                    ", select_count='" + select_count + '\'' +
                    ", userId='" + userId + '\'' +
                    ", animateType='" + animateType + '\'' +
                    ", svgaAnimate='" + svgaAnimate + '\'' +
                    ", select_item=" + select_item +
                    ", select_position=" + select_position +
                    '}';
        }
    }
}
