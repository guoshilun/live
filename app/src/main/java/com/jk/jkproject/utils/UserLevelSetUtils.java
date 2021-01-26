package com.jk.jkproject.utils;

import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.R;

public class UserLevelSetUtils {

    //划分用户等级
    public static void setUserLevel(TextView tvUserLevel, String level) {
        int userLevel = Integer.parseInt(level);
        if (userLevel > 100) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_120);
        } else if (userLevel > 80) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_100);
        } else if (userLevel > 60) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_80);
        } else if (userLevel > 40) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_60);
        } else if (userLevel > 20) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_40);
        } else if (userLevel >= 0) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_user_level_round_20);
        }
    }

    public static void setHostLevel(TextView tvUserLevel, String level, boolean isHost) {
        if (!isHost) {
            if (SPUtils.getType() == 2) {
                tvUserLevel.setVisibility(View.VISIBLE);
            } else {
                tvUserLevel.setVisibility(View.GONE);
            }
        }
        setHostLevel(tvUserLevel, level);
    }

    //划分主播等级
    public static void setHostLevel(TextView tvUserLevel, String level) {
        int userLevel = Integer.parseInt(level);
        if (userLevel > 100) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_host_level_round_120);
        } else if (userLevel > 80) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_host_level_round_100);
        } else if (userLevel > 60) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_host_level_round_80);
        } else if (userLevel > 40) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_host_level_round_60);
        } else if (userLevel > 20) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_host_level_round_40);
        } else if (userLevel >= 0) {
            tvUserLevel.setBackgroundResource(R.drawable.bg_host_level_round_20);
        }
    }
}
