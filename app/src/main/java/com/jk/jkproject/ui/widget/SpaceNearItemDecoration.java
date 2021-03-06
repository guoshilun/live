package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;

public class SpaceNearItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int space;  //位移间距

    public SpaceNearItemDecoration(Context mContext, int space) {
        this.mContext = mContext;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) % 3 == 0) {
            outRect.left = SizeUtils.dp2px(10); //第一列左边贴边
            outRect.right = SizeUtils.dp2px(5);
        } else if (parent.getChildAdapterPosition(view) % 3 == 1) {
            outRect.left = SizeUtils.dp2px(5);//第二列移动一个位移间距
            outRect.right = SizeUtils.dp2px(5);//
        } else if (parent.getChildAdapterPosition(view) % 3 == 2) {
            outRect.left = SizeUtils.dp2px(5);//第三列移动一个位移间距
            outRect.right = SizeUtils.dp2px(10);//
        }
        outRect.top = SizeUtils.dp2px(10);
    }
}
