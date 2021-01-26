package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;


/**
 * 
 * @filename : DrawableLeftCenterRadioButton.java
 * @author : zhaotun
 * @description : drawableLeft的图标和文字居中显示的RadioButton
 * @version : 1.0
 */
public class DrawableLeftCenterRadioButton extends RadioButton {

	public DrawableLeftCenterRadioButton(Context context) {
		super(context);
	}

	public DrawableLeftCenterRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DrawableLeftCenterRadioButton(Context context, AttributeSet attrs,
                                         int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable[] drawables = getCompoundDrawables();
		if (drawables != null) {
			if (drawables[0] != null) {
				float textWidth = getPaint().measureText(getText().toString());
				int drawablePadding = getCompoundDrawablePadding();
//				int drawableWidth = (int) getResources().getDimension(R.dimen.padding_size_28);
				int drawableWidth = drawables[0].getIntrinsicWidth();
				float bodyWidth = textWidth + drawableWidth + drawablePadding;
				float dx = (getWidth() - bodyWidth) / 2;
				
				canvas.translate(dx, 0);
			} else if (drawables[2] != null) {
				float textWidth = getPaint().measureText(getText().toString());
				int drawablePadding = getCompoundDrawablePadding();
				int drawableWidth = drawables[2].getIntrinsicWidth();
				float bodyWidth = textWidth + drawableWidth + drawablePadding;
				float dx = - (getWidth() - bodyWidth) / 2;
				
				canvas.translate(dx, 0);
			} else if (drawables[3] != null) {
				Paint p = getPaint();
				p.setTextSize(getTextSize());
				FontMetrics fm = p.getFontMetrics();
				double textHeight = Math.ceil(fm.descent - fm.ascent);
				int drawablePadding = getCompoundDrawablePadding();
				int drawableHeight = drawables[3].getIntrinsicHeight();
				double bodyHeight = textHeight + drawableHeight	+ drawablePadding;
				float dy = - (float) (getHeight() - bodyHeight) / 2;
				
				canvas.translate(0, dy);
			}
		}
		super.onDraw(canvas);
	}
}
