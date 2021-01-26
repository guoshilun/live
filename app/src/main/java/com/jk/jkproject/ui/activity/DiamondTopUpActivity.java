package com.jk.jkproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.DiamondListAdapter;
import com.jk.jkproject.ui.entity.DiamondTopUpBean;
import com.jk.jkproject.ui.entity.MyDiamondBean;
import com.jk.jkproject.ui.entity.MyMoneyBean;
import com.jk.jkproject.ui.entity.pay.AppPayInfo;
import com.jk.jkproject.ui.entity.pay.PayListener;
import com.jk.jkproject.ui.widget.SpaceDiamondItemDecoration;
import com.jk.jkproject.utils.PayUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yuyan.statusbar.StatusBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/8/24 10:56 AM
 * @desc 充值页面
 */
@Route(path = "/jk/charge")
public class DiamondTopUpActivity extends BActivity implements PayListener , IWXAPIEventHandler {
    private static final String TAG = "DiamondTopUpActivity";
    @BindView(R.id.sbv)
    StatusBarView sbv;
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
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_diamond)
    TextView tvDiamond;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_diamond)
    EditText etDiamond;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_zfb_pay)
    TextView tvZfbPay;
    @BindView(R.id.tv_wx_pay)
    TextView tvWxPay;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private String pay_amt;
    private int pay_type = 0;

    private String diamond[] = {"6000", "30000", "100000", "598000", "998000", "1998000"};
    private String money[] = {"6.00", "30.00", "100.00", "598.00", "998.00", "1998.00"};
    private List<DiamondTopUpBean> diamondTopUpBeans = new ArrayList<>();
    private Unbinder bind;
    private boolean isOk = false;
    private DiamondListAdapter adapter;
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diamond_top_up);
        bind = ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(this, AppApplication.APP_ID_WX);
        api.handleIntent(getIntent(), this);
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        AppApis.getMoney(this);
    }

    private void initView() {
        tvRightTitle.setText("充值记录");
        tvRightTitle.setTextColor(getResources().getColor(R.color.color_FC5E8E));
        tvRightTitle.setTextSize(14);
        tvRightTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("钻石充值");
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvTitle.setTextSize(18);
        String str = getResources().getString(R.string.str_diamond_);
        tvOk.setText(Html.fromHtml(str));
//        AppApis.getMoney(this);
        for (int i = 0; i < diamond.length; i++) {
            DiamondTopUpBean diamondTopUpBean = new DiamondTopUpBean();
            diamondTopUpBean.setDiamond(diamond[i]);
            diamondTopUpBean.setMoney(money[i]);
            diamondTopUpBean.setIsClick(i == 0);
            diamondTopUpBeans.add(diamondTopUpBean);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv.addItemDecoration(new SpaceDiamondItemDecoration(this, 9));
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager((RecyclerView.LayoutManager) gridLayoutManager);
        rv.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        adapter = new DiamondListAdapter(diamondTopUpBeans);
        rv.setAdapter(adapter);
        pay_amt = "6.00";
        adapter.setOnItemClickListener(param1DanmuBean -> {
            if (!etDiamond.getText().toString().isEmpty()) {
                etDiamond.setText("");
                etDiamond.setHint("请输入其他金额（单次最少2元，单次最多10000元）");
            }
            pay_amt = param1DanmuBean.getMoney();
        });
//        InputFilter[] filters = {new EditInputFilter()};
//        etDiamond.setFilters(filters);

        etDiamond.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    if (Integer.parseInt(s.toString().trim()) > 10000) {
                        etDiamond.setText("10000");
                        etDiamond.setSelection(s.toString().trim().length());
                    }
                } else {
                    etDiamond.setHint("请输入其他金额（单次最少2元，单次最多10000元）");
                }
            }
        });
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_MONEY)) {
            if (obj != null && obj instanceof MyMoneyBean) {
                MyMoneyBean bean = (MyMoneyBean) obj;
                if (bean.getCode() == 200) {
                    tvDiamond.setText(bean.getData().getAmount1() + "");
                }
            }
        } else if (url.equals(Urls.GET_DIAMOND)) {
            if (obj != null && obj instanceof MyDiamondBean) {
                MyDiamondBean diamondBean = (MyDiamondBean) obj;
                if (diamondBean.getCode() == 200) {
                    openBrowser(this, diamondBean.getData().getAli());
                    finish();
                } else {
                    ToastUtils.showShortToast(diamondBean.getMsg());
                }
            }
        } else if (url.equals(Urls.GET_APP_PAY_INFO)) {
            if (obj != null && obj instanceof AppPayInfo) {
                AppPayInfo appPayInfo = (AppPayInfo) obj;
                if (appPayInfo.getCode() == 200) {
                    switch (appPayInfo.getData().getPay_type()) {
                        case 0://支付宝
                            PayUtils.payZFB(appPayInfo.getData().getAli(), this, this);
                            break;
                        case 1://微信
                            PayUtils.payWX(appPayInfo.getData().getWx(), this);
                            break;
                    }
                } else {
                    ToastUtils.showShortToast(appPayInfo.getMsg());
                }
            }
        }
    }



    public static void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
//            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            ToastUtils.showShortToast("链接错误或无浏览器");
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        ToastUtils.showShortToast(error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void finish() {
        setResult(1000);
        super.finish();
    }

    @OnClick({R.id.iv_title, R.id.tv_right_title, R.id.tv_zfb_pay, R.id.tv_wx_pay, R.id.tv_ok, R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_right_title:
                intent = new Intent(this, TopUpRecordsActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_zfb_pay:
                pay_type = 0;
                tvZfbPay.setBackgroundResource(R.mipmap.bg_diamond_pay_onclick);
                tvWxPay.setBackgroundResource(R.drawable.bg_diamond_pay_unclick);
                break;
            case R.id.tv_wx_pay:
                pay_type = 1;
                tvWxPay.setBackgroundResource(R.mipmap.bg_diamond_pay_onclick);
                tvZfbPay.setBackgroundResource(R.drawable.bg_diamond_pay_unclick);
                break;
            case R.id.tv_ok:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("type", 12);
                startActivity(intent);
                break;
            case R.id.tv_submit:
                if (!etDiamond.getText().toString().trim().isEmpty()) {
                    pay_amt = etDiamond.getText().toString().trim();
                    if (Integer.parseInt(etDiamond.getText().toString().trim()) < 2) {
                        ToastUtils.showShortToast("单次最少充值2元");
                        return;
                    }
                }
                AppApis.getAppPayInfo(pay_type, pay_amt, this);
//                AppApis.getDiamond(pay_type, pay_amt, this);
                break;
        }
    }

    @Override
    public void onPaySuccess(int type, String result) {
        Looper.prepare();
        ToastUtils.showShortToast("支付成功");
        AppApis.getMoney(this);
        Looper.loop();
    }

    @Override
    public void onPayFailure(int type, String error) {
        Looper.prepare();
        ToastUtils.showShortToast(error);
        Looper.loop();
    }

    @Override
    public void onPayCancel(int type, String cancel) {
        Looper.prepare();
        ToastUtils.showShortToast("支付取消");
        Looper.loop();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        LogUtils.e(TAG, "onPayFinish, errCode = " + baseResp.errCode);
        LogUtils.e(TAG, "onPayFinish, errCode = " + baseResp.toString());
    }
}
