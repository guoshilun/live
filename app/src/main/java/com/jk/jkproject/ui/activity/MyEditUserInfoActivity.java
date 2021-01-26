package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.entity.LiveRoomUserInfo;
import com.jk.jkproject.ui.entity.QiNiuTokenInfo;
import com.jk.jkproject.ui.widget.CustomEditText;
import com.jk.jkproject.utils.KeyBoardUtils;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.QiNiuFileUpdownUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/6/9 10:02 AM
 * @desc 编辑用户信息
 */
public class MyEditUserInfoActivity extends BActivity {


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
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.rl_4)
    RelativeLayout rl4;
    @BindView(R.id.et_sing)
    EditText etSing;
    @BindView(R.id.tv_sign_count)
    TextView tvSignCount;
    @BindView(R.id.et_nick_name)
    CustomEditText etNickName;
    private Unbinder bind;
    private LiveRoomUserInfo.DataBean userInfo;
    private String headPath;
    private String QiNiuUrl = "null";
    private String QiniuToken;
    private OptionsPickerView<String> sexPicker;
    private TimePickerView pvTime;
    private List<String> sexList = new ArrayList<>();
    private String headUrl = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_info);
        bind = ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        sexList.add("男");
        sexList.add("女");
        initCustomOptionPicker();
        initTimePicker();
    }


    private void initView() {
        if (getIntent() != null) {
            tvTitle.setText("编辑资料");
            tvTitle.setTextColor(getResources().getColor(R.color.color_333));
            tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvTitle.setTextSize(18);
            tvRightTitle.setVisibility(View.VISIBLE);
            tvRightTitle.setText("保存");
            userInfo = (LiveRoomUserInfo.DataBean) getIntent().getSerializableExtra("userInfo");
            sdvHeader.setImageURI(userInfo.getPicture());
            etNickName.setText(userInfo.getNickname());
            etNickName.setSelection(userInfo.getNickname().length());
            tvCount.setText(userInfo.getNickname().length() + "/12");
            etSing.setText(userInfo.getSgin());
            tvSignCount.setText(userInfo.getSgin().length() + "/30");
            switch (userInfo.getSex()) {
                case "0":
                    tvSex.setText("女");
                    break;
                case "1":
                    tvSex.setText("男");
                    break;
            }
            tvBirthday.setText(userInfo.getBirthday());
            etNickName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!s.toString().trim().isEmpty()) {
                        if (s.toString().trim().length() <= 12) {
                            tvCount.setText(s.toString().length() + "/12");
                            tvCount.setFocusable(true);
                        } else {
                            tvCount.setFocusable(false);
                        }
                    } else {
                        tvCount.setText(s.toString().length() + "/12");
                        tvCount.setFocusable(true);
                    }
                }
            });
            etSing.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!s.toString().trim().isEmpty()) {
                        if (s.toString().trim().length() <= 30) {
                            tvSignCount.setText(s.toString().length() + "/30");
                            tvSignCount.setFocusable(true);
                        } else {
                            tvSignCount.setFocusable(false);
                        }
                    } else {
                        tvSignCount.setText(s.toString().length() + "/30");
                        tvSignCount.setFocusable(true);
                    }
                }
            });
        }
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_QINIU_TOKEN)) {
            if (obj != null && obj instanceof QiNiuTokenInfo) {
                QiNiuTokenInfo qnToken = (QiNiuTokenInfo) obj;
                if (qnToken.getCode() == 200) {
                    QiNiuUrl = qnToken.getData().getUrl();
                    QiniuToken = qnToken.getData().getQnToken();
                }
            }
        } else if (url.equals(0 + "")) {
            headUrl = QiNiuUrl + obj.toString();
            AppApis.getEditUserInfo(headUrl, etNickName.getText().toString(), tvSex.getText().toString().trim().equals("--") ? "--" : tvSex.getText().toString().trim().equals("男") ? "1" : "0", tvBirthday.getText().toString().trim(), etSing.getText().toString().trim(), this);
        } else if (url.equals(Urls.GET_EDITOR_USER_INFO)) {
            try {
                JSONObject object = new JSONObject(obj.toString());
                if (object.getInt("code") == 200) {
                    if (!headUrl.equals("null")) {
                        SPUtils.setPicture(headUrl);
                    }
                    SPUtils.setNickname(etNickName.getText().toString());
                    SPUtils.setSex(tvSex.getText().toString().trim().equals("--") ? 1 : tvSex.getText().toString().trim().equals("男") ? 1 : 0);
                    SPUtils.setBirthday(tvBirthday.getText().toString().trim());
                    SPUtils.setSgin(etSing.getText().toString().trim());
                    ToastUtils.showShortToast("信息修改成功");
                    finish();
                } else {
                    ToastUtils.showShortToast(object.getString("msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        LogUtils.e("===", error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @OnClick({R.id.iv_title, R.id.tv_right_title, R.id.rl_1, R.id.rl_3, R.id.rl_4})
    public void onViewClicked(View view) {
        KeyBoardUtils.closeKeybord(this, etNickName);
        KeyBoardUtils.closeKeybord(this, etSing);
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.rl_1:
                PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE).selectPicture();
                break;
            case R.id.rl_3:
                if (sexPicker != null) {
                    sexPicker.show();
                }
                break;
            case R.id.rl_4:
                if (pvTime != null) {
                    pvTime.show();
                }
                break;
            case R.id.tv_right_title:
                if (OnClickUtils.isFastClick()) {
                    setSaveEdit();
                }
                break;
        }
    }

    private void questToken() {
        AppApis.getGitPicToken(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                headPath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                if (!headPath.isEmpty()) {
                    File file = new File(headPath);
                    Uri uri = Uri.fromFile(file);
                    sdvHeader.setImageURI(uri);
                    questToken();
                }
            }
        }
    }

    private void setSaveEdit() {
        if (etNickName.getText().toString().trim().isEmpty()) {
            ToastUtils.showLongToast("昵称不能为空");
            return;
        }
        if (!QiNiuUrl.equals("null")) {
            QiNiuFileUpdownUtils.get().upload(0, QiniuToken, headPath, this);
        } else {
            AppApis.getEditUserInfo(userInfo.getPicture(), etNickName.getText().toString().trim(), tvSex.getText().toString().trim().equals("--") ? "--" : tvSex.getText().toString().trim().equals("男") ? "1" : "0", tvBirthday.getText().toString().trim(), etSing.getText().toString().trim(), this);
        }
    }

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        sexPicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = sexList.get(options1);
                tvSex.setText(tx);
            }
        })
