package com.jk.jkproject.utils;

import android.text.InputFilter;
import android.text.Spanned;
import java.io.UnsupportedEncodingException;

public class LengthInputFilter implements InputFilter {
  private int max;
  
  public LengthInputFilter(int paramInt) {
    this.max = paramInt * 2;
  }
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4) {
    try {
      return ((paramSpanned.toString().getBytes("GB18030")).length + (paramCharSequence.toString().getBytes("GB18030")).length > this.max) ? "" : ((paramCharSequence.length() < 1 && paramInt4 - paramInt3 >= 1) ? paramSpanned.subSequence(paramInt3, paramInt4 - 1) : paramCharSequence);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      unsupportedEncodingException.printStackTrace();
      return "";
    } 
  }
}