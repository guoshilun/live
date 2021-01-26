package com.jk.jkproject.ui.widget.recyclerview;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //    dispatchTouchEvent
    private int lastX = -1;
    private int lastY = -1;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();
        int dealtX = 0;
        int dealtY = 0;

        lastX = x;
        lastY = y;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (listener != null) {
                    listener.OnListener("ACTION_DOWN", "ACTION_DOWN");
                }
                dealtX = 0;
                dealtY = 0;
                // 保证子View能够接收到Action_move事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (listener != null) {
                    listener.OnListener("ACTION_MOVE", "ACTION_MOVE");
                }
                dealtX += Math.abs(x - lastX);
                dealtY += Math.abs(y - lastY);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    listener.OnListener("ACTION_UP", "ACTION_UP");
                }

                break;
        }
        if (dealtX >= dealtY) {
            getParent().requestDisallowInterceptTouchEvent(true);
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 定义一个接口
     */
    public interface CallBack {
        void OnListener(String code, String msg);
    }

    /**
     * 定义一个变量储存数据
     */
    private CallBack listener;

    /**
     * 提供公共的方法,并且初始化接口类型的数据
     */
    public void setListener(CallBack listener) {
        this.listener = listener;
    }
}
