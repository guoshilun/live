package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.PkAcceptInfo;


public class PkStatusView extends RelativeLayout {
    private TextView desc;
    private SimpleDraweeView head;
    private ImageView noData;

    public PkStatusView(Context context) {
        super(context);
        init(context);
    }

    public PkStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PkStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_live_pk_status, this, true);
        desc = findViewById(R.id.tv_desc);
        head = findViewById(R.id.head);
        noData = findViewById(R.id.im_no_body);
    }

    public void setData(PkAcceptInfo pkAcceptInfo) {
        Spanned strB = Html.fromHtml(pkAcceptInfo.getHtml());
//        MLog.e("tag setData", strB);
        desc.setText(strB);
        //指定好友匹配
        if (pkAcceptInfo.getSender() != null) {
            head.setImageURI(pkAcceptInfo.getSender().getAvatar());
            head.setVisibility(VISIBLE);
            noData.setVisibility(INVISIBLE);
        } else {
            head.setVisibility(INVISIBLE);
            noData.setVisibility(VISIBLE);
            head.setImageResource(R.mipmap.icon_nobody);
        }
    }

    public void destory() {

    }
}
