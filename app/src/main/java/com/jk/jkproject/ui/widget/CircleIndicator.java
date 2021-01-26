package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.adapter.LiveFoundCarouselAdapter;
import com.jk.jkproject.ui.adapter.LiveHotHeaderAdapter;

import java.util.ArrayList;
import java.util.List;

public class CircleIndicator extends View implements ViewPager.OnPageChangeListener {
  private ViewPager viewPager;
  private List<ShapeHolder> tabItems;
  private ShapeHolder movingItem;
  private ViewPager.OnPageChangeListener mListener;

  //config list
  private int mCurItemPosition;
  private float mCurItemPositionOffset;
  private float mIndicatorRadius;
  private float mIndicatorMargin;
  private int mIndicatorBackground;
  private int mIndicatorSelectedBackground;
  private Gravity mIndicatorLayoutGravity;
  private Mode mIndicatorMode;

  //default value
  private final int DEFAULT_INDICATOR_RADIUS = 10;
  private final int DEFAULT_INDICATOR_MARGIN = 40;
  private final int DEFAULT_INDICATOR_BACKGROUND = Color.BLUE;
  private final int DEFAULT_INDICATOR_SELECTED_BACKGROUND = Color.RED;
  private final int DEFAULT_INDICATOR_LAYOUT_GRAVITY = Gravity.CENTER.ordinal();
  private final int DEFAULT_INDICATOR_MODE = Mode.SOLO.ordinal();

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override
  public void onPageSelected(int position) {

  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }

  public enum Gravity {
    LEFT,
    CENTER,
    RIGHT
  }

  public enum Mode {
    INSIDE,
    OUTSIDE,
    SOLO
  }

  public CircleIndicator(Context context) {
    super(context);
    init(context, null);
  }

  public CircleIndicator(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    tabItems = new ArrayList<>();
    handleTypedArray(context, attrs);
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
    mListener = listener;
  }

  private void handleTypedArray(Context context, AttributeSet attrs) {
    if (context == null || attrs == null) return;
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
    mIndicatorRadius = typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_radius, DEFAULT_INDICATOR_RADIUS);
    mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_margin, DEFAULT_INDICATOR_MARGIN);
    mIndicatorBackground = typedArray.getColor(R.styleable.CircleIndicator_ci_background, DEFAULT_INDICATOR_BACKGROUND);
    mIndicatorSelectedBackground = typedArray.getColor(R.styleable.CircleIndicator_ci_selected_background, DEFAULT_INDICATOR_SELECTED_BACKGROUND);
    int gravity = typedArray.getInt(R.styleable.CircleIndicator_ci_gravity, DEFAULT_INDICATOR_LAYOUT_GRAVITY);
    mIndicatorLayoutGravity = Gravity.values()[gravity];
    int mode = typedArray.getInt(R.styleable.CircleIndicator_ci_mode, DEFAULT_INDICATOR_MODE);
    mIndicatorMode = Mode.values()[mode];
    typedArray.recycle();
  }

  public void setViewPager(final ViewPager viewPager) {
    this.viewPager = viewPager;
    tabItems.clear();
    createTabItems();
    createMovingItem();
    setUpListener();
  }

  private void setUpListener() {
    viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        if (mIndicatorMode != Mode.SOLO) {
          if (viewPager.getAdapter() instanceof LiveFoundCarouselAdapter) {
            position = ((LiveFoundCarouselAdapter) viewPager.getAdapter()).getPosition(position);
          }
          if (viewPager.getAdapter() instanceof LiveHotHeaderAdapter) {
            position = ((LiveHotHeaderAdapter) viewPager.getAdapter()).getPosition(position);
          }
          trigger(position, positionOffset);
          if (mListener != null) {
            mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
          }
        }
      }

      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (viewPager.getAdapter() instanceof LiveFoundCarouselAdapter) {
          position = ((LiveFoundCarouselAdapter) viewPager.getAdapter()).getPosition(position);

        }
        if (viewPager.getAdapter() instanceof LiveHotHeaderAdapter) {
          position = ((LiveHotHeaderAdapter) viewPager.getAdapter()).getPosition(position);
        }

        if (mIndicatorMode == Mode.SOLO) {
          trigger(position, 0);
        }
        if (mListener != null) {
          mListener.onPageSelected(position);
        }
      }
    });
  }

  /**
   * trigger to redraw the indicator when the ViewPager's selected item changed!
   *
   * @param position
   * @param positionOffset
   */
  private void trigger(int position, float positionOffset) {
    CircleIndicator.this.mCurItemPosition = position;
    CircleIndicator.this.mCurItemPositionOffset = positionOffset;
//        Log.e("CircleIndicator", "onPageScrolled()" + position + ":" + positionOffset);
    requestLayout();
    invalidate();
  }

  private void createTabItems() {
    int size = viewPager.getAdapter().getCount();
    for (int i = 0; i < size; i++) {
      OvalShape circle = new OvalShape();
      ShapeDrawable drawable = new ShapeDrawable(circle);
      ShapeHolder shapeHolder = new ShapeHolder(drawable);
      Paint paint = drawable.getPaint();
      paint.setColor(mIndicatorBackground);
      paint.setAntiAlias(true);
      shapeHolder.setPaint(paint);
      tabItems.add(shapeHolder);
    }
    if (size == 1) setVisibility(INVISIBLE);
    else setVisibility(VISIBLE);
  }

  private void createMovingItem() {
    OvalShape circle = new OvalShape();
    ShapeDrawable drawable = new ShapeDrawable(circle);
    movingItem = new ShapeHolder(drawable);
    Paint paint = drawable.getPaint();
    paint.setColor(mIndicatorSelectedBackground);
    paint.setAntiAlias(true);

    switch (mIndicatorMode) {
      case INSIDE:
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        break;
      case OUTSIDE:
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        break;
      case SOLO:
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        break;
    }

    movingItem.setPaint(paint);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        Log.e("CircleIndicator", "onLayout()");
    super.onLayout(changed, left, top, right, bottom);
    final int width = getWidth();
    final int height = getHeight();
    layoutTabItems(width, height);
    layoutMovingItem(mCurItemPosition, mCurItemPositionOffset);
  }

  private void layoutTabItems(final int containerWidth, final int containerHeight) {
    if (tabItems == null) {
      throw new IllegalStateException("forget to create tabItems?");
    }
    final float yCoordinate = containerHeight * 0.5f;
    final float startPosition = startDrawPosition(containerWidth);
    for (int i = 0, size = tabItems.size(); i < size; i++) {
      ShapeHolder item = tabItems.get(i);
      item.resizeShape(2 * mIndicatorRadius, 2 * mIndicatorRadius);
      item.setY(yCoordinate - mIndicatorRadius);
      float x = startPosition + (mIndicatorMargin + mIndicatorRadius * 2) * i;
      item.setX(x);
    }

  }

  private float startDrawPosition(final int containerWidth) {
    if (mIndicatorLayoutGravity == Gravity.LEFT) return 0;
    float tabItemsLength = tabItems.size() * (2 * mIndicatorRadius + mIndicatorMargin) - mIndicatorMargin;
    if (containerWidth < tabItemsLength) {
      return 0;
    }
    if (mIndicatorLayoutGravity == Gravity.CENTER) {
      return (containerWidth - tabItemsLength) / 2;
    }
    return containerWidth - tabItemsLength;
  }

  private void layoutMovingItem(final int position, final float positionOffset) {
    if (movingItem == null) {
      //            throw new IllegalStateException("forget to create movingItem?");
      return;
    }
    if (tabItems.size() != 0) {
      if (tabItems.size() <= position) {
        return;
      }
      ShapeHolder item = tabItems.get(position);
      movingItem.resizeShape(item.getWidth(), item.getHeight());
      float x = item.getX() + (mIndicatorMargin + mIndicatorRadius * 2) * positionOffset;
      movingItem.setX(x);
      movingItem.setY(item.getY());
    }


  }

  @Override
  protected void onDraw(Canvas canvas) {
//        Log.e("CircleIndicator", "onDraw()");
    super.onDraw(canvas);
    int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
    for (ShapeHolder item : tabItems) {
      canvas.save();
      canvas.translate(item.getX(), item.getY());
      item.getShape().draw(canvas);
      canvas.restore();
    }

    if (movingItem != null) {
      canvas.save();
      canvas.translate(movingItem.getX(), movingItem.getY());
      movingItem.getShape().draw(canvas);
      canvas.restore();
    }
    canvas.restoreToCount(sc);
  }

  public void setIndicatorRadius(float mIndicatorRadius) {
    this.mIndicatorRadius = mIndicatorRadius;
  }

  public void setIndicatorMargin(float mIndicatorMargin) {
    this.mIndicatorMargin = mIndicatorMargin;
  }

  public void setIndicatorBackground(int mIndicatorBackground) {
    this.mIndicatorBackground = mIndicatorBackground;
  }

  public void setIndicatorSelectedBackground(int mIndicatorSelectedBackground) {
    this.mIndicatorSelectedBackground = mIndicatorSelectedBackground;
  }

  public void setIndicatorLayoutGravity(Gravity mIndicatorLayoutGravity) {
    this.mIndicatorLayoutGravity = mIndicatorLayoutGravity;
  }

  public void setIndicatorMode(Mode mIndicatorMode) {
    this.mIndicatorMode = mIndicatorMode;
  }
}