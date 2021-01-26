package com.jk.jkproject.net.im.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class EmoticonProvider extends ContentProvider {

    private static final int EMOTICON_HISTORY_INSERT = 0;
    private static final int EMOTICON_HISTORY_QUERY_ALL = 1;
    private static final int EMOTICON_HISTORY_UPDATE = 2;
    private static final int EMOTICON_HISTORY_DELETE_BY_ID = 3;
    private static final int EMOTICON_HISTORY_DELETE = 4;

    private static final int EMOTICON_LIST_INSERT = 5;
    private static final int EMOTICON_LIST_QUERY_ALL = 6;
    private static final int EMOTICON_LIST_UPDATE = 7;
    private static final int EMOTICON_LIST_DELETE_BY_ID = 8;
    private static final int EMOTICON_LIST_DELETE = 9;


    private static final UriMatcher uriMatcher;
    private EmoticonDBOpenHelper helper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_history/insert
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_HISTORY + "/" + DBConstants.OPT_INSERT, EMOTICON_HISTORY_INSERT);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_history/
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_HISTORY, EMOTICON_HISTORY_QUERY_ALL);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_history/update
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_HISTORY + "/" + DBConstants.OPT_UPDATE, EMOTICON_HISTORY_UPDATE);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_history/delete/#
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_HISTORY + "/" + DBConstants.OPT_DELETE_ID, EMOTICON_HISTORY_DELETE_BY_ID);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_history/delete
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_HISTORY + "/" + DBConstants.OPT_DELETE, EMOTICON_HISTORY_DELETE);


        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_list/insert
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_LIST + "/" + DBConstants.OPT_INSERT, EMOTICON_LIST_INSERT);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_list/
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_LIST, EMOTICON_LIST_QUERY_ALL);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_list/update
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_LIST + "/" + DBConstants.OPT_UPDATE, EMOTICON_LIST_UPDATE);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_list/delete/#
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_LIST + "/" + DBConstants.OPT_DELETE_ID, EMOTICON_LIST_DELETE_BY_ID);

        // content://com.chujian.liehuo.im.db.EmoticonProvider/emoticon_list/delete
        uriMatcher.addURI(DBConstants.AUTHORITY, DBConstants.TABLE_EMOTICON_LIST + "/" + DBConstants.OPT_DELETE, EMOTICON_LIST_DELETE);

    }

    @Override
    public boolean onCreate() {
        helper = EmoticonDBOpenHelper.get(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            switch (uriMatcher.match(uri)) {
                case EMOTICON_HISTORY_QUERY_ALL: // 查询所有历史记录
                    if (db.isOpen()) {
                        Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_HISTORY, projection, selection, selectionArgs, null, null, sortOrder);

                        if (cursor != null) {
                            cursor.setNotificationUri(getContext().getContentResolver(), DBConstants.EMOTICON_HISTORY_QUERY_ALL_URI);
                        }

                        return cursor;
                    }
                    break;
                case EMOTICON_LIST_QUERY_ALL:
                    if (db.isOpen()) {
                        Cursor cursor = db.query(DBConstants.TABLE_EMOTICON_LIST, projection, selection, selectionArgs, null, null, sortOrder);

                        if (cursor != null) {
                            cursor.setNotificationUri(getContext().getContentResolver(), DBConstants.EMOTICON_LIST_QUERY_ALL_URI);
                        }

                        return cursor;
                    }
                    break;
                default:
                    break;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            switch (uriMatcher.match(uri)) {
                case EMOTICON_HISTORY_INSERT:    // 插入或更新表情历史记录
                    if (db.isOpen()) {
                        long id = -1;

                        String sql = "select " + DBConstants.Impl._ID + " from " + DBConstants.TABLE_EMOTICON_HISTORY + " where " + DBConstants.Impl.COLUMN_UID + "=? and " + DBConstants.Impl.COLUMN_EMO_ID + "=? and " + DBConstants.Impl.COLUMN_EPKG_PKG_NAME + "=?";
                        Cursor cursor = db.rawQuery(sql, new String[]{values.getAsString(DBConstants.Impl.COLUMN_UID), values.getAsString(DBConstants.Impl.COLUMN_EMO_ID), values.getAsString(DBConstants.Impl.COLUMN_EPKG_PKG_NAME)});
                        if (cursor != null && cursor.moveToFirst()) {
                            id = cursor.getLong(0);
                            cursor.close();
                        }

                        if (id < 1) {
                            // 插入
                            id = db.insert(DBConstants.TABLE_EMOTICON_HISTORY, null, values);
                        } else {
                            // 更新
                            id = db.update(DBConstants.TABLE_EMOTICON_HISTORY, values, DBConstants.Impl._ID + "=?", new String[]{String.valueOf(id)});
                        }

                        if (id > 0) {
                            getContext().getContentResolver().notifyChange(DBConstants.EMOTICON_HISTORY_QUERY_ALL_URI, null);
                        }

                        return ContentUris.withAppendedId(uri, id);
                    }
                    break;
                case EMOTICON_LIST_INSERT:    // 插入或更新表情包记录
                    if (db.isOpen()) {
                        long id = -1;

                        String sql = "select " + DBConstants.Impl._ID + " from " + DBConstants.TABLE_EMOTICON_LIST + " where " + DBConstants.Impl.COLUMN_UID + "=? and " + DBConstants.Impl.COLUMN_EPKG_ID + "=?";
                        Cursor cursor = db.rawQuery(sql, new String[]{values.getAsString(DBConstants.Impl.COLUMN_UID), values.getAsString(DBConstants.Impl.COLUMN_EPKG_ID)});
                        if (cursor != null && cursor.moveToFirst()) {
                            id = cursor.getLong(0);
                            cursor.close();
                        }

                        if (id < 1) {
                            // 插入
                            id = db.insert(DBConstants.TABLE_EMOTICON_LIST, null, values);
                        } else {
                            // 更新
                            id = db.update(DBConstants.TABLE_EMOTICON_LIST, values, DBConstants.Impl._ID + "=?", new String[]{String.valueOf(id)});
                        }

                        if (id > 0) {
                            getContext().getContentResolver().notifyChange(DBConstants.EMOTICON_LIST_QUERY_ALL_URI, null);
                        }

                        return ContentUris.withAppendedId(uri, id);
                    }
                    break;
                default:
                    break;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            switch (uriMatcher.match(uri)) {
                case EMOTICON_HISTORY_DELETE_BY_ID:    // 根据id删除历史记录
                    if (db.isOpen()) {
                        long id = -1;

                        try {
                            id = ContentUris.parseId(uri);
                        } catch (Throwable e) {
                        }

                        StringBuilder where = new StringBuilder();

                        if (id > 0) {
                            where.append(DBConstants.Impl._ID).append(" = ").append(id);
                        }

                        if (!TextUtils.isEmpty(selection)) {
                            where.append(" and ").append(selection);
                        }

                        int count = db.delete(DBConstants.TABLE_EMOTICON_HISTORY, where.toString(), selectionArgs);

                        if (count > 0) {
                            //通知数据库改变了
                            getContext().getContentResolver().notifyChange(DBConstants.EMOTICON_HISTORY_QUERY_ALL_URI, null);
                        }

                        return count;
                    }
                    break;
                case EMOTICON_HISTORY_DELETE:
                    if (db.isOpen()) {
                        int count = db.delete(DBConstants.TABLE_EMOTICON_HISTORY, selection, selectionArgs);

                        if (count > 0) {
                            //通知数据库改变了
                            getContext().getContentResolver().notifyChange(DBConstants.EMOTICON_HISTORY_QUERY_ALL_URI, null);
                        }

                        return count;
                    }
                    break;
                case EMOTICON_LIST_DELETE_BY_ID:    // 根据id删除历史记录
                    if (db.isOpen()) {
                        long id = -1;

                        try {
                            id = ContentUris.parseId(uri);
                        } catch (Throwable e) {
                        }

                        StringBuilder where = new StringBuilder();

                        if (id > 0) {
                            where.append(DBConstants.Impl._ID).append(" = ").append(id);
                        }

                        if (!TextUtils.isEmpty(selection)) {
                            where.append(" and ").append(selection);
                        }

                        int count = db.delete(DBConstants.TABLE_EMOTICON_LIST, where.toString(), selectionArgs);

                        if (count > 0) {
                            //通知数据库改变了
                            getContext().getContentResolver().notifyChange(DBConstants.EMOTICON_LIST_QUERY_ALL_URI, null);
                        }

                        return count;
                    }
                    break;
                case EMOTICON_LIST_DELETE:
                    if (db.isOpen()) {
                        int count = db.delete(DBConstants.TABLE_EMOTICON_LIST, selection, selectionArgs);

                        if (count > 0) {
                            //通知数据库改变了
                            getContext().getContentResolver().notifyChange(DBConstants.EMOTICON_LIST_QUERY_ALL_URI, null);
                        }

                        return count;
                    }
                    break;
                default:
                    break;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            switch (uriMatcher.match(uri)) {
                case EMOTICON_HISTORY_UPDATE:    // 更新表情历史记录
                    if (db.isOpen()) {
                        int rows = db.update(DBConstants.TABLE_EMOTICON_HISTORY, values, selection, selectionArgs);

                        if (rows > 0) {
                            // 通知数据库改变了
                            getContext().getContentResolver().notifyChange(DBConstants.EMOTICON_HISTORY_QUERY_ALL_URI, null);
                        }

                        return rows;
                    }
                    break;
                case EMOTICON_LIST_UPDATE:    // 更新表情包记录
                    if (db.isOpen()) {
                        int rows = db.update(DBConstants.TABLE_EMOTICON_LIST, values, selection, selectionArgs);

                        // && values.containsKey(DBConstants.Impl.COLUMN_DATETIME)
                        if (rows > 0) {
                            // 通知数据库改变了
                            getContext().getContentResolver().notifyChange(DBConstants.EMOTICON_LIST_QUERY_ALL_URI, null);
                        }

                        return rows;
                    }
                    break;
                default:
                    break;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return 0;
    }


}
