package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.adapter.EmoGridViewAdapter;
import com.jk.jkproject.ui.adapter.EmoViewPageAdapter;
import com.jk.jkproject.ui.model.EmotionModle;
import com.jk.jkproject.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class EmoGridView extends LinearLayout {
  private Context context;
  
  private int currentIndex;
  
  private List<EmotionModle> data;
  
  private ImageView[] dots;
  
  private List<GridView> list_Views;
  
  private LinearLayout llDot;
  
  private OnEmoGridViewItemClick onEmoGridViewItemClick;
  
  public int pageItemCount = 20;
  
  private ViewPager viewPager;
  
  private int viewPager_size;
  
  public EmoGridView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    setBackgroundResource(R.color.white);
  }
  
  public EmoGridView(Context paramContext, List<EmotionModle> paramList) {
    super(paramContext);
    this.context = paramContext;
    setBackgroundResource(R.color.white);
  }
  
  private ArrayList<EmotionModle> getGridViewData(int paramInt) {
    int i = paramInt + 1;
    paramInt = this.pageItemCount;
    int j = (i - 1) * paramInt;
    i = paramInt * i;
    paramInt = i;
    if (i > this.data.size())
      paramInt = this.data.size(); 
    ArrayList<EmotionModle> arrayList = new ArrayList();
    for (i = j; i < paramInt; i++)
      arrayList.add(this.data.get(i)); 
    if (paramInt - j + 1 > 1) {
      EmotionModle emotionModle = new EmotionModle();
      emotionModle.setEmoticonName("del");
      emotionModle.setId(R.drawable.emotion_del);
      arrayList.add(emotionModle);
    } 
    return arrayList;
  }
  
  private GridView getViewPagerItem(final int index) {
    GridView gridView = (GridView)LayoutInflater.from(this.context).inflate(R.layout.emo_gridview, null).findViewById(R.id.chart_face_gv);
    gridView.setNumColumns(7);
    gridView.setVerticalScrollBarEnabled(false);
    gridView.setHorizontalScrollBarEnabled(false);
    gridView.setOverScrollMode(2);
    gridView.setAdapter((ListAdapter)new EmoGridViewAdapter(this.context, getGridViewData(index), true, null));
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            int i = index;
            int j = EmoGridView.this.pageItemCount;
            EmoGridView.this.onEmoGridViewItemClick.onItemClick(param1Int + i * j, index);
          }
        });
    return gridView;
  }
  
  private void initFootDots() {
    this.llDot.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
    this.llDot.setGravity(17);
    this.llDot.setOrientation(VERTICAL);
    double d1 = this.pageItemCount;
    double d2 = this.data.size();
    Double.isNaN(d2);
    Double.isNaN(d1);
    this.viewPager_size = (int)Math.ceil(d2 / d1);
    LayoutInflater layoutInflater = LayoutInflater.from(this.context);
    int i = this.viewPager_size;
    if (i > 0)
      if (i == 1) {
        this.llDot.setVisibility(GONE);
      } else {
        this.llDot.setVisibility(VISIBLE);
        for (i = 0; i < this.viewPager_size; i++) {
          ImageView imageView = (ImageView)layoutInflater.inflate(R.layout.emo_dot_image, null).findViewById(R.id.face_dot);
          LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(12, 12);
          layoutParams.setMargins(5, 10, 5, 10);
          imageView.setEnabled(false);
          this.llDot.addView((View)imageView, (ViewGroup.LayoutParams)layoutParams);
        } 
      }  
    i = this.viewPager_size;
    if (1 < i) {
      this.dots = new ImageView[i];
      for (i = 0; i < this.viewPager_size; i++) {
        this.dots[i] = (ImageView)this.llDot.getChildAt(i);
        this.dots[i].setEnabled(true);
        this.dots[i].setTag(Integer.valueOf(i));
      } 
      this.currentIndex = 0;
      this.dots[this.currentIndex].setEnabled(false);
      this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int param1Int) {}
            
            public void onPageScrolled(int param1Int1, float param1Float, int param1Int2) {}
            
            public void onPageSelected(int param1Int) {
              EmoGridView.this.setCurDot(param1Int);
            }
          });
      addView((View)this.llDot);
    } 
  }
  
  private void initViewPage() {
    setOrientation(VERTICAL);
    this.viewPager = new ViewPager(this.context);
    this.llDot = new LinearLayout(this.context);
    int i = ScreenUtils.dp2px(this.context, 155.0F);
    this.viewPager.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, i));
    this.viewPager.setOverScrollMode(2);
    addView((View)this.viewPager);
  }
  
  private void setCurDot(int paramInt) {
    if (paramInt < 0 || paramInt > this.viewPager_size - 1 || this.currentIndex == paramInt)
      return; 
    this.dots[paramInt].setEnabled(false);
    this.dots[this.currentIndex].setEnabled(true);
    this.currentIndex = paramInt;
  }
  
  public void setAdapter() {
    if (this.onEmoGridViewItemClick == null)
      return; 
    this.list_Views = new ArrayList<GridView>();
    for (byte b = 0; b < this.viewPager_size; b++)
      this.list_Views.add(getViewPagerItem(b)); 
    this.viewPager.setAdapter((PagerAdapter)new EmoViewPageAdapter(this.list_Views));
  }
  
  public void setGridViewData(List<EmotionModle> paramList) {
    this.data = paramList;
    if (paramList != null) {
      initViewPage();
      initFootDots();
    } 
  }
  
  public void setOnEmoGridViewItemClick(OnEmoGridViewItemClick paramOnEmoGridViewItemClick) {
    this.onEmoGridViewItemClick = paramOnEmoGridViewItemClick;
  }
  
  public static interface OnEmoGridViewItemClick {
    void onItemClick(int param1Int1, int param1Int2);
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\widget\EmoGridView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */