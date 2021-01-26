package com.jk.jkproject.ui.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.ui.dialog.DialogLiveRoomLackBalance;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
@Route(path = "/jk/message")
public class LiveMessageActivity extends BActivity implements SwipeRefreshLayout.OnRefreshListener, Observer {

    public static String mSenderId = "right";
    public static String mTargetId = "left";
    public static final int REQUEST_CODE_IMAGE = 0000;
    public static final int REQUEST_CODE_VEDIO = 1111;
    public static final int REQUEST_CODE_FILE = 2222;

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
    public static int type;//1 系统消息 2 在线客服 3 聊天消息
    @BindView(R.id.tv_m1)
    TextView tvM1;
    @BindView(R.id.tv_m2)
    TextView tvM2;
    @BindView(R.id.ll_6)
    LinearLayout ll6;
    private Unbinder bind;
    private ChatAdapter mAdapter;
    private Message message;
    private int page = 0;
    private int isHasData = 1;
    private long msgTime = -1;


    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(android.os.Message param1Message) {
            Bundle bundle = (Bundle) param1Message.obj;
            switch (bundle.getInt("method")) {
                case IMEventType.ACTION_CHAT_MSG_PUSH:
                    if (mAdapter != null) {
                        Message message = (Message) bundle.getSerializable("mMessage");
                        if (message.getUserId().equals(mTargetId)) {
                            mAdapter.addData(message);
                            mRvChat.scrollToPosition(mAdapter.getItemCount() - 1);
                        }
                    }
                    break;
                case IMEventType.ACTION_CHAT_MSG:
                    if (mAdapter != null) {
                        int position = 0;
                        Message message = (Message) bundle.getSerializable("message");
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
                    break;
                default:
                    break;
            }
        }
    };
    private String mMothod;
    private int state;
    private ChatRecordBeanInfo beanInfo;
    private int isAttention;
    private int isBlock;
    private DialogLiveRoomLackBalance mBalanceDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        ARouter.getInstance().inject(this);
        window.setAttributes(layoutParams);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        setContentView(R.layout.activity_chat);
        bind = ButterKnife.bind(this);
        ivRightTitle.setVisibility(View.VISIBLE);
        ivRightTitle.setImageResource(R.mipmap.live_icon_live_room_three_point);
    }


    @Override
    protected void onStart() {
        super.onStart();
        MessageNotifyCenter.getInstance().addObserver(this);
        if (getIntent() != null) {
            type = getIntent().getIntExtra("type", -1);
            if (type == 3) {
                message = (Message) getIntent().getSerializableExtra("message");
                mSenderId = SPUtils.getUserId();
                mTargetId = message.getTargetId();
            } else if (type == 2) {
                state = getIntent().getIntExtra("state", -1);
                mSenderId = SPUtils.getUserId();
                mTargetId = "0";
            }

        }
        switch (type) {
            case 1:
                tvTitle.setText("系统消息");
                break;
            case 2:
                tvTitle.setText("在线客服");
                mMothod = "live.SendMessageAirlinesTalk";
                ivRightTitle.setVisibility(View.GONE);
                break;
            case 3:
                if (null != message) {
                    mMothod = "live.SendMessageTalk";
                    tvTitle.setText(message.getUserName());
                }
                break;
        }
        getData(0);
        initChatListView();
    }

    private void getData(int page) {
        switch (type) {
            case 2:
                AppApis.getChatMsg("0", page, this);
                break;
            case 3:
                AppApis.getChatMsg(message.getTargetId(), page, this);
                break;
        }
    }

    private void initChatListView() {
        mAdapter = new ChatAdapter(this, new ArrayList<Message>(), 1);
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(this);
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
                MediaManager.playSound(LiveMessageActivity.this, ((AudioMsgBody) mAdapter.getData().get(position).getBody()).getLocalPath(), new MediaPlayer.OnCompletionListener() {
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
        final ChatUiHelper mUiHelper = ChatUiHelper.with(this);
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
        ((RecordButton) mBtnAudio).setOnFinishedRecordListener((audioPath, time) -> {
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
                beanInfo = (ChatRecordBeanInfo) obj;
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
        new Handler().postDelayed(() -> {
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
        }, 2000);

    }

    @OnClick({R.id.iv_title, R.id.ivEmo, R.id.btn_send, R.id.iv_right_title, R.id.tv_m1, R.id.tv_m2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
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
        mBalanceDialog = new DialogLiveRoomLackBalance(this, mType);
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

    @Override
    protected void onStop() {
        super.onStop();
        switch (type) {
            case 2:
//                AppApis.getGetClearCountInfo("0", this);
                break;
            case 3:
                if (null != message) {
                    AppApis.getGetClearCountInfo(message.getTargetId(), this);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        MessageNotifyCenter.getInstance().unregister(this);
        if (handler != null) {
            handler = null;
        }
        mTargetId = "left";
        super.onDestroy();
        bind.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FILE:
                    String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                    LogUtils.d("获取到的文件路径:" + filePath);
                    sendFileMessage(mSenderId, mTargetId, filePath);
                    break;
                case REQUEST_CODE_IMAGE:
                    // 图片选择结果回调
                    List<LocalMedia> selectListPic = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : selectListPic) {
                        LogUtils.d("获取图片路径成功:" + media.getPath());
                        sendImageMessage(media);
                    }
                    break;
                case REQUEST_CODE_VEDIO:
                    // 视频选择结果回调
                    List<LocalMedia> selectListVideo = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : selectListVideo) {
                        LogUtils.d("获取视频路径成功:" + media.getPath());
                        sendVedioMessage(media);
                    }
                    break;
            }
        }
    }


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
//        //下拉刷新模拟获取历史消息
//        List<Message> mReceiveMsgList = new ArrayList<Message>();
//        //构建文本消息
//        Message mMessgaeText = getBaseReceiveMessage(MsgType.TEXT);
//        TextMsgBody mTextMsgBody = new TextMsgBody();
//        mTextMsgBody.setMessage("收到的消息");
//        mMessgaeText.setBody(mTextMsgBody);
//        mReceiveMsgList.add(mMessgaeText);
//        //构建图片消息
//        Message mMessgaeImage = getBaseReceiveMessage(MsgType.IMAGE);
//        ImageMsgBody mImageMsgBody = new ImageMsgBody();
//        mImageMsgBody.setThumbUrl("https://c-ssLogUtils.euitang.com/uploads/item/201208/30/20120830173930_PBfJE.thumb.700_0.jpeg");
//        mMessgaeImage.setBody(mImageMsgBody);
//        mReceiveMsgList.add(mMessgaeImage);
//        //构建文件消息
//        Message mMessgaeFile = getBaseReceiveMessage(MsgType.FILE);
//        FileMsgBody mFileMsgBody = new FileMsgBody();
//        mFileMsgBody.setDisplayName("收到的文件");
//        mFileMsgBody.setSize(12);
//        mMessgaeFile.setBody(mFileMsgBody);
//        mReceiveMsgList.add(mMessgaeFile);
//        mAdapter.addData(0, mReceiveMsgList);
//        mSwipeRefresh.setRefreshing(false);
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
            if (mAdapter.getItemCount() > 0) {
                mRvChat.smoothScrollToPosition(mAdapter.getItemCount() - 1);
            }
        }, 300);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            android.os.Message message = android.os.Message.obtain();
            message.obj = arg;
            handler.sendMessage(message);
        }
    }
}