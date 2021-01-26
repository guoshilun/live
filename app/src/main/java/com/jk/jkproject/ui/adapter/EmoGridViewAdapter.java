package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.model.EmotionModle;
import com.jk.jkproject.utils.EmoticonUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;
import java.util.List;

public class EmoGridViewAdapter extends BaseAdapter {

	private List<EmotionModle> data;

	private LayoutInflater inflater;

	private int size = 0;
	private boolean isEmoji = true;
	private Context context;
	private String basePath;
	private DisplayImageOptions option;

	public EmoGridViewAdapter(Context context, List<EmotionModle> list,
							  boolean isEmoji, String epkg_pkg_name) {
		this.inflater = LayoutInflater.from(context);
		this.data = list;
		this.isEmoji = isEmoji;
		this.context = context;
		this.size = list.size();

		if (TextUtils.isEmpty(epkg_pkg_name)) {
			basePath = null;
		} else {
			basePath = EmoticonUtils.get().getEmoticonBasePath()
					+ File.separator + epkg_pkg_name + File.separator;
		}
	}

	@Override
	public int getCount() {
		return this.size;
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EmotionModle emoji = data.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			if (isEmoji) {
				convertView = inflater.inflate(R.layout.emoji_grid_item, null);
			} else {
				convertView = inflater
						.inflate(R.layout.emo_gif_grid_item, null);
				viewHolder.tv_face = (TextView) convertView
						.findViewById(R.id.face_tv);
			}
			viewHolder.iv_face = (ImageView) convertView
					.findViewById(R.id.face_iv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (emoji.getId() == R.mipmap.app_default_small) {
			convertView.setBackgroundDrawable(null);
			viewHolder.iv_face.setImageResource(emoji.getId());
		} else if (TextUtils.isEmpty(emoji.getEmoticonName())) {
			convertView.setBackgroundDrawable(null);
			viewHolder.iv_face.setImageDrawable(null);
		} else {
			viewHolder.iv_face.setTag(emoji);
			if (isEmoji) {
				viewHolder.iv_face.setImageResource(emoji.getId());
			} else {
				try {
					if (!TextUtils.isEmpty(emoji.getEmoticonName())) {
						viewHolder.tv_face.setVisibility(View.VISIBLE);
						viewHolder.tv_face.setText(emoji.getEmoticonName());
					} else {
						viewHolder.tv_face.setVisibility(View.GONE);
					}

					if (!TextUtils.isEmpty(basePath)
							&& !TextUtils.isEmpty(emoji.getEmoticonFile())) {
						String path = basePath + emoji.getEmoticonId() + ".png";
						if (new File(path).exists()) {
							setImage(viewHolder.iv_face, "file:///" + path);
						} else if (!TextUtils.isEmpty(emoji.getEmoticonUrl())) {
							setImage(viewHolder.iv_face, emoji.getEmoticonUrl());
						} else {
							viewHolder.iv_face
									.setImageResource(R.mipmap.app_default_small);
						}
					} else {
						viewHolder.iv_face
								.setImageResource(R.mipmap.app_default_small);
					}
				} catch (Throwable e) {
				}

			}
		}

		return convertView;
	}

	private void setImage(ImageView iv, String emo_gif_url) {
		if (option == null) {
			option = getOption();
		}

		try {
			ImageLoader.getInstance().displayImage(emo_gif_url, iv, option);
		} catch (Throwable e) {
			iv.setImageBitmap(null);
		}
	}

	private DisplayImageOptions getOption() {
		return new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.build();
	}

	class ViewHolder {
		private ImageView iv_face;
		private TextView tv_face;
	}
}