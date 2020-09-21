package com.websarva.wings.android.menusample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //リストビューを表すフィールド
    private ListView _lvMenu;
    //リストデータ
    private List<Map<String, Object>> _menuList;
    //SimpleAdapter引数fromに使用する定数フィールド
    private static final String[] FROM = {"name", "price"};
    //SimpleAdapterの引数toに使用する定数フィールド
    private static final int[] TO = {R.id.tvMenuName, R.id.tvMenuPrice};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面部品ListViewを取得しフィールドに格納
        _lvMenu = findViewById(R.id.lvMenu);
        //SimpleAdapterで使用する定食メニューListオブジェクトをprivateメソッドを利用して用意、フィールドに格納
        _menuList = createTeishokuList();
        //SimpleAdapterを生成
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.row, FROM, TO);
        //アダプタの登録
        _lvMenu.setAdapter(adapter);
        //リストタップのリスナクラス登録
        _lvMenu.setOnItemClickListener(new ListItemClickListener());

        //コンテキストメニューをリストビューに登録、長押しを検知するビューを登録している（リストビュー）
        registerForContextMenu(_lvMenu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        //IDのR値による処理の分岐
        switch (itemId) {
            //定食が選択された時
            case R.id.menuListOptionTeishoku:
                _menuList = createTeishokuList();
                break;
            //カレーが選択された時の処理
            case R.id.menuListOptionCurry:
                _menuList = createCurryList();
                break;
        }

        //SimpleAdapterを選択sれたメニューデータで生成
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.row, FROM, TO);
        //アダプタの登録
        _lvMenu.setAdapter(adapter);
        // 親クラスの同名メソッドを呼び出し、その戻り値を返却
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //親クラスの同名メソッドの呼び出し
        super.onCreateContextMenu(menu, v, menuInfo);
        //メニューインフレーたの取得
        MenuInflater inflater = getMenuInflater();
        //コンテキストメニューようxmlファイルをインフレーと
        inflater.inflate(R.menu.menu_context_menu_list, menu);
        //コンテキストメニューのヘッダタイトルを設定
        menu.setHeaderTitle(R.string.menu_list_context_header);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int listPosition = info.position;

        Map<String, Object> menu = _menuList.get(listPosition);


        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.menuListContextDesc:
                String desc = (String) menu.get("desc");
                Toast.makeText(MainActivity.this, desc, Toast.LENGTH_LONG).show();
                break;

            case R.id.menuListContextOrder:
                order(menu);
                break;
        }

        return super.onContextItemSelected(item);
    }

    // アクションバーメニューリストを表示する
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //メニューインフレーターの取得
        MenuInflater inflater = getMenuInflater();
        //オプションメニュー用xmlファイルをインフレーと
        inflater.inflate(R.menu.menu_options_menu_list, menu);
        //親クラスの同名メソッドを呼び出し、その戻り値を返却
        return super.onCreateOptionsMenu(menu);
    }

    // リストビューに表示させる定食リストデータを生成するメソッド
    private List<Map<String, Object>> createTeishokuList() {
        // 定食メニューリスト用のListオブジェクトを用意
        List<Map<String, Object>> menuList = new ArrayList<>();

        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");
        menu.put("price", 800);
        menu.put("desc", "若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", 850);
        menu.put("desc", "すりおろし生姜を使った生姜焼きにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", 1000);
        menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "野菜炒め定食");
        menu.put("price", 750);
        menu.put("desc", "季節の野菜炒めにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "とんかつ定食");
        menu.put("price", 900);
        menu.put("desc", "ロースとんかつにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ミンチかつ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねミンチカツにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "チキンカツ定食");
        menu.put("price", 900);
        menu.put("desc", "ボリュームたっぷりチキンカツにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "コロッケ定食");
        menu.put("price", 850);
        menu.put("desc", "北海道ポテトコロッケにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼き魚定食");
        menu.put("price", 850);
        menu.put("desc", "鰆の塩焼きにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼肉定食");
        menu.put("price", 950);
        menu.put("desc", "特性たれの焼肉にサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        return menuList;
    }

    private List<Map<String, Object>> createCurryList() {
        //カレーメニューリスト用のListObjectを用意
        List<Map<String, Object>> menuList = new ArrayList<>();

        //データを格納するMapObjectの用意とmenuListへのデータ登録
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "ビーフカレー");
        menu.put("price", 520);
        menu.put("desc", "特選スパイスを聞かせた国産ビーフ100%のカレーです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ポークカレー");
        menu.put("price", 420);
        menu.put("desc", "特選スパイスを聞かせた国産ポーク100%のカレーです。");

        menu = new HashMap<>();
        menu.put("name", "ハンバーグカレー");
        menu.put("price", 620);
        menu.put("desc", "特選スパイスをきかせたカレーに手ごねハンバーグをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "チーズカレー");
        menu.put("price", 560);
        menu.put("desc", "特選スパイスをきかせたカレーにとろけるチーズをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "カツカレー");
        menu.put("price", 760);
        menu.put("desc", "特選スパイスをきかせたカレーに国産ロースカツをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ビーフカツカレー");
        menu.put("price", 880);
        menu.put("desc", "特選スパイスをきかせたカレーに国産ビーフカツをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "からあげカレー");
        menu.put("price", 540);
        menu.put("desc", "特選スパイスをきかせたカレーに若鳥のから揚げをトッピングです。");
        menuList.add(menu);

        return menuList;
    }

    //注文メソッド
    private void order(Map<String, Object> menu) {
        //メニュー名と金額を取得.Mapの値部分がObject型なのでキャストが必要
        String menuName = (String) menu.get("name");
        Integer menuPrice = (Integer) menu.get("price");

        //インテントオブジェクトを生成
        Intent intent = new Intent(MainActivity.this, MenuThanksActivity.class);
        //第２画面に送るデータを格納する
        intent.putExtra("menuName", menuName);
        //データの受け取りと合わせる必要があるため、円を追加
        intent.putExtra("menuPrice", menuPrice + "円");

        //遷移先画面の起動
        startActivity(intent);
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // タップされた行のデータを取得
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
            //orderメソッドで注文処理
            order(item);
        }
    }

}
