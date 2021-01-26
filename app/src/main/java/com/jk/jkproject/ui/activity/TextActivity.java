package com.jk.jkproject.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.utils.ToastUtils;
import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.listener.IResult;
import com.facebook.fresco.helper.utils.CircleBitmapTransform;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.utils.SpanUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TextActivity extends BActivity {


    @BindView(R.id.tv_content)
    TextView tvContent;
    private Unbinder bind;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        bind = ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        Phoenix.with(this)
                .setUrl("https://goss.veer.com/creative/vcg/veer/800water/veer-308318668.jpg")
                .setWidth(200)
                .setHeight(200)
                .setResult(new IResult<Bitmap>() {
                    public void onResult(Bitmap bitmap) {
                        setBitmap(bitmap);
                        CircleBitmapTransform.transform(bitmap);
                    }
                }).load();
    }


    private void setBitmap(Bitmap bitmap) {

        this.bitmap = bitmap;
        SpanUtils.with(tvContent).appendLine("小图").setBackgroundColor(Color.GREEN).appendImage(bitmap).create();

    }

    private void initView() {

        SpanUtils.with(tvContent)
                .appendLine("SpanUtils").setBackgroundColor(Color.LTGRAY).setBold().setForegroundColor(Color.YELLOW).setHorizontalAlign(Layout.Alignment.ALIGN_CENTER)
                .appendLine("前景色").setForegroundColor(Color.GREEN)
                .appendLine("背景色").setBackgroundColor(Color.LTGRAY)
                .appendLine("行高居中对齐").setLineHeight(20, SpanUtils.ALIGN_CENTER).setBackgroundColor(Color.LTGRAY)
                .appendLine("行高底部对齐").setLineHeight(20, SpanUtils.ALIGN_BOTTOM).setBackgroundColor(Color.GREEN)
                .appendLine("测试段落缩，首行缩进两字，其他行不缩进").setLeadingMargin(20 * 2, 10).setBackgroundColor(Color.GREEN)
                .appendLine("测试引用，后面的字是为了凑到两行的效果").setQuoteColor(Color.GREEN, 10, 10).setBackgroundColor(Color.LTGRAY)
                .appendLine("测试列表项，后面的字是为了凑到两行的效果").setBullet(Color.GREEN, 20, 10).setBackgroundColor(Color.LTGRAY).setBackgroundColor(Color.GREEN)
                .appendLine("32dp 字体").setFontSize(32, true)
                .appendLine("2 倍字体").setFontProportion(2f)
                .appendLine("横向 2 倍字体").setFontXProportion(1.5f)
                .appendLine("删除线").setStrikethrough()
                .appendLine("下划线").setUnderline()
                .append("测试").appendLine("上标").setSuperscript()
                .append("测试").appendLine("下标").setSubscript()
                .appendLine("粗体").setBold()
                .appendLine("斜体").setItalic()
                .appendLine("粗斜体").setBoldItalic()
                .appendLine("monospace 字体").setFontFamily("monospace")
//                  .appendLine("自定义字体").setTypeface(Typeface.createFromAsset(assets, "fonts/dnmbhs.ttf"))
                .appendLine("相反对齐").setHorizontalAlign(Layout.Alignment.ALIGN_OPPOSITE)
                .appendLine("居中对齐").setHorizontalAlign(Layout.Alignment.ALIGN_CENTER)
                .appendLine("正常对齐").setHorizontalAlign(Layout.Alignment.ALIGN_NORMAL)
                .append("测试").appendLine("点击事件").setClickSpan(clickableSpan)
                .append("测试").appendLine("Url").setUrl("https://github.com/Blankj/AndroidUtilCode")
                .append("测试").appendLine("模糊").setBlur(3f, BlurMaskFilter.Blur.NORMAL)
//                .appendLine("颜色渐变").setShader(LinearGradient(0f, 0f, 64f * density * 4f, 0f, resources.getIntArray(R.array.rainbow), null, Shader.TileMode.REPEAT)).setFontSize(64, true)
//                .appendLine("图片着色").setFontSize(64, true).setShader(BitmapShader(BitmapFactory.decodeResource(getResources(), R.mipmap.audio_animation_list_left_3), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT))
                .appendLine("阴影效果").setFontSize(64, true).setBackgroundColor(Color.BLACK).setShadow(24f, 8f, 8f, Color.WHITE)
                .append("小图").setBackgroundColor(Color.GREEN)
//                .appendImage(R.drawable.span_block_low, SpanUtils.ALIGN_TOP)
//                .append("顶部").setBackgroundColor(Color.GREEN)
//                .appendImage(R.drawable.span_block_low, SpanUtils.ALIGN_CENTER)
//                .append("居中").setBackgroundColor(Color.GREEN)
//                .appendImage(R.drawable.span_block_low, SpanUtils.ALIGN_BASELINE)
//                .append("底部").setBackgroundColor(Color.GREEN)
//                .appendImage(R.drawable.span_block_low, SpanUtils.ALIGN_BOTTOM)
//                .appendLine("对齐").setBackgroundColor(Color.GREEN)
//                .appendImage(Uri.parse("https://goss.veer.com/creative/vcg/veer/800water/veer-308318668.jpg"), SpanUtils.ALIGN_TOP)
//                .append("大图").setBackgroundColor(Color.LTGRAY)
//                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_TOP)
//                .append("顶部").setBackgroundColor(Color.LTGRAY)
//                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_TOP)
//                .appendLine("对齐").setBackgroundColor(Color.LTGRAY)
//                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_CENTER)
//                .append("大图").setBackgroundColor(Color.GREEN)
//                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_CENTER)
//                .append("居中").setBackgroundColor(Color.GREEN)
//                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_CENTER)
//                .appendLine("对齐").setBackgroundColor(Color.GREEN)
//                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_BOTTOM)
//                .append("大图").setBackgroundColor(Color.LTGRAY)
//                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_BOTTOM)
//                .append("底部").setBackgroundColor(Color.LTGRAY)
//                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_BOTTOM)
//                .appendLine("对齐").setBackgroundColor(Color.LTGRAY)
                .append("测试空格").appendSpace(30, Color.LTGRAY).appendSpace(50, Color.GREEN).appendSpace(100).appendSpace(30, Color.LTGRAY).appendSpace(50, Color.GREEN)
                .create();
    }


    private ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(@NonNull View widget) {
            ToastUtils.showShortToast("事件触发了");
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.BLUE);
            ds.setUnderlineText(false);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
