package com.jk.jkproject.ui.entity;


import com.jk.jkproject.net.im.info.GameLogProto;

import java.io.Serializable;
import java.util.List;

public class LiveRoomGameGoldBaseInfo extends BaseInfo implements Serializable {
    private List<GameLogProto.GameLogResult> data;

    public List<GameLogProto.GameLogResult> getData() {
        return this.data;
    }

    public void setData(List<GameLogProto.GameLogResult> paramList) {
        this.data = paramList;
    }

    @Override
    public String toString() {
        return "LiveRoomGameGoldBaseInfo{" +
                "data=" + data +
                '}';
    }


    public static class DataBean implements Serializable {

        private int rank;
        private String gold;
        private boolean isWin;

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public boolean isWin() {
            return isWin;
        }

        public void setWin(boolean win) {
            isWin = win;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "rank=" + rank +
                    ", gold='" + gold + '\'' +
                    ", isWin=" + isWin +
                    '}';
        }
    }
}