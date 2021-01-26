package com.jk.jkproject.ui.widget.room;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.adapter.LiveRoomPKHeaderAdapter;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.entity.PKBattleBaseInfo;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SeekBarRelatelayout extends RelativeLayout {
    @BindView(R.id.own_win)
    ImageView ownWin;
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.rl_win_header)
    RelativeLayout rlWinHeader;
    @BindView(R.id.im_other)
    ImageView imOther;
    @BindView(R.id.sdv_right_header)
    SimpleDraweeView sdvRightHeader;
    @BindView(R.id.tv_right_name)
    TextView tvRightName;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.rl_pk_right_host)
    RelativeLayout rlPkRightHost;
    @BindView(R.id.pk_seekbar)
    AppCompatSeekBar pkSeekbar;
    @BindView(R.id.tv_own)
    TextView tvOwn;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.relate_seekbar)
    RelativeLayout relateSeekbar;
    @BindView(R.id.rv_pk_left_header)
    RecyclerView rvPkLeftHeader;
    @BindView(R.id.sdv_pk_left_header)
    SimpleDraweeView sdvPkLeftHeader;
    @BindView(R.id.tv_pk_left_name)
    TextView tvPkLeftName;
    @BindView(R.id.tv_pk_left_count)
    TextView tvPkLeftCount;
    @BindView(R.id.rl_pk_left)
    RelativeLayout rlPkLeft;
    @BindView(R.id.im_left)
    ImageView imLeft;
    @BindView(R.id.linear_left)
    RelativeLayout linearLeft;
    @BindView(R.id.rv_pk_right_header)
    RecyclerView rvPkRightHeader;
    @BindView(R.id.sdv_pk_right_header)
    SimpleDraweeView sdvPkRightHeader;
    @BindView(R.id.tv_pk_right_name)
    TextView tvPkRightName;
    @BindView(R.id.tv_pk_right_count)
    TextView tvPkRightCount;
    @BindView(R.id.rl_pk_right)
    RelativeLayout rlPkRight;
    @BindView(R.id.im_right)
    ImageView imRight;
    @BindView(R.id.linear_right)
    RelativeLayout linearRight;
    private SeekBarListener listener;
    private ObjectAnimator objectAnimatorLeft, objectAnimatorRight, objectAnimatorHeader, objectAnimatorVS, pKLeftEndOA, pkMvpOA;
    private int translationX;
    private Context mContext;
    private List<PKBattleBaseInfo.aMessage> dataLeftBeans = new ArrayList<>();
    private List<PKBattleBaseInfo.aMessage> dataRightBeans = new ArrayList<>();
    private LiveRoomPKHeaderAdapter mAdapter, mAdapterRight;
    private String aUserId;
    private String pkId;
    private String roomId;
    private JSONObject object;
    private float startLeftX, startLeftY, startRightX, startRightY, endX, endY;
    private PKBattleBaseInfo baseInfo;
    private boolean isFast = true;

    public void setDialogClickListener(SeekBarListener listener) {
        this.listener = listener;
    }


    public static interface SeekBarListener {
        void onClick(int type, PKBattleBaseInfo baseInfo);
    }

    public SeekBarRelatelayout(Context context) {
        super(context);
        init(context);
    }

    public SeekBarRelatelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SeekBarRelatelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    //贡献榜参数
    public void setContribution(String aUserId, String pkId, String roomId) {
        this.aUserId = aUserId;
        this.pkId = pkId;
        this.roomId = roomId;
    }

    public void setData(LiveRoomWheatBaseInfo.DataBean info) {
        if (info != null) {
            sdvRightHeader.setImageURI(info.getPicture());
            tvRightName.setText(info.getNickname());
            tvUserLevel.setText(info.getAnchorGrade());
            UserLevelSetUtils.setHostLevel(tvUserLevel, info.getAnchorGrade());
            if (info.getLeftPkMaxVictory() != null) {
                setMaxVictory(1, info.getLeftPkMaxVictory());
            }
            if (info.getRightPkMaxVictory() != null) {
                setMaxVictory(2, info.getRightPkMaxVictory());
            }
        }
    }


    int aReceiveNumber, bReceiveNumber;

    public void setSendGiftHeader(PKBattleBaseInfo baseInfo, String roomId) {
        this.baseInfo = baseInfo;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            int maxNum = Math.toIntExact(baseInfo.getaReceiveNumber() + baseInfo.getbReceiveNumber());
            aReceiveNumber = Math.toIntExact(baseInfo.getaReceiveNumber());
            bReceiveNumber = Math.toIntExact(baseInfo.getbReceiveNumber());
            pkSeekbar.setMax(maxNum);
        }

        if (baseInfo.getaReceiveNumber() == 1) {
            baseInfo.setaReceiveNumber(0);
        }
        if (baseInfo.getbReceiveNumber() == 1) {
            baseInfo.setbReceiveNumber(0);
        }
        if (roomId.equals(baseInfo.getaRoomId())) {
            tvOwn.setText("我方战力 " + baseInfo.getaReceiveNumber());
            tvOther.setText("对方战力 " + baseInfo.getbReceiveNumber());
            pkSeekbar.setProgress(aReceiveNumber);
            if (baseInfo.getaMessage() != null && !baseInfo.getaMessage().equals("")) {
                setLeftData(baseInfo.getaMessages());
            }
            if (baseInfo.getbMessage() != null && !baseInfo.getbMessage().equals("")) {
                setRightData(baseInfo.getbMessages());
            }
        } else {
            tvOwn.setText("我方战力 " + baseInfo.getbReceiveNumber());
            tvOther.setText("对方战力 " + baseInfo.getaReceiveNumber());
            pkSeekbar.setProgress(bReceiveNumber);
            if (baseInfo.getbMessage() != null && !baseInfo.getbMessage().equals("")) {
                setLeftData(baseInfo.getbMessages());
            }
            if (baseInfo.getaMessage() != null && !baseInfo.getaMessage().equals("")) {
                setRightData(baseInfo.getaMessages());
            }
        }
    }


    private void setLeftData(List<PKBattleBaseInfo.aMessage> baseInfo) {
        if (baseInfo.size() == 3) {
            mAdapter.clear(true);
            dataLeftBeans.set(2, baseInfo.get(0));
            dataLeftBeans.set(1, baseInfo.get(1));
            dataLeftBeans.set(0, baseInfo.get(2));
            mAdapter.addAll(dataLeftBeans, true);
//            mAdapter.addAll(baseInfo, true);
        } else if (baseInfo.size() == 2) {
            mAdapter.clear(true);
            dataLeftBeans.set(2, baseInfo.get(0));
            dataLeftBeans.set(1, baseInfo.get(1));
            mAdapter.addAll(dataLeftBeans, true);
//            mAdapter.notifyDataSetChanged();
        } else if (baseInfo.size() == 1) {
            mAdapter.clear(true);
            dataLeftBeans.set(2, baseInfo.get(0));
//            mAdapter.notifyDataSetChanged();
            mAdapter.addAll(dataLeftBeans, true);
        }
    }

    private void setRightData(List<PKBattleBaseInfo.aMessage> info) {
        if (info.size() == 3) {
            mAdapterRight.clear(true);
            mAdapterRight.addAll(info, true);
        } else if (info.size() == 2) {
            mAdapterRight.clear(true);
            dataRightBeans.set(0, info.get(0));
            dataRightBeans.set(1, info.get(1));
//            mAdapter.notifyDataSetChanged();
            mAdapterRight.addAll(dataRightBeans, true);
        } else if (info.size() == 1) {
            mAdapterRight.clear(true);
            dataRightBeans.set(0, info.get(0));
            mAdapterRight.addAll(dataRightBeans, true);
//            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * @param type       1.左边 2.右
     * @param maxVictory
     */
    private void setMaxVictory(int type, String maxVictory) {
        try {
            object = new JSONObject(maxVictory);
            int max = object.getInt("maxVictory");
            String nickname = object.getString("nickname");
            String picture = object.getString("picture");
            switch (type) {
                case 1:
                    sdvPkLeftHeader.setImageURI(picture);
                    tvPkLeftName.setText(nickname);
                    tvPkLeftCount.setText(max + "");
                    break;
                case 2:
                    sdvPkRightHeader.setImageURI(picture);
                    tvPkRightName.setText(nickname);
                    tvPkRightCount.setText(max + "");
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_live_pk_seek, this, true);
        ownWin = findViewById(R.id.own_win);
        rlWinHeader = findViewById(R.id.rl_win_header);
        sdvHeader = findViewById(R.id.sdv_header);
        imOther = findViewById(R.id.im_other);
        rlPkRightHost = findViewById(R.id.rl_pk_right_host);
        sdvRightHeader = findViewById(R.id.sdv_right_header);
        tvRightName = findViewById(R.id.tv_right_name);
        tvUserLevel = findViewById(R.id.tv_user_level);
        relateSeekbar = findViewById(R.id.relate_seekbar);
        pkSeekbar = findViewById(R.id.pk_seekbar);
        tvOwn = findViewById(R.id.tv_own);
        tvOther = findViewById(R.id.tv_other);
        rvPkLeftHeader = findViewById(R.id.rv_pk_left_header);
        sdvPkLeftHeader = findViewById(R.id.sdv_pk_left_header);
        tvPkLeftName = findViewById(R.id.tv_pk_left_name);
        tvPkLeftCount = findViewById(R.id.tv_pk_left_count);
        rlPkLeft = findViewById(R.id.rl_pk_left);
        imLeft = findViewById(R.id.im_left);
        linearLeft = findViewById(R.id.linear_left);
        rvPkRightHeader = findViewById(R.id.rv_pk_right_header);
        sdvPkRightHeader = findViewById(R.id.sdv_pk_right_header);
        tvPkRightName = findViewById(R.id.tv_pk_right_name);
        tvPkRightCount = findViewById(R.id.tv_pk_right_count);
        rlPkRight = findViewById(R.id.rl_pk_right);
        imRight = findViewById(R.id.im_right);
        linearRight = findViewById(R.id.linear_right);
        mAdapter = new LiveRoomPKHeaderAdapter(mContext, dataLeftBeans, 1);
//        rvPkLeftHeader.addItemDecoration(new SpaceItemPKDecoration(mContext, 1));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvPkLeftHeader.setLayoutManager(gridLayoutManager);
        rvPkLeftHeader.setAdapter(mAdapter);
        mAdapterRight = new LiveRoomPKHeaderAdapter(mContext, dataRightBeans, 2);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(mContext, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//        rvPkRightHeader.addItemDecoration(new SpaceItemPKRightDecoration(mContext, 2));
        rvPkRightHeader.setLayoutManager(gridLayoutManager1);
        rvPkRightHeader.setAdapter(mAdapterRight);

        mAdapter.setOnItemClickListener(new LiveRoomPKHeaderAdapter.OnItemClickListener() {
            @Override
            public void onClick(int type, PKBattleBaseInfo.aMessage info) {
                if (listener != null) {
                    listener.onClick(0, baseInfo);
                }
            }

            @Override
            public void onClick(int position, RelativeLayout rlHeader, ImageView ivRankCount) {
                setHeaderAnim(position, rlHeader, ivRankCount);
            }
        });

        mAdapterRight.setOnItemClickListener(new LiveRoomPKHeaderAdapter.OnItemClickListener() {
            @Override
            public void onClick(int type, PKBattleBaseInfo.aMessage info) {
                if (listener != null) {
                    listener.onClick(1, baseInfo);
                }
            }

            @Override
            public void onClick(int position, RelativeLayout rlHeader, ImageView ivRankCount) {
                setHeaderAnim(position, rlHeader, ivRankCount);
            }
        });
        setInitData();
        startLeftX = ownWin.getTranslationX();
        startLeftY = ownWin.getTranslationY();
        startRightX = imOther.getTranslationX();
        startRightY = imOther.getTranslationY();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(context) / 2 + ScreenUtils.dp2px(context, 20), ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(context) / 2 + ScreenUtils.dp2px(context, 20), ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, ScreenUtils.dp2px(mContext, 280), 0, 0);
        layoutParams1.setMargins(0, ScreenUtils.dp2px(mContext, 280), 0, 0);
        linearLeft.setLayoutParams(layoutParams);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        linearRight.setLayoutParams(layoutParams1);

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(ScreenUtils.dp2px(context, 140), ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(ScreenUtils.dp2px(context, 140), ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams2.setMargins(0, ScreenUtils.dp2px(mContext, 280), 0, 0);
//        layoutParams3.setMargins(0, ScreenUtils.dp2px(mContext, 280), 0, 0);
        rvPkLeftHeader.setLayoutParams(layoutParams2);
        layoutParams3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rvPkRightHeader.setLayoutParams(layoutParams3);

        pkSeekbar.setOnTouchListener((v, event) -> true);
    }


    public void setPKHeader() {

    }

    //设置初始数据
    public void setInitData() {
        isFast = true;
        dataLeftBeans.clear();
        dataRightBeans.clear();
        for (int i = 0; i < 3; i++) {
            PKBattleBaseInfo.aMessage info = new PKBattleBaseInfo.aMessage();
            dataLeftBeans.add(info);
        }
        for (int i = 0; i < 3; i++) {
            PKBattleBaseInfo.aMessage info = new PKBattleBaseInfo.aMessage();
            dataRightBeans.add(info);
        }
        mAdapter.clear(true);
        mAdapterRight.clear(true);
        mAdapterRight.addAll(dataRightBeans, true);
        mAdapter.addAll(dataLeftBeans, true);
//            imOther.setOnClickListener(v -> setInitData());
    }

    //胜利展示
    public void setOwnWinData(LiveRoomWheatBaseInfo.DataBean ownWinData) {
        sdvHeader.setImageURI(ownWinData.getPicture());
        switch (ownWinData.getCondition()) {
            case 1:
                imOther.setImageResource(R.mipmap.icon_pk_other);
                ownWin.setImageResource(R.mipmap.icon_pk_win);
                break;
            case 2:
                ownWin.setImageResource(R.mipmap.icon_pk_other);
                imOther.setImageResource(R.mipmap.icon_pk_win);
                break;
        }
        setResetOwn();
        startPKLeftEndAnim(1);
        startPKLeftEndAnim(2);
    }

    private void setResetOwn() {
        imOther.setTranslationX(startRightX);
        imOther.setTranslationY(startRightY);
        ownWin.setTranslationX(startLeftX);
        ownWin.setTranslationY(startLeftY);
    }

    //pk开始时动画
    public void startAnim() {
        objectAnimatorLeft = ObjectAnimator.ofFloat(linearLeft, "translationX", -getResources().getDimensionPixelSize(R.dimen.dp_220), 0);
        objectAnimatorLeft.setDuration(400);
        objectAnimatorLeft.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                hostHeader(1, rlPkLeft, imLeft);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimatorRight = ObjectAnimator.ofFloat(linearRight, "translationX", getResources().getDimensionPixelSize(R.dimen.dp_220), 0);
        objectAnimatorRight.setDuration(400);
        objectAnimatorRight.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                hostHeader(2, rlPkRight, imRight);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimatorLeft.start();
        objectAnimatorRight.start();
    }

    private void hostHeader(int type, View view, View pkView) {
        view.setVisibility(VISIBLE);
        if (type == 1) {
            translationX = -getResources().getDimensionPixelSize(R.dimen.dp_6);
        } else {
            translationX = getResources().getDimensionPixelSize(R.dimen.dp_6);
        }
        objectAnimatorHeader = ObjectAnimator.ofFloat(view, "translationX", translationX, 0);
        objectAnimatorHeader.setDuration(200);
        objectAnimatorHeader.setStartDelay(200);
        objectAnimatorHeader.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startVSAnim(type, pkView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimatorHeader.start();
    }


    //vs的动画
    private void startVSAnim(int type, View v) {
        v.setVisibility(VISIBLE);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofKeyframe("alpha",
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.25f, 1f),
                Keyframe.ofFloat(0.5f, 1f),
                Keyframe.ofFloat(1f, 1f)
        );
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX",
                Keyframe.ofFloat(0f, 10f),
                Keyframe.ofFloat(0.25f, 1f),
                Keyframe.ofFloat(0.5f, 1.25f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY",
                Keyframe.ofFloat(0f, 10f),
                Keyframe.ofFloat(0.25f, 1f),
                Keyframe.ofFloat(0.5f, 1.25f),
                Keyframe.ofFloat(1f, 1f)
        );
        objectAnimatorVS = ObjectAnimator.ofPropertyValuesHolder(v, alpha, scaleX, scaleY);
        objectAnimatorVS.setDuration(480);
        if (type == 2) {
            objectAnimatorVS.setStartDelay(200);
        }
        objectAnimatorVS.start();
        objectAnimatorVS.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setPkView();
                if (listener != null) {
                    listener.onClick(2, baseInfo);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    /**
     * //PK结束胜利动画
     *
     * @param type 1.我方胜利 2.对方胜利
     */
    private View view;
    private float startX, startY;

    public void startPKLeftEndAnim(int type) {
        imOther.setVisibility(VISIBLE);
        ownWin.setVisibility(VISIBLE);
        switch (type) {
            case 1:
                view = ownWin;
                startX = startLeftX;
                startY = startLeftY;
//                endX = view.getTranslationX() - getResources().getDimensionPixelSize(R.dimen.dp_52);
//                endY = view.getTranslationY() + getResources().getDimensionPixelSize(R.dimen.dp_100);
                endX = startLeftX - ScreenUtils.dp2px(mContext, 52);
                endY = startLeftY + ScreenUtils.dp2px(mContext, 100);
                break;
            case 2:
                view = imOther;
                startX = startRightX;
                startY = startRightY;
                endX = startRightX - ScreenUtils.dp2px(mContext, 52);
                endY = startRightY + ScreenUtils.dp2px(mContext, 100);
                break;
        }
        PropertyValuesHolder alpha = PropertyValuesHolder.ofKeyframe("alpha",
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, 1f),
                Keyframe.ofFloat(1f, 1f)
        );
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX",
                Keyframe.ofFloat(0f, 3f),
                Keyframe.ofFloat(0.3f, 1f),
                Keyframe.ofFloat(0.4f, 1.2f),
                Keyframe.ofFloat(0.5f, 1f),
                Keyframe.ofFloat(0.8f, 0.8f)
        );
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY",
                Keyframe.ofFloat(0f, 3f),
                Keyframe.ofFloat(0.3f, 1f),
                Keyframe.ofFloat(0.4f, 1.2f),
                Keyframe.ofFloat(0.5f, 1f),
                Keyframe.ofFloat(0.8f, 0.8f)
        );

        PropertyValuesHolder mvpAlpha = PropertyValuesHolder.ofKeyframe("alpha",
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, 1f),
                Keyframe.ofFloat(0.9f, 1f),
                Keyframe.ofFloat(1f, 0f)
        );

        PropertyValuesHolder mvpScaleX = PropertyValuesHolder.ofKeyframe("scaleX",
                Keyframe.ofFloat(0f, 0.1f),
                Keyframe.ofFloat(0.1f, 1f),
                Keyframe.ofFloat(0.15f, 0.8f),
                Keyframe.ofFloat(0.2f, 1f),
                Keyframe.ofFloat(1f, 1f)
        );
        PropertyValuesHolder mvpScaleY = PropertyValuesHolder.ofKeyframe("scaleY",
                Keyframe.ofFloat(0f, 0.1f),
                Keyframe.ofFloat(0.1f, 1f),
                Keyframe.ofFloat(0.15f, 0.8f),
                Keyframe.ofFloat(0.2f, 1f),
                Keyframe.ofFloat(1f, 1f)
        );

        AnimatorSet animatorSet = new AnimatorSet();  //组合动画

        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", startX, endX);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", startY, endY);
        translationX.setStartDelay(1600);
        translationY.setStartDelay(1600);
        translationX.setDuration(2000);
        translationY.setDuration(2000);
        pKLeftEndOA = ObjectAnimator.ofPropertyValuesHolder(view, alpha, scaleX, scaleY);
        pKLeftEndOA.setDuration(2000);
        pkMvpOA = ObjectAnimator.ofPropertyValuesHolder(rlWinHeader, mvpAlpha, mvpScaleX, mvpScaleY);
        pkMvpOA.setStartDelay(2000);
        pkMvpOA.setDuration(5000);
        animatorSet.playTogether(pKLeftEndOA, pkMvpOA, translationX, translationY); //设置动画
        animatorSet.start(); //启动
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void setPkView() {
        rlPkLeft.setVisibility(GONE);
        rlPkRight.setVisibility(GONE);
        rvPkLeftHeader.setVisibility(VISIBLE);
        rvPkRightHeader.setVisibility(VISIBLE);
    }

    public void relase() {
        ownWin.setVisibility(INVISIBLE);
        imOther.setVisibility(INVISIBLE);
        rlWinHeader.setAlpha(0);
        imLeft.setVisibility(INVISIBLE);
        imRight.setVisibility(INVISIBLE);
        pkSeekbar.setMax(100);
        pkSeekbar.setProgress(50);
        tvOwn.setText("我方战力 0");
        tvOther.setText("对方战力 0");
        setInitData();
    }


    public void initView() {

    }

    private void setHeaderAnim(int type, View v, View rank) {
        v.setVisibility(View.GONE);
        rank.setVisibility(View.GONE);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofKeyframe("alpha",
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(1f, 1f)
        );
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX",
                Keyframe.ofFloat(0f, 0.1f),
                Keyframe.ofFloat(0.25f, 1f),
                Keyframe.ofFloat(0.5f, 0.8f),
                Keyframe.ofFloat(1f, 1f)
        );
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY",
                Keyframe.ofFloat(0f, 0.1f),
                Keyframe.ofFloat(0.25f, 1f),
                Keyframe.ofFloat(0.5f, 0.8f),
                Keyframe.ofFloat(1f, 1f)
        );

        objectAnimatorHeader = ObjectAnimator.ofPropertyValuesHolder(v, alpha, scaleX, scaleY);
        switch (type) {
            case 0:
//                objectAnimatorHeader.setStartDelay(400);
                break;
            case 1:
                objectAnimatorHeader.setStartDelay(200);
                break;
            case 2:
                objectAnimatorHeader.setStartDelay(400);
                break;
        }
        objectAnimatorHeader.setDuration(300);
        objectAnimatorHeader.start();
        objectAnimatorHeader.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                v.setVisibility(View.VISIBLE);
                rank.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimatorHeader.start();
    }
}
