package com.android.dialogcallback;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;


public class SampleDialogFragment extends DialogFragment {

    // インスタンスを生成するメソッド
    public static SampleDialogFragment newInstance() {
        return new SampleDialogFragment();
    }

    // イベントコースバックを受け取るためのインターフェースを実装
    public interface SampleDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
    }

    // クリックイベント発火を伝えるために使用するインターフェースインスタンスを定義
    private SampleDialogListener listener;

    // onAttach()で呼び出し元の親フラグメントがインターフェースを実装しているかを検証
    // onAttach(): フラグメントのライフサイクルで最初に呼ばれるメソッドであり、
    // フラグメントがアクティビティと関連づけられたときに一度だけ呼び出される。contextには所属親アクティビティが入っている
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            // 親フラグメントにイベントを送信できるように呼び出し元であるSampleFragmentオブジェクトを取得し、
            // listenerのインスタンスを生成する
            listener = (SampleDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            // 親フラグメントがインターフェースを実装していない場合は例外を投げる
            throw new ClassCastException(getTargetFragment().toString() + "はインターフェースを実装していません");
        }
    }

    // ダイアログを生成するonCreateDialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // ダイアログを生成する
        return new AlertDialog.Builder(getActivity())
                .setTitle("確認")
                .setMessage("コールバックを開始しますか？")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 処理を親のフラグメントにコールバックする
                        listener.onDialogPositiveClick(SampleDialogFragment.this);
                    }
                })
                .setNegativeButton("キャンセル", null) // キャンセルボタンでは何もしないためnull
                .create();
    }
}
