package com.android.dialogcallback;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class SampleDialogFragment extends DialogFragment {

    // インスタンスを生成するメソッド
    public static SampleDialogFragment newInstance() {
        return new SampleDialogFragment();
    }

    // イベントコースバックを受け取るためのインターフェースを実装
    public interface SampleDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
    }

    // リスナーのインスタンス化
    private SampleDialogListener listener;

    // 呼び出し元のフラグメントとの関連付けを検証するonAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // これ重要な記述これないとnullポ
//        listener = (SampleDialogListener) getTargetFragment();
//        if (listener instanceof SampleDialogListener == false) {
//            // 関連付けが適切にされていない場合は例外を投げる
//            throw new ClassCastException("error Fragment との関連付けを確認");
//        }

        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            // これがないとヌルポ
            listener = (SampleDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getTargetFragment().toString()
                    + " must implement DialogListener");
        }

    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    // ダイアログを生成するonCreateDialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle("確認")
                .setMessage("メッセージ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // コールバック
                        listener.onDialogPositiveClick(SampleDialogFragment.this);
                    }
                })
                .setNegativeButton("キャンセル", null)
                .create();
    }
}
