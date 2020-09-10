package com.websarva.wings.android.hellosample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 表示ボタンであるButtonオブジェクトを取得。
        Button btClick = findViewById(R.id.btClick);
        // リスなクラスのインスタンスを生成
        HelloListener listener = new HelloListener();
        // 表示ボタンにリスなを設定
        btClick.setOnClickListener(listener);

        // クリアボタン要素をを取得
        Button btClear = findViewById(R.id.btClear);
        // クリアボタン要素にリスナを設定
        btClear.setOnClickListener(listener);
    }

    private class HelloListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            // 名前入力欄であるEditTextオブジェクトを取得
            EditText input = findViewById(R.id.etName);
            // メッセージを表示するTextViewオブジェクトを取得
            TextView output = findViewById(R.id.tvOutput);

            // タップされた画面部品のidのR値を取得
            int id = view.getId();
            // idのRちに応じて処理を分岐
            switch(id) {
                // idがbtClickの場合
                case R.id.btClick:
                    // 入力された名前文字列を取得
                    String inputStr = input.getText().toString();
                    // メッセージを表示
                    output.setText(inputStr + "さん、こんにちは！");
                    break;
                // idがbtClearの場合
                case R.id.btClear:
                    // 名前入力らんを空文字にする
                    input.setText("");
                    // メッセージ表示欄を空文字にする
                    output.setText("");
                    break;
            }
        }
    }
}