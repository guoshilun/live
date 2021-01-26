package com.jk.jkproject.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.net.im.db.DBConstants;
import com.jk.jkproject.ui.adapter.EmoticonHistoryAdapter;
import com.jk.jkproject.ui.widget.EmoGridView;
import com.jk.jkproject.utils.EmoticonUtils;

public class EmoFragment extends BFragment {
  private EmoticonHistoryAdapter adapter;

  private OnEmoGridViewItemClickListener listener;

  private Cursor mCursor;

  private String tag;

  private String uid;

  public static EmoFragment get(String paramString) {
    EmoFragment emoFragment = new EmoFragment();
    Bundle bundle = new Bundle();
    bundle.putString("tag", paramString);
    emoFragment.setArguments(bundle);
    return emoFragment;
  }

  private Cursor getCursor() {
    try {
      this.mCursor = getActivity().getContentResolver().query(DBConstants.EMOTICON_HISTORY_QUERY_ALL_URI, null, "uid=?", new String[] { this.uid }, "datetime desc limit 10");
    } catch (Throwable throwable) {}
    return this.mCursor;
  }

  private void initEmojiGridView(View paramView) {
    final EmoGridView emoGridView = (EmoGridView)paramView.findViewById(R.id.emo_gridview);
    emoGridView.setGridViewData(EmoticonUtils.get().getDefaultEmojis());
    emoGridView.setOnEmoGridViewItemClick(new EmoGridView.OnEmoGridViewItemClick() {
          public void onItemClick(int param1Int1, int param1Int2) {
//            if (EmoFragment.this.listener != null)
//              EmoFragment.this.listener.onItemClick(param1Int1, param1Int2, emojiGridView.pageItemCount, EmoFragment.this.tag);
          }
        });
    emoGridView.setAdapter();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = null;
    this.tag = getArguments().getString("tag", "");
    if ("emoticon_emoji".equals(this.tag)) {
      view = paramLayoutInflater.inflate(R.layout.emo_default_layout, paramViewGroup, false);
      initEmojiGridView(view);
    }
    return view;
  }

  public void onDestroy() {
    Cursor cursor = this.mCursor;
    if (cursor != null)
      cursor.close();
    super.onDestroy();
  }

  protected int setLayoutId() {
    return 0;
  }

  public void setOnEmoGridViewItemClickListener(OnEmoGridViewItemClickListener paramOnEmoGridViewItemClickListener) {
    this.listener = paramOnEmoGridViewItemClickListener;
  }

  public void show(boolean paramBoolean) {
    if (paramBoolean && "emoticon_history".equals(this.tag)) {
      EmoticonHistoryAdapter emoticonHistoryAdapter = this.adapter;
      if (emoticonHistoryAdapter != null)
        emoticonHistoryAdapter.changeCursor(getCursor());
    }
  }

  public static interface OnEmoGridViewItemClickListener {
    void onHistoryClick(long param1Long, String param1String1, String param1String2, String param1String3, String param1String4, String param1String5);

    void onItemClick(int param1Int1, int param1Int2, int param1Int3, String param1String);
  }
}
