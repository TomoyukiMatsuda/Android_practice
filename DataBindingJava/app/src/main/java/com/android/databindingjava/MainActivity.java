package com.android.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.android.databindingjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private User mUser = new User("ともゆき");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml に対応したクラスのインスタンスを作成
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // activity_main.xmlのuserにmUserをセット
        binding.setUser(mUser);
    }
}
