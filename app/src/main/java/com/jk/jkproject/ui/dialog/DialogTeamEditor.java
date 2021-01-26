package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jk.jkproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogTeamEditor extends BaseDialog {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_old_name)
    TextView tvOldName;
    @BindView(R.id.tv_new_name)
    TextView tvNewName;
    @BindView(R.id.et_team_name)
    EditText etTeamName;
    @BindView(R.id.ll_team_editor_name)
    RelativeLayout llTeamEditorName;
    @BindView(R.id.tv_team_init)
    TextView tvTeamInit;
    @BindView(R.id.et_int)
    EditText etInt;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.ll_introduction_name_editor)
    LinearLayout llIntroductionNameEditor;
    @BindView(R.id.ll_team_editor_init)
    RelativeLayout llTeamEditorInit;
    @BindView(R.id.tv_dialog_amend)
    TextView tvDialogAmend;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    private int mType;

    private String titleName;

    private String userName;

    public DialogTeamEditor(Context paramContext, int paramInt, String paramString1, String paramString2) {
        super(paramContext);
        this.mContext = paramContext;
        this.mType = paramInt;
        this.userName = paramString1;
        this.titleName = paramString2;
    }

    private void amend() {
        switch (this.mType) {
            default:
                return;
            case 2:
                if (this.etInt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this.context, "新用户简介不能为空！", 0).show();
                    return;
                }
                if (listener != null)
                  listener.onDialogReturnClick(this.etInt.getText().toString().trim());
                dismiss();
                break;
            case 1:
                if (this.etTeamName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this.context, "新用户昵称不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                DialogReturnListener dialogReturnListener = this.listener;
                if (dialogReturnListener != null)
                    dialogReturnListener.onDialogReturnClick(this.etTeamName.getText().toString().trim());
                dismiss();
                break;
        }

    }

    private void init() {
        this.tvName.setText(this.titleName);
        this.ivClose.setOnClickListener(this);
        this.tvDialogAmend.setOnClickListener(this);
        this.etInt.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
                if (!param1Editable.toString().trim().isEmpty()) {
                    if (param1Editable.toString().trim().length() <= 60) {
                        TextView textView = DialogTeamEditor.this.tvCount;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(param1Editable.toString().length());
                        stringBuilder.append("/60");
                        textView.setText(stringBuilder.toString());
                    } else {
                        Toast.makeText(DialogTeamEditor.this.context, "输入字数已达上限", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    TextView textView = DialogTeamEditor.this.tvCount;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(param1Editable.toString().length());
                    stringBuilder.append("/60");
                    textView.setText(stringBuilder.toString());
                }
            }

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            }

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            }
        });
        this.etTeamName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
                if (param1Editable.toString().trim().isEmpty() || param1Editable.toString().trim().length() <= 5)
                    return;
                Toast.makeText(DialogTeamEditor.this.context, "输入字数已达上限", Toast.LENGTH_SHORT).show();
            }

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            }

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            }
        });
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_team_editor);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.h = 600;
        this.gravity = 80;
    }

    protected void initView() {
        TextView textView = this.tvOldName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("昵称：");
        stringBuilder.append(this.userName);
        textView.setText(stringBuilder.toString());
        switch (this.mType) {
            case 2:
                this.llTeamEditorName.setVisibility(View.GONE);
                this.llTeamEditorInit.setVisibility(View.VISIBLE);
                break;
            case 1:
                this.llTeamEditorName.setVisibility(View.VISIBLE);
                this.llTeamEditorInit.setVisibility(View.GONE);
                break;
        }
        init();
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick({R.id.tv_name, R.id.iv_close, R.id.tv_old_name, R.id.tv_new_name, R.id.et_team_name, R.id.ll_team_editor_name, R.id.tv_team_init, R.id.et_int, R.id.tv_count, R.id.ll_introduction_name_editor, R.id.ll_team_editor_init, R.id.tv_dialog_amend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_old_name:
                break;
            case R.id.tv_new_name:
                break;
            case R.id.et_team_name:
                break;
            case R.id.ll_team_editor_name:
                break;
            case R.id.tv_team_init:
                break;
            case R.id.et_int:
                break;
            case R.id.tv_count:
                break;
            case R.id.ll_introduction_name_editor:
                break;
            case R.id.ll_team_editor_init:
                break;
            case R.id.tv_dialog_amend:
                amend();
                break;
        }
    }

    public static interface DialogReturnListener {
        void onDialogReturnClick(String param1String);
    }
}