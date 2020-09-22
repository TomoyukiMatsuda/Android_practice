package com.websarva.wings.android.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MenuThanksFragment extends Fragment {

    //フラグメントが所属する親アクティビティ
    private Activity _parentActivity;


    // 所属親アクティビティを取得してる
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 所属するアクティビティオブジェクトを取得
        _parentActivity = getActivity();
    }

    // 所属親アクティビティから渡されたデータを受け取り、戻るボタンにリスナを登録している
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //フラグメントで表示する画面をxmlファイルからインフレーと
        View view = inflater.inflate(R.layout.fragment_menu_thanks, container, false);
        //所属親アクティビティからインテントを取得
        Intent intent = _parentActivity.getIntent();
        //インテントから引継ぎデータをまとめたBundleオブジェクトを取得
        Bundle extras = intent.getExtras();
        //引き継ぎデータがうまく取得できなかった時のために""で初期化
        String menuName = "";
        String menuPrice = "";

        //引継ぎデータがnullでない場合には変数にメニュー名と価格を取得
        if (extras != null) {
            menuName = extras.getString("menuName");
            menuPrice = extras.getString("menuPrice");
        }

        // TextViewパーツ要素を取得
        TextView tvMenuName = view.findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);

        //TextViewパーツに受け取ったデータを格納
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        //戻るボタン要素を取得
        Button btBackButton = view.findViewById(R.id.btBackButton);

        // 戻るボタンにリスナを登録
        btBackButton.setOnClickListener(new ButtonClickListener());

        return view;
    }

    //ボタンが押された時の処理を記述したメンバクラス
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //自分の所属するアクティビティを終了する
            _parentActivity.finish();
        }
    }
}
