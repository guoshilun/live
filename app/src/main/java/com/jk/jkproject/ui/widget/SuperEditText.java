package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.utils.LengthInputFilter;
import com.jk.jkproject.utils.RegularConstants;
import com.jk.jkproject.utils.ToastUtils;

import java.util.Arrays;

public class SuperEditText extends RelativeLayout implements TextWatcher {
  private EditText editText;
  private TextView textView;
  private TextView textViewHead;
  private FrameLayout frameHead;
  private ImageView imgHead;
  private ImageButton button;
  private int ERROR_BG = Color.parseColor("#F78CA7");

  private int NORMAL_BG = Color.parseColor("#FFFFFFFF");

  boolean isError;
  private Integer normalID  = 0;
  private Integer successID  = 0;
  private String format = "" ;
  private int minLength , maxLength  ;

  private ITextWatcher iTextWatcher;

  public SuperEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public SuperEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SuperEditText(Context context) {
    super(context);
  }


  //setIconVisibility
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    setEnabled(false);
    textView = (TextView) findViewById(R.id.super_toast_msg);
    textViewHead = (TextView) findViewById(R.id.super_head);
    editText = (EditText) findViewById(R.id.super_edittext);
    button = (ImageButton) findViewById(R.id.super_delete);
    frameHead = (FrameLayout) findViewById(R.id.super_head_frame);
    imgHead = (ImageView) findViewById(R.id.super_head_icon) ;
    button.setVisibility(INVISIBLE);
    editText.setOnFocusChangeListener(new OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        Editable ss = editText.getText();
        boolean visible = ss.length() > 0 && hasFocus;
        button.setVisibility(visible ? VISIBLE
                : INVISIBLE);
        if (hasFocus) {
          setEnabled(false);
          if (normalID != 0) imgHead.setImageResource(normalID);
          else textViewHead.setTextColor(getResources().getColor(R.color.blackColor));
        } else {
          if (ss.length() > 0) {
            if ((!TextUtils.isEmpty(format) && ss.toString().trim().matches(format)) || (TextUtils
                    .isEmpty(format) && ss.toString().trim().length() <= maxLength && ss.toString().trim
                    ().length() >= minLength)) {
              setEnabled(false);
              if (successID != 0) imgHead.setImageResource(successID);
              else
                textViewHead.setTextColor(getResources().getColor(R.color.app_red_color));
            } else {
              setEnabled(true);
              if (normalID != 0) imgHead.setImageResource(normalID);
              else
                textViewHead.setTextColor(getResources().getColor(R.color.blackColor));
            }
          } else {
            setEnabled(false);
            if (normalID != 0) imgHead.setImageResource(normalID);
            else textViewHead.setTextColor(getResources().getColor(R.color.black_alpha));
          }

        }
      }
    });
    editText.addTextChangedListener(this);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        editText.setText(null);
        editText.requestFocus();
      }
    });
  }

  /**
   * 任意的设置maxLength
   *
   * @param maxLength
   */
  public void setMaxLength2(int maxLength) {
    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    this.maxLength = maxLength;
  }

  public void setOnClickListener(final IViewClickListener listener){
    editText.setInputType(0);
    editText.setFocusable(false);
    editText.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.onClick(SuperEditText.this);
      }
    });
  }

  public void requestDownFocus() {
    if (null != editText) {
      editText.requestFocus(View.FOCUS_DOWN);
    }
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count,
                                int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    button.setVisibility((s.length() > 0 && editText.isFocused()) ? VISIBLE
            : INVISIBLE);
    if (isError) {
      isError = false;
//            setBackgroundColor(NORMAL_BG);
      textView.setText(null);
    }
    if (null != iTextWatcher)
      iTextWatcher.textLenght(this, s.toString().trim().length());
  }

  public boolean isNull() {
    return getText().length() == 0;
  }

  public CharSequence getText() {
    return editText.getText();
  }

  public void showError(CharSequence error) {
    if (null != error && error.length() > 0) {
      textView.setText(error);
//            setBackgroundColor(ERROR_BG);
      ToastUtils.showShortToast(error.toString());
      editText.requestFocus();
      isError = true;
    } else {
      textView.setText(null);
//            setBackgroundColor(NORMAL_BG);
      isError = false;
    }
  }

  public void showError(int resoureID) {
    if (-1 != resoureID) {
      showError(getResources().getString(resoureID));
    } else {
      showError(null);
    }
  }

  public void setSuccessIcon(Integer resID){
    this.successID = resID ;
  }

  public void setTextFormat(String format){
    if (TextUtils.isEmpty(format)){
      format = RegularConstants.FORMAT_PHONE;//手机号
    }
    this.format = format ;
  }


  /**
   * 显示+86头部，注册时显示
   */
  public void showHead(){
    frameHead.setVisibility(VISIBLE);
    textViewHead.setVisibility(VISIBLE);
    imgHead.setVisibility(INVISIBLE);
  }

  /**
   * 显示头部icon
   * @param resId
   */
  public void showHeadIcon(Integer resId) {
    showHeadIcon(resId , false);
  }

  /**
   * 显示头部icon
   * @param resId
   */
  public void showHeadIcon(Integer resId , boolean needBg) {
    frameHead.setVisibility(VISIBLE);
    textViewHead.setVisibility(INVISIBLE);
    imgHead.setImageResource(resId);
    this.normalID = resId ;
    if (!needBg)
      frameHead.setBackgroundResource(0);
  }

  /**
   * 设置EditText的输入类型
   *
   * @param type
   */
  public void setInputType(int type) {
    editText.setInputType(type);
  }

  public void setText(CharSequence text) {
    editText.setText(text);
    try {
      if (null != text)
        editText.setSelection(text.length());
    }catch (Exception e){
    }
    editText.requestFocus();
  }

  public void setMaxLength(int maxlength) {
    InputFilter[] filters = editText.getFilters();
    filters = Arrays.copyOf(filters, filters.length + 1);
    filters[filters.length - 1] = new LengthInputFilter(maxlength);
    this.maxLength = maxlength * 2 ;
    editText.setFilters(filters);
  }

  public void setMinLength(int minLength){
    this.minLength = minLength ;
  }

  public void setTextHint(CharSequence hint) {
    editText.setHint(hint);
  }

  @Override
  public Parcelable onSaveInstanceState() {
    Parcelable superState = super.onSaveInstanceState();
    SavedState ss = new SavedState(superState);
    ss.isError = isError;
    return ss;
  }

  @Override
  public void onRestoreInstanceState(Parcelable state) {
    SavedState ss = (SavedState) state;
    super.onRestoreInstanceState(ss.getSuperState());
    if (ss.isError) {
      showError(textView.getText());
    } else {
      showError(null);
    }
  }

  public void setiTextWatcher(ITextWatcher iTextWatcher) {
    this.iTextWatcher = iTextWatcher;
  }

  static class SavedState extends BaseSavedState {
    boolean isError;
    SavedState(Parcelable superState) {
      super(superState);
    }

    private SavedState(Parcel in) {
      super(in);
      isError = (Boolean) in.readValue(null);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
      super.writeToParcel(out, flags);
      out.writeValue(isError);
    }

    public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
      public SavedState createFromParcel(Parcel in) {
        return new SavedState(in);
      }

      public SavedState[] newArray(int size) {
        return new SavedState[size];
      }
    };
  }

  public  interface ITextWatcher {
    void textLenght(View v, int lenght);
  }

  public interface  IViewClickListener{
    void onClick(View v) ;
  }

//    /**
//     * This filter will constrain edits not to make the length of the text
//     * greater than the specified length.
//     */
//    public static class ByteLengthFilter implements InputFilter {
//        public ByteLengthFilter(int max) {
//            mMax = max;
//        }
//
//        public CharSequence filter(CharSequence source, int start, int end,
//                                   Spanned dest, int dstart, int dend) {
//            int keep = mMax - (dest.length() - (dend - dstart));
//
//            if (keep <= 0) {
//                return "";
//            } else if (keep >= end - start) {
//                return null; // keep original
//            } else {
//                keep += start;
//                if (Character.isHighSurrogate(source.charAt(keep - 1))) {
//                    --keep;
//                    if (keep == start) {
//                        return "";
//                    }
//                }
//                return source.subSequence(start, keep);
//            }
//        }
//
//        private int mMax;
//    }
}
