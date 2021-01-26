package com.jk.jkproject.ui.widget.danmaku;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.core.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.listener.IResult;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.entity.LiveGiftUtils;
import com.jk.jkproject.utils.emoji.LHImageSpan;

import java.util.ArrayList;

public class RichTextParse {

  /**
   * 聊天消息解析和弹幕消息解析
   *
   * @param textView
   * @param richText
   * @param isChatList
   */
  public static void parse(final TextView textView, ArrayList<RichMessage> richText, final boolean isChatList) {
    try {
      final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
      if (isChatList) {
        String name = "直播消息：";
        spannableStringBuilder.append(name);

        int nameColor = ContextCompat.getColor(textView.getContext(), R.color.live_yellow);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(nameColor),
                0,
                name.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }

      for (RichMessage message : richText) {
        final int length = spannableStringBuilder.length();
        if ("text".equals(message.getType())) {
          String content = message.getContent();
          spannableStringBuilder.append(content);

          String textColor = message.getColor();
          if (TextUtils.isEmpty(textColor)) {
            textColor = "FFFFFF";
          }

          spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#" + textColor)),
                  length,
                  length + content.length(),
                  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if ("icon_gift".equals(message.getType())) {
          String gifId = message.getGift_id();
          final LiveGiftInfoBean.DataBean gift = LiveGiftUtils.get().getGift(gifId);
          if (gift != null && !TextUtils.isEmpty(gift.getG_icon())) {
            spannableStringBuilder.append(gift.getG_name());

            final int imgSize = (int) (textView.getTextSize() * 1.5);
//                        LogUtils.e(">>>>>>>>>>gift.image=parse=" + gift.image + "=imgSize=" + imgSize + "==" + Phoenix.isInDiskCacheSync(Uri.parse(gift.image)));

            Phoenix.with(textView.getContext())
                    .setUrl(gift.getG_icon())
                    .setWidth(imgSize)
                    .setHeight(imgSize)
                    .setResult(new IResult<Bitmap>() {
                      @Override
                      public void onResult(Bitmap bitmap) {
                        LHImageSpan imageSpan = new LHImageSpan(textView.getContext(), bitmap, imgSize);
                        spannableStringBuilder.setSpan(imageSpan,
                                length,
                                length + gift.getG_name().length(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                      }
                    })
                    .load();
          } else {
            String content = message.getContent();
            spannableStringBuilder.append(content);

            spannableStringBuilder.setSpan(
                    new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(),
                            R.color.transparent)),
                    length,
                    length + content.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
        }
      }

      textView.setText(spannableStringBuilder);

    } catch (Exception e) {
    }
  }

  /**
   * 弹幕的文本解析
   *
   * @param context
   * @param richText
   * @param textSize
   * @param isChatList
   * @return
   */
  public static SpannableStringBuilder parse(final Context context, ArrayList<RichMessage> richText,
                                             int textSize, boolean isChatList) {

    final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
    try {
      if (isChatList) {
        String name = "直播消息：";
        spannableStringBuilder.append(name);

        int nameColor = ContextCompat.getColor(context, R.color.live_yellow);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(nameColor),
                0,
                name.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }

      for (RichMessage message : richText) {
        final int length = spannableStringBuilder.length();
        if ("text".equals(message.getType())) {
          String content = message.getContent();
          spannableStringBuilder.append(content);

          String textColor = message.getColor();
          if (TextUtils.isEmpty(textColor)) {
            textColor = "FFFFFF";
          }

          spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#" + textColor)),
                  length,
                  length + content.length(),
                  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if ("icon_gift".equals(message.getType())) {
          String gifId = message.getGift_id();
          LiveGiftInfoBean.DataBean gift = LiveGiftUtils.get().getGift(gifId);
          if (gift != null && !TextUtils.isEmpty(gift.getG_icon())) {
            spannableStringBuilder.append("中奖礼物");
//                        LogUtils.e(">>>>>>>>>>gift.image=SpannableStringBuilder=" + gift.image);
            final int imgSize = (int) (textSize);
            Phoenix.with(context)
                    .setUrl(gift.getG_icon())
                    .setWidth(imgSize)
                    .setHeight(imgSize)
                    .setResult(new IResult<Bitmap>() {
                      @Override
                      public void onResult(Bitmap bitmap) {
                        LHImageSpan imageSpan = new LHImageSpan(context, bitmap, imgSize);
                        spannableStringBuilder.setSpan(imageSpan,
                                length,
                                length + 4,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                      }
                    })
                    .load();
          } else {
            String content = message.getContent();
            spannableStringBuilder.append(content);

            spannableStringBuilder.setSpan(
                    new ForegroundColorSpan(ContextCompat.getColor(context,
                            R.color.transparent)),
                    length,
                    length + content.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
        }
      }

    } catch (Exception e) {
    }
    return spannableStringBuilder;
  }
  public static SpannableStringBuilder parse(final Context context, ArrayList<RichMessage> richText,
                                             int textSize, boolean isChatList, boolean isPk) {

    final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
    try {
      if (isPk) {
        String name = "200 ";
        spannableStringBuilder.append(name);

        int nameColor = ContextCompat.getColor(context, R.color.live_yellow);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(nameColor),
                0,
                name.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }

      for (RichMessage message : richText) {
        final int length = spannableStringBuilder.length();
        if ("text".equals(message.getType())) {
          String content = message.getContent();
          spannableStringBuilder.append(content);

          String textColor = message.getColor();
          if (TextUtils.isEmpty(textColor)) {
            textColor = "FFFFFF";
          }

          spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#" + textColor)),
                  length,
                  length + content.length(),
                  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if ("icon_gift".equals(message.getType())) {
          String gifId = message.getGift_id();
          LiveGiftInfoBean.DataBean gift = LiveGiftUtils.get().getGift(gifId);
          if (gift != null && !TextUtils.isEmpty(gift.getG_icon())) {
            spannableStringBuilder.append("中奖礼物");
            final int imgSize = (int) (textSize);
            Phoenix.with(context)
                    .setUrl(gift.getG_icon())
                    .setWidth(imgSize)
                    .setHeight(imgSize)
                    .setResult(new IResult<Bitmap>() {
                      @Override
                      public void onResult(Bitmap bitmap) {
                        LHImageSpan imageSpan = new LHImageSpan(context, bitmap, imgSize);
                        spannableStringBuilder.setSpan(imageSpan,
                                length,
                                length + 4,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                      }
                    })
                    .load();
          } else {
            String content = message.getContent();
            spannableStringBuilder.append(content);

            spannableStringBuilder.setSpan(
                    new ForegroundColorSpan(ContextCompat.getColor(context,
                            R.color.color_ciricle_bg)),
                    length,
                    length + content.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
        }
      }

    } catch (Exception e) {
    }
    return spannableStringBuilder;
  }
}