package com.websarva.wings.android.fragmentqiitasample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // フラグメントで表示する画面をlayoutファイルからインフレートする
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // 所属している親アクティビティを取得
        MainActivity activity = (MainActivity) getActivity();
        // アクションバーにタイトルをセット
        activity.setTitle("メインフラグメント");
        // 戻るボタンは非表示にする（MainFragmentでは戻るボタン不要）
        activity.setupBackButton(false);

        // ボタン要素を取得
        Button bt1 = view.findViewById(R.id.bt1);
        Button bt2 = view.findViewById(R.id.bt2);

        // ①ボタンをクリックした時の処理
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SubFragment1に遷移させる
                replaceFragment(new SubFragment1());
            }
        });

        // ②ボタンをクリックした時の処理
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SubFragment2に遷移させる
                replaceFragment(new SubFragment2());
            }
        });

        return view;
    }

    // 表示させるFragmentを切り替えるメソッドを定義（表示したいFragmentを引数として渡す）
    private void replaceFragment(Fragment fragment) {
        // フラグメントマネージャーの取得
        FragmentManager manager = getFragmentManager(); // アクティビティではgetSupportFragmentManager()?
        // フラグメントトランザクションの開始
        FragmentTransaction transaction = manager.beginTransaction();
        // レイアウトをfragmentに置き換え（追加）
        transaction.replace(R.id.activityMain, fragment);
        // 置き換えのトランザクションをバックスタックに保存する
        transaction.addToBackStack(null);
        // フラグメントトランザクションをコミット
        transaction.commit();
    }
}
