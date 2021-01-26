package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.listener.IResult;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.SpanUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SystemNotifitonListAdapter extends BaseWrapperRecyclerAdapter<SystemNotiBeanInfo.DataBean, SystemNotifitonListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {

    private Context context;

    private List<SystemNotiBeanInfo.DataBean> list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public SystemNotifitonListAdapter(Context paramContext, List<SystemNotiBeanInfo.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(final ItemViewHolder viewHolder, int paramInt) {
        this.list = getList();
        if (this.list.size() > 0) {
            SystemNotiBeanInfo.DataBean listBean = list.get(paramInt);
            //1.文本信息 ( title ) 2.文本链接 ( title + url  ) 3.图片链接 ( title + img + url )
            String title = "", url = "", img = "";
            try {
                JSONObject object = new JSONObject(listBean.getMessage());
                title = object.getString("title");
                switch (listBean.getType()) {
                    case 1:
                        viewHolder.chatItemContentText.setText(title);
                        break;
                    case 2:
                        url = object.getString("url");
                        SpanUtils.with(viewHolder.chatItemContentText).append(title).
                                append("活动详情>").setForegroundColor(context.getResources().getColor(R.color.color_D83FDD)).setUrl(url).create();
                        break;
                    case 3:
                        url = object.getString("url");
                        img = object.getString("img");
                        if (!img.trim().isEmpty()) {
                            final String finalTitle = title;
                            final String finalUrl = url;
                            final String finalImg = img;
                            Phoenix.with(context)
                                    .setUrl("https://goss.veer.com/creative/vcg/veer/800water/veer-308318668.jpg")
                                    .setWidth(ScreenUtils.dp2px(context, 228))
                                    .setHeight(ScreenUtils.dp2px(context, 80))
                                    .setResult(new IResult<Bitmap>() {
                                        public void onResult(Bitmap bitmap) {
                                            setBitmap(bitmap, finalTitle, finalUrl, finalImg, viewHolder.chatItemContentText);
//                                            CircleBitmapTransform.transform(bitmap);
                                        }
                                    }).load();
                        }
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            viewHolder.chatItemHeader.setImageResource(R.mipmap.live_icon_message_noti);
            viewHolder.itemTvTime.setText(TimeUtils.getFriendlyTimeSpanByNow(listBean.getCreate_time()));
        }
    }

    private void setBitmap(Bitmap bitmap, String title, String url, String img, TextView content) {
        SpanUtils.with(content).appendLine(title).
                setForegroundColor(context.getResources().getColor(R.color.color_D83FDD)).
                appendImage(bitmap).setUrl(url).appendLine().appendLine().appendImage(R.drawable.bg_line).
                append("点击查看>").setForegroundColor(context.getResources().getColor(R.color.color_149CFF)).setUrl(url).
                create();
    }

    public void onClick(View paramView, int paramInt) {
        if (FuncUtils.isFastDoubleClick())
            return;
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.system_noti_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.item_tv_time)
        TextView itemTvTime;
        @BindView(R.id.chat_item_header)
        SimpleDraweeView chatItemHeader;
        @BindView(R.id.chat_item_content_text)
        TextView chatItemContentText;
        @BindView(R.id.chat_item_layout_content)
        LinearLayout chatItemLayoutContent;
        @BindView(R.id.ll_text_receive)
        LinearLayout llTextReceive;

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.chatItemHeader = param1View.findViewById(R.id.chat_item_header);
            this.itemTvTime = param1View.findViewById(R.id.item_tv_time);
            this.chatItemContentText = param1View.findViewById(R.id.chat_item_content_text);
            this.chatItemLayoutContent = param1View.findViewById(R.id.chat_item_layout_content);
            this.llTextReceive = param1View.findViewById(R.id.ll_text_receive);
        }
    }

    public static interface OnItemClickListener {
        void terminationClick(View param1View, String param1String);
    }
}