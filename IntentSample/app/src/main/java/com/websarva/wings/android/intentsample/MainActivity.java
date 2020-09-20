package com.websarva.wings.android.intentsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 画面部品をlayoutから取得
        ListView lvMenu = findViewById(R.id.lvMenu);
        // SimpleAdapterで使用するListオブジェクトを用意する
        List<Map<String, String>> menuList = new ArrayList<>();

        //Mapオブジェクトの用意とmenuListへのデータ登録
        Map<String, String> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");
        menu.put("price", "800円");
        menuList.add(menu);

        // ２件目以降のデータを格納する
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", "1000円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "野菜炒め定食");
        menu.put("price", "750円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "とんかつ定食");
        menu.put("price", "900円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ミンチかつ定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "チキンカツ定食");
        menu.put("price", "900円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "コロッケ定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼き魚定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼肉定食");
        menu.put("price", "950円");
        menuList.add(menu);

        // SimpleAdapter第４引数from用データの用意
        String[] from = {"name", "price"};
        //SimpleAdapter第５引数to用データを用意
        int[] to = {android.R.id.text1, android.R.id.text2};
        //SimpleAdapterを生成
        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, menuList, android.R.layout.simple_list_item_2, from, to);
        //アダプタの登録
        lvMenu.setAdapter(simpleAdapter);

        // メニューリストの各要素をクリックした時イベントを登録
        lvMenu.setOnItemClickListener(new ListItemClickListener());
    }

    // リストがタップされた時の処理が記述されたメンバクラス
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMap型
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            // 定食名と金額を取得
            String menuName = item.get("name");
            String menuPrice = item.get("price");

            // インテントオブジェクトを生成
            Intent intent = new Intent(MainActivity.this, MenuThanksActivity.class);
            // 遷移後の画面に送るデータをIntentに格納
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice);

            // 遷移先画面の起動
            startActivity(intent);
        }
    }
}
