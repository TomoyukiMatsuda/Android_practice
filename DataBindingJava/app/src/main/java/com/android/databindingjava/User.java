package com.android.databindingjava;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

// BaseObservableを継承
public class User extends BaseObservable {
    private String name;

    public User(String name) {
        this.name = name;
    }

    // getNameに@Bindableを付与することにより監視用の定数BR.nameが生成される
    @Bindable
    public String getName() {
        return name;
    }

    // setNameにnotifyPropertyChanged(BR.name)を付与することで
    // レイアウト側からBR.nameに対応するgetName()が呼ばれる（setNameされるタイミングでgetNameがレイアウト側から呼ばれる）　
    public void setName(String name) {
        this.name = name;

        notifyPropertyChanged(BR.name);
    }
}
