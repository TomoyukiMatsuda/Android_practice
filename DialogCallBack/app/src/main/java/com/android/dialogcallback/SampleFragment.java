package com.android.dialogcallback;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

            // インターフェースを実装するためにimplementsする
public class SampleFragment extends Fragment implements SampleDialogFragment.SampleDialogListener {

    // ダイアログ表示のボタン要素をグローバル変数として定義
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);

        // アクションバーにタイトルをセット
        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle("ダイアログコールバックサンプル");

        // ボタン要素をレイアウトから取得
        button = view.findViewById(R.id.bt_show_dialog);

        // ボタンクリックされたときの処理
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // フラグメントマネージャーを取得
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                // SampleDialogFragmentのインスタンスオブジェクトを生成
                SampleDialogFragment dialogFragment = SampleDialogFragment.newInstance();
                // SampleDialogFragmentのオブジェクトに呼び出し元のSampleFragmentオブジェクトをセット
                dialogFragment.setTargetFragment(SampleFragment.this, 0);
                // ダイアログを表示
                dialogFragment.show(fragmentManager, "");
            }
        });
        return view;
    }

    // コールバックされて実行される処理
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // ボタンに表示されている文字を変更
        button.setText("コールバック成功");
    }
}
