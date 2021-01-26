package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;

public class OrientationViewPager extends ViewPager {

    private boolean isOrientation = false;
    private float down_X, down_Y;

    public OrientationViewPager(Context context) {
        super(context);
    }


    public OrientationViewPager(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
////        return super.onTouchEvent(ev);
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                return false;
//        }
//        return true;
//    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                down_X = ev.getX();
//                down_Y = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtils.e("event==", ev.getX() + "=====" + ev.getY());
//                if (down_Y > 280 && down_Y < 1185) {
//                    return super.dispatchTouchEvent(ev);
//                }
//                return false;
//        }
//        return super.dispatchTouchEvent(ev);
//    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
////        return super.onInterceptTouchEvent(ev);
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                return false;
//        }
//        return true;
//    }

    public void setOrientation(boolean orientation) {
        isOrientation = orientation;
    }
}
