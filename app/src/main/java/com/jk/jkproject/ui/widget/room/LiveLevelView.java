package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;

/**
 * Created by zhaot on 2016/2/27.
 */
public class LiveLevelView extends LinearLayout {


    private TextView tvText;
    private boolean showZeroLevel = false;//是否显示0级等级

    public LiveLevelView(Context context) {
        super(context);
        init(context);
    }

    public LiveLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LiveLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.live_level_view, this, true);
        tvText = (TextView) findViewById(R.id.live_level_text);
    }


    private void setText(CharSequence text) {
        if (tvText != null) {
            tvText.setText(text);
        }
    }

    public void setTextSize(float size) {
        if (tvText != null) {
            tvText.setTextSize(size);
        }
    }


    public void setShowZeroLevel(boolean show) {
        showZeroLevel = show;
    }

    private void setLevelBg(int resId) {
        if (tvText != null) {
            tvText.setBackgroundResource(resId);
        }
    }

    /**
     * 设置等级
     *
     * @param level level=100表示主播
     */
    public void setLevel(int level) {
        setVisibility(View.VISIBLE);

        switch (level) {
            case 100:
//                setLevelBg(R.mipmap.lv_1000);
                setText("");
                break;
            case 0:
                if (showZeroLevel) {
                    setLevelBg(R.mipmap.icon_level_stage_zero);
                    setText("");
                    setVisibility(View.VISIBLE);
                } else {
                    setVisibility(View.GONE);
                }
                break;

            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                //一级
                setLevelBg(R.drawable.icon_level_stage_one);
                setText(String.valueOf(level));
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                //二级
                setLevelBg(R.drawable.icon_level_stage_two);
                setText(String.valueOf(level));
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
//                三级
                setLevelBg(R.drawable.icon_level_stage_three);
                setText(String.valueOf(level));
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
//                四级
                setLevelBg(R.drawable.icon_level_stage_four);
                setText(String.valueOf(level));
                break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
//                五级
                setLevelBg(R.drawable.icon_level_stage_five);
                setText(String.valueOf(level));
                break;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
//                六级
                setLevelBg(R.drawable.icon_level_stage_six);
                setText(String.valueOf(level));
                break;

            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
//                七级
//                setLevelBg(R.drawable.icon_level_stage_seven);
                setText(String.valueOf(level));
                break;


//            90——    Level11
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
//                八级
//                setLevelBg(R.drawable.icon_level_stage_eight);
                setText(String.valueOf(level));
                break;
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
//                九级              56-69      Level9
//                setLevelBg(R.drawable.icon_level_stage_nine);
                setText(String.valueOf(level));
                break;
            case 70:
            case 71:
            case 72:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
//                setLevelBg(R.drawable.icon_level_stage_ten);
                setText(String.valueOf(level));
                break;
            default:
//                setLevelBg(R.drawable.icon_level_stage_eleven);
                setText(String.valueOf(level));
                break;
        }
    }

//    public void setRole(int role, int level) {
//        setVisibility(View.VISIBLE);
//        if (role > 0) {
//            //0:普通 1:主播 2:守护 4:匿名 8:男友 16:老公
//            if ((role & 16) == 16) {
//                setLevelBg(R.mipmap.lv_9002);
//            } else if ((role & 8) == 8) {
//                setLevelBg(R.mipmap.lv_9001);
//            } else if ((role & 4) == 4) {
//                setLevelBg(R.mipmap.lv_1002);
//            } else if ((role & 2) == 2) {
//                setLevelBg(R.mipmap.lv_1001);
//            } else if ((role & 1) == 1) {
//                setLevelBg(R.mipmap.lv_1000);
//            }
//            setText("");
//        } else {
//            setLevel(level);
//        }
//    }

    public void setAge(int sex, int age) {
        setText(String.valueOf(age));
        if (sex == 1) {
            // 女
            setLevelBg(R.mipmap.live_icon_my_female);
        } else {

            setLevelBg(R.mipmap.live_icon_my_male);
        }
    }

    public void setSelfVisibility(int visibility) {
        tvText.setVisibility(visibility);
        setVisibility(visibility);
    }

}