//                .setLayoutRes(R.layout.sex_picker, new CustomListener() {
//                    @Override
//                    public void customLayout(View v) {
//                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_ok);
//                        tvSubmit.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                sexPicker.returnData();
//                                sexPicker.dismiss();
//                            }
//                        });
//                    }
//                })
                .setSubmitText("完成")//确定按钮文字
                .setCancelText("请选择你的性别")//取消按钮文字
//                .setTitleText("城市选择")//标题
                .setSubCalSize(16)//确定和取消文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.color_0390FC))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.color_333))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.white))//标题背景颜色 Night mode
//                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                .setContentTextSize(20)//滚轮文字大小
                .isDialog(false)
                .build();
        sexPicker.setPicker(sexList);//添加数据

    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1950, 1, 1);
        Calendar endDate = Calendar.getInstance();
//        endDate.set(2050, 12, 31);
        endDate.setTime(TimeUtils.getNowDate());
        //时间选择器
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                tvBirthday.setText(TimeUtils.date2String(date, "yyyy-MM-dd"));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setSubmitText("完成")//确定按钮文字
                .setCancelText("请选择你的生日")//取消按钮文字
//                .setTitleText("城市选择")//标题
                .setSubCalSize(16)//确定和取消文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.color_0390FC))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.color_333))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.white))//标题背景颜色 Night mode
//                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                .setContentTextSize(20)//滚轮文字大小
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }
}