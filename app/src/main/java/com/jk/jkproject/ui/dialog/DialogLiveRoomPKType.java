package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.LiveRoomPkListAdapter;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.utils.ScreenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc pk类型选择框
 */
public class DialogLiveRoomPKType extends BaseDialog {

    @BindView(R.id.tv_pk_record)
    TextView tvPkRecord;
    @BindView(R.id.ll_1)
    RelativeLayout ll1;
    @BindView(R.id.rv_pk_type)
    RecyclerView rvPkType;
    private DialogReturnListener listener;
    List<PKTypeBaseInfo> baseInfos = new ArrayList<>();
    private Context mContext;
    private Unbinder bind;
    private int mType;
    private LiveRoomPkListAdapter mAdapter;
    private DialogLiveRoomPKRecord mPkRecordDialog;
    private DialogLiveRoomPKList mPKListDialog;
    private int position = -1, position_1 = -1;
    private DialogLiveRoomPKRules mPkRulesDialog;


    public DialogLiveRoomPKType(Context paramContext, int mType) {
        super(paramContext);
        this.mContext = paramContext;
        this.mType = mType;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_pk_type);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.BOTTOM;
    }

    @Override
    public void show() {
        super.show();
        AppApis.getPKStatus(this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_PK_STATUS)) {
            if (obj != null) {
                try {
                    JSONObject object = new JSONObject(obj.toString());
                    if (object.getInt("code") == 200) {
                        JSONObject object1 = new JSONObject(object.getString("data"));
                        int random = object1.getInt("random");
                        int friend = object1.getInt("friend");
                        if (random == 0 && friend == 0) {

                        } else {
                            mAdapter.getItem(0).setSelect(random);
                            mAdapter.getItem(1).setSelect(friend);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (url.equals(Urls.UPDATE_PK_STATUS)) {
            if (obj != null) {
                try {
                    JSONObject object = new JSONObject(obj.toString());
                    if (object.getInt("code") != 200) {
                        if (position == 1) {
                            mAdapter.getItem(0).setSelect(mAdapter.getItem(0).isSelect() == 0 ? 1 : 0);
                        }
                        if (position_1 == 2) {
                            mAdapter.getItem(1).setSelect(mAdapter.getItem(1).isSelect() == 0 ? 1 : 0);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void initView() {
        PKTypeBaseInfo baseInfo = new PKTypeBaseInfo();
        baseInfo.setPicture(R.mipmap.icon_pk_random_pic);
        baseInfo.setDescribe("与神秘人对战，更刺激！");
        baseInfo.setName("随机PK");
        baseInfo.setSgin("接受随机匹配PK");
        baseInfo.setSelect(0);
        baseInfo.setId(1);
        baseInfos.add(baseInfo);
        PKTypeBaseInfo baseInfo1 = new PKTypeBaseInfo();
        baseInfo1.setPicture(R.mipmap.icon_pk_friend_pic);
        baseInfo1.setDescribe("拉上朋友一起玩，更有趣！");
        baseInfo1.setName("好友PK");
        baseInfo1.setSgin("接受好友邀请PK");
        baseInfo1.setSelect(0);
        baseInfo1.setId(2);
        baseInfos.add(baseInfo1);
        mAdapter = new LiveRoomPkListAdapter(mContext, baseInfos, R.layout.live_room_pk_item);
        rvPkType.setNestedScrollingEnabled(false);
        rvPkType.setLayoutManager(new LinearLayoutManager(context));
        rvPkType.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((type, info) -> {
            switch (type) {
                case 0:
                    switch (info.getId()) {
                        case 1:
                            if (listener != null) {
                                listener.onClick(info);
                                dismiss();
                            }
                            break;
                        case 2:
                            if (listener != null) {
                                listener.onItemClick(1, null);
                                dismiss();
                            }
                            break;
                    }
                    break;
                case 1:
                    updatePkStatus(info);
                    break;
            }

        });
    }

    private void updatePkStatus(PKTypeBaseInfo info) {
        switch (info.getId()) {
            case 1:
                position = 1;
                AppApis.updatePKStatus(info.isSelect() == 0 ? 1 : 0, mAdapter.getItem(1).isSelect(), this);
                mAdapter.getItem(0).setSelect(mAdapter.getItem(0).isSelect() == 0 ? 1 : 0);
                break;
            case 2:
                position_1 = 2;
                AppApis.updatePKStatus(mAdapter.getItem(0).isSelect(), info.isSelect() == 0 ? 1 : 0, this);
                mAdapter.getItem(1).setSelect(mAdapter.getItem(0).isSelect() == 0 ? 1 : 0);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.getScreenW(getContext()), ScreenUtils.dp2px(getContext(), 259));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick({R.id.tv_pk_record, R.id.ll_1, R.id.iv_rules})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pk_record:
//                ToastUtils.showLongToast("PK记录");
                showPkRecordDialog();
                break;
            case R.id.iv_rules:
//                ToastUtils.showLongToast("规则");
                showPkRulesDialog();
                break;
            case R.id.ll_1:
                break;
        }
    }

    public static interface DialogReturnListener {
        void onClick(PKTypeBaseInfo info);

        void onItemClick(int type, LiveRoomWheatBaseInfo.DataBean info);
    }

    private void showPkRulesDialog() {
        if (mContext == null) {
            return;
        }
        if (mPkRulesDialog == null) {
            mPkRulesDialog = new DialogLiveRoomPKRules(getContext(), mType);
        }
        mPkRulesDialog.show();
        mPkRulesDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showPkRecordDialog() {
        if (mContext == null) {
            return;
        }
        if (mPkRecordDialog == null) {
            mPkRecordDialog = new DialogLiveRoomPKRecord(getContext(), mType);
        }
        mPkRecordDialog.show();
        mPkRecordDialog.getData(0);
        mPkRecordDialog.setDialogClickListener((type, info) -> {
            switch (type) {
                case 1: //邀请
                    if (listener != null) {
                        LiveRoomWheatBaseInfo.DataBean baseInfo = new LiveRoomWheatBaseInfo.DataBean();
                        baseInfo.setRoomId(info.getRoomId());
                        baseInfo.setAnchorGrade(info.getAnchorGrade());
                        baseInfo.setNickname(info.getNickname());
                        baseInfo.setPicture(info.getPicture());
                        baseInfo.setUserId(info.getUserId());
                        listener.onItemClick(2, baseInfo);
                    }
                    dismiss();
                    break;
                case 2:
                    break;
            }
        });
        mPkRecordDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }
}