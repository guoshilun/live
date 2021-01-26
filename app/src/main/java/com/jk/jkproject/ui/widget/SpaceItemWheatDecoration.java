package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class SpaceItemWheatDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int space;  //位移间距

    public SpaceItemWheatDecoration(Context mContext, int space) {
        this.mContext = mContext;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        if (parent.getChildAdapterPosition(view) % 4 == 0) {
//            outRect.left = SizeUtils.dp2px(23); //第一列左边贴边
//            outRect.right = SizeUtils.dp2px(13);
//        } else if (parent.getChildAdapterPosition(view) % 4 == 1) {
//            outRect.left = SizeUtils.dp2px(13);//第二列移动一个位移间距
//            outRect.right = SizeUtils.dp2px(13);//
//        } else if (parent.getChildAdapterPosition(view) % 4 == 2) {
//            outRect.left = SizeUtils.dp2px(13);//第三列移动一个位移间距
//            outRect.right = SizeUtils.dp2px(13);//
//        }else {
//            outRect.left = SizeUtils.dp2px(13);//第四列移动一个位移间距
//            outRect.right = SizeUtils.dp2px(21);
//        }
    }
}
