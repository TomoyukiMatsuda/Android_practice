package com.websarva.wings.android.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuListFragment extends Fragment {

    //このフラグメントが所属する親アクティビティ
    private Activity _parentActivity;

    // 大画面判定を行うための変数を定義
    // 大画面であればtrue, 通常であればfalseとする
    // 判定ロジックは menuThanksFrame があるかどうかで判定する
    private boolean _isLayoutXLarge = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //親クラスonCreateの呼び出し

        //所属するアクティビティオブジェクトを取得
        _parentActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //フラグメントで表示する画面をxmlファイルからインフレートする
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        //画面部品ListViewを取得
        ListView lvMenu = view.findViewById(R.id.lvMenu);

        //SimpleAdapterで使用するListオブジェクトを用意
        List<Map<String, String>> menuList = new ArrayList<>();

        //データを格納するMapオブジェクトの用意とmenuListへのデータ登録
        Map<String, String> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");
        menu.put("price", "800円");
        menuList.add(menu);

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

        //SimpleAdapter第４引数from用データの用意
        String[] from = {"name", "price"};
        //SimpleAdapter第5引数to用データの用意
        int[] to = {android.R.id.text1, android.R.id.text2};
        //SimpleAdapterを生成
        SimpleAdapter adapter = new SimpleAdapter(_parentActivity, menuList, android.R.layout.simple_list_item_2, from, to);
        //アダプタを取得したリストビューに登録
        lvMenu.setAdapter(adapter);

        //リストビューにクリックリスナを登録（リストビューがクリックされた時に呼び出されるように設定）
        lvMenu.setOnItemClickListener(new ListItemClickListener());

        //インフレートされた画面を戻り値として返す
        return view;
    }

    //ここのonActivityCreatedで判定処理を記述する
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //親クラスのメソッド呼び出し
        super.onActivityCreated(savedInstanceState);
        //所属親アクティビティからmenuThanksFrameを取得する
        View menuThanksFrame = _parentActivity.findViewById(R.id.menuThanksFrame);
        //menuThanksFrameがnull（通常画面）であれば
        if (menuThanksFrame == null) {
            // 大画面判定変数にfalseを代入する
            _isLayoutXLarge = false;
        }
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMap型!
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            //定食名と金額を取得。
            String menuName = item.get("name");
            String menuPrice = item.get("price");

            //渡すデータを格納するBundleオブジェクトを生成
            Bundle bundle = new Bundle();
            //Bundleに渡すデータを格納
            bundle.putString("menuName", menuName);
            bundle.putString("menuPrice", menuPrice);

            //大画面の場合と通常の場合でデータの格納方法を変更する
            if (_isLayoutXLarge) {
                //大画面の場合
            } else {
                //通常画面の場合
                //インテントオブジェクトを生成
                Intent intent = new Intent(_parentActivity, MenuThanksActivity.class);
                //第２画面に送るデータをintentに格納、ここではBundleオブジェクトとしてまとめてデータを格納してる
                intent.putExtras(bundle);
                //第２画面の起動
                startActivity(intent);
            }
        }
    }
}
