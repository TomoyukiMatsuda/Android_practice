package com.android.databindingjava;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public abstract class EventHandlers extends BaseObservable {
    // クリックイベントに対応させたいため、引数はView.OnClickListenerのonClickと同じ（View view）にする
    public abstract void onChangeClick(View view);

    @Bindable
    public abstract boolean isChecked();

    public abstract void setChecked(boolean isEnabled);

    //MVVM設計の見直し兼DataBindingのお勉強じゃ！！Qiita投稿
}
