package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;

public class DrawerItemView extends RelativeLayout {
    private ImageView ivIcon;

    private ImageView ivShare;

    private TextView tvDes;
    private TextView tvDesc;

    private TextView tvName;
    private TextView tvRightName;
    private View line;

    public DrawerItemView(Context paramContext) {
        super(paramContext);
        init(paramContext);
    }

    public DrawerItemView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init(paramContext);
    }

    public DrawerItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init(paramContext);
    }

    private void init(Context paramContext) {
        LayoutInflater.from(paramContext).inflate(R.layout.view_drawer_item, (ViewGroup) this, true);
        this.ivIcon = (ImageView) findViewById(R.id.iv_icon);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvDes = (TextView) findViewById(R.id.tv_des);
        line = (View) findViewById(R.id.line);
        this.tvDesc = (TextView) findViewById(R.id.tv_desc);
        this.tvRightName = (TextView) findViewById(R.id.tv_right_name);
        this.ivShare = (ImageView) findViewById(R.id.img_share);
    }

    public DrawerItemView setText(@IdRes int viewId, String name) {
        TextView view = (TextView) getView(viewId);
        view.setText(name);
        return this;
    }

    public DrawerItemView setTextSize(@IdRes int viewId, int size) {
        TextView view = (TextView) getView(viewId);
        view.setTextSize(size);
        return this;
    }

    public DrawerItemView setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = (TextView) getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public DrawerItemView setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public DrawerItemView setVisible(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public DrawerItemView setGone(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }


    public void setDes(int paramInt) {
        if (tvDes != null) {
            tvDes.setText(paramInt);
        }
    }

    public View getView(int res) {
        return findViewById(res);
    }

    public void setDesc(CharSequence paramCharSequence) {
        if (tvDesc != null) {
            tvDesc.setText(paramCharSequence);
        }
    }

    public void setDes(CharSequence paramCharSequence) {
        if (tvDes != null) {
            tvDes.setText(paramCharSequence);
        }
    }

    public void setDesTextColor(int paramInt) {
        if (tvDes != null) {
            tvDes.setTextColor(paramInt);
        }
    }

    public void setIconResId(int paramInt) {
        if (ivIcon != null) {
            ivIcon.setImageResource(paramInt);
        }
    }

    public DrawerItemView setInVisib(int paramInt) {
        ((ImageView) findViewById(paramInt)).setVisibility(INVISIBLE);
        return this;
    }

    public void setIvShareVisiable() {
        if (ivShare != null) {
            ivShare.setVisibility(VISIBLE);
        }
    }

    public void setName(int paramInt) {
        if (tvName != null) {
            tvName.setText(paramInt);
        }
    }

    public void setName(CharSequence paramCharSequence) {
        if (tvName != null) {
            tvName.setText(paramCharSequence);
        }
    }

    public void setNameTextColor(int paramInt) {
        if (tvName != null) {
            tvName.setTextColor(paramInt);
        }
    }

    public void setPress(boolean paramBoolean) {
        ImageView imageView = this.ivIcon;
        if (imageView != null)
            imageView.setPressed(paramBoolean);
        TextView textView = this.tvName;
        if (textView != null)
            textView.setPressed(paramBoolean);
        textView = this.tvDes;
        if (textView != null)
            textView.setPressed(paramBoolean);
    }

    public void setSelect(boolean paramBoolean) {
        ImageView imageView = this.ivIcon;
        if (imageView != null)
            imageView.setSelected(paramBoolean);
        TextView textView = this.tvName;
        if (textView != null)
            textView.setSelected(paramBoolean);
        textView = this.tvDes;
        if (textView != null)
            textView.setSelected(paramBoolean);
    }

    public void setShareResId(int paramInt) {
        ImageView imageView = this.ivShare;
        if (imageView != null) {
            imageView.setVisibility(VISIBLE);
            this.ivShare.setImageResource(paramInt);
        }
    }

    public DrawerItemView setVisib(int paramInt) {
        ((ImageView) findViewById(paramInt)).setVisibility(GONE);
        return this;
    }
}
