package com.websarva.wings.android.recyclerviewsample;


import android.graphics.Color;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbarを取得
        Toolbar toolbar = findViewById(R.id.toolbar);
        //ツールバーにロゴを設定
        toolbar.setLogo(R.mipmap.ic_launcher);
        //アクションバーにツールバーを設定
        setSupportActionBar(toolbar);
        //CollapsingToolbarLayoutを取得
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);
        // タイトルの文字列をstringファイルから設定
        toolbarLayout.setTitle(getString(R.string.toolbar_title));
        //通常サイズ時の文字色を設定
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        //縮小サイズ時の文字色を設定
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);

        //RecyclerViewを取得。
        RecyclerView lvMenu = findViewById(R.id.lvMenu);
        //LinearLayoutManagerオブジェクトを生成。
        LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this);
        //以下は他の2種のレイアウトマネージャー。
//		GridLayoutManager layout = new GridLayoutManager(ScrollListActivity.this, 5);
//		StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        //RecyclerViewにレイアウトマネージャーとしてLinearLayoutManagerを設定。
        lvMenu.setLayoutManager(layout);
        //定食メニューリストデータを生成。
        List<Map<String, Object>> menuList = createTeishokuList();
        //アダプタオブジェクトを生成。
        RecyclerListAdapter adapter = new RecyclerListAdapter(menuList);
        //RecyclerViewにアダプタオブジェクトを設定。
        lvMenu.setAdapter(adapter);
    }

    private List<Map<String, Object>> createTeishokuList() {
        //定食メニューリスト用のListオブジェクトを用意。
        List<Map<String, Object>> menuList = new ArrayList<>();

        //「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "から揚げ定食");
        menu.put("price", 800);
        menu.put("desc", "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        //「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        //以下データ登録の繰り返し。
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


    //    RecyclerViewのビューホルダクラス
    private class RecyclerListViewHolder extends RecyclerView.ViewHolder {
        // リストでメニュー名を表示する部品
        public TextView _tvMenuName;
        // リストで金額を表示する
        public TextView _tvMenuPrice;

        // コンストラクタ
        public RecyclerListViewHolder(View itemView) {
            //親クラスのコンストラクタの呼び出し
            super(itemView);
            //引数で渡されたリスト1行分の画面部品中から表示に使われるTextViewを登録
            _tvMenuName = itemView.findViewById(R.id.tvMenuName);
            _tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice);
        }
    }

    //アダプタクラス
    private class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListViewHolder> {
        // リストデータを保持するフィールド
        private List<Map<String, Object>> _listData;

        public RecyclerListAdapter(List<Map<String, Object>> listData) {
            // 引数のリストデータをフィールドに格納
            _listData = listData;
        }

        @Override
        public RecyclerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //レイアウトインフレータを取得
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            //row.xmlをインフレートし、1行分の画面部品とする
            View view = inflater.inflate(R.layout.row, parent, false);
            //ビューホルダオブジェクトを生成
            RecyclerListViewHolder holder = new RecyclerListViewHolder(view);
            //生成したビューホルダをリターン
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerListViewHolder holder, int position) {
            // リストデータから該当1行分のデータを取得
            Map<String, Object> item = _listData.get(position);
            //メニュー名文字列を取得
            String menuName = (String) item.get("name");
            //メニュー金額を取得
            int menuPrice = (Integer) item.get("price");
            // 表示用に金額を文字列に変換
            String menuPriceStr = String.valueOf(menuPrice);
            //ビューホルダのTextViewに設定
            holder._tvMenuName.setText(menuName);
            holder._tvMenuPrice.setText((menuPriceStr));
        }

        @Override
        public int getItemCount() {
            //リストデータ中の件数をリターン。
            return _listData.size();
        }

    }
}
