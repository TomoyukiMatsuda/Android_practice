package com.websarva.wings.android.intentsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thanks);

        //インテントオブジェクトを取得
        Intent intent = getIntent();
        //Intentに格納されたデータを取得
        String menuName = intent.getStringExtra("menuName");
        String menuPrice = intent.getStringExtra("menuPrice");

        // 表示先のTextView要素を取得
        TextView tvMenuName = findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);

        // 表示先のTextViewにIntentから取得したデータを格納する
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);
    }

    //戻るボタンをタップした時の処理
    public void onBackButtonClick(View view) {
        // Activityを終了させて、前画面に戻る
        finish();
    }
}
