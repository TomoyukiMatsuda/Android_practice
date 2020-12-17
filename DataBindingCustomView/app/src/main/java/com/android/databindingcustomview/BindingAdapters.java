package com.android.databindingcustomview;

import android.graphics.Color;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("user_info")
    public static void setUser(UserInfoView view, User user) {
        view.setUser(user);
    }

    @BindingAdapter("user_info")
    public static void setUser(TextView view, User user) {
        // 30以上は赤文字
        if (user.getAge() >= 30) {
            view.setTextColor(Color.RED);
        } else {
            view.setTextColor(Color.BLUE);
        }
    }
}
