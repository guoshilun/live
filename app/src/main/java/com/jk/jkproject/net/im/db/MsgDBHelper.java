package com.jk.jkproject.net.im.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jk.jkproject.net.im.info.General;
import com.jk.jkproject.net.im.info.MessageEntity;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.SPUtils;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MsgDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String TABLE_MSG = "msg";
    private static final String DB = "msg.db";
    private static MsgDBHelper inst;
    private static Context context;

    public static synchronized MsgDBHelper instance(Context ctx) {
        if (inst == null) {
            inst = new MsgDBHelper(ctx, DB, null, DB_VERSION);
            context = ctx;
        }
        return inst;
    }

    public MsgDBHelper(Context context, String name, CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
    }


    private void createMsgTable(SQLiteDatabase db) {

        String sql = "create table if not exists msg ("
                + "id integer primary key autoincrement,"
                + "owner_id varchar(50) not null,"
                + "msg_id varchar(50),"
                + "sms_id long ,"
                + "sms_key long ,"
                + "chat_type int not null," // 消息类型 群组 正常 系统
                + "from_id long not null,"
                + "to_id long not null,"
                + "session_id long not null,"
                + "time long not null,"
                + "text_type int not null,"// 文本 语音 图片 位置
                + "status int default 1 not null,"
                + "url text,"//  image:thumb_url; 缩略图url // voice:voice_url; map:map_url
                + "path text,"// image:thumb_path;本地缩略图路径 // voice:voice_path;
                + "url2 text,"// image:image_url; 大图url
                + "path2 text,"//image:image_path;大图本地路径

                + "string_reserved1 text,"  //system tip系统提示语
                + "string_reserved2 text,"  //bubble 气泡id
                + "string_reserved3 text,"
                + "string_reserved4 text,"
                + "int_reserved1 int default 0," // voice:voice_time[语音时长];// image:progress[上传图片进度]
                + "int_reserved2 int default 0," // voice:voice_read[0代表未下载，1表示正在下载，2代表未读,3已读,4发送];// image[下载状态0未下载，1正在下载，// 2已下载成功4发送]
                + "int_reserved3 int default 0,"
                + "int_reserved4 int default 0,"
                + "display_type int default 0,"// 消息显示类型[0是正常气泡消息,1是系统浮框消息]
                + "content text)";

        db.execSQL(sql);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createMsgTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MSG);
        onCreate(db);
    }

    public synchronized void deleteMsg(String msgId) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }
        String sql = String.format("delete from msg where msg_id == '%s'",
                msgId);
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }


    public synchronized void deleteMsgBykey(long sms_key) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }
        String sql = String.format("delete from msg where sms_key == '%s'",
                sms_key);
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }


    /**
     * 删除和该联系人的所有聊天记录
     *
     * @param sessionId
     */
    public synchronized void deleteMsg(long sessionId) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }
        String sql = String.format("delete from msg where session_id == '%s'",
                sessionId);
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    public synchronized void saveMsg(MessageEntity msg) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }
        String uid = SPUtils.Impl.getUid();
        String sqlBase = "insert into msg (owner_id, msg_id, sms_id, sms_key, chat_type, from_id, to_id, session_id, time, text_type, status, display_type, content, string_reserved1, string_reserved2";
        if (msg.getTextType() == General.ETextType.ETT_IMAGE) {
            String sql = sqlBase
                    + ", path, path2, url, url2, int_reserved2) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // 20
            try {
                db.execSQL(
                        sql,
                        new Object[]{uid, msg.getMsgID(), msg.getSmsID(),
                                msg.getSmsKey(), msg.getChatType().getNumber(),
                                msg.getFromID(), msg.getToID(),
                                msg.getSessionId(), msg.getTime(),
                                msg.getTextType().getNumber(),
                                msg.getMessageState(), msg.getDisplayType(),
                                msg.getText(), "", msg.getBubble(),
                                msg.getThumbPath(),
                                msg.getImagePath(), msg.getThumbUrl(),
                                msg.getImageUrl(), msg.getMediaDownloadStatus()});
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
        } else if (msg.getTextType() == General.ETextType.ETT_YUYIN) {
            String sql = sqlBase
                    + ", path, url, int_reserved1, int_reserved2) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // 19
            try {
                db.execSQL(
                        sql,
                        new Object[]{uid, msg.getMsgID(), msg.getSmsID(),
                                msg.getSmsKey(), msg.getChatType().getNumber(),
                                msg.getFromID(), msg.getToID(),
                                msg.getSessionId(), msg.getTime(),
                                msg.getTextType().getNumber(),
                                msg.getMessageState(), msg.getDisplayType(),
                                msg.getText(), "", msg.getBubble(), msg.getVoicePath(),
                                msg.getVoiceUrl(), msg.getVoiceTime(),
                                msg.getMediaDownloadStatus()});
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
        } else {
            String sql = sqlBase + ") values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";// 15
            try {
                db.execSQL(
                        sql,
                        new Object[]{uid, msg.getMsgID(), msg.getSmsID(),
                                msg.getSmsKey(), msg.getChatType().getNumber(),
                                msg.getFromID(), msg.getToID(),
                                msg.getSessionId(), msg.getTime(),
                                msg.getTextType().getNumber(),
                                msg.getMessageState(), msg.getDisplayType(),
                                msg.getText(), msg.getSystemHint(), msg.getBubble()});
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
        }
    }


    /**
     * 如果是 发送瞬间照片，则把图片的消息格式改成瞬间图片格式
     *
     * @param msgId
     * @param chat_type
     * @param text_type
     */
    public synchronized void updateImage2Snap(String msgId, String content, int chat_type, int text_type, int display_type) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }
        String sql = "update msg set content=?,chat_type=?,text_type=?,display_type=? where msg_id=?";
        try {
            db.execSQL(sql, new Object[]{content, chat_type, text_type, display_type, msgId});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }


    /**
     * @param msgId
     * @param element
     * @param elementValue
     * @author xuxs
     */
    public synchronized void updateElementByMsgId(String msgId, String element,
                                                  String elementValue) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }

        String sql = "update msg set " + element + "=? where msg_id=?";

        try {
            db.execSQL(sql, new Object[]{elementValue, msgId});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    /**
     * @param msgId
     * @param element
     * @param elementValue
     * @author xuxs
     */
    public synchronized void updateElementByMsgId(String msgId, String element,
                                                  int elementValue) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }

        String sql = "update msg set " + element + "=? where msg_id=?";

        try {
            db.execSQL(sql, new Object[]{elementValue, msgId});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    /**
     * 根据msgid来更新已发送
     *
     * @param msg
     */
    public synchronized void updateMsgStatusSend(MessageEntity msg) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }

        // String sql =
        // "update msg set status=? ,time=?,sms_key=? where msg_id=?";
        String sql = "update msg set status=? ,sms_key=? where msg_id=?";

        try {
            db.execSQL(
                    sql,
                    new Object[]{msg.getMessageState(), msg.getSmsKey(),
                            msg.getMsgID()});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    /**
     * 根据msg 中的getStateSmsKeyID 列表 来更新消息已阅读
     */
    public synchronized void updateMsgStatusRead(long from_id, long to_id) {

        // TODO暂时 一次更新所有消息已读
        SQLiteDatabase db = getWritableDatabase();
        if (db == null || to_id == 0 || from_id == 0) {
            return;
        }
        try {
            String sql = "update msg set status=? where from_id=? and to_id=? and status=? ";
            db.execSQL(sql, new Object[]{
                    Constants.MESSAGE_STATE_SUCCESSED_FINISH, from_id, to_id,
                    Constants.MESSAGE_STATE_SUCCESSED_UNREAD});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    /**
     * recv msg list unread state to read
     *
     * @param keys
     */
    public synchronized void updateMsgStatusRead(ArrayList<Long> keys) {

        SQLiteDatabase db = getWritableDatabase();
        if (db == null || keys == null || keys.size() == 0) {
            return;
        }
        try {
            String sql = "update msg set status=? where sms_key=? ";
            db.beginTransaction();
            for (int i = 0; i < keys.size(); i++) {
                long key = keys.get(i);
                if (key != 0) {
                    db.execSQL(sql, new Object[]{
                            Constants.MESSAGE_STATE_SUCCESSED_FINISH, key});
                }
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }

    }

//    public synchronized void updateMessageContent(MessageEntity msg) {
//
//        SQLiteDatabase db = getWritableDatabase();
//        if (db == null) {
//            return;
//        }
//        String sqlFormat = "update msg set content=? where msg_id=? ";
//        try {
//            db.execSQL(sqlFormat, new Object[]{msg.getText(), msg.getTime(),
//                    msg.getMsgID()});
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            db.close();
//        }
//
//    }

    public synchronized int getMessageStateByMsgId(String msgId) {
        String sql;
        sql = String.format("select status from %s where msg_id = '%s'",
                TABLE_MSG, msgId);
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) {
            return 0;
        }
        Cursor cursor = null;
        int status = 1;
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                status = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return status;
    }

    // 查询进度
    public synchronized int getProgressByMsgId(String msgId) {
        String sql;
        sql = String.format("select int_reserved1 from %s where msg_id = '%s'",
                TABLE_MSG, msgId);
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) {
            LogUtils.d("db#db is null");
            return 0;
        }
        Cursor cursor = null;
        int progress = 0;
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                progress = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return progress;
    }

    public synchronized int getMediaDownloadStatusBySmsId(long smsId) {
        String sql;
        sql = String.format("select int_reserved2 from %s where sms_id = '%s'",
                TABLE_MSG, smsId);
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) {
            LogUtils.e("db#db is null");
            return 0;
        }
        Cursor cursor = null;
        int mediaDownloadStatus = -1;
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                mediaDownloadStatus = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return mediaDownloadStatus;
    }

    public synchronized int getVoiceReadByMsgId(String msgId) {
        String sql;
        sql = String.format("select int_reserved2 from %s where msg_id = '%s'",
                TABLE_MSG, msgId);
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) {
            return 0;
        }
        Cursor cursor = null;
        int voiceReadStatus = 0;
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                voiceReadStatus = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return voiceReadStatus;
    }

    /**
     * 获取图片url
     *
     * @param ownerId
     * @param sessionId
     * @param //chatType
     * @param textType1
     * @return
     */
    public synchronized List<ImageInfo> getImagePath(String ownerId,
                                                     long sessionId, General.ETextType textType1) {

//        String sql;
//        int textType = textType1.getNumber();
//        sql = String
//                .format(Locale.CHINA, "select path, url2 from %s where owner_id = '%s' and session_id = '%s' and  text_type = %d",
//                        TABLE_MSG, ownerId, sessionId, textType);
//        SQLiteDatabase db = getReadableDatabase();
//        if (db == null) {
//            L.d("db#db is null");
//            return null;
//        }
//        Cursor cursor = null;
//        List<ImageInfo> imageList = new ArrayList<ImageInfo>();
//        try {
//            cursor = db.rawQuery(sql, null);
//            int pos = 0;
//            while (cursor.moveToNext()) {
//                String imagePath = cursor.getString(0);
//                String imageUrl = cursor.getString(1);
//                ImageInfo imageInfo = new ImageInfo();
//                if (imagePath != null) {
//                    imageInfo.setUrlType(false);
//                    imageInfo.setUrl(imagePath);
//                }
//                if (imageUrl != null) {
//                    imageInfo.setUrlType(true);
//                    imageInfo.setUrl(imageUrl);
//                }
//                imageInfo.setPos(pos);
//                pos++;
//                imageList.add(imageInfo);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//            db.close();
//        }

        return null;
    }

    public synchronized void resetStatus() {
        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }
        String sqlFormat = "update msg set status=? where status=? and from_id=?";
        try {
            db.execSQL(
                    sqlFormat,
                    new Object[]{
                            Constants.MESSAGE_STATE_FINISH_FAILED,
                            Constants.MESSAGE_STATE_UNLOAD,
                            SPUtils.Impl.getUid()});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
//
//    public synchronized void deleteUndownloadMediaMsg() {
//
//        SQLiteDatabase db = getWritableDatabase();
//        if (db == null) {
//            return;
//        }
//        try {
//            db.delete(
//                    TABLE_MSG,
//                    "(text_type==? or text_type==?) and sms_id!=0 and int_reserved2<?",
//                    new String[]{ETextType.ETT_IMAGE_VALUE + "",
//                            ETextType.ETT_YUYIN_VALUE + "",
//                            Constants.MESSAGE_MEDIA_STATUS_UNPLAY + ""});
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            db.close();
//        }
//
//    }
//
//    public synchronized void resetVoiceDownloadStatus() {
//        SQLiteDatabase db = getWritableDatabase();
//        if (db == null) {
//            return;
//        }
//        String sqlFormat = "update msg set int_reserved2=? where text_type=? and int_reserved2=? and to_id=?";
//        try {
//            db.execSQL(
//                    sqlFormat,
//                    new Object[]{
//                            Constants.MESSAGE_MEDIA_STATUS_UNDOWNLOAD,
//                            ETextType.ETT_YUYIN_VALUE,
//                            Constants.MESSAGE_MEDIA_STATUS_DOWNLOADING,
//                            SPUtils.Impl.getUid()});
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            db.close();
//        }
//    }

    public void updateMsgStatus(String msgId, int status) {
        SQLiteDatabase db = getWritableDatabase();
        if (db == null) {
            return;
        }
        String sqlFormat = "update msg set status=? where msg_id=? ";
        try {
            db.execSQL(sqlFormat, new Object[]{status, msgId});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public synchronized int getDbIdByMsgId(String msgIdCur) {
        String sql;
        sql = String.format("select id from %s where msg_id = '%s'", TABLE_MSG,
                msgIdCur);
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) {
            L.d("db#db is null");
            return 0;
        }
        Cursor cursor = null;
        int id = 0;
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                id = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return id;
    }

    public synchronized MessageEntity getNextMsgByMsgId(int id) {
        String sql;
        sql = String
                .format("select from_id, status, content, sms_id, sms_key, msg_id, time, session_id, to_id, text_type, chat_type, "
                        + "path, url, path2, url2, "
                        + "int_reserved1, int_reserved2"
                        + " from %s where id > '%s' limit 1", TABLE_MSG, id);
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) {
            L.d("db#db is null");
            return null;
        }
        Cursor cursor = null;
        MessageEntity msgInfo = new MessageEntity();
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                long fromId = cursor.getLong(0);
                int status = cursor.getInt(1);
                String content = cursor.getString(2);
                long smsId = cursor.getLong(3);
                long smsKey = cursor.getLong(4);
                String msgId = cursor.getString(5);
                long time = cursor.getLong(6);
                long sessionId = cursor.getLong(7);
                long toId = cursor.getLong(8);
                int textType = cursor.getInt(9);
                int chat = cursor.getInt(10);
                String path = cursor.getString(11);
                String url = cursor.getString(12);
                String path2 = cursor.getString(13);
                String url2 = cursor.getString(14);
                int iReserved1 = cursor.getInt(15);
                int iReserved2 = cursor.getInt(16);
                msgInfo.setSmsID(smsId);
                msgInfo.setSmsKey(smsKey);
                msgInfo.setMsgID(msgId);
                msgInfo.setTime(time);
                msgInfo.setFromID(fromId);
                msgInfo.setToID(toId);
                msgInfo.setTextType(General.ETextType.valueOf(textType));
                msgInfo.setMessageState(status);
                msgInfo.setText(content);
                msgInfo.setSessionId(sessionId);
                msgInfo.setChatType(General.EChatType.valueOf(chat));
                if (textType == General.ETextType.ETT_YUYIN_VALUE) {
                    msgInfo.setVoicePath(path);
                    msgInfo.setVoiceUrl(url);
                    msgInfo.setVoiceTime(iReserved1);
                    msgInfo.setMediaDownloadStatus(iReserved2);
                } else if (textType == General.ETextType.ETT_IMAGE_VALUE) {
                    msgInfo.setThumbPath(path);
                    msgInfo.setImagePath(path2);
                    msgInfo.setThumbUrl(url);
                    msgInfo.setImageUrl(url2);
                    msgInfo.setProgress(iReserved1);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return msgInfo;
    }
//
//    public synchronized List<MessageEntity> getUnDownloadMediaMsg(long toId,
//                                                                  int voiceReadStatus, int textType) {
//
//        String sql;
//        sql = String
//                .format(Locale.CHINA, "select from_id, status, content, sms_id, sms_key, msg_id, session_id, chat_type, time, "
//                                + "path, url, int_reserved1 "
//                                + "from %s where to_id = '%s' and int_reserved2 = %d and text_type = %d",
//                        TABLE_MSG, toId, voiceReadStatus, textType);
//
//        SQLiteDatabase db = getReadableDatabase();
//        if (db == null) {
//            L.d("db#db is null");
//            return null;
//        }
//        Cursor cursor = null;
//        List<MessageEntity> msgList = new ArrayList<MessageEntity>();
//        try {
//            cursor = db.rawQuery(sql, null);
//
//            while (cursor.moveToNext()) {
//                long fromId = cursor.getLong(0);
//                int status = cursor.getInt(1);
//                String content = cursor.getString(2);
//                long smsId = cursor.getLong(3);
//                long smsKey = cursor.getLong(4);
//                String msgId = cursor.getString(5);
//                long sessionId = cursor.getLong(6);
//                int chat = cursor.getInt(7);
//                long time = cursor.getLong(8);
//                String path = cursor.getString(9);
//                String url = cursor.getString(10);
//                int iReserved1 = cursor.getInt(11);
//
//                MessageEntity msgInfo = new MessageEntity();
//                msgInfo.setSmsID(smsId);
//                msgInfo.setSmsKey(smsKey);
//                msgInfo.setMsgID(msgId);
//                msgInfo.setTime(time);
//                msgInfo.setFromID(fromId);
//                msgInfo.setToID(toId);
//                msgInfo.setTextType(ETextType.valueOf(textType));
//                msgInfo.setMessageState(status);
//                msgInfo.setText(content);
//                msgInfo.setSessionId(sessionId);
//                msgInfo.setChatType(EChatType.valueOf(chat));
//                msgInfo.setVoicePath(path);
//                msgInfo.setVoiceUrl(url);
//                msgInfo.setVoiceTime(iReserved1);
//                msgInfo.setMediaDownloadStatus(voiceReadStatus);
//                msgList.add(msgInfo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//            db.close();
//        }
//
//        return msgList;
//    }

    public synchronized List<MessageEntity> getHistoryMsg(String ownerId,
                                                          long sessionId, int chatType, int offset, int count,
                                                          long firstHistoryMsgTime) {
        String sql;
        if (firstHistoryMsgTime <= 0) {
            sql = String
                    .format(Locale.CHINA, "select time, from_id, to_id, text_type, status, display_type, content, sms_id, sms_key, msg_id, chat_type, "
                                    + "path, url, path2, url2, "
                                    + "int_reserved1, int_reserved2, string_reserved1, string_reserved2 "
                                    + "from %s where owner_id = '%s' and session_id = '%s' order by time desc limit %d offset %d",
                            TABLE_MSG, ownerId, sessionId, count, offset);
        } else {
            sql = String
                    .format(Locale.CHINA, "select time, from_id, to_id, text_type, status, display_type, content, sms_id, sms_key, msg_id, chat_type, "
                                    + "path, url, path2, url2, "
                                    + "int_reserved1, int_reserved2, string_reserved1, string_reserved2 "
                                    + "from %s where owner_id = '%s' and session_id = '%s'  and time < %d order by time desc limit %d offset %d",
                            TABLE_MSG, ownerId, sessionId, firstHistoryMsgTime,
                            count, offset);
        }

        SQLiteDatabase db = getReadableDatabase();
        if (db == null) {
            L.d("db#db is null");
            return null;
        }
        Cursor cursor = null;
        List<MessageEntity> msgList = new ArrayList<MessageEntity>();
        try {
            cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                long time = cursor.getLong(0);
                long fromId = cursor.getLong(1);
                long toId = cursor.getLong(2);
                int textType = cursor.getInt(3);
                int status = cursor.getInt(4);
                int display = cursor.getInt(5);
                String content = cursor.getString(6);
                long smsId = cursor.getLong(7);
                long smsKey = cursor.getLong(8);
                String msgId = cursor.getString(9);
                int chat = cursor.getInt(10);
                String path = cursor.getString(11);
                String url = cursor.getString(12);
                String path2 = cursor.getString(13);
                String url2 = cursor.getString(14);
                int iReserved1 = cursor.getInt(15);
                int iReserved2 = cursor.getInt(16);
                String string_reserved1 = cursor.getString(17);
                String string_reserved2 = cursor.getString(18);

                if (content != null)
                    status = refreshMessageStatus(msgId, status);

                if (((textType == General.ETextType.ETT_YUYIN_VALUE || textType == General.ETextType.ETT_IMAGE_VALUE) && iReserved2 > Constants.MESSAGE_MEDIA_STATUS_DOWNLOADING)
                    || (textType != General.ETextType.ETT_YUYIN_VALUE && textType != General.ETextType.ETT_IMAGE_VALUE)) {
                    MessageEntity msgInfo = new MessageEntity();
                    msgInfo.setSmsID(smsId);
                    msgInfo.setSmsKey(smsKey);
                    msgInfo.setMsgID(msgId);
                    msgInfo.setTime(time);
                    msgInfo.setFromID(fromId);
                    msgInfo.setToID(toId);
                    msgInfo.setTextType(General.ETextType.valueOf(textType));
                    msgInfo.setMessageState(status);
                    msgInfo.setDisplayType(display);
                    msgInfo.setText(content);
                    msgInfo.setSessionId(sessionId);
                    msgInfo.setChatType(General.EChatType.valueOf(chat));
                    msgInfo.setSystemHint(string_reserved1);
                    msgInfo.setBubble(string_reserved2);
                    if (textType == General.ETextType.ETT_YUYIN_VALUE) {
                        msgInfo.setVoicePath(path);
                        msgInfo.setVoiceUrl(url);
                        msgInfo.setVoiceTime(iReserved1);
                        msgInfo.setMediaDownloadStatus(iReserved2);
                    } else if (textType == General.ETextType.ETT_IMAGE_VALUE) {
                        msgInfo.setThumbPath(path);
                        msgInfo.setImagePath(path2);
                        msgInfo.setThumbUrl(url);
                        msgInfo.setImageUrl(url2);
                        msgInfo.setProgress(iReserved1);
                    }
                    msgList.add(msgInfo);
                }
            }
            // 反转指定List集合中元素的顺序
            Collections.reverse(msgList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return msgList;
    }

    private int refreshMessageStatus(String msgId, int dbStatus) {
        if (dbStatus != Constants.MESSAGE_STATE_SUCCESSED_UNREAD
                && dbStatus != Constants.MESSAGE_STATE_SUCCESSED_FINISH) {
                return dbStatus;
            } else {
                if (dbStatus == Constants.MESSAGE_STATE_LOADDING) {
                    dbStatus = Constants.MESSAGE_STATE_FINISH_FAILED;
                    updateMsgStatus(msgId,
                            Constants.MESSAGE_STATE_FINISH_FAILED);
                } else if (dbStatus == Constants.MESSAGE_STATE_UNLOAD) {

                }
            }
        return dbStatus;
    }

//    private void tryRecoverAudioMsg(MessageEntity msgInfo, String content,
//                                    int textType) {
//        // if (displayType == ETextType.ETT_IMAGE.getNumber()) {
//        if (textType == Constants.DISPLAY_TYPE_AUDIO) {
//            AudioInfo audioInfo = AudioInfo.create(content);
//            msgInfo.setPlayTime(audioInfo.getLength());
//            msgInfo.setSavePath(audioInfo.getPath());
//        }
//    }
//
//    private void tryRecoverPicMsg(MessageEntity msgInfo, String content,
//                                  int textType) {
//        if (textType == ETextType.ETT_IMAGE.getNumber()) {
//
//            PicInfo picInfo = PicInfo.create(content);
//
//            if (picInfo != null) {
//                msgInfo.setSavePath(picInfo.getPath());
//                msgInfo.setUrl(picInfo.getUrl());
//            }
//        }
//    }

}
