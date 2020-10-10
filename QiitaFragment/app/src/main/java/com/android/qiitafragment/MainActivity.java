package com.android.qiitafragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // デフォルト表示のフラグメントを表示させる
        showFragment(new DefaultFragment(), getSupportFragmentManager());
    }

    @Override
    public void onBackPressed() {
        // ここで現在いるフラグメントを取得
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_activity_main);

        Log.i(MainActivity.class.getSimpleName(), fragment.toString() + "で端末戻るボタンを押したよ");

        if (fragment instanceof FirstFragment || fragment instanceof SecondFragment) {

            finish();
        }
        super.onBackPressed();
    }

    // フラグメントを表示する切り替えるメソッド
    public void showFragment(Fragment fragment, FragmentManager fragmentManager) {

        fragmentManager
                .beginTransaction()
                .replace(R.id.fl_activity_main, fragment)
                .addToBackStack(null)
                .commit();
    }

    // バックボタン実装メソッド
    public void setupBackButton(boolean enableBackButton) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(enableBackButton);
    }

}
