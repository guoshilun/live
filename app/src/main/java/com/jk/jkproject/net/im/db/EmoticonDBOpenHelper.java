package com.jk.jkproject.net.im.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.blankj.utilcode.util.LogUtils;


/**
 * @author : zhaotun
 * @version : 1.0
 * @filename : EmoticonDBOpenHelper.java
 * @description : 创建表情数据库
 */
public class EmoticonDBOpenHelper extends SQLiteOpenHelper {

    private static EmoticonDBOpenHelper instance;

    public static EmoticonDBOpenHelper get(Context context) {
        if (instance == null) {
            synchronized (EmoticonDBOpenHelper.class) {
                if (instance == null) {
                    instance = new EmoticonDBOpenHelper(context);
                }
            }
        }

        return instance;
    }

    private EmoticonDBOpenHelper(Context context) {
        super(context, DBConstants.DB_NAME_EMOTICON, null,
                DBConstants.DB_VERSION_EMOTICON);
    }

    private EmoticonDBOpenHelper(Context context, String name,
                                 CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DBConstants.DB_VERSION_EMOTICON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createEmoticonHistoryTable(db);
        createEmoticonListTable(db);
    }


    private void createEmoticonHistoryTable(SQLiteDatabase db) {
        try {
            db.beginTransaction();

            db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_EMOTICON_HISTORY);
            String sql = "CREATE TABLE " + DBConstants.TABLE_EMOTICON_HISTORY + "("
                    + DBConstants.Impl._ID + " Integer PRIMARY KEY AUTOINCREMENT,"
                    + DBConstants.Impl.COLUMN_UID + " Integer,"         // 用户初见号
                    + DBConstants.Impl.COLUMN_EMO_ID + " TEXT,"         // 表情唯一标识:emoji中存的是emoji资源id；gif表情中存的是表情id
                    + DBConstants.Impl.COLUMN_EMO_NAME + " TEXT,"       // 表情包xml中对应的name字段
                    + DBConstants.Impl.COLUMN_EMO_FILE + " TEXT,"       // 表情包xml中对应的file字段
                    + DBConstants.Impl.COLUMN_EPKG_PKG_NAME + " TEXT,"  // 表情包package_name[tag]
                    + DBConstants.Impl.COLUMN_EMO_GIF_URL + " TEXT,"    // 表情gif的url；表情包xml中对应的url字段
                    + DBConstants.Impl.COLUMN_LOCAL_URL + " TEXT,"      // 表情本地存储路径
                    + DBConstants.Impl.COLUMN_DATETIME + " Long,"       // 时间，用来排序的

                    + "data1 TEXT,"     // 预留字段1
                    + "data2 TEXT,"     // 预留字段2
                    + "data3 TEXT,"     // 预留字段3
                    + "data4 TEXT,"     // 预留字段4
                    + "data5 TEXT"      // 预留字段5
                    + ")";
            db.execSQL(sql);

            db.setTransactionSuccessful();
        } catch (Throwable e) {
            LogUtils.e(e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    private void createEmoticonListTable(SQLiteDatabase db) {
        try {
            db.beginTransaction();

            db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_EMOTICON_LIST);
            String sql = "CREATE TABLE " + DBConstants.TABLE_EMOTICON_LIST + "("
                    + DBConstants.Impl._ID + " Integer PRIMARY KEY AUTOINCREMENT,"
                    + DBConstants.Impl.COLUMN_UID + " Integer,"                 // 用户初见号
                    + DBConstants.Impl.COLUMN_EPKG_ID + " TEXT,"                // 表情包id
                    + DBConstants.Impl.COLUMN_EPKG_TITLE + " TEXT,"             // 表情包名称
                    + DBConstants.Impl.COLUMN_EPKG_PKG_NAME + " TEXT,"          // 表情包package_name[tag]
                    + DBConstants.Impl.COLUMN_EPKG_DOWNLOAD_ICON_URL + " TEXT," // 表情包列表图标url
                    + DBConstants.Impl.COLUMN_EPKG_BANNER + " TEXT,"            // 表情包详情中顶部banner图url
                    + DBConstants.Impl.COLUMN_EPKG_SPACE_USAGE + " TEXT,"       // 表情包大小
                    + DBConstants.Impl.COLUMN_EPKG_DESCRIPTION + " TEXT,"       // 表情包描述
                    + DBConstants.Impl.COLUMN_EPKG_PRICE + " TEXT,"             // 表情包价格
                    + DBConstants.Impl.COLUMN_EPKG_URL + " TEXT,"               // 表情包下载地址
                    + DBConstants.Impl.COLUMN_EPKG_MODEL + " TEXT,"             // 表情包类别
                    + DBConstants.Impl.COLUMN_EPKG_ACTIVE_TIME + " TEXT,"       // 表情包有效时间
                    + DBConstants.Impl.COLUMN_EPKG_INVALID_TIME + " TEXT default -1," // 表情包过期时间

                    + DBConstants.Impl.COLUMN_STATUS + " Integer default " + DBConstants.Impl.EPKG_DOWN_STATUS_NOT + "," // 表情状态
                    + DBConstants.Impl.COLUMN_DATETIME + " Long,"               // 时间，用来排序
                    + DBConstants.Impl.COLUMN_LOCAL_URL + " TEXT,"              // 表情包本地存储路径

                    + "data1 TEXT,"     // 预留字段1
                    + "data2 TEXT,"     // 预留字段2
                    + "data3 TEXT,"     // 预留字段3
                    + "data4 TEXT,"     // 预留字段4
                    + "data5 TEXT"      // 预留字段5
                    + ")";
            db.execSQL(sql);

            db.setTransactionSuccessful();
        } catch (Throwable e) {
            LogUtils.e(e.getMessage());
        } finally {
            db.endTransaction();
        }
    }


}
