package com.jk.jkproject.ui.widget.room;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveEntryInfo;
import com.jk.jkproject.ui.inter.LiveEntryActionListener;
import com.jk.jkproject.ui.inter.LiveEntryAnimListener;

public class LiveViewForEntry extends TextView implements LiveEntryAnimListener {

  private static final int FLAG_OUT = 1;
  private static final int FLAG_OUT_DELAY = 3000;

  private boolean isRunning = false;
  private LiveEntryActionListener mLiveEntryAction;

  private ObjectAnimator alphaIn;
  private ObjectAnimator alphaOut;

  public LiveViewForEntry(Context context) {
    super(context);
  }

  public LiveViewForEntry(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LiveViewForEntry(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }


  @Override
  public void setLiveEntryActionListener(LiveEntryActionListener listener) {
    this.mLiveEntryAction = listener;
  }

  @Override
  public boolean isRunning() {
    return isRunning;
  }

  @Override
  public void startAnim(LiveEntryInfo info) {
    if (info == null || TextUtils.isEmpty(info.getContent()) || TextUtils.isEmpty(info.getNickname())) {
      return;
    }

    this.isRunning = true;


    String glamour = String.valueOf(info.getGlamour());
    String content = TextUtils.replace(info.getContent(), new String[]{"{nickname}", "{glamour}"}, new String[]{info.getNickname(), glamour}).toString();
    SpannableString ss = new SpannableString(content);

    // nickname变色
    int nameIndex = content.indexOf(info.getNickname());
    if (nameIndex >= 0) {
      ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.live_view_gift_name)), nameIndex, nameIndex + info.getNickname().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    // 魅力值加粗
    int glamourIndex = content.indexOf(glamour);
    if (glamourIndex >= 0) {
      ss.setSpan(new StyleSpan(Typeface.BOLD), glamourIndex, glamourIndex + glamour.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    setText(ss);
    setVisibility(VISIBLE);


    if (alphaIn == null) {
      PropertyValuesHolder alpha1 = PropertyValuesHolder.ofFloat(View.ALPHA, 0.3f, 1.0f);
      alphaIn = ObjectAnimator.ofPropertyValuesHolder(this, alpha1);
      alphaIn.setDuration(300);
    }

    if (alphaIn != null) {
      alphaIn.start();
    }

    if (handler != null) {
      handler.removeMessages(FLAG_OUT);
      handler.sendEmptyMessageDelayed(FLAG_OUT, FLAG_OUT_DELAY);
    }
  }

  @Override
  public void release() {
    isRunning = false;

    if (alphaIn != null) {
      alphaIn = null;
    }

    if (alphaOut != null) {
      alphaOut = null;
    }

    if (handler != null) {
      handler.removeMessages(FLAG_OUT);
      handler = null;
    }
  }

  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case FLAG_OUT:
          if (alphaOut == null) {
            PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f, 0f);
            alphaOut = ObjectAnimator.ofPropertyValuesHolder(LiveViewForEntry.this, alpha);
            alphaOut.setDuration(300);
            alphaOut.addListener(new Animator.AnimatorListener() {
              @Override
              public void onAnimationStart(Animator animation) {
              }

              @Override
              public void onAnimationEnd(Animator animation) {
                LiveViewForEntry.this.setVisibility(INVISIBLE);
                LiveViewForEntry.this.isRunning = false;

                if (mLiveEntryAction != null) {
                  mLiveEntryAction.pollEntry();
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

          if (alphaOut != null) {
            alphaOut.start();
          }
          break;
      }
    }
  };

}