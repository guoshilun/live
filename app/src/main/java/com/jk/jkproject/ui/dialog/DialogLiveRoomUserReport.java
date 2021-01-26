package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.okhttp.ResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogLiveRoomUserReport extends BaseDialog implements ResponseListener {
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_7)
    TextView tv7;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    private String uid;

    public DialogLiveRoomUserReport(Context paramContext, String paramString) {
        super(paramContext);
        this.mContext = paramContext;
        this.uid = paramString;
    }

    private void initData() {
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_user_report);
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
                    ToastUtils.showShortToast("操作成功");
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
    }

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6, R.id.tv_7})
    public void onViewClicked(View paramView) {
        switch (paramView.getId()) {
            default:
                break;
            case R.id.tv_7:
                dismiss();
                break;
            case R.id.tv_6:
//                AppApis.getReportUser(6, this.uid, this);
                if (listener != null) {
                    listener.onDialogReturnClick(uid, 6);
                }
                break;
            case R.id.tv_5:
                AppApis.getReportUser(5, this.uid, this);
                break;
            case R.id.tv_4:
                AppApis.getReportUser(4, this.uid, this);
                break;
            case R.id.tv_3:
                AppApis.getReportUser(3, this.uid, this);
                break;
            case R.id.tv_2:
                AppApis.getReportUser(2, this.uid, this);
                break;
            case R.id.tv_1:
                AppApis.getReportUser(1, this.uid, this);
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
