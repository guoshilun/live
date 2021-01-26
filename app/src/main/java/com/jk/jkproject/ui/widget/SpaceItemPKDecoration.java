package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;

public class SpaceItemPKDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int direction;

    public SpaceItemPKDecoration(Context mContext, int direction) {
        this.mContext = mContext;
        this.direction = direction;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) % 3 == 0) {
            outRect.left = SizeUtils.dp2px(13); //第一列左边贴边
            outRect.right = SizeUtils.dp2px(5);
        } else if (parent.getChildAdapterPosition(view) % 3 == 1) {
            outRect.left = SizeUtils.dp2px(8);//第二列移动一个位移间距
            outRect.right = SizeUtils.dp2px(5);//
        } else if (parent.getChildAdapterPosition(view) % 3 == 2) {
            outRect.left = SizeUtils.dp2px(8);//第三列移动一个位移间距
        }
    }
}
