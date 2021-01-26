package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.utils.FastClickUtils;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.ViewHolder;

import java.util.List;

/**
 * @author Zick
 * @params
 * @date 2020/7/31 10:44 AM
 * @desc 发现页面推荐
 */
public class LiveFoundRecommendAdapter extends CommonAdapter<LiveHomeBeanInfo.DataBean> {
    Context context;

    private List<LiveHomeBeanInfo.DataBean> datas;

    private OnItemClickListener onItemClickListener;

    public LiveFoundRecommendAdapter(Context paramContext, List<LiveHomeBeanInfo.DataBean> paramList, int paramInt) {
        super(paramContext, paramList, paramInt);
        this.context = paramContext;
        this.datas = paramList;
    }

    public void convert(ViewHolder paramViewHolder, LiveHomeBeanInfo.DataBean paramDataBean) {
        if (paramDataBean != null) {
            TextView textView1 = (TextView) paramViewHolder.itemView.findViewById(R.id.tv_title);
            TextView textView2 = (TextView) paramViewHolder.itemView.findViewById(R.id.tv_count);
            ProgressBar progressBar = (ProgressBar) paramViewHolder.itemView.findViewById(R.id.pb_progressbar);
            ((SimpleDraweeView) paramViewHolder.itemView.findViewById(R.id.sdv_pic)).setImageURI(paramDataBean.getR_cover());
            TextView textView3 = (TextView) paramViewHolder.itemView.findViewById(R.id.tv_live_type);
            textView1.setText(paramDataBean.getR_name());
            textView2.setText(paramDataBean.getPeopleNumber() + "");
            if (paramDataBean.getLive_type() != 1) {
                textView3.setText("未开播");
                textView3.setBackgroundResource(R.drawable.bg_gray_btn);
            } else {
                textView3.setText("直播中");
                textView3.setBackgroundResource(R.drawable.bg_red_btn);
            }
            progressBar.setProgress(0);
            progressBar.setMax(100);
            if (getPosition((RecyclerView.ViewHolder) paramViewHolder) == 0) {
                progressBar.setProgressDrawable(this.context.getResources().getDrawable(R.drawable.bg_progress_left));
            } else {
                progressBar.setProgressDrawable(this.context.getResources().getDrawable(R.drawable.bg_progress_right));
            }

            switch (getPosition(paramViewHolder)) {
                case 0:
                    if (onItemClickListener != null) {
                        onItemClickListener.click(progressBar);
                    }
                    break;
                case 1:
                    if (onItemClickListener != null) {
                        onItemClickListener.positionClick(progressBar);
                    }
                    break;
            }

            paramViewHolder.itemView.findViewById(R.id.sdv_pic).setOnClickListener(v -> {
                if (FastClickUtils.isFastClick()) {
                    Intent intent = new Intent(context, SlideActivity.class);
                    intent.putExtra("roomId", paramDataBean.getRoomId());
                    context.startActivity(intent);
                }
            });

        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public static interface OnItemClickListener {
        void click(ProgressBar view);

        void positionClick(ProgressBar view);
    }
}
