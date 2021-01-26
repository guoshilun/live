package com.jk.jkproject.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.ui.chat.AudioMsgBody;
import com.jk.jkproject.ui.chat.ChatAdapter;
import com.jk.jkproject.ui.chat.ChatRecordBeanInfo;
import com.jk.jkproject.ui.chat.ChatUiHelper;
import com.jk.jkproject.ui.chat.FileMsgBody;
import com.jk.jkproject.ui.chat.ImageMsgBody;
import com.jk.jkproject.ui.chat.IndicatorView;
import com.jk.jkproject.ui.chat.MediaManager;
import com.jk.jkproject.ui.chat.Message;
import com.jk.jkproject.ui.chat.MsgSendStatus;
import com.jk.jkproject.ui.chat.MsgType;
import com.jk.jkproject.ui.chat.RecordButton;
import com.jk.jkproject.ui.chat.StateButton;
import com.jk.jkproject.ui.chat.TextMsgBody;
import com.jk.jkproject.ui.chat.VideoMsgBody;
import com.jk.jkproject.ui.chat.WrapContentHeightViewPager;
import com.jk.jkproject.ui.fragment.LiveRoomFragment;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/13 1:26 PM
 * @desc 聊天界面
 */
public class DialogLiveMessage extends BaseDialog implements SwipeRefreshLayout.OnRefreshListener {

