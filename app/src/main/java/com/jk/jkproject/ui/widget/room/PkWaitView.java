package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anbetter.log.MLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jk.jkproject.R;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.entity.PkSuccessInfo;
import com.jk.jkproject.utils.Constants;

import cn.iwgang.countdownview.CountdownView;

public class PkWaitView extends RelativeLayout implements View.OnClickListener, ResponseListener {
  private CountdownView countdown;
  private PkSuccessInfo successInfo;
  private TextView title, tvUnit, tvDesc;
  private SimpleDraweeView head;

  public PkWaitView(Context context) {
    super(context);
    init(context);
  }

  public PkWaitView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public PkWaitView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  //高斯模糊
  public static void showUrlBlur(SimpleDraweeView draweeView, String url, int iterations, int blurRadius) {
    try {
      Uri uri = Uri.parse(url);
      ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
              .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
              .build();
      AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
              .setOldController(draweeView.getController())
              .setImageRequest(request)
              .build();
      draweeView.setController(controller);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void init(Context context) {
    LayoutInflater.from(context).inflate(R.layout.view_live_pk_wait, this, true);
    countdown = findViewById(R.id.wait_countdown);
    tvDesc = findViewById(R.id.wait_desc);
    title = findViewById(R.id.wait_title);
    tvUnit = findViewById(R.id.wait_unit);
    head = findViewById(R.id.wait_image);
    findViewById(R.id.im_close_wait).setOnClickListener(this);
  }

  public void setDate(final PkSuccessInfo successInfo) {
    this.successInfo = successInfo;
    //随机匹配
    if (TextUtils.isEmpty(successInfo.getUid()) || successInfo.isRandom()) {
      countdown.setVisibility(INVISIBLE);
      tvUnit.setVisibility(INVISIBLE);
      title.setText("随机挑战");
      tvDesc.setText("匹配中");
      if (successInfo != null && successInfo.getHead() != null) {
        showUrlBlur(head, successInfo.getHead(), 8, 8);
      }
    } else {//指定好友匹配

      if (successInfo != null && successInfo.getHead() != null) {
        head.setImageURI(successInfo.getHead());
      }
      tvDesc.setText(successInfo.getNickname());
      title.setText("挑战好友");
      countdown.setVisibility(VISIBLE);
      tvUnit.setVisibility(VISIBLE);
      countdown.customTimeShow(false, false, false, true, false);
      countdown.start(successInfo.getWait_seconds() * 1000);
      countdown.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
        @Override
        public void onEnd(CountdownView cv) {
          if (getVisibility() == VISIBLE) {
//            AppApis.startOrEndPk(SPUtils.Impl.getUid(), successInfo.getUid(), "end", PkWaitView.this);
            setVisibility(GONE);
          }
        }
      });
    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.im_close_wait:
//        AppApis.startOrEndPk(SPUtils.Impl.getUid(), successInfo.getUid(), "end", this);
        setVisibility(GONE);
        break;
    }
  }

  @Override
  public void onSuccess(String url, Object obj) {
    MLog.e("tag  wait ", obj.toString());
    Bundle bd = new Bundle();
    bd.putInt("method", Constants.OBSERVABLE_ACTION_PK_END_MESSAGE_ROOM_FOR_USER);
    bd.putString("object", "您取消了pk挑战");
    MessageNotifyCenter.getInstance().doNotify(bd);
  }

  @Override
  public void onStartRequest() {

  }

  @Override
  public void onFailure(int code, String url, String error) {

  }

  public void setVisible() {
    if (countdown != null) {
      countdown.pause();
    }
  }

  public void destory() {

    if (countdown != null) {
      countdown = null;
    }
    if (successInfo != null) {
      successInfo = null;
    }
    if (title != null) {
      title = null;
    }
    if (tvUnit != null) {
      tvUnit = null;
    }
    if (tvDesc != null) {
      tvDesc = null;
    }
    if (head != null) {
      head = null;
    }
  }
}