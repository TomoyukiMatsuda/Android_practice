package com.android.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.databindingjava.databinding.ActivityMainBinding;

// EventHandlers（インターフェース） を実装
public class MainActivity extends AppCompatActivity implements EventHandlers {

    private Character chara = new Character("ドラえもん");

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

        // charaのnameの文字列によって、セットする文字列を変える
        if (chara.getName().equals("ドラえもん")) {
            chara.setName("のびた");
        } else {
            chara.setName("ドラえもん");
        }
    }
}
