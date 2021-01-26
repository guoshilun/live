package com.jk.jkproject.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.ui.entity.JoinRoomBeanInfo;
import com.jk.jkproject.ui.entity.LiveRoomBeanList;
import com.jk.jkproject.ui.entity.ScrollEvent;
import com.jk.jkproject.ui.fragment.LiveRoomFragment;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.ui.widget.VerticalViewPager;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.FrescoUtil;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.Zego;
import com.yuyan.statusbar.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SlideActivity extends BActivity implements Observer {
    private static int MESSAGE_TYPE = 291;
    private static final int RECONNECT_TIME_DISCONNECT = 1200;

    private static final int RECONNECT_TIME_HOST_LEAVE = 1200;

    private static final int SELECT_DELAY = 1000;

    private static final int SELECT_FIRST = 1;

    private static final int SELECT_LAST = 2;

    public static final String TAG = "SlideActivity";

    private int entrance;

    private RelativeLayout frameVideo;

    private ImageView imPkBackGround;

    private boolean isCanAdd = false;

    private boolean isFirst = false;

    private boolean isHostLeave = true;

    private boolean isPk = false;

    private List<LiveRoomBeanList.DataBean> liveCardInfos;

    private int mCurrentItem;

    private FrameLayout mFragmentContainer;

    private FragmentManager mFragmentManager;

    private boolean mInit = false;
    private String mOid;

    private PagerAdapter mPagerAdapter;
    private TextureView mPusherView;
    private SimpleDraweeView mImgLoading;

    private RelativeLayout mRoomContainer;

    private LiveRoomFragment mRoomFragment;

    private int mRoomId = -1;

    private String mVideoPath = null;

    private VerticalViewPager mViewPager;

    private String rtmpUrl = "rtmp://py.quokka.top/live/3?txSecret=291f0cc299dfc29a3467a320dad2ee1e&txTime=5F71D66C";

    private String streamID = "";
    private JoinRoomBeanInfo roomBeanInfo = null;
    private SurfaceHolder holder;
    private String roomId;
    private SurfaceTexture surfaceTexture;

    private void loadVideoAndChatRoom(ViewGroup viewGroup, int currentItem) {
        if (!mInit) {
            if (mRoomFragment != null) {
                mFragmentManager.beginTransaction().add(mFragmentContainer.getId(), mRoomFragment).commitAllowingStateLoss();
                mInit = true;
            }
        }
        viewGroup.addView(mRoomContainer);
        mRoomId = currentItem % liveCardInfos.size();
        EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_ENTER, liveCardInfos.get(mRoomId)));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ScrollEvent paramScrollEvent) {
        if (mViewPager != null) {
            LogUtils.e("Event=", paramScrollEvent.isCanScroll());
            mViewPager.setNoScroll(paramScrollEvent.isCanScroll());
        }
    }

    public boolean isBindEventBusHere() {
        return true;
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }

    @Override
    public boolean isHideVirtual() {
        return false;
    }

    public void onBackPressed() {
        finish();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        window.setAttributes(layoutParams);
        setContentView(R.layout.activity_room_forlive);
        MessageNotifyCenter.getInstance().addObserver(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        StatusBarCompat.setStatusBarColor((Activity) this, getResources().getColor(R.color.black_alpha));
        AppApis.getLiveRoomList(this);
        liveCardInfos = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            roomId = intent.getStringExtra("roomId");
        }
        mViewPager = (VerticalViewPager) findViewById(R.id.my_view_pager);
        mRoomContainer = (RelativeLayout) LayoutInflater.from((Context) this).inflate(R.layout.view_room_container, null);
        mFragmentContainer = (FrameLayout) this.mRoomContainer.findViewById(R.id.fragment_container);
        frameVideo = (RelativeLayout) this.mRoomContainer.findViewById(R.id.frame_video);
        mPusherView = (TextureView) this.mRoomContainer.findViewById(R.id.texture_view);
        mImgLoading = (SimpleDraweeView) this.mRoomContainer.findViewById(R.id.img_loading);
        imPkBackGround = (ImageView) this.mRoomContainer.findViewById(R.id.im_pk_bg);
        mFragmentManager = getSupportFragmentManager();
        mPagerAdapter = new VideoPagerAdapter();
        LiveRoomFragment.mActivity = this;
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
                LogUtils.e("onPageScrollStateChanged");
                if (state != ViewPager.SCROLL_STATE_IDLE) {
                    return;
                }
                if (mCurrentItem == 0) {
                    LogUtils.e("mCurrentItem");
                    isCanAdd = true;
                    mViewPager.setCurrentItem(liveCardInfos.size(), true);
                    if (mViewPager != null) {
                        mViewPager.setNoScroll(false);
                    }
                } else {
                    if (mViewPager != null) {
                        mViewPager.setNoScroll(false);
                    }
                }
            }

            public void onPageScrolled(int position, float param1Float, int param1Int2) {
                mCurrentItem = position;

            }

            public void onPageSelected(int position) {
                mCurrentItem = position;
                if (mViewPager != null) {
                    // TODO: 2019-09-16
                    mViewPager.setNoScroll(true);
                }
            }
        });
        this.mViewPager.setPageTransformer(false, (page, position) -> {
            ViewGroup viewGroup = (ViewGroup) page;
            if ((position < 0 && viewGroup.getId() != mCurrentItem)) {
                View roomContainer = viewGroup.findViewById(R.id.room_container);
                if (roomContainer != null && roomContainer.getParent() != null && roomContainer.getParent() instanceof ViewGroup) {
                    ((ViewGroup) (roomContainer.getParent())).removeView(roomContainer);
                }
            }
            // 满足此种条件，表明需要加载直播视频，以及聊天室了
            if (viewGroup.getId() == mCurrentItem && position == 0 && mCurrentItem != mRoomId) {
                if (mRoomContainer.getParent() != null && mRoomContainer.getParent() instanceof ViewGroup) {
                    ((ViewGroup) (mRoomContainer.getParent())).removeView(mRoomContainer);
                }
                loadVideoAndChatRoom(viewGroup, mCurrentItem);
            }
        });
    }


    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_LIVE_ROOM_LIST)) {
            if (obj != null && obj instanceof LiveRoomBeanList) {
                LiveRoomBeanList beanList = (LiveRoomBeanList) obj;
                liveCardInfos.clear();
                //直播间列表
                if (beanList.getCode() == 200 && beanList.getData() != null) {
                    liveCardInfos.addAll(beanList.getData());
                    mViewPager.setAdapter(mPagerAdapter);
                    if (liveCardInfos.size() > 0) {
                        boolean isClick = false;
                        for (int i = 0; i < liveCardInfos.size(); i++) {
                            if (liveCardInfos.get(i).getRoomId().equals(roomId)) {
                                mRoomFragment = LiveRoomFragment.newInstance(mCurrentItem, liveCardInfos.get(i), entrance, 0, null, null);
                                mViewPager.setCurrentItem(i, true);
                                mViewPager.setOffscreenPageLimit(3);
                                isClick = true;
                            }
                        }
                        if (!isClick) {
                            Intent intent = new Intent(this, UserLiveActivity.class);
                            intent.putExtra("roomId", roomId);
                            intent.putExtra("type", 1);
                            startActivity(intent);
                            finish();
                        }
                    } else {
//                        setDefaultLiveRoom();
                        Intent intent = new Intent(this, UserLiveActivity.class);
                        intent.putExtra("roomId", roomId);
                        intent.putExtra("type", 1);
                        startActivity(intent);
                        finish();
                    }
                } else {
//                    setDefaultLiveRoom();
                    Intent intent = new Intent(this, UserLiveActivity.class);
                    intent.putExtra("roomId", roomId);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    private void setDefaultLiveRoom() {
        LiveRoomBeanList.DataBean dataBean = new LiveRoomBeanList.DataBean();
        dataBean.setCover("http://sl.file.diaodiaosocial.com/1598004590279.png");
        dataBean.setId(0);
        dataBean.setRoomId(roomId);
        liveCardInfos.add(dataBean);
        mRoomFragment = LiveRoomFragment.newInstance(mCurrentItem, liveCardInfos.get(0), entrance, 0, null, null);
        mViewPager.setCurrentItem(0);
    }

    protected void onDestroy() {
//        Zego.instance().reset();
        if (mViewPager != null) {
            mViewPager.setAdapter(null);
            mViewPager = null;
        }
        if (liveCardInfos != null) {
            liveCardInfos.clear();
            liveCardInfos = null;
        }
        if (mRoomContainer != null)
            mRoomContainer = null;
        if (mPagerAdapter != null)
            mPagerAdapter = null;
        if (mFragmentContainer != null)
            mFragmentContainer = null;
        if (mRoomFragment != null) {
            mRoomFragment = null;
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        MessageNotifyCenter.getInstance().deleteObserver(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PostResult result) {
        super.onEvent(result);
        if (result.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_PLAY_URL)) {
            roomBeanInfo = (JoinRoomBeanInfo) result.getResult();
            if (roomBeanInfo != null) {
                imPkBackGround.setVisibility(View.GONE);
                Zego.instance().initPull(false, roomBeanInfo.getRoomId(), mPusherView);
//                setPkView(false, "", roomBeanInfo.getRoomId());
            }
        } else if (result.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_STREAMPK_PK)) {
            String merageStreamUrl = (String) result.getResult();
            setPkView(true, merageStreamUrl, LiveRoomFragment.LIVE_ROOM_ID);
        } else if (result.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_FINISH_PK)) {
            setPkView(false, "", LiveRoomFragment.LIVE_ROOM_ID);
        }
    }

    //PK布局初始化
    @SuppressLint("ClickableViewAccessibility")
    private void setPkView(boolean isPk, String mergeStreamUrl, String roomId) {
        if (isPk) {
            imPkBackGround.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(this), ScreenUtils.dp2px(this, 261));
            layoutParams.topMargin = ScreenUtils.dp2px(this, 79);
            frameVideo.setLayoutParams(layoutParams);
            Zego.instance().reset();
            Zego.instance().initPlayRtmp(roomId, mergeStreamUrl, mPusherView);
        } else {
            imPkBackGround.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(this), ScreenUtils.getScreenH(this) + ScreenUtils.getStatusBarHeight(this));
            frameVideo.setLayoutParams(layoutParams);
//            Zego.instance().reset();
            Zego.instance().initPull(false, roomId, mPusherView);
        }
    }

    protected void onNewIntent(Intent paramIntent) {
        super.onNewIntent(paramIntent);
        if (mPusherView != null) {
            mPusherView.setVisibility(View.INVISIBLE);
        }
        if (frameVideo != null) {
            frameVideo.setVisibility(View.INVISIBLE);
        }
        if (imPkBackGround != null) {
            imPkBackGround.setVisibility(View.INVISIBLE);
        }
        if (paramIntent != null) {
            mCurrentItem = paramIntent.getIntExtra("postion", 0);
        }

        if (mViewPager != null) {
            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.setCurrentItem(mCurrentItem);
            Animation animation = AnimationUtils.loadAnimation((Context) this, R.anim.anim_alpha_in);
            mViewPager.startAnimation(animation);
        }

    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();

    }

    class VideoPagerAdapter extends PagerAdapter {
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = container.findViewById(position);
            if (view == null) {
                return;
            }
            SimpleDraweeView imageView = view.findViewById(R.id.anchor_img);
            if (imageView != null) {
                imageView.setImageURI("");
                imageView.setBackground(null);
            }
            (container).removeView(view); //核心，解决OOM
        }

        public int getCount() {
            if (liveCardInfos.size() > 1) {
                return Integer.MAX_VALUE;
            } else {
                return 1;
            }
        }

        public int getItemPosition(Object param1Object) {
            return PagerAdapter.POSITION_NONE;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_room_item, null);
            if (liveCardInfos.size() != 0) {
                int lastPosition = position % liveCardInfos.size();
                FrescoUtil.showUrlBlur(view.findViewById(R.id.anchor_img), liveCardInfos.get(lastPosition).getCover());
                view.setId(position);
                container.addView(view);
            }
            return view;
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    public void update(Observable paramObservable, Object paramObject) {
//        if (paramObject != null) {
//            Message message = Message.obtain();
//            message.obj = paramObject;
//            handler.sendMessage(message);
//        }
    }
}