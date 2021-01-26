package com.jk.jkproject.ui.widget;

import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

public class ShapeHolder {
  private float alpha = 1.0F;
  
  private int color;
  
  private Paint paint;
  
  private ShapeDrawable shape;
  
  private float x = 0.0F;
  
  private float y = 0.0F;
  
  public ShapeHolder(ShapeDrawable paramShapeDrawable) {
    this.shape = paramShapeDrawable;
  }
  
  public int getColor() {
    return this.color;
  }
  
  public float getHeight() {
    return this.shape.getShape().getHeight();
  }
  
  public Paint getPaint() {
    return this.paint;
  }
  
  public ShapeDrawable getShape() {
    return this.shape;
  }
  
  public float getWidth() {
    return this.shape.getShape().getWidth();
  }
  
  public float getX() {
    return this.x;
  }
  
  public float getY() {
    return this.y;
  }
  
  public void resizeShape(float paramFloat1, float paramFloat2) {
    this.shape.getShape().resize(paramFloat1, paramFloat2);
  }
  
  public void setAlpha(float paramFloat) {
    this.alpha = paramFloat;
    this.shape.setAlpha((int)(255.0F * paramFloat + 0.5F));
  }
  
  public void setColor(int paramInt) {
    this.shape.getPaint().setColor(paramInt);
    this.color = paramInt;
  }
  
  public void setHeight(float paramFloat) {
    Shape shape = this.shape.getShape();
    shape.resize(shape.getWidth(), paramFloat);
  }
  
  public void setPaint(Paint paramPaint) {
    this.paint = paramPaint;
  }
  
  public void setShape(ShapeDrawable paramShapeDrawable) {
    this.shape = paramShapeDrawable;
  }
  
  public void setWidth(float paramFloat) {
    Shape shape = this.shape.getShape();
    shape.resize(paramFloat, shape.getHeight());
  }
  
  public void setX(float paramFloat) {
    this.x = paramFloat;
  }
  
  public void setY(float paramFloat) {
    this.y = paramFloat;
  }
}
