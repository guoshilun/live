package com.jk.jkproject.net.im.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author : zhaotun
 * @version : 1.0
 * @filename : DBConstants.java
 * @description : 数据库相关常量：数据库名称，数据库版本号，数据库表名称，操作数据库的uri，数据库字段名称等
 */
public class DBConstants {

    private DBConstants() {

    }

    /**
     * 数据库名称 : 表情
     */
    public static final String DB_NAME_EMOTICON = "hy_emoticon.db";
    /**
     * 数据库版本 : 表情
     */
    public static final int DB_VERSION_EMOTICON = 1;

    /**
     * 表情历史记录表名
     */
    public static final String TABLE_EMOTICON_HISTORY = "emoticon_history";

    /**
     * 表情列表记录表名
     */
    public static final String TABLE_EMOTICON_LIST = "emoticon_list";

    public static final String AUTHORITY = "com.firehot.cn.im.db.EmoticonProvider";

    private static final String baseUri = "content://" + AUTHORITY + "/";
    private static final String baseUriEmoHistory = baseUri + TABLE_EMOTICON_HISTORY + "/";
    private static final String baseUriEmoList = baseUri + TABLE_EMOTICON_LIST + "/";


    public static final String OPT_INSERT = "insert";
    public static final String OPT_UPDATE = "update";
    public static final String OPT_DELETE = "delete";
    public static final String OPT_DELETE_ID = "delete/#";


    /**
     * 表情历史  插入记录的uri
     */
    public static final Uri EMOTICON_HISTORY_INSERT_URI = Uri.parse(baseUriEmoHistory + OPT_INSERT);
    /**
     * 表情历史  查询所有记录的uri
     */
    public static final Uri EMOTICON_HISTORY_QUERY_ALL_URI = Uri.parse(baseUriEmoHistory);
    /**
     * 表情历史  更新记录的uri
     */
    public static final Uri EMOTICON_HISTORY_UPDATE_URI = Uri.parse(baseUriEmoHistory + OPT_UPDATE);
    /**
     * 表情历史  根据记录id删除记录的uri
     */
    public static final Uri EMOTICON_HISTORY_DELETE_BY_ID_URI = Uri.parse(baseUriEmoHistory + OPT_DELETE_ID);
    /**
     * 表情历史  删除记录uri
     */
    public static final Uri EMOTICON_HISTORY_DELETE_URI = Uri.parse(baseUriEmoHistory + OPT_DELETE);


    /**
     * 表情包列表  插入记录的uri
     */
    public static final Uri EMOTICON_LIST_INSERT_URI = Uri.parse(baseUriEmoList + OPT_INSERT);
    /**
     * 表情包列表  查询所有记录的uri
     */
    public static final Uri EMOTICON_LIST_QUERY_ALL_URI = Uri.parse(baseUriEmoList);
    /**
     * 表情包列表  更新记录的uri
     */
    public static final Uri EMOTICON_LIST_UPDATE_URI = Uri.parse(baseUriEmoList + OPT_UPDATE);
    /**
     * 表情包列表  根据记录id删除记录的uri
     */
    public static final Uri EMOTICON_LIST_DELETE_BY_ID_URI = Uri.parse(baseUriEmoList + OPT_DELETE_ID);
    /**
     * 表情包列表  删除记录uri
     */
    public static final Uri EMOTICON_LIST_DELETE_URI = Uri.parse(baseUriEmoList + OPT_DELETE);


    public static final class Impl implements BaseColumns {
        private Impl() {
        }

        /**
         * 用户初见号
         */
        public static final String COLUMN_UID = "uid";

        /**
         * 表情包id
         */
        public static final String COLUMN_EPKG_ID = "epkg_id";

        /**
         * 表情包名称
         */
        public static final String COLUMN_EPKG_TITLE = "epkg_title";

        /**
         * 表情包标识package_name[tag]
         */
        public static final String COLUMN_EPKG_PKG_NAME = "epkg_pkg_name";

        /**
         * 表情包列表图标url
         */
        public static final String COLUMN_EPKG_DOWNLOAD_ICON_URL = "epkg_download_icon_url";

        /**
         * 表情包详情中顶部banner图url
         */
        public static final String COLUMN_EPKG_BANNER = "epkg_banner";

        /**
         * 表情包大小
         */
        public static final String COLUMN_EPKG_SPACE_USAGE = "epkg_space_usage";

        /**
         * 表情包描述
         */
        public static final String COLUMN_EPKG_DESCRIPTION = "epkg_description";

        /**
         * 表情包价格
         */
        public static final String COLUMN_EPKG_PRICE = "epkg_price";

        /**
         * 表情包下载地址
         */
        public static final String COLUMN_EPKG_URL = "epkg_url";

        /**
         * 表情包类别
         */
        public static final String COLUMN_EPKG_MODEL = "epkg_model";

        /**
         * 表情包有效时间
         */
        public static final String COLUMN_EPKG_ACTIVE_TIME = "epkg_active_time";

        /**
         * 表情包过期时间
         */
        public static final String COLUMN_EPKG_INVALID_TIME = "epkg_invalid_time";

        /**
         * 表情状态
         */
        public static final String COLUMN_STATUS = "status";


        /**
         * 表情id
         */
        public static final String COLUMN_EMO_ID = "emo_id";

        /**
         * 表情xml中对应name
         */
        public static final String COLUMN_EMO_NAME = "emo_name";

        /**
         * 表情xml中对应file
         */
        public static final String COLUMN_EMO_FILE = "emo_file";

        /**
         * 表情gif的url
         */
        public static final String COLUMN_EMO_GIF_URL = "emo_gif_url";

        /**
         * 表情png的url
         */
        public static final String COLUMN_EMO_THUMB_URL = "emo_thumb_url";

        /**
         * 表情本地url
         */
        public static final String COLUMN_LOCAL_URL = "local_url";


        /**
         * 时间，用来排序的
         */
        public static final String COLUMN_DATETIME = "datetime";


        /**
         * 表情包还未下载
         */
        public static final int EPKG_DOWN_STATUS_NOT = 0;

        /**
         * 表情包已下载成功
         */
        public static final int EPKG_DOWN_STATUS_SUCCESS = 1;

        /**
         * 表情包已下载失败
         */
        public static final int EPKG_DOWN_STATUS_FAILED = 2;

        /**
         * 表情包正在下载中
         */
        public static final int EPKG_DOWN_STATUS_LOADING = 3;

        /**
         * 表情包已删除
         */
        public static final int EPKG_STATUS_DELETED = 4;

    }


}
