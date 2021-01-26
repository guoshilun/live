package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int space;  //位移间距

    public SpaceItemDecoration(Context mContext, int space) {
        this.mContext = mContext;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) % 2 == 1) {
            outRect.left = SizeUtils.dp2px(10); //第一列左边贴边
        } else {
            if (parent.getChildAdapterPosition(view) % 2 == 0) {
                outRect.left = SizeUtils.dp2px(9);//第二列移动一个位移间距
                outRect.right = SizeUtils.dp2px(10);//
            } else {
//                outRect.left = space * 2;//由于第二列已经移动了一个间距，所以第三列要移动两个位移间距就能右边贴边，且item间距相等
            }
        }

//        if (parent.getChildAdapterPosition(view) >= 2) {
//            outRect.top = SizeUtils.dp2px(10);
//        } else {
//            outRect.top = 0;
//        }
    }
}
