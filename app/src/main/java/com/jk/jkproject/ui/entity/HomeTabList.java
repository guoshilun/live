package com.jk.jkproject.ui.entity;

import java.io.Serializable;
import java.util.List;

public class HomeTabList extends BaseInfo implements Serializable {

  private DataBean data;
  


  public DataBean getData() {
    return this.data;
  }
  

  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }


  @Override
  public String toString() {
    return "HomeTabList{" +
            "data=" + data +
            '}';
  }

  public static class DataBean implements Serializable {
    private List<AfficheBean> affiche;
    
    private List<AnchorListBean> anchorList;
    
    private List<CarouselBean> carousel;
    
    private List<ColumnListBean> columnList;
    
    private List<ScheduleBean> scheduleList;
    
    private List<ScheduleLiveBean> scheduleLiveList;
    
    public List<AfficheBean> getAffiche() {
      return this.affiche;
    }
    
    public List<AnchorListBean> getAnchorList() {
      return this.anchorList;
    }
    
    public List<CarouselBean> getCarousel() {
      return this.carousel;
    }
    
    public List<ColumnListBean> getColumnList() {
      return this.columnList;
    }
    
    public List<ScheduleBean> getScheduleList() {
      return this.scheduleList;
    }
    
    public List<ScheduleLiveBean> getScheduleLiveList() {
      return this.scheduleLiveList;
    }
    
    public void setAffiche(List<AfficheBean> param1List) {
      this.affiche = param1List;
    }
    
    public void setAnchorList(List<AnchorListBean> param1List) {
      this.anchorList = param1List;
    }
    
    public void setCarousel(List<CarouselBean> param1List) {
      this.carousel = param1List;
    }
    
    public void setColumnList(List<ColumnListBean> param1List) {
      this.columnList = param1List;
    }
    
    public void setScheduleList(List<ScheduleBean> param1List) {
      this.scheduleList = param1List;
    }
    
    public void setScheduleLiveList(List<ScheduleLiveBean> param1List) {
      this.scheduleLiveList = param1List;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("DataBean{affiche=");
      stringBuilder.append(this.affiche);
      stringBuilder.append(", scheduleList=");
      stringBuilder.append(this.scheduleList);
      stringBuilder.append(", columnList=");
      stringBuilder.append(this.columnList);
      stringBuilder.append(", scheduleLiveList=");
      stringBuilder.append(this.scheduleLiveList);
      stringBuilder.append(", carousel=");
      stringBuilder.append(this.carousel);
      stringBuilder.append(", anchorList=");
      stringBuilder.append(this.anchorList);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public static class AfficheBean implements Serializable {
      private String arrive;
      
      private String columnType;
      
      private String id;
      
      private String notice;
      
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
      
      public String getNotice() {
        return this.notice;
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
      
      public void setNotice(String param2String) {
        this.notice = param2String;
      }
      
      public void setType(String param2String) {
        this.type = param2String;
      }
      
      public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AfficheBean{columnType='");
        stringBuilder.append(this.columnType);
        stringBuilder.append('\'');
        stringBuilder.append(", arrive='");
        stringBuilder.append(this.arrive);
        stringBuilder.append('\'');
        stringBuilder.append(", id='");
        stringBuilder.append(this.id);
        stringBuilder.append('\'');
        stringBuilder.append(", type='");
        stringBuilder.append(this.type);
        stringBuilder.append('\'');
        stringBuilder.append(", notice='");
        stringBuilder.append(this.notice);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
      }
    }
    
    public static class AnchorListBean implements Serializable {
      private String live_type;
      
      private int peopleNumber;
      
      private String r_name;
      
      private String roomId;
      
      private String rootCover;
      
      private String u_id;
      
      private String u_name;
      
      public String getLive_type() {
        return this.live_type;
      }
      
      public int getPeopleNumber() {
        return this.peopleNumber;
      }
      
      public String getR_name() {
        return this.r_name;
      }
      
      public String getRoomId() {
        return this.roomId;
      }
      
      public String getRootCover() {
        return this.rootCover;
      }
      
      public String getU_id() {
        return this.u_id;
      }
      
      public String getU_name() {
        return this.u_name;
      }
      
      public void setLive_type(String param2String) {
        this.live_type = param2String;
      }
      
      public void setPeopleNumber(int param2Int) {
        this.peopleNumber = param2Int;
      }
      
      public void setR_name(String param2String) {
        this.r_name = param2String;
      }
      
      public void setRoomId(String param2String) {
        this.roomId = param2String;
      }
      
      public void setRootCover(String param2String) {
        this.rootCover = param2String;
      }
      
      public void setU_id(String param2String) {
        this.u_id = param2String;
      }
      
      public void setU_name(String param2String) {
        this.u_name = param2String;
      }
      
      public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AnchorListBean{r_name='");
        stringBuilder.append(this.r_name);
        stringBuilder.append('\'');
        stringBuilder.append(", roomId='");
        stringBuilder.append(this.roomId);
        stringBuilder.append('\'');
        stringBuilder.append(", live_type=");
        stringBuilder.append(this.live_type);
        stringBuilder.append(", peopleNumber=");
        stringBuilder.append(this.peopleNumber);
        stringBuilder.append(", u_name='");
        stringBuilder.append(this.u_name);
        stringBuilder.append('\'');
        stringBuilder.append(", u_id='");
        stringBuilder.append(this.u_id);
        stringBuilder.append('\'');
        stringBuilder.append(", rootCover='");
        stringBuilder.append(this.rootCover);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
      }
    }
    
    public static class CarouselBean implements Serializable {
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
      
      public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CarouselBean{imgUrl='");
        stringBuilder.append(this.imgUrl);
        stringBuilder.append('\'');
        stringBuilder.append(", columnType='");
        stringBuilder.append(this.columnType);
        stringBuilder.append('\'');
        stringBuilder.append(", arrive='");
        stringBuilder.append(this.arrive);
        stringBuilder.append('\'');
        stringBuilder.append(", id='");
        stringBuilder.append(this.id);
        stringBuilder.append('\'');
        stringBuilder.append(", type='");
        stringBuilder.append(this.type);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
      }
    }
    
    public static class ColumnListBean implements Serializable {
      private int id;
      
      private String name;
      
      public int getId() {
        return this.id;
      }
      
      public String getName() {
        return this.name;
      }
      
      public void setId(int param2Int) {
        this.id = param2Int;
      }
      
      public void setName(String param2String) {
        this.name = param2String;
      }
      
      public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ColumnListBean{name='");
        stringBuilder.append(this.name);
        stringBuilder.append('\'');
        stringBuilder.append(", id=");
        stringBuilder.append(this.id);
        stringBuilder.append('}');
        return stringBuilder.toString();
      }
    }
    
    public static class ScheduleBean implements Serializable {
      private int id;
      
      private String name;
      
      private String play_time;
      
      private int state;
      
      private int teamId1;
      
      private int teamId2;
      
      private String team_name1;
      
      private String team_name2;
      
      private String team_url1;
      
      private String team_url2;
      
      public int getId() {
        return this.id;
      }
      
      public String getName() {
        return this.name;
      }
      
      public String getPlay_time() {
        return this.play_time;
      }
      
      public int getState() {
        return this.state;
      }
      
      public int getTeamId1() {
        return this.teamId1;
      }
      
      public int getTeamId2() {
        return this.teamId2;
      }
      
      public String getTeam_name1() {
        return this.team_name1;
      }
      
      public String getTeam_name2() {
        return this.team_name2;
      }
      
      public String getTeam_url1() {
        return this.team_url1;
      }
      
      public String getTeam_url2() {
        return this.team_url2;
      }
      
      public void setId(int param2Int) {
        this.id = param2Int;
      }
      
      public void setName(String param2String) {
        this.name = param2String;
      }
      
      public void setPlay_time(String param2String) {
        this.play_time = param2String;
      }
      
      public void setState(int param2Int) {
        this.state = param2Int;
      }
      
      public void setTeamId1(int param2Int) {
        this.teamId1 = param2Int;
      }
      
      public void setTeamId2(int param2Int) {
        this.teamId2 = param2Int;
      }
      
      public void setTeam_name1(String param2String) {
        this.team_name1 = param2String;
      }
      
      public void setTeam_name2(String param2String) {
        this.team_name2 = param2String;
      }
      
      public void setTeam_url1(String param2String) {
        this.team_url1 = param2String;
      }
      
      public void setTeam_url2(String param2String) {
        this.team_url2 = param2String;
      }
      
      public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ScheduleBean{team_url2='");
        stringBuilder.append(this.team_url2);
        stringBuilder.append('\'');
        stringBuilder.append(", team_url1='");
        stringBuilder.append(this.team_url1);
        stringBuilder.append('\'');
        stringBuilder.append(", name='");
        stringBuilder.append(this.name);
        stringBuilder.append('\'');
        stringBuilder.append(", id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", state=");
        stringBuilder.append(this.state);
        stringBuilder.append(", team_name1='");
        stringBuilder.append(this.team_name1);
        stringBuilder.append('\'');
        stringBuilder.append(", teamId1=");
        stringBuilder.append(this.teamId1);
        stringBuilder.append(", team_name2='");
        stringBuilder.append(this.team_name2);
        stringBuilder.append('\'');
        stringBuilder.append(", teamId2=");
        stringBuilder.append(this.teamId2);
        stringBuilder.append(", play_time='");
        stringBuilder.append(this.play_time);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
      }
    }
    
    public static class ScheduleLiveBean implements Serializable {
      private int id;
      
      private String name;
      
      private String play_time;
      
      private int state;
      
      private int teamId1;
      
      private int teamId2;
      
      private String team_name1;
      
      private String team_name2;
      
      private String team_url1;
      
      private String team_url2;
      
      public int getId() {
        return this.id;
      }
      
      public String getName() {
        return this.name;
      }
      
      public String getPlay_time() {
        return this.play_time;
      }
      
      public int getState() {
        return this.state;
      }
      
      public int getTeamId1() {
        return this.teamId1;
      }
      
      public int getTeamId2() {
        return this.teamId2;
      }
      
      public String getTeam_name1() {
        return this.team_name1;
      }
      
      public String getTeam_name2() {
        return this.team_name2;
      }
      
      public String getTeam_url1() {
        return this.team_url1;
      }
      
      public String getTeam_url2() {
        return this.team_url2;
      }
      
      public void setId(int param2Int) {
        this.id = param2Int;
      }
      
      public void setName(String param2String) {
        this.name = param2String;
      }
      
      public void setPlay_time(String param2String) {
        this.play_time = param2String;
      }
      
      public void setState(int param2Int) {
        this.state = param2Int;
      }
      
      public void setTeamId1(int param2Int) {
        this.teamId1 = param2Int;
      }
      
      public void setTeamId2(int param2Int) {
        this.teamId2 = param2Int;
      }
      
      public void setTeam_name1(String param2String) {
        this.team_name1 = param2String;
      }
      
      public void setTeam_name2(String param2String) {
        this.team_name2 = param2String;
      }
      
      public void setTeam_url1(String param2String) {
        this.team_url1 = param2String;
      }
      
      public void setTeam_url2(String param2String) {
        this.team_url2 = param2String;
      }
      
      public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ScheduleLiveBean{team_url2='");
        stringBuilder.append(this.team_url2);
        stringBuilder.append('\'');
        stringBuilder.append(", team_url1='");
        stringBuilder.append(this.team_url1);
        stringBuilder.append('\'');
        stringBuilder.append(", name='");
        stringBuilder.append(this.name);
        stringBuilder.append('\'');
        stringBuilder.append(", id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", state=");
        stringBuilder.append(this.state);
        stringBuilder.append(", team_name1='");
        stringBuilder.append(this.team_name1);
        stringBuilder.append('\'');
        stringBuilder.append(", teamId1=");
        stringBuilder.append(this.teamId1);
        stringBuilder.append(", team_name2='");
        stringBuilder.append(this.team_name2);
        stringBuilder.append('\'');
        stringBuilder.append(", teamId2=");
        stringBuilder.append(this.teamId2);
        stringBuilder.append(", play_time='");
        stringBuilder.append(this.play_time);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
      }
    }
  }
  
  public static class AfficheBean implements Serializable {
    private String arrive;
    
    private String columnType;
    
    private String id;
    
    private String notice;
    
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
    
    public String getNotice() {
      return this.notice;
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
    
    public void setNotice(String param1String) {
      this.notice = param1String;
    }
    
    public void setType(String param1String) {
      this.type = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AfficheBean{columnType='");
      stringBuilder.append(this.columnType);
      stringBuilder.append('\'');
      stringBuilder.append(", arrive='");
      stringBuilder.append(this.arrive);
      stringBuilder.append('\'');
      stringBuilder.append(", id='");
      stringBuilder.append(this.id);
      stringBuilder.append('\'');
      stringBuilder.append(", type='");
      stringBuilder.append(this.type);
      stringBuilder.append('\'');
      stringBuilder.append(", notice='");
      stringBuilder.append(this.notice);
      stringBuilder.append('\'');
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
  
  public static class AnchorListBean implements Serializable {
    private String live_type;
    
    private int peopleNumber;
    
    private String r_name;
    
    private String roomId;
    
    private String rootCover;
    
    private String u_id;
    
    private String u_name;
    
    public String getLive_type() {
      return this.live_type;
    }
    
    public int getPeopleNumber() {
      return this.peopleNumber;
    }
    
    public String getR_name() {
      return this.r_name;
    }
    
    public String getRoomId() {
      return this.roomId;
    }
    
    public String getRootCover() {
      return this.rootCover;
    }
    
    public String getU_id() {
      return this.u_id;
    }
    
    public String getU_name() {
      return this.u_name;
    }
    
    public void setLive_type(String param1String) {
      this.live_type = param1String;
    }
    
    public void setPeopleNumber(int param1Int) {
      this.peopleNumber = param1Int;
    }
    
    public void setR_name(String param1String) {
      this.r_name = param1String;
    }
    
    public void setRoomId(String param1String) {
      this.roomId = param1String;
    }
    
    public void setRootCover(String param1String) {
      this.rootCover = param1String;
    }
    
    public void setU_id(String param1String) {
      this.u_id = param1String;
    }
    
    public void setU_name(String param1String) {
      this.u_name = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AnchorListBean{r_name='");
      stringBuilder.append(this.r_name);
      stringBuilder.append('\'');
      stringBuilder.append(", roomId='");
      stringBuilder.append(this.roomId);
      stringBuilder.append('\'');
      stringBuilder.append(", live_type=");
      stringBuilder.append(this.live_type);
      stringBuilder.append(", peopleNumber=");
      stringBuilder.append(this.peopleNumber);
      stringBuilder.append(", u_name='");
      stringBuilder.append(this.u_name);
      stringBuilder.append('\'');
      stringBuilder.append(", u_id='");
      stringBuilder.append(this.u_id);
      stringBuilder.append('\'');
      stringBuilder.append(", rootCover='");
      stringBuilder.append(this.rootCover);
      stringBuilder.append('\'');
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
  
  public static class CarouselBean implements Serializable {
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
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("CarouselBean{imgUrl='");
      stringBuilder.append(this.imgUrl);
      stringBuilder.append('\'');
      stringBuilder.append(", columnType='");
      stringBuilder.append(this.columnType);
      stringBuilder.append('\'');
      stringBuilder.append(", arrive='");
      stringBuilder.append(this.arrive);
      stringBuilder.append('\'');
      stringBuilder.append(", id='");
      stringBuilder.append(this.id);
      stringBuilder.append('\'');
      stringBuilder.append(", type='");
      stringBuilder.append(this.type);
      stringBuilder.append('\'');
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
  
  public static class ColumnListBean implements Serializable {
    private int id;
    
    private String name;
    
    public int getId() {
      return this.id;
    }
    
    public String getName() {
      return this.name;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setName(String param1String) {
      this.name = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ColumnListBean{name='");
      stringBuilder.append(this.name);
      stringBuilder.append('\'');
      stringBuilder.append(", id=");
      stringBuilder.append(this.id);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
  
  public static class ScheduleBean implements Serializable {
    private int id;
    
    private String name;
    
    private String play_time;
    
    private int state;
    
    private int teamId1;
    
    private int teamId2;
    
    private String team_name1;
    
    private String team_name2;
    
    private String team_url1;
    
    private String team_url2;
    
    public int getId() {
      return this.id;
    }
    
    public String getName() {
      return this.name;
    }
    
    public String getPlay_time() {
      return this.play_time;
    }
    
    public int getState() {
      return this.state;
    }
    
    public int getTeamId1() {
      return this.teamId1;
    }
    
    public int getTeamId2() {
      return this.teamId2;
    }
    
    public String getTeam_name1() {
      return this.team_name1;
    }
    
    public String getTeam_name2() {
      return this.team_name2;
    }
    
    public String getTeam_url1() {
      return this.team_url1;
    }
    
    public String getTeam_url2() {
      return this.team_url2;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setName(String param1String) {
      this.name = param1String;
    }
    
    public void setPlay_time(String param1String) {
      this.play_time = param1String;
    }
    
    public void setState(int param1Int) {
      this.state = param1Int;
    }
    
    public void setTeamId1(int param1Int) {
      this.teamId1 = param1Int;
    }
    
    public void setTeamId2(int param1Int) {
      this.teamId2 = param1Int;
    }
    
    public void setTeam_name1(String param1String) {
      this.team_name1 = param1String;
    }
    
    public void setTeam_name2(String param1String) {
      this.team_name2 = param1String;
    }
    
    public void setTeam_url1(String param1String) {
      this.team_url1 = param1String;
    }
    
    public void setTeam_url2(String param1String) {
      this.team_url2 = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ScheduleBean{team_url2='");
      stringBuilder.append(this.team_url2);
      stringBuilder.append('\'');
      stringBuilder.append(", team_url1='");
      stringBuilder.append(this.team_url1);
      stringBuilder.append('\'');
      stringBuilder.append(", name='");
      stringBuilder.append(this.name);
      stringBuilder.append('\'');
      stringBuilder.append(", id=");
      stringBuilder.append(this.id);
      stringBuilder.append(", state=");
      stringBuilder.append(this.state);
      stringBuilder.append(", team_name1='");
      stringBuilder.append(this.team_name1);
      stringBuilder.append('\'');
      stringBuilder.append(", teamId1=");
      stringBuilder.append(this.teamId1);
      stringBuilder.append(", team_name2='");
      stringBuilder.append(this.team_name2);
      stringBuilder.append('\'');
      stringBuilder.append(", teamId2=");
      stringBuilder.append(this.teamId2);
      stringBuilder.append(", play_time='");
      stringBuilder.append(this.play_time);
      stringBuilder.append('\'');
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
  
  public static class ScheduleLiveBean implements Serializable {
    private int id;
    
    private String name;
    
    private String play_time;
    
    private int state;
    
    private int teamId1;
    
    private int teamId2;
    
    private String team_name1;
    
    private String team_name2;
    
    private String team_url1;
    
    private String team_url2;
    
    public int getId() {
      return this.id;
    }
    
    public String getName() {
      return this.name;
    }
    
    public String getPlay_time() {
      return this.play_time;
    }
    
    public int getState() {
      return this.state;
    }
    
    public int getTeamId1() {
      return this.teamId1;
    }
    
    public int getTeamId2() {
      return this.teamId2;
    }
    
    public String getTeam_name1() {
      return this.team_name1;
    }
    
    public String getTeam_name2() {
      return this.team_name2;
    }
    
    public String getTeam_url1() {
      return this.team_url1;
    }
    
    public String getTeam_url2() {
      return this.team_url2;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setName(String param1String) {
      this.name = param1String;
    }
    
    public void setPlay_time(String param1String) {
      this.play_time = param1String;
    }
    
    public void setState(int param1Int) {
      this.state = param1Int;
    }
    
    public void setTeamId1(int param1Int) {
      this.teamId1 = param1Int;
    }
    
    public void setTeamId2(int param1Int) {
      this.teamId2 = param1Int;
    }
    
    public void setTeam_name1(String param1String) {
      this.team_name1 = param1String;
    }
    
    public void setTeam_name2(String param1String) {
      this.team_name2 = param1String;
    }
    
    public void setTeam_url1(String param1String) {
      this.team_url1 = param1String;
    }
    
    public void setTeam_url2(String param1String) {
      this.team_url2 = param1String;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ScheduleLiveBean{team_url2='");
      stringBuilder.append(this.team_url2);
      stringBuilder.append('\'');
      stringBuilder.append(", team_url1='");
      stringBuilder.append(this.team_url1);
      stringBuilder.append('\'');
      stringBuilder.append(", name='");
      stringBuilder.append(this.name);
      stringBuilder.append('\'');
      stringBuilder.append(", id=");
      stringBuilder.append(this.id);
      stringBuilder.append(", state=");
      stringBuilder.append(this.state);
      stringBuilder.append(", team_name1='");
      stringBuilder.append(this.team_name1);
      stringBuilder.append('\'');
      stringBuilder.append(", teamId1=");
      stringBuilder.append(this.teamId1);
      stringBuilder.append(", team_name2='");
      stringBuilder.append(this.team_name2);
      stringBuilder.append('\'');
      stringBuilder.append(", teamId2=");
      stringBuilder.append(this.teamId2);
      stringBuilder.append(", play_time='");
      stringBuilder.append(this.play_time);
      stringBuilder.append('\'');
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
}
