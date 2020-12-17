package com.android.databindingcustomview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;

import com.android.databindingcustomview.databinding.ViewUserInfoBinding;

public class UserInfoView extends LinearLayout {
    ViewUserInfoBinding mBinding;

    public UserInfoView(Context context) {
        super(context);
    }

    public UserInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.view_user_info, this, true);
    }

    public void setUser(User user) {
        mBinding.setUser(user);
    }
}
