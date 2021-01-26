package com.jk.jkproject.net.im.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.ui.entity.EmoticonPkgInfo;
import com.nostra13.universalimageloader.utils.L;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class EmoticonDao {

    private EmoticonDBOpenHelper helper;
    private Context mContext;

    public EmoticonDao(Context context) {
        helper = EmoticonDBOpenHelper.get(context);
        mContext = context;
    }

    public void delEmoHistoryOver10(String uid) {
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_HISTORY, null, DBConstants.Impl.COLUMN_UID + "=?", new String[]{uid}, null, null, DBConstants.Impl.COLUMN_DATETIME + " desc");
            if (cursor != null) {
                if (cursor.getCount() > 10) {
                    cursor.moveToPosition(9);
                    int index = -1;
                    while (cursor.moveToNext()) {
                        if (index == -1) {
                            index = cursor.getColumnIndex(DBConstants.Impl._ID);
                        }

                        if (index != -1) {
                            long id = cursor.getLong(index);
                            db.delete(DBConstants.TABLE_EMOTICON_HISTORY, DBConstants.Impl._ID + "=?", new String[]{String.valueOf(id)});
                        }
                    }
                }
                cursor.close();
            }
        } catch (Throwable e) {
            LogUtils.e(e.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }
    }

    /**
     * 查询已下载成功的表情包数目
     *
     * @param uid
     * @return
     * @author zhaotun
     */
    public int queryEpkgDownCount(String uid) {
        int count = 0;
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            String selection = DBConstants.Impl.COLUMN_UID + "=? and " + DBConstants.Impl.COLUMN_STATUS + "=?";
            String[] selectionArgs = new String[]{uid, String.valueOf(DBConstants.Impl.EPKG_DOWN_STATUS_SUCCESS)};
            Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_LIST, null, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                count = cursor.getCount();
                cursor.close();
            }
            cursor = null;
        } catch (Throwable e) {
            L.e(e.getMessage());
            count = 0;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return count;
    }

    /**
     * 查询表情包列表的数目
     *
     * @param uid
     * @return
     * @author zhaotun
     */
    public int queryEpkgCount(String uid) {
        int count = 0;
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_LIST, null, DBConstants.Impl.COLUMN_UID + "=?", new String[]{uid}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                count = cursor.getCount();
                cursor.close();
            }
            cursor = null;
        } catch (Throwable e) {
            L.e(e.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return count;
    }

    /**
     * 查询所有已下载的表情包的package_name[tag]
     *
     * @param uid
     * @return
     * @author zhaotun
     */
    public List<String> queryAllDownloadedEpkgNames(String uid) {
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            String[] columns = new String[]{DBConstants.Impl.COLUMN_EPKG_PKG_NAME};
            String selection = DBConstants.Impl.COLUMN_STATUS + "=? and " + DBConstants.Impl.COLUMN_UID + "=?";
            String[] selectionArgs = new String[]{String.valueOf(DBConstants.Impl.EPKG_DOWN_STATUS_SUCCESS), uid};
            Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_LIST, columns, selection, selectionArgs, null, null, DBConstants.Impl.COLUMN_DATETIME + " desc");
            while (cursor != null && cursor.moveToNext()) {
                list.add(cursor.getString(0));
            }

            if (cursor != null) {
                cursor.close();
            }
            cursor = null;

        } catch (Throwable e) {
            L.e(e.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return list;
    }

    /**
     * 更新表情列表
     *
     * @param list
     * @param uid
     * @return
     * @author zhaotun
     */
    public boolean updateEpkgs(List<EmoticonPkgInfo> list, String uid) {
        boolean result = false;
        if (TextUtils.isEmpty(uid) || list == null || list.size() == 0) {
            return result;
        }

        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            long id = -1;
            long time = queryDownDateTime(uid, db,
                    DBConstants.Impl.COLUMN_DATETIME + " asc limit 1");
            int index = 1;
            String sql = "select " + DBConstants.Impl._ID + " from " + DBConstants.TABLE_EMOTICON_LIST + " where "
                    + DBConstants.Impl.COLUMN_UID + "=? and " + DBConstants.Impl.COLUMN_EPKG_ID + "=?";
            ContentResolver resolver = mContext.getContentResolver();
            for (EmoticonPkgInfo info : list) {
                id = -1;
                Cursor cursor = db.rawQuery(sql, new String[]{uid, info.getId()});
                if (cursor != null && cursor.moveToFirst()) {
                    id = cursor.getLong(0);
                    cursor.close();
                }

                ContentValues values = new ContentValues();
                values.put(DBConstants.Impl.COLUMN_UID, uid);
                values.put(DBConstants.Impl.COLUMN_EPKG_ID, info.getId());
                values.put(DBConstants.Impl.COLUMN_EPKG_TITLE, info.getTitle());
                values.put(DBConstants.Impl.COLUMN_EPKG_PKG_NAME, info.getPackage_name());
                values.put(DBConstants.Impl.COLUMN_EPKG_DOWNLOAD_ICON_URL, info.getDownload_icon());
                values.put(DBConstants.Impl.COLUMN_EPKG_BANNER, info.getBanner());
                values.put(DBConstants.Impl.COLUMN_EPKG_SPACE_USAGE, info.getSpace_usage());
                values.put(DBConstants.Impl.COLUMN_EPKG_DESCRIPTION, info.getDescription());
                values.put(DBConstants.Impl.COLUMN_EPKG_PRICE, info.getPrice());
                values.put(DBConstants.Impl.COLUMN_EPKG_URL, info.getUrl());
                values.put(DBConstants.Impl.COLUMN_EPKG_MODEL, info.getModel());
                values.put(DBConstants.Impl.COLUMN_EPKG_ACTIVE_TIME, info.getActive_time());
                if (id == -1) {
                    // 数据库中没有，需要入库
                    if (time == 0) {
                        values.put(DBConstants.Impl.COLUMN_DATETIME, System.currentTimeMillis());
                    } else {
                        values.put(DBConstants.Impl.COLUMN_DATETIME, String.valueOf(time - index));
                        index++;
                    }

                    resolver.insert(DBConstants.EMOTICON_LIST_INSERT_URI, values);

                    result = true;
                } else {
                    db.update(DBConstants.TABLE_EMOTICON_LIST, values, DBConstants.Impl._ID + "=?", new String[]{String.valueOf(id)});
                }
            }
        } catch (Throwable e) {
            L.e(e.getMessage());
            result = false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return result;
    }

    public boolean updateEpkgs(String uid, String _id) {
        boolean result = false;
        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(_id)) {
            return result;
        }

        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(DBConstants.Impl.COLUMN_DATETIME, System.currentTimeMillis());
            db.update(DBConstants.TABLE_EMOTICON_HISTORY, values, DBConstants.Impl._ID + "=?", new String[]{_id});
        } catch (Throwable e) {
            L.e(e.getMessage());
            result = false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return result;
    }

    /**
     * 排序
     *
     * @param list 从from到to位置上有序数据集合
     * @param from 开始位置
     * @param to   结束位置
     * @return
     * @author zhaotun
     */
    public boolean sortEmoticonPkgs(ArrayList<BasicNameValuePair> list, int from, int to) {
        boolean result = false;
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            db.beginTransaction();

            int fromId;
            long toSort;
            int size = list.size();
            if (from > to) {
                fromId = Integer.valueOf(list.get(size - 1).getName());
                toSort = Long.valueOf(list.get(0).getValue());

                for (int i = size; i > 1; i--) {
                    values.put(DBConstants.Impl.COLUMN_DATETIME, list.get(i - 1).getValue());
                    db.update(DBConstants.TABLE_EMOTICON_LIST, values, DBConstants.Impl._ID + "=?", new String[]{list.get(i - 2).getName()});
                }
            } else {
                fromId = Integer.valueOf(list.get(0).getName());
                toSort = Long.valueOf(list.get(size - 1).getValue());

                for (int i = 0; i < size - 1; i++) {
                    values.put(DBConstants.Impl.COLUMN_DATETIME, list.get(i).getValue());
                    db.update(DBConstants.TABLE_EMOTICON_LIST, values, DBConstants.Impl._ID + "=?", new String[]{list.get(i + 1).getName()});
                }
            }

            values.put(DBConstants.Impl.COLUMN_DATETIME, String.valueOf(toSort));
            int r = mContext.getContentResolver().update(DBConstants.EMOTICON_LIST_UPDATE_URI, values, DBConstants.Impl._ID + "=?", new String[]{String.valueOf(fromId)});
            if (r > 0) {
                result = true;
            }

            db.setTransactionSuccessful();
        } catch (Throwable e) {
            L.e(e.getMessage());
            result = false;
        } finally {
            db.endTransaction();

            if (db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return result;
    }

    /**
     * 查询表情包的状态
     *
     * @param uid
     * @param epkg_id
     * @return
     * @author zhaotun
     */
    public int queryEpkgStatus(String uid, String epkg_id) {
        int status = DBConstants.Impl.EPKG_DOWN_STATUS_NOT;
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            String selection = DBConstants.Impl.COLUMN_EPKG_ID + "=? and " + DBConstants.Impl.COLUMN_UID + "=?";
            String[] selectionArgs = new String[]{epkg_id, uid};
            Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_LIST, new String[]{DBConstants.Impl.COLUMN_STATUS}, selection, selectionArgs, null, null, null);
            while (cursor != null && cursor.moveToNext()) {
                status = cursor.getInt(0);
            }

            if (cursor != null) {
                cursor.close();
            }
            cursor = null;

        } catch (Throwable e) {
            L.e(e.getMessage());
            status = DBConstants.Impl.EPKG_DOWN_STATUS_NOT;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return status;
    }

    /**
     * 查询数据库中最久已下载的表情包时间
     *
     * @param uid
     * @return
     * @author zhaotun
     */
    public long queryEpkgDownLastDateTime(String uid) {
        long time = 0;
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            time = queryDownDateTime(uid, db, DBConstants.Impl.COLUMN_DATETIME + " asc limit 1");
        } catch (Throwable e) {
            L.e(e.getMessage());
            time = 0;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return time;
    }

    /**
     * 查询数据库中最新已下载的表情包时间
     *
     * @param uid
     * @return
     * @author zhaotun
     */
    public long queryEpkgDownLatestDateTime(String uid) {
        long time = 0;
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            time = queryDownDateTime(uid, db, DBConstants.Impl.COLUMN_DATETIME + " desc limit 1");
        } catch (Throwable e) {
            L.e(e.getMessage());
            time = 0;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return time;
    }

    /**
     * 查询已下载表情包时间
     *
     * @param uid
     * @param db
     * @param orderBy
     * @return
     * @author zhaotun
     */
    private long queryDownDateTime(String uid, SQLiteDatabase db, String orderBy) {
        long time = 0;

        try {
            String selection = DBConstants.Impl.COLUMN_UID + "=? and " + DBConstants.Impl.COLUMN_STATUS + "=?";
            String[] selectionArgs = new String[]{uid, String.valueOf(DBConstants.Impl.EPKG_DOWN_STATUS_SUCCESS)};
            Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_LIST, new String[]{DBConstants.Impl.COLUMN_DATETIME}, selection, selectionArgs, null, null, orderBy);
            while (cursor != null && cursor.moveToNext()) {
                time = cursor.getLong(0);
            }

            if (cursor != null) {
                cursor.close();
            }
            cursor = null;

        } catch (Throwable e) {
            L.e(e.getMessage());
            time = 0;
        }

        return time;
    }

    /**
     * 查询当前表情包是否已经在数据库中
     *
     * @param uid
     * @param epkg_id
     * @return
     * @author zhaotun
     */
    public boolean queryEpkgExist(String uid, String epkg_id) {
        boolean flag = false;
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            String selection = DBConstants.Impl.COLUMN_UID + "=? and " + DBConstants.Impl.COLUMN_EPKG_ID + "=?";
            String[] selectionArgs = new String[]{uid, epkg_id};
            Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_LIST, new String[]{DBConstants.Impl._ID}, selection, selectionArgs, null, null, null);
            while (cursor != null && cursor.moveToNext()) {
                long id = cursor.getLong(0);
                if (id > 0) {
                    flag = true;
                }
            }

            if (cursor != null) {
                cursor.close();
            }
            cursor = null;

        } catch (Throwable e) {
            LogUtils.e(e.getMessage());
            flag = false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            db = null;
        }

        return flag;
    }

}
