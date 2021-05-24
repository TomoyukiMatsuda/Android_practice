package com.android.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.android.databindingjava.databinding.ActivityMainBinding;

// EventHandlers（インターフェース） を実装
public class MainActivity extends AppCompatActivity ,EventHandlers {

    private Character chara = new Character("最初");
    private boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml に対応したクラスの bindingインスタンスを作成
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // activity_main.xmlのcharacterにcharaをセット
        binding.setCharacter(chara);
        // activity_main.xmlのeventHandlersにMainActivityをセット
        binding.setEventHandlers(this);
    }

    // button_changeのクリックイベント処理
    @Override
    public void onChangeClick(View view) {
        setChecked(!isChecked);

        // charaのnameの文字列によって、セットする文字列を変える
        if (isChecked) {
            chara.setName("チェックありだよ○");
        } else {
            chara.setName("チェックなしだよ×");
        }
    }

    @Override
    public boolean isChecked() {
        return this.isChecked;
    }

    @Override
    public void setChecked(boolean isEnabled) {
        this.isChecked = isEnabled;
        notifyPropertyChanged(BR.checked);
        notifyPropertyChanged(BR.character);
    }
}
