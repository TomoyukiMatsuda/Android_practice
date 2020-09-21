package com.websarva.wings.android.menusample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MenuThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thanks);

        // Intentを取得
        Intent intent = getIntent();
        // Intentに格納されている文字列を変数に格納
        String menuName = intent.getStringExtra("menuName");
        String menuPrice = intent.getStringExtra("menuPrice");

        //表示するパーツを取得
        TextView tvMenuName = findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);

        // 表示パーツに文字列を格納
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        //アクションバーを取得
        ActionBar actionBar = getSupportActionBar();
        //アクションバーに戻るメニューを設定,trueで有効になる
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //選択されたメニューのIDを取得
        int itemId = item.getItemId();
        //戻るボタンタップでアクティビティ終了
        if(itemId == android.R.id.home) {
            finish();
        }
        // 親クラスの同名メソッドを呼び出し、その戻り値を返却
        return super.onOptionsItemSelected(item);
    }
}
