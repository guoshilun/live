package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

public class OnScrollRecyclerView extends RecyclerView {
    public OnScrollRecyclerView(Context context) {
        super(context);
    }

    public OnScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OnScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ViewParent parent = this;
        // 循环查找ViewPager, 请求ViewPager不拦截触摸事件
        while (!((parent = parent.getParent()) instanceof ViewPager)) {
        }
        parent.requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);

    }
}
