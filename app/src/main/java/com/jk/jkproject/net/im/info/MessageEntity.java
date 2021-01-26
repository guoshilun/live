package com.jk.jkproject.net.im.info;

import android.app.Activity;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.msg.IUploadProgress;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.DateUtils;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;


/**
 * @author zhoujiyu
 * @Description 消息列表中的消息实体
 * @date 2015-2-5
 */
public class MessageEntity implements Serializable, Observer {
    private static final long serialVersionUID = 1L;

    public static final int STATUS_NOT = 0;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILED = 2;
    public static final int STATUS_LOADING = 3;

    private boolean IsSend = false;
    public long SessionId;
    private long UserID;// 用户ID
    private long FromID; // 发送者
    private long ToID; // 接收者
    private long SmsID;// 服务器自己会话内消息编号
    private long SmsKey; // 服务器消息池会话的唯一编号
    private String MsgID; // 客户端对所有消息的唯一编号(UUID) 主要是设置状态的消息唯一编号
    private long GroupID;// 群IDz
    // add by xiaosong.xu
    private String thumbPath = null; // 本地小图路径
    private String thumbUrl = null; // 小图url
    private String imagePath = null; // 本地大图路径
    private String imageUrl = null; // 大图URL
    private int photoType =0; //0是相册  1 是实景相机 2是瞬间照片

    private int progress = 0;
    private String voicePath = null;
    private String voiceUrl = null;
    private int voiceTime = 0;
    private int mediaDownloadStatus = 0;//0代表未下载，1代表未读，2代表已读
    private int oldProgress = 0;
    // end by xiaosong.xu
    private String Text;// 聊天内容
    private General.ETextType TextType = General.ETextType.ETT_TEXT;
    private General.EChatType ChatType = General.EChatType.ECT_NORMAL;
    // ECT_NORMAL = 1;正常聊天
    // ECT_SYSTEM = 2;系统聊天
    // ECT_READSTATE = 3;聊天消息已读状态设置
    // ECT_PRODUCT = 4;产品消息
    // ECT_GROUP = 5;群聊
    private long Time;// 时间

    private int MessageState = Constants.MESSAGE_STATE_LOADDING;
    // 是发送中，1是送达，2是已阅，3是发送失败,4是对方发过来接受成功的消息

    private String savePath = null; // 图片或语音保存路径
    private String url = null; // 图片或语音链接
    private int displayType = Constants.MSG_DISPLAY_TYPE_NORMAL; // 消息显示类型 0气泡, 1系统浮框 ,2居中显示,3是系统打分图文混排
    private int playTime = 0; // 语音播放时长
    private boolean resend = false;
    private int action; // 发过来的消息协议编号
    private String errDes;// 消息发送失败的原因
    private List<Long> StateSmsKeyID; // 设置状态的消息唯一编号

    private String sTime;
    private String systemHint;

    private int snapState;//0是瞬间图片未看 1是是瞬间图片看了


    /** 上传状态 */
    private int mUploadStatus = STATUS_NOT;
    /** 上传进度(数字) */
    private int mProgress;

    /** 气泡索引（id）*/
    private String bubble;


    //add by zjy
    //livechat





    public MessageEntity() {
        String msg = UUID.randomUUID().toString();
        setMsgID(msg);
    }

    public long getUserID() {
        return UserID;
    }

    public void setUserID(long userID) {
        UserID = userID;
    }

    public long getFromID() {
        return FromID;
    }

    public void setFromID(long fromID) {
        FromID = fromID;
    }

    public long getSmsID() {
        return SmsID;
    }

    public void setSmsID(long smsID) {
        SmsID = smsID;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
        sTime = DateUtils.getMsgItemTime(time);
    }

    public long getSmsKey() {
        return SmsKey;
    }

    public void setSmsKey(long smsKey) {
        SmsKey = smsKey;
    }

    public long getGroupID() {
        return GroupID;
    }

