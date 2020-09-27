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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // setTitleしてもする？

        // ボタン要素を取得
        Button bt1 = view.findViewById(R.id.bt1);
        Button bt2 = view.findViewById(R.id.bt2);

        // それぞれをクリックした時の処理を記述する
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // フラグメントに遷移させる
                replaceFragment(new SubFragment1());
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }

    private void replaceFragment(Fragment fragment) {
        // フラグメントマネージャーの取得
        FragmentManager manager = getFragmentManager(); // アクティビティではgetSupportFragmentManager()?
        // フラグメントトランザクションの開始
        FragmentTransaction transaction = manager.beginTransaction();
        // MainFragmentを置換
        transaction.replace(R.id.activityMain, fragment);
        // 戻るボタンを利用する？
        transaction.addToBackStack(null);
        // フラグメントトランザクションのコミット
        transaction.commit();
    }
}
