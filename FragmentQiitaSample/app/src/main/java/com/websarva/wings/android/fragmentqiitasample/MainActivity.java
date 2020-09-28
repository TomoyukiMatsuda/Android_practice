package com.websarva.wings.android.fragmentqiitasample;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MainFragmentを表示
        addFragment(new MainFragment());
    }

    // Fragmentを表示させるメソッドを定義（表示したいFragmentを引数として渡す）
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

    // 戻るボタン「←」をアクションバー（上部バー）にセットするメソッドを定義
    public void setupBackButton(boolean enableBackButton) {
        // アクションバーを取得
        ActionBar actionBar = getSupportActionBar();
        // アクションバーに戻るボタン「←」をセット（引数が true: 表示、false: 非表示）
        actionBar.setDisplayHomeAsUpEnabled(enableBackButton);
    }
}
