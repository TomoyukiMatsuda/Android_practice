package com.websarva.wings.android.fragmentqiitasample;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MainFragmentを表示
        addFragment(new MainFragment());
    }

    // ボタンクリックしたらフラグメントを切り替える処理を書きたい


    private void addFragment(Fragment fragment) {
        // フラグメントマネージャーの取得
        FragmentManager manager = getSupportFragmentManager();
        // フラグメントトランザクションの開始
        FragmentTransaction transaction = manager.beginTransaction();
        // MainFragmentを追加
        transaction.add(R.id.activityMain, fragment);
        // フラグメントトランザクションのコミット
        transaction.commit();
    }

    // 戻るボタン「←」をセットするメソッド
    public void setupBackButton(boolean enableBackButton) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(enableBackButton);
    }
}
