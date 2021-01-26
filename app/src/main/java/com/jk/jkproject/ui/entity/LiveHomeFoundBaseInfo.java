package com.jk.jkproject.ui.entity;

import java.util.List;

public class LiveHomeFoundBaseInfo extends BaseInfo {
  private DataBean data;
  
  public DataBean getData() {
    return this.data;
  }
  
  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  
  public static class DataBean {
    private List<AnchorRoomListBean> anchorRoomList;
    
    private List<CarouselBean> carousel;
    
    private List<RankingListBean> rankingList;
    
    public List<AnchorRoomListBean> getAnchorRoomList() {
      return this.anchorRoomList;
    }
    
    public List<CarouselBean> getCarousel() {
      return this.carousel;
    }
    
    public List<RankingListBean> getRankingList() {
      return this.rankingList;
    }
    
    public void setAnchorRoomList(List<AnchorRoomListBean> param1List) {
      this.anchorRoomList = param1List;
    }
    
    public void setCarousel(List<CarouselBean> param1List) {
      this.carousel = param1List;
    }
    
    public void setRankingList(List<RankingListBean> param1List) {
      this.rankingList = param1List;
    }
    
    public static class AnchorRoomListBean {
      private String cover;
      
      private int live_type;
      
      private int peopleNumber;
      
      private String r_msg;
      
      private String r_name;
      
      private String roomId;
      
      private String u_id;
      
      private String u_name;
      
      private String u_picture;
      
      public String getCover() {
        return this.cover;
      }
      
      public int getLive_type() {
        return this.live_type;
      }
      
      public int getPeopleNumber() {
        return this.peopleNumber;
      }
      
      public String getR_msg() {
        return this.r_msg;
      }
      
      public String getR_name() {
        return this.r_name;
      }
      
      public String getRoomId() {
        return this.roomId;
      }
      
      public String getU_id() {
        return this.u_id;
      }
      
      public String getU_name() {
        return this.u_name;
      }
      
      public String getU_picture() {
        return this.u_picture;
      }
      
      public void setCover(String param2String) {
        this.cover = param2String;
      }
      
      public void setLive_type(int param2Int) {
        this.live_type = param2Int;
      }
      
      public void setPeopleNumber(int param2Int) {
        this.peopleNumber = param2Int;
      }
      
      public void setR_msg(String param2String) {
        this.r_msg = param2String;
      }
      
      public void setR_name(String param2String) {
        this.r_name = param2String;
      }
      
      public void setRoomId(String param2String) {
        this.roomId = param2String;
      }
      
      public void setU_id(String param2String) {
        this.u_id = param2String;
      }
      
      public void setU_name(String param2String) {
        this.u_name = param2String;
      }
      
      public void setU_picture(String param2String) {
        this.u_picture = param2String;
      }
    }
    
    public static class CarouselBean {
      private String arrive;
      
      private String columnType;
      
      private String id;
      
      private String imgUrl;
      
      private String type;
      
      public String getArrive() {
        return this.arrive;
      }
      
      public String getColumnType() {
        return this.columnType;
      }
      
      public String getId() {
        return this.id;
      }
      
      public String getImgUrl() {
        return this.imgUrl;
      }
      
      public String getType() {
        return this.type;
      }
      
      public void setArrive(String param2String) {
        this.arrive = param2String;
      }
      
      public void setColumnType(String param2String) {
        this.columnType = param2String;
      }
      
      public void setId(String param2String) {
        this.id = param2String;
      }
      
      public void setImgUrl(String param2String) {
        this.imgUrl = param2String;
      }
      
      public void setType(String param2String) {
        this.type = param2String;
      }
    }
    
    public static class RankingListBean {
      private String nickName;
      
      private String picture;
      
      private int ranking;
      
      private String userId;
      
      public String getNickName() {
        return this.nickName;
      }
      
      public String getPicture() {
        return this.picture;
      }
      
      public int getRanking() {
        return this.ranking;
      }
      
      public String getUserId() {
        return this.userId;
      }
      
      public void setNickName(String param2String) {
        this.nickName = param2String;
      }
      
      public void setPicture(String param2String) {
        this.picture = param2String;
      }
      
      public void setRanking(int param2Int) {
        this.ranking = param2Int;
      }
      
      public void setUserId(String param2String) {
        this.userId = param2String;
      }
    }
  }
  
  public static class AnchorRoomListBean {
    private String cover;
    
    private int live_type;
    
    private int peopleNumber;
    
    private String r_msg;
    
    private String r_name;
    
    private String roomId;
    
    private String u_id;
    
    private String u_name;
    
    private String u_picture;
    
    public String getCover() {
      return this.cover;
    }
    
    public int getLive_type() {
      return this.live_type;
    }
    
    public int getPeopleNumber() {
      return this.peopleNumber;
    }
    
    public String getR_msg() {
      return this.r_msg;
    }
    
    public String getR_name() {
      return this.r_name;
    }
    
    public String getRoomId() {
      return this.roomId;
    }
    
    public String getU_id() {
      return this.u_id;
    }
    
    public String getU_name() {
      return this.u_name;
    }
    
    public String getU_picture() {
      return this.u_picture;
    }
    
    public void setCover(String param1String) {
      this.cover = param1String;
    }
    
    public void setLive_type(int param1Int) {
      this.live_type = param1Int;
    }
    
    public void setPeopleNumber(int param1Int) {
      this.peopleNumber = param1Int;
    }
    
    public void setR_msg(String param1String) {
      this.r_msg = param1String;
    }
    
    public void setR_name(String param1String) {
      this.r_name = param1String;
    }
    
    public void setRoomId(String param1String) {
      this.roomId = param1String;
    }
    
    public void setU_id(String param1String) {
      this.u_id = param1String;
    }
    
    public void setU_name(String param1String) {
      this.u_name = param1String;
    }
    
    public void setU_picture(String param1String) {
      this.u_picture = param1String;
    }
  }
  
  public static class CarouselBean {
    private String arrive;
    
    private String columnType;
    
    private String id;
    
    private String imgUrl;
    
    private String type;
    
    public String getArrive() {
      return this.arrive;
    }
    
    public String getColumnType() {
      return this.columnType;
    }
    
    public String getId() {
      return this.id;
    }
    
    public String getImgUrl() {
      return this.imgUrl;
    }
    
    public String getType() {
      return this.type;
    }
    
    public void setArrive(String param1String) {
      this.arrive = param1String;
    }
    
    public void setColumnType(String param1String) {
      this.columnType = param1String;
    }
    
    public void setId(String param1String) {
      this.id = param1String;
    }
    
    public void setImgUrl(String param1String) {
      this.imgUrl = param1String;
    }
    
    public void setType(String param1String) {
      this.type = param1String;
    }
  }
  
  public static class RankingListBean {
    private String nickName;
    
    private String picture;
    
    private int ranking;
    
    private String userId;
    
    public String getNickName() {
      return this.nickName;
    }
    
    public String getPicture() {
      return this.picture;
    }
    
    public int getRanking() {
      return this.ranking;
    }
    
    public String getUserId() {
      return this.userId;
    }
    
    public void setNickName(String param1String) {
      this.nickName = param1String;
    }
    
    public void setPicture(String param1String) {
      this.picture = param1String;
    }
    
    public void setRanking(int param1Int) {
      this.ranking = param1Int;
    }
    
    public void setUserId(String param1String) {
      this.userId = param1String;
    }
  }
}
