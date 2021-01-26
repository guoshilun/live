package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.jk.jkproject.R;

public class AutoSwipeRefreshLayout extends SwipeRefreshLayout {
    /**
     * 滑动到最下面时的上拉操作
     */
    private int mTouchSlop;
    /**
     * ListView的加载中footer
     */
    private View mViewFooter;
    /**
     * listview实例
     */
    private ListView mListView;

    /**
     * RecyclerView实例
     */
    private RecyclerView mRecyclerView;

    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;
    /**
     * 按下坐标
     */
    private int dX = 0, dY = 0, uX = 0, uY = 0;
    /**
     * 是否为点击,避免点击时触发滑动效果
     */
    private boolean isMove = false;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;
    /**
     * 首页加载条数
     */
    private int mItemCount = -1;


    public AutoSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public AutoSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        mViewFooter = LayoutInflater.from(context).inflate(
                R.layout.cube_views_load_more_default_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 初始化ListView对象
        if (mListView == null || mRecyclerView == null) {
            getView();
        }
    }

    /**
     * 获取ListView对象
     */
    private void getView() {
        int childs = getChildCount();
        if (childs > 0) {
            View childView = getChildAt(0);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
                // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        if (canLoad()) {
                            loadData();
                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    }
                });
            } else if (childView instanceof RecyclerView) {
                mRecyclerView = (RecyclerView) childView;
                mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (canLoad()) {
                            loadData();
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                dX = (int) event.getX();
                dY = (int) event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                isMove = false;
                // 移动
                if (canLoad()) {
                    loadData();
                }

                break;

            case MotionEvent.ACTION_UP:
                uX = (int) event.getX();
                uY = (int) event.getY();
                if (uX != dX && uY != dY) {
                    isMove = true;
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }


    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     *
     * @return
     */
    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isBottom() {
        boolean b = false;
        if (mListView != null && mListView.getAdapter() != null) {

            if (mItemCount > 0) {
                if (mListView.getAdapter().getCount() < mItemCount) {
                    // 第一页未满，禁止下拉
                    b = false;
                } else {
                    b = mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
                }
            } else {
                // 未设置数据长度，则默认第一页数据不满时也可以上拉
                b = mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
            }
            return b;
        }
        return false;
    }

    /**
     * 设置可上滑的条数
     */
    public void setItemCount(int itemCount) {
        this.mItemCount = itemCount;
    }

    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
        return (dY - uY) >= mTouchSlop;
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData() {
        if (isMove) {
            if (mOnLoadListener != null) {
                // 设置状态
                setLoading(true);
                //
                mOnLoadListener.onLoad();
            }
        }
    }

    /**
     * 是否处于上滑状态
     *
     * @return
     */
    public boolean getIsLoading() {
        return isLoading;
    }

    /**
     * @param loading
     * @方法说明:设置刷新
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mViewFooter);
        } else {
            mListView.removeFooterView(mViewFooter);
            uY = 0;
            dY = 0;
        }
    }

    /**
     * 设置加载监听
     *
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }


    /**
     * 加载更多的监听器
     */
    public static interface OnLoadListener {
        public void onLoad();
    }
}