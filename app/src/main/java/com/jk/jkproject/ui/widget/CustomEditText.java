package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/**
*@params
*@date 2020/10/28 3:50 PM
*@author Zick
*@desc 只支持输入汉字，字母、数字的编辑框，不支持输入特殊字符和空格
*/
public class CustomEditText extends AppCompatEditText {


    public CustomEditText(Context context) {

        super(context);

    }

    public CustomEditText(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {

        return new mInputConnection(super.onCreateInputConnection(outAttrs), false);

    }

    class mInputConnection extends InputConnectionWrapper implements InputConnection {

        public mInputConnection(InputConnection target, boolean mutable) {

            super(target, mutable);

        }
        //拦截内容
        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            // 只能输入汉字或者英文
            if (!text.toString().matches("[\u4e00-\u9fa5]+") && !text.toString().matches("[a-zA-Z0-9 /]+")) {
                return false;
            }
            return super.commitText(text, newCursorPosition);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean setSelection(int start, int end) {
            return super.setSelection(start, end);
        }
    }
}