    public static String mSenderId = "right";
    public static String mTargetId = "left";
    public static final int REQUEST_CODE_IMAGE = 0000;
    public static final int REQUEST_CODE_VEDIO = 1111;
    public static final int REQUEST_CODE_FILE = 2222;
    private final Context mContext;
    public static int mType;//1 系统消息 2 在线客服 3 聊天消息
    private final Activity mActivity;

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_left_name)
    TextView tvLeftName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_title)
    ImageView ivRightTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.rv_chat_list)
    RecyclerView mRvChat;
    @BindView(R.id.swipe_chat)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.ivEmo)
    ImageView mIvEmo;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.btnAudio)
    RecordButton mBtnAudio;
    @BindView(R.id.ivAdd)
    ImageView mIvAdd;
    @BindView(R.id.btn_send)
    StateButton mBtnSend;
    @BindView(R.id.llContent)
    LinearLayout mLlContent;
    @BindView(R.id.vp_emoji)
    WrapContentHeightViewPager vpEmoji;
    @BindView(R.id.ind_emoji)
    IndicatorView indEmoji;
    @BindView(R.id.home_emoji)
    LinearLayout homeEmoji;
    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.rlPhoto)
    RelativeLayout rlPhoto;
    @BindView(R.id.ivVideo)
    ImageView ivVideo;
    @BindView(R.id.rlVideo)
    RelativeLayout rlVideo;
    @BindView(R.id.ivFile)
    ImageView ivFile;
    @BindView(R.id.rlFile)
    RelativeLayout rlFile;
    @BindView(R.id.ivLocation)
    ImageView ivLocation;
    @BindView(R.id.rlLocation)
    RelativeLayout rlLocation;
    @BindView(R.id.bottom_layout)
    RelativeLayout mRlBottomLayout;
    @BindView(R.id.rlEmotion)
    LinearLayout mLlEmotion;//表情布局
    @BindView(R.id.llAdd)
    LinearLayout mLlAdd;//添加布局
    @BindView(R.id.ivAudio)
    ImageView mIvAudio;//录音图片
    @BindView(R.id.tv_m1)
    TextView tvM1;
    @BindView(R.id.tv_m2)
    TextView tvM2;
    @BindView(R.id.ll_6)
    LinearLayout ll6;
    private Unbinder bind;
    private ChatAdapter mAdapter;
    private int page = 0;
    private int isHasData = 1;
    private String Target;
    private String userName;
    private String mMothod = "";
    private int mState = 0;// 0.暂无客服 1.有客服 2.客服已匹配过
    private int isAttention;
    private int isBlock;
    private long msgTime = -1;
    private DialogLiveRoomLackBalance mBalanceDialog;

    public void setMsgPush(Message message) {
        if (mAdapter != null) {
            mAdapter.addData(message);
            mRvChat.scrollToPosition(mAdapter.getItemCount() - 1);
        }
    }

    public void setMsgSend(Message message) {
        if (mAdapter != null) {
            int position = 0;
            for (int i = 0; i < mAdapter.getData().size(); i++) {
                Message message1 = mAdapter.getData().get(i);
                if (message.getUuid().equals(message1.getUuid())) {
                    message1.setSentStatus(message.getSentStatus());
                    if (msgTime == -1) {
                        message1.setShowTime(true);
                        msgTime = message1.getSentTime();
                    } else {
                        long timeSpan = TimeUtils.getTimeSpan(msgTime, message1.getSentTime(), TimeConstants.MIN);
                        if (timeSpan >= 3) {
                            message1.setShowTime(true);
                            msgTime = message1.getSentTime();
                        } else {
                            message1.setShowTime(false);
                        }
                    }
                    position = i;
                }
            }
            mAdapter.notifyItemChanged(position);
            if (message.getCode() == 10044) {
                ToastUtils.showShortToast(message.getMsg());
            }
        }
    }

    public DialogLiveMessage(Context context, int type, String target, String userName, Activity mActivity) {
        super(context);
        this.mContext = context;
        this.mType = type;
        this.Target = target;
        this.userName = userName;
        this.mActivity = mActivity;
        LogUtils.e("uid=", target);
    }

    @Override
    protected void create(Bundle b) {
        setContentView(R.layout.activity_chat);
        bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 1.0F;
        this.mDimAmount = 0.6F;
        this.h = 528;
        this.gravity = Gravity.BOTTOM;
    }

    @Override
    protected void initView() {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }


    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(getContext(), 528));
        ivRightTitle.setVisibility(View.VISIBLE);
        ivRightTitle.setImageResource(R.mipmap.live_icon_live_room_three_point);
        if (mType == 3) {
            mSenderId = SPUtils.getUserId();
            mTargetId = Target;
        } else if (mType == 2) {
            mSenderId = SPUtils.getUserId();
            mTargetId = "0";
        }
        switch (mType) {
            case 1:
                tvTitle.setText("系统消息");
                break;
            case 2:
                tvTitle.setText("在线客服");
                mMothod = "live.SendMessageAirlinesTalk";
                mState = Integer.parseInt(Target);
                ivRightTitle.setVisibility(View.GONE);
//                AppApis.getGetClearCountInfo("0", this);
                break;
            case 3:
                if (userName == null) {
                    tvTitle.setText(LiveRoomFragment.UserName);
                } else {
                    tvTitle.setText(userName);
                }
                mMothod = "live.SendMessageTalk";
//                AppApis.getGetClearCountInfo(mSenderId, this);
                break;
        }
        getData(0);
        initChatListView();

    }

    private void getData(int page) {
        switch (mType) {
            case 2:
                AppApis.getChatMsg("0", page, this);
                break;
            case 3:
                AppApis.getChatMsg(mTargetId, page, this);
                break;
        }
    }

    private void initChatListView() {
        mAdapter = new ChatAdapter(mContext, new ArrayList<Message>(), 2);
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(mContext);
        mRvChat.setLayoutManager(mLinearLayout);
        mRvChat.setAdapter(mAdapter);
        mSwipeRefresh.setOnRefreshListener(this);
        initChatUi();
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {

            final boolean isSend = mAdapter.getItem(position).getUserId().equals(mSenderId);
            if (mIvAudio != null) {
                if (isSend) {
                    mIvAudio.setBackgroundResource(R.mipmap.audio_animation_list_right_3);
                } else {
                    mIvAudio.setBackgroundResource(R.mipmap.audio_animation_list_left_3);
                }
                mIvAudio = null;
                MediaManager.reset();
            } else {
//                    ivAudio = view.findViewById(R.id.ivAudio);
                MediaManager.reset();
                if (isSend) {
                    mIvAudio.setBackgroundResource(R.drawable.audio_animation_right_list);
                } else {
                    mIvAudio.setBackgroundResource(R.drawable.audio_animation_left_list);
                }
                AnimationDrawable drawable = (AnimationDrawable) mIvAudio.getBackground();
                drawable.start();
                MediaManager.playSound(mContext, ((AudioMsgBody) mAdapter.getData().get(position).getBody()).getLocalPath(), new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (isSend) {
                            mIvAudio.setBackgroundResource(R.mipmap.audio_animation_list_right_3);
                        } else {
                            mIvAudio.setBackgroundResource(R.mipmap.audio_animation_list_left_3);
                        }
                        MediaManager.release();
                    }
                });
            }
        });

        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    mBtnSend.setBackgroundResource(R.drawable.bg_red_send_btn);
                } else {
                    mBtnSend.setBackgroundResource(R.drawable.msg_send_selector);
                }
            }
        });
    }

    private void initChatUi() {
        //mBtnAudio
        final ChatUiHelper mUiHelper = ChatUiHelper.with(mActivity);
        mUiHelper.bindContentLayout(mLlContent)
                .bindttToSendButton(mBtnSend)
                .bindEditText(mEtContent)
                .bindBottomLayout(mRlBottomLayout)
                .bindEmojiLayout(mLlEmotion)
                .bindAddLayout(mLlAdd)
                .bindToAddButton(mIvAdd)
                .bindToEmojiButton(mIvEmo)
                .bindAudioBtn(mBtnAudio)
                .bindAudioIv(mIvAudio)
                .bindEmojiData();
        //底部布局弹出,聊天列表上滑
        mRvChat.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom < oldBottom) {
                mRvChat.post(() -> {
                    if (mAdapter.getItemCount() > 0) {
                        mRvChat.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                    }
                });
            }
        });
        //点击空白区域关闭键盘
        mRvChat.setOnTouchListener((view, motionEvent) -> {
            mUiHelper.hideBottomLayout(false);
            mUiHelper.hideSoftInput();
            mEtContent.clearFocus();
            mIvEmo.setImageResource(R.mipmap.ic_emoji);
            return false;
        });
        //
        ((RecordButton) mBtnAudio).setOnFinishedRecordListener((RecordButton.OnFinishedRecordListener) (audioPath, time) -> {
            LogUtils.d("录音结束回调");
            File file = new File(audioPath);
            if (file.exists()) {
                sendAudioMessage(audioPath, time);
            }
        });

    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        LogUtils.e("===", obj);
        if (url.equals(Urls.GET_CHAT_MSG)) {
            mSwipeRefresh.setRefreshing(false);
            if (obj != null && obj instanceof ChatRecordBeanInfo) {
                ChatRecordBeanInfo beanInfo = (ChatRecordBeanInfo) obj;
                if (beanInfo.getCode() == 200) {
                    setData(beanInfo);
                }
            }
        } else if (url.equals(Urls.GET_ATTAND_BLOCK)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    JSONObject jsonObject1 = new JSONObject(jSONObject.getString("data"));
                    isAttention = jsonObject1.getInt("isAttention");
                    isBlock = jsonObject1.getInt("isBlock");
                    ll6.setVisibility(View.VISIBLE);
                    switch (isAttention) {
                        case 0:
                            tvM1.setText("关注");
                            break;
                        case 1:
                            tvM1.setText("取消关注");
                            break;
                    }
                    switch (isBlock) {
                        case 0:
                            tvM2.setText("拉黑");
                            break;
                        case 1:
                            tvM2.setText("取消拉黑");
                            break;
                    }
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        } else if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    switch (isAttention) {
                        case 0:
                            ToastUtils.showShortToast("关注成功");
                            break;
                        case 1:
                            ToastUtils.showShortToast("取消关注成功");
                            break;
                    }
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        } else if (url.equals(Urls.UPDATE_USER_BLACKLIST)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    switch (isBlock) {
                        case 0:
                            ToastUtils.showShortToast("拉黑成功");
                            break;
                        case 1:
                            ToastUtils.showShortToast("取消拉黑成功");
                            break;
                    }
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        }
    }

    //语音消息
    private void sendAudioMessage(final String path, int time) {
        final Message mMessgae = getBaseSendMessage(MsgType.AUDIO);
        AudioMsgBody mFileMsgBody = new AudioMsgBody();
        mFileMsgBody.setLocalPath(path);
        mFileMsgBody.setDuration(time);
        mMessgae.setBody(mFileMsgBody);
        //开始发送
        mAdapter.addData(mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);
    }

    private Message getBaseSendMessage(MsgType msgType) {
        Message mMessgae = new Message();
        mMessgae.setUuid(UUID.randomUUID() + "");
        mMessgae.setUserId(SPUtils.getUserId());
        mMessgae.setTargetId(mTargetId);
        mMessgae.setSentTime(System.currentTimeMillis());
        mMessgae.setSentStatus(MsgSendStatus.SENDING);
        mMessgae.setMsgType(msgType);
        return mMessgae;
    }


    private void updateMsg(final Message mMessgae) {
        mRvChat.scrollToPosition(mAdapter.getItemCount() - 1);
        //模拟2秒后发送成功
        new Handler().postDelayed(new Runnable() {
            public void run() {
                int position = 0;
                mMessgae.setSentStatus(MsgSendStatus.SENT);
                //更新单个子条目
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    Message mAdapterMessage = mAdapter.getData().get(i);
                    if (mMessgae.getUuid().equals(mAdapterMessage.getUuid())) {
                        position = i;
                    }
                }
                mAdapter.notifyItemChanged(position);
            }
        }, 2000);

    }

    @OnClick({R.id.iv_title, R.id.ivEmo, R.id.btn_send, R.id.iv_right_title, R.id.tv_m1, R.id.tv_m2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                dismiss();
                break;
            case R.id.ivEmo:
                break;
            case R.id.btn_send:
                if (!mEtContent.getText().toString().isEmpty()) {
                    sendTextMsg(mEtContent.getText().toString());
                    mEtContent.setText("");
                }
                break;
            case R.id.iv_right_title:
                if (ll6.getVisibility() == View.GONE) {
                    AppApis.getAttandBlock(mTargetId, this);
                } else {
                    ll6.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_m1:
                ll6.setVisibility(View.GONE);
                AppApis.getUpdateFollow(isAttention == 0 ? 1 : 0, mTargetId, this);
                break;
            case R.id.tv_m2:
                ll6.setVisibility(View.GONE);
                switch (isBlock) {
                    case 0:
                        setDialog(16);
                        break;
                    case 1:
                        setDialog(17);
                        break;
                }
                break;
        }
    }

    private void setDialog(int mType) {
        mBalanceDialog = new DialogLiveRoomLackBalance(getContext(), mType);
        mBalanceDialog.show();
        mBalanceDialog.setDialogClickListener(type -> {
            switch (type) {
                case 16:
                case 17:
                    AppApis.getUpdateBlock(isBlock == 0 ? 1 : 0, mTargetId, this);
                    break;
            }
        });
        mBalanceDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case REQUEST_CODE_FILE:
//                    String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
//                    LogUtils.d("获取到的文件路径:" + filePath);
//                    sendFileMessage(mSenderId, mTargetId, filePath);
//                    break;
//                case REQUEST_CODE_IMAGE:
//                    // 图片选择结果回调
//                    List<LocalMedia> selectListPic = PictureSelector.obtainMultipleResult(data);
//                    for (LocalMedia media : selectListPic) {
//                        LogUtils.d("获取图片路径成功:" + media.getPath());
//                        sendImageMessage(media);
//                    }
//                    break;
//                case REQUEST_CODE_VEDIO:
//                    // 视频选择结果回调
//                    List<LocalMedia> selectListVideo = PictureSelector.obtainMultipleResult(data);
//                    for (LocalMedia media : selectListVideo) {
//                        LogUtils.d("获取视频路径成功:" + media.getPath());
//                        sendVedioMessage(media);
//                    }
//                    break;
//            }
//        }
//    }


    @Override
    public void onRefresh() {
        if (isHasData == 1) {
            mSwipeRefresh.setRefreshing(true);
            page++;
            getData(page);
        } else {
            mSwipeRefresh.setRefreshing(false);
            ToastUtils.showLongToast("没有更多的数据了~");
        }
    }

    private Message getBaseReceiveMessage(MsgType msgType) {
        Message mMessgae = new Message();
        mMessgae.setUuid(UUID.randomUUID() + "");
        mMessgae.setUserId(SPUtils.getUserId());
        mMessgae.setTargetId(mTargetId);
        mMessgae.setSentTime(System.currentTimeMillis());
        mMessgae.setSentStatus(MsgSendStatus.SENDING);
        mMessgae.setMsgType(msgType);
        return mMessgae;
    }

    //文本消息
    private void sendTextMsg(String hello) {
        final Message mMessgae = getBaseSendMessage(MsgType.TEXT);
        TextMsgBody mTextMsgBody = new TextMsgBody();
        mTextMsgBody.setMessage(hello);
        mMessgae.setBody(mTextMsgBody);
        //开始发送
        mAdapter.addData(mMessgae);
        //模拟两秒后发送成功
//        updateMsg(mMessgae);
        mRvChat.scrollToPosition(mAdapter.getItemCount() - 1);
        mMessgae.setContent(hello);
        mMessgae.setmType(Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_0);
        IMLiveRoomManager.instance().sendMsg(mMessgae, mMothod);
    }


    //图片消息
    private void sendImageMessage(final LocalMedia media) {
        final Message mMessgae = getBaseSendMessage(MsgType.IMAGE);
        ImageMsgBody mImageMsgBody = new ImageMsgBody();
        mImageMsgBody.setThumbUrl(media.getCompressPath());
        mMessgae.setBody(mImageMsgBody);
        //开始发送
        mAdapter.addData(mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);
    }


    //视频消息
    private void sendVedioMessage(final LocalMedia media) {
        final Message mMessgae = getBaseSendMessage(MsgType.VIDEO);
        //生成缩略图路径
        String vedioPath = media.getPath();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(vedioPath);
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime();
        String imgname = System.currentTimeMillis() + ".jpg";
        String urlpath = Environment.getExternalStorageDirectory() + "/" + imgname;
        File f = new File(urlpath);
        try {
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            LogUtils.d("视频缩略图路径获取失败：" + e.toString());
            e.printStackTrace();
        }
        VideoMsgBody mImageMsgBody = new VideoMsgBody();
        mImageMsgBody.setExtra(urlpath);
        mMessgae.setBody(mImageMsgBody);
        //开始发送
        mAdapter.addData(mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);

    }

    @Override
    protected void onStop() {
        if (listener != null) {
            listener.onDialogReturnClick(mTargetId, 1);
        }
        super.onStop();
    }

    @Override
    public void dismiss() {
        mTargetId = "left";
        super.dismiss();
    }

    //文件消息
    private void sendFileMessage(String from, String to, final String path) {
        final Message mMessgae = getBaseSendMessage(MsgType.FILE);
        FileMsgBody mFileMsgBody = new FileMsgBody();
        mFileMsgBody.setLocalPath(path);
        mFileMsgBody.setDisplayName(FileUtils.getFileName(path));
        mFileMsgBody.setSize(FileUtils.getFileLength(path));
        mMessgae.setBody(mFileMsgBody);
        //开始发送
        mAdapter.addData(mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);

    }

    private void setData(final ChatRecordBeanInfo info) {
        if (info == null || info.getData() == null || info.getData().getUserList() == null || info.getData().getUserList().size() == 0) {
            if (info != null && info.getData().getUserList() != null) {
                isHasData = info.getData().getUserList().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            page = -1;
            return;
        }
        isHasData = info.getData().getUserList().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        new Handler().postDelayed(() -> {
            //execute the task
            mAdapter.addData(0, info.getData().getReverseMessage());
        }, 300);
//        mRvChat.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);

    }

    private DialogReturnListener listener;


    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public static interface DialogReturnListener {
        void onDialogReturnClick(String param1String, int param1Int);
    }
}