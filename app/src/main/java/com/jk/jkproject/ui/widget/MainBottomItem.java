package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;


/**
 * Created by zhaotun on 16/06/28.
 */
public class MainBottomItem extends RelativeLayout {

    private ImageView ivIcon;

    private TextView ivRedDot;

    private TextView tvName;

    public MainBottomItem(Context context) {
        super(context);
        init(context);
    }

    public MainBottomItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MainBottomItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_main_bottom_item, this, true);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        ivRedDot = (TextView) findViewById(R.id.iv_red_dot_num);
        tvName = (TextView) findViewById(R.id.tv_name);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (ivIcon != null) {
            ivIcon.setEnabled(enabled);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);

        if (ivIcon != null) {
            ivIcon.setSelected(selected);
        }

//        if (ivRedDot != null) {
//            ivRedDot.setSelected(selected);
//        }
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);

        if (ivIcon != null) {
            ivIcon.setPressed(pressed);
        }

//        if (ivRedDot != null) {
//            ivRedDot.setPressed(pressed);
//        }
    }


    public void setIconResId(int srcResId) {
        if (ivIcon != null) {
            ivIcon.setImageResource(srcResId);
        }
    }

//    public void setRedDotVisibility(int visibility) {
//        if (ivRedDot != null) {
//            ivRedDot.setVisibility(visibility);
//        }
//    }
//
//    public boolean isRedDotVisibility() {
//        if (ivRedDot != null) {
//            return ivRedDot.getVisibility() == View.VISIBLE;
//        }
//
//        return false;
//    }

    public void setTvName(String name) {
        tvName.setText(name);
    }

    public MainBottomItem setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = (TextView) getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public View getView(int res) {
        return findViewById(res);
    }

    public void setRedDotVisibility(int visibility, int num) {
        if (ivRedDot != null && num > 0) {
            ivRedDot.setVisibility(visibility);
            ivRedDot.setText(num + "");
        } else {
            ivRedDot.setVisibility(INVISIBLE);
        }
    }

    public boolean isRedDotVisibility() {
        if (ivRedDot != null) {
            return ivRedDot.getVisibility() == View.VISIBLE;
        }

        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
