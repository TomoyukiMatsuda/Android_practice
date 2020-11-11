package com.android.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.databindingjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements EventHandlers {

    private User mUser = new User("ともゆき");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml に対応したクラスのインスタンスを作成
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // activity_main.xmlのuserにmUserをセット
        binding.setUser(mUser);
        // activity_main.xmlのhandlersにセット
        binding.setEventHandlers(this);
    }

    // button_changeのクリックイベント処理
    @Override
    public void onChangeClick(View view) {

        // mUserのnameの情報によってセットする文字列を変える
        if (mUser.getName() == "ともゆき") {
            mUser.setName("まつだ");
        } else {
            mUser.setName("ともゆき");
        }

        Log.d("DEBUG", mUser.getName());
    }
}