    public void setGroupID(long groupID) {
        GroupID = groupID;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public General.ETextType getTextType() {
        return TextType;
    }

    public void setTextType(General.ETextType textType) {
        TextType = textType;
    }

    public General.EChatType getChatType() {
        return ChatType;
    }

    public void setChatType(General.EChatType chatType) {
        ChatType = chatType;
    }

    public long getToID() {
        return ToID;
    }

    public void setToID(long toID) {
        ToID = toID;
    }

    public int getMessageState() {
        return MessageState;
    }

    public void setMessageState(int messageState) {
        MessageState = messageState;
    }

    public long getSessionId() {

        return IsSend ? ToID : FromID;

    }

    public boolean isIsSend() {
        return IsSend;
    }

    public void setIsSend(boolean isSend) {
        IsSend = isSend;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public boolean isResend() {
        return resend;
    }

    public void setResend(boolean resend) {
        this.resend = resend;
    }

    public void setSessionId(long sessionId) {
        SessionId = sessionId;
    }

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        MsgID = msgID;
    }

    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getErrDes() {
        return errDes;
    }

    public void setErrDes(String errDes) {
        this.errDes = errDes;
    }

    public List<Long> getStateSmsKeyID() {
        return StateSmsKeyID;
    }

    public void setStateSmsKeyID(List<Long> stateSmsKeyID) {
        StateSmsKeyID = stateSmsKeyID;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void update(Observable observable, Object data) {

    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getVoicePath() {
        return voicePath;
    }

    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public int getVoiceTime() {
        return voiceTime;
    }

    public void setVoiceTime(int voiceTime) {
        this.voiceTime = voiceTime;
    }

    public int getOldProgress() {
        return oldProgress;
    }

    public void setOldProgress(int oldProgress) {
        this.oldProgress = oldProgress;
    }

    public int getMediaDownloadStatus() {
        return mediaDownloadStatus;
    }

    public void setMediaDownloadStatus(int mediaDownloadStatus) {
        this.mediaDownloadStatus = mediaDownloadStatus;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public int getSnapState() {
        return snapState;
    }

    public void setSnapState(int snapState) {
        this.snapState = snapState;
    }

    public String getSystemHint() {
        return systemHint;
    }

    public void setSystemHint(String systemHint) {
        this.systemHint = systemHint;
    }

    public int getPhotoType() {
        return photoType;
    }

    public void setPhotoType(int photoType) {
        this.photoType = photoType;
    }

    public String getBubble() {
        return bubble;
    }

    public void setBubble(String bubble) {
        this.bubble = bubble;
    }

    public int getmUploadStatus() {
        return mUploadStatus;
    }

    public void setmUploadStatus(int mUploadStatus) {
        this.mUploadStatus = mUploadStatus;
    }

    public int getmProgress() {
        return mProgress;
    }

    public void setmProgress(int progress) {
        this.mProgress = progress;
    }

    private SparseArray<WeakReference<IUploadProgress>> callbacks = new SparseArray<>();

    public synchronized void addProgressViews(IUploadProgress... progressViews) {
        for (IUploadProgress callback : progressViews) {
            if (callback instanceof View) {
                View view = (View) callback;
                Object obj = view.getTag(R.id.upload_id);
                if (obj != null && !TextUtils.isEmpty((CharSequence) obj)) {
                    callbacks.put(callback.hashCode(), new WeakReference<IUploadProgress>(callback));
                } else {
                    throw new RuntimeException("the callback must setTag and use R.id.upload_id as the key");
                }
            } else {
                throw new RuntimeException("the callback isn't the instance of View!");
            }
        }
    }

    public void notifyProgress() {
        notifyProgress(null);
    }

    public synchronized void notifyProgress(Activity activity) {
        int size;
        if (callbacks != null && (size = callbacks.size()) > 0) {
            for (int i = 0; i < size; i++) {
                final IUploadProgress callback = callbacks.valueAt(i).get();
                if (callback == null) {
                    callbacks.removeAt(i);
                    i--;
                    size--;
                } else {
                    if (callback instanceof View) {
                        final View v = (View) callback;
                        final Object u = v.getTag(R.id.upload_id);
                        if (u != null) {
                            if (u.equals(MsgID)) {
                                Runnable run = new Runnable() {

                                    @Override
                                    public void run() {
                                        if (u.equals(v.getTag(R.id.upload_id))) {
                                            callback.showStatus(MessageEntity.this);
                                            callback.showProgress(mProgress);
                                        }
                                    }
                                };

                                if (activity == null) {
                                    v.post(run);
                                } else {
                                    activity.runOnUiThread(run);
                                }
                            } else {
                                callbacks.removeAt(i);
                                i--;
                                size--;
                            }
                        } else {
                            callbacks.removeAt(i);
                            i--;
                            size--;
                        }
                    } else {
                        callbacks.removeAt(i);
                        i--;
                        size--;
                    }
                }
            }
        }
    }


}