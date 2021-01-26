package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogLiveRoomUserReport1 extends BaseDialog implements ResponseListener {
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_7)
    TextView tv7;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    private String uid;

    public DialogLiveRoomUserReport1(Context paramContext, String uid) {
        super(paramContext);
        this.mContext = paramContext;
        this.uid = uid;
    }

    private void initData() {
    }

    public void setData(String uid, int isBlock) {
        switch (isBlock) {
            case 0:
                tv2.setText("拉黑");
                break;
            case 1:
                tv2.setText("取消拉黑");
                break;
        }
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_user_report_1);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = 80;
    }

    protected void initView() {
        initData();
    }

    public void onClick(View paramView) {
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_REPORT_USER))
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    ToastUtils.showShortToast("举报成功");
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
    }

    @OnClick({R.id.tv_1, R.id.tv_2})
    public void onViewClicked(View paramView) {
        switch (paramView.getId()) {
            default:
                break;
            case R.id.tv_7:
                dismiss();
                break;
            case R.id.tv_2:
                if (listener != null) {
                    listener.onDialogReturnClick(uid, 2);
                }
                break;
            case R.id.tv_1:
//                AppApis.getReportUser(1, this.uid, this);
                ToastUtils.showShortToast("举报成功");
                break;
        }
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }


    public static interface DialogReturnListener {
        void onDialogReturnClick(String param1String, int param1Int);
    }
}
