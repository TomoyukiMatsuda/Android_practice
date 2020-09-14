package com.websarva.wings.android.listviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewオブジェクトを取得
        ListView lvMenu = findViewById((R.id.lvMenu));
        // Listviewにリスナを設定
        lvMenu.setOnItemClickListener(new ListItemClickListener());
    }

    // リストビューのタップというイベントに対するリスナを呼び出す
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        // AdapterView<?>parent->タップされたリスト全体,View view->タップされた1行分の画面部品そのもの
        // int position->タップされた行番号, long id->
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // タップされた定食を取得
            String item = (String) parent.getItemAtPosition(position); // position(行番号)
            // トーストで表示する文字列を生成
            String show = "あなたが選んだ定食: " + item;
            // トーストの表示
            Toast.makeText(MainActivity.this, show, Toast.LENGTH_LONG).show();

        }
    }
}
