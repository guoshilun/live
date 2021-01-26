package com.jk.jkproject.utils;

public class CodeUtils {
  public static final CodeUtils ACCOUNT_IS_NULL;
  
  public static final CodeUtils AMOUNT_IS_NULL;
  
  public static final CodeUtils ANCHOR_NOT_APPLY;
  
  public static final CodeUtils ARAMETER_IS_NULL;
  
  public static final CodeUtils BANK_CARD_NUMBER_IS_NULL;
  
  public static final CodeUtils CAN_NOT_DISBAND_TEAM_KICKED_OUT_ALL_MEMBERS;
  
  public static final CodeUtils CAPTAIN_CANNOT_QUIT_TEAM;
  
  public static final CodeUtils CARD_ADDRESS_IS_NULL;
  
  public static final CodeUtils CARD_HOLDER_IS_NULL;
  
  public static final CodeUtils CARD_NAME_IS_NULL;
  
  public static final CodeUtils CLAN_DOESN_EXIST;
  
  public static final CodeUtils CLOSE_PLAY_IS_ERROR;
  
  public static final CodeUtils CODE_ERROR;
  
  public static final CodeUtils CODE_IS_NULL;
  
  public static final CodeUtils CODE_OFTEN;
  
  public static final CodeUtils CODE_PAST;
  
  public static final CodeUtils CONTRACT_EXTENSION_ERROR;
  
  public static final CodeUtils CONTRACT_TERMINATION_ERROR;
  
  public static final CodeUtils COVER_FOLLOW_UID_IS_NULL;
  
  public static final CodeUtils DISBAND_TEAM_ERROR;
  
  public static final CodeUtils EXIT_CLAN_ERROR;
  
  public static final CodeUtils EX_MSG_IS_NULL;
  
  public static final CodeUtils FAILED_TO_CLEAR_CLAN_MEMBERS;
  
  public static final CodeUtils FOLLOW_IS_ERROR;
  
  public static final CodeUtils GET_TEAM_INFORMATION_ABNORMAL;
  
  public static final CodeUtils HAVE_A_TEAM;
  
  public static final CodeUtils HAVE_JOINED_THE_CLAN;
  
  public static final CodeUtils HEADURL_IS_NULL;
  
  public static final CodeUtils ID_CARD_BACK_URL_IS_NULL;
  
  public static final CodeUtils ID_CARD_IS_NULL;
  
  public static final CodeUtils ID_CARD_JUST_URL_IS_NULL;
  
  public static final CodeUtils ID_CARD_holding_URL_IS_NULL;
  
  public static final CodeUtils ID_IS_NULL;
  
  public static final CodeUtils LOGIN_TOKEN_INVALID;
  
  public static final CodeUtils MEMBER_USER_ID_IS_NULL;
  
  public static final CodeUtils MSG_IS_NULL;
  
  public static final CodeUtils NAME_IS_NULL;
  
  public static final CodeUtils NICKNAME_IS_NULL;
  
  public static final CodeUtils NOT_ANCHOR_ASK;
  
  public static final CodeUtils NOT_CAPTAIN_CAN_OPERATE;
  
  public static final CodeUtils NOT_CAPTAIN_VICE_CAPTAIN_CAN_NOT_OPERATE;
  
  public static final CodeUtils NOT_JOINING_TEAM;
  
  public static final CodeUtils NOT_REPEAT_APPLY_FOR_CREATION_TEAM;
  
  public static final CodeUtils NOT_REPEAT_APPLY_FOR_JOIN_TEAM;
  
  public static final CodeUtils NOT_TEAM_BOSS;
  
  public static final CodeUtils NO_FOLLOW_OR_UNFOLLOW_ONESELF;
  
  public static final CodeUtils OPERATION_ERROR_OTHER_MANAGEMENT_AND_HANDLING;
  
  public static final CodeUtils PARAM_IS_ERROR;
  
  public static final CodeUtils PASSWOLD_IS_ERROR;
  
  public static final CodeUtils PASSWORD_IS_NULL;
  
  public static final CodeUtils PAS_IS_NULL;
  
  public static final CodeUtils PHONE_FORMAT_IS_ERROT;
  
  public static final CodeUtils PHONE_IS_NULL;
  
  public static final CodeUtils PLEASE_DO_NOT_REPEAT_FOLLOWING;
  
  public static final CodeUtils REGISTER_IS_ERROR;
  
  public static final CodeUtils REGIST_NUMBER_REGISTED;
  
  public static final CodeUtils REGIST_PSW_NOT_EQUALS;
  
  public static final CodeUtils REQUEST_DOES_NOT_EXIST = new CodeUtils(1, "请求类型不存在");
  
  public static final CodeUtils ROLE_HAS_BEEN_SET_SUCCESS_DO_NOT_REPEAT_SETTINGS;
  
  public static final CodeUtils ROOMID_IS_NULL;
  
  public static final CodeUtils ROOM_IS_NULL;
  
  public static final CodeUtils SERVER_CODE;
  
  public static final CodeUtils SERVER_NOT_AVAILABLE = new CodeUtils(2, "没有可用的服务器");
  
  public static final CodeUtils STATE_IS_NULL;
  
  public static final CodeUtils STATUS_IS_NULL;
  
  public static final CodeUtils TEAM_CREATION_ERROR;
  
  public static final CodeUtils TEAM_GRADE_IS_NULL;
  
  public static final CodeUtils TEAM_ID_IS_NULL;
  
  public static final CodeUtils TEAM_JOIN_ERROR;
  
  public static final CodeUtils TEAM_MSG_IS_NULL;
  
  public static final CodeUtils TEAM_NAME_IS_NULL;
  
  public static final CodeUtils TEAM_URL_IS_NULL;
  
  public static final CodeUtils THE_POSITION_OF_CAPTAIN_CANNOT_BE_CHANGED;
  
  public static final CodeUtils THE_REVIEW_HAS_BEEN_CANCELLED;
  
  public static final CodeUtils TOKEN_FAILURE;
  
  public static final CodeUtils TOKEN_IS_NULL;
  
  public static final CodeUtils TYPE_ID_ERROR;
  
  public static final CodeUtils TYPE_IS_NULL;
  
  public static final CodeUtils UNFOLLOW_IS_ERROR;
  
  public static final CodeUtils UPDATE_PASSWORD_IS_ERROR;
  
  public static final CodeUtils UPDATE_STATE_ERROR;
  
  public static final CodeUtils UPDATE_TEAM_MESSAGE_ERROR;
  
  public static final CodeUtils USERID_IS_NULL;
  
  public static final CodeUtils USER_IS_NO_LONGER_WITH_TEAM;
  
  public static final CodeUtils USER_IS_NULL;
  
  public static final CodeUtils USER_TEAM_ENTRY_STATUS_ERROR;
  
  public static final CodeUtils USER_TYPE;
  
  public static final CodeUtils VISITORS_USER_TYPE;
  
  private int code;
  
  private String msg;
  
  static {
    PARAM_IS_ERROR = new CodeUtils(3, "参数错误");
    SERVER_CODE = new CodeUtils(100, "手机验证码平台异常");
    USER_TYPE = new CodeUtils(500, "数据库用户");
    VISITORS_USER_TYPE = new CodeUtils(501, "游客用户");
    NOT_ANCHOR_ASK = new CodeUtils(502, "用户未实名");
    ARAMETER_IS_NULL = new CodeUtils(9000, "参数为空");
    TYPE_IS_NULL = new CodeUtils(9001, "类型为空");
    CODE_IS_NULL = new CodeUtils(9002, "验证码为空");
    PHONE_IS_NULL = new CodeUtils(9003, "手机号码为空");
    ACCOUNT_IS_NULL = new CodeUtils(9004, "账号为空");
    PASSWORD_IS_NULL = new CodeUtils(9005, "密码为空");
    ID_IS_NULL = new CodeUtils(9006, "id为空");
    USERID_IS_NULL = new CodeUtils(9007, "userId为空");
    NICKNAME_IS_NULL = new CodeUtils(9009, "昵称为空");
    HEADURL_IS_NULL = new CodeUtils(9010, "头像为空");
    STATE_IS_NULL = new CodeUtils(9011, "状态为空");
    STATUS_IS_NULL = new CodeUtils(9012, "状态为空");
    AMOUNT_IS_NULL = new CodeUtils(9013, "金额为空");
    BANK_CARD_NUMBER_IS_NULL = new CodeUtils(9015, "银行卡号为空");
    CARD_HOLDER_IS_NULL = new CodeUtils(9016, "持卡人姓名为空");
    CARD_NAME_IS_NULL = new CodeUtils(9017, "银行卡类型为空");
    CARD_ADDRESS_IS_NULL = new CodeUtils(9018, "开户行地址为空");
    NAME_IS_NULL = new CodeUtils(9019, "姓名为空");
    ID_CARD_IS_NULL = new CodeUtils(9020, "身份证号为空");
    ID_CARD_JUST_URL_IS_NULL = new CodeUtils(9021, "身份证正面图为空");
    ID_CARD_BACK_URL_IS_NULL = new CodeUtils(9022, "身份证备面图为空");
    ID_CARD_holding_URL_IS_NULL = new CodeUtils(9023, "身份证手持图为空");
    TEAM_ID_IS_NULL = new CodeUtils(9024, "战队ID为空");
    EX_MSG_IS_NULL = new CodeUtils(9025, "备注为空");
    TEAM_GRADE_IS_NULL = new CodeUtils(9026, "战队角色为空");
    TEAM_NAME_IS_NULL = new CodeUtils(9027, "战队名称为空");
    TEAM_URL_IS_NULL = new CodeUtils(9028, "战队头像为空");
    TEAM_MSG_IS_NULL = new CodeUtils(9029, "战队简介为空");
    MEMBER_USER_ID_IS_NULL = new CodeUtils(9030, "任命userId为空");
    COVER_FOLLOW_UID_IS_NULL = new CodeUtils(9031, "关注目前userId为空");
    ROOMID_IS_NULL = new CodeUtils(9032, "roomId为空");
    PHONE_FORMAT_IS_ERROT = new CodeUtils(10000, "手机号码格式有误");
    CODE_PAST = new CodeUtils(10001, "验证码已过期");
    CODE_ERROR = new CodeUtils(10002, "验证码错误");
    REGIST_NUMBER_REGISTED = new CodeUtils(10003, "此号码已注册过");
    REGIST_PSW_NOT_EQUALS = new CodeUtils(10004, "二次密码输入不一致");
    CODE_OFTEN = new CodeUtils(10005, "操作频繁");
    REGISTER_IS_ERROR = new CodeUtils(10006, "注册失败");
    USER_IS_NULL = new CodeUtils(10007, "未找到该用户");
    PASSWOLD_IS_ERROR = new CodeUtils(10008, "密码错误");
    TOKEN_IS_NULL = new CodeUtils(10009, "TOKEN为空");
    LOGIN_TOKEN_INVALID = new CodeUtils(10010, "TOKEN解析失败");
    TOKEN_FAILURE = new CodeUtils(10011, "TOKEN过期失效/不存在");
    UPDATE_PASSWORD_IS_ERROR = new CodeUtils(10012, "修改密码失败");
    ROOM_IS_NULL = new CodeUtils(10013, "直播间不存在");
    MSG_IS_NULL = new CodeUtils(10014, "消息为空");
    PAS_IS_NULL = new CodeUtils(10015, "未设置密码");
    ANCHOR_NOT_APPLY = new CodeUtils(10016, "未申请为主播");
    CLOSE_PLAY_IS_ERROR = new CodeUtils(10017, "下播失败");
    UNFOLLOW_IS_ERROR = new CodeUtils(10018, "取消关注错误");
    FOLLOW_IS_ERROR = new CodeUtils(10019, "关注错误");
    NO_FOLLOW_OR_UNFOLLOW_ONESELF = new CodeUtils(10020, "不能关注或取消关注自己");
    PLEASE_DO_NOT_REPEAT_FOLLOWING = new CodeUtils(10021, "已经关注请勿重复关注");
    NOT_JOINING_TEAM = new CodeUtils(11000, "没加入战队");
    HAVE_JOINED_THE_CLAN = new CodeUtils(11001, "已经加入战队");
    NOT_TEAM_BOSS = new CodeUtils(11002, "不是队长不可操作");
    USER_IS_NO_LONGER_WITH_TEAM = new CodeUtils(11003, "该队员已经离开了战队");
    ROLE_HAS_BEEN_SET_SUCCESS_DO_NOT_REPEAT_SETTINGS = new CodeUtils(11004, "角色已设置成功，请勿重复设置");
    NOT_REPEAT_APPLY_FOR_CREATION_TEAM = new CodeUtils(11005, "请勿重复申请创建战队");
    NOT_REPEAT_APPLY_FOR_JOIN_TEAM = new CodeUtils(11006, "请勿重复申请加入战队");
    TEAM_CREATION_ERROR = new CodeUtils(11007, "战队创建错误");
    TEAM_JOIN_ERROR = new CodeUtils(11008, "战队加入错误");
    UPDATE_STATE_ERROR = new CodeUtils(11009, "状态修改错误");
    HAVE_A_TEAM = new CodeUtils(11010, "请退出战队后,再提交申请");
    OPERATION_ERROR_OTHER_MANAGEMENT_AND_HANDLING = new CodeUtils(11011, "操作失败,其他管理已经处理");
    CONTRACT_TERMINATION_ERROR = new CodeUtils(11012, "解约失败");
    CONTRACT_EXTENSION_ERROR = new CodeUtils(11013, "续约失败");
    NOT_CAPTAIN_CAN_OPERATE = new CodeUtils(11014, "不是队长不可以操作");
    NOT_CAPTAIN_VICE_CAPTAIN_CAN_NOT_OPERATE = new CodeUtils(11015, "不是队长副队长不可以操作");
    USER_TEAM_ENTRY_STATUS_ERROR = new CodeUtils(11016, "用户战队加入状态错误");
    CLAN_DOESN_EXIST = new CodeUtils(11017, "战队不存在");
    THE_REVIEW_HAS_BEEN_CANCELLED = new CodeUtils(11018, "已经撤销审核");
    GET_TEAM_INFORMATION_ABNORMAL = new CodeUtils(11019, "获取战队信息异常");
    CAN_NOT_DISBAND_TEAM_KICKED_OUT_ALL_MEMBERS = new CodeUtils(11020, "踢出所有成员后,在解散战队");
    FAILED_TO_CLEAR_CLAN_MEMBERS = new CodeUtils(11021, "清空战队成员失败");
    DISBAND_TEAM_ERROR = new CodeUtils(11022, "解散战队错误");
    UPDATE_TEAM_MESSAGE_ERROR = new CodeUtils(11023, "修改战队信息错误");
    THE_POSITION_OF_CAPTAIN_CANNOT_BE_CHANGED = new CodeUtils(11024, "不可以更改队长职务");
    CAPTAIN_CANNOT_QUIT_TEAM = new CodeUtils(11025, "队长不能退出战队");
    EXIT_CLAN_ERROR = new CodeUtils(11026, "退出战队错误");
    TYPE_ID_ERROR = new CodeUtils(20000, "类型错误");
  }
  
  public CodeUtils(int paramInt, String paramString) {
    this.code = paramInt;
    this.msg = paramString;
  }
}
