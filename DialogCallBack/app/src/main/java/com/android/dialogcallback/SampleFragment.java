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

public class SampleFragment extends Fragment implements SampleDialogFragment.SampleDialogListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);

        // タイトル設置
        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle("サンプルフラグメント");

        // ボタン要素をレイアウトから取得
        Button button = view.findViewById(R.id.bt_show_dialog);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // フラグメントマネージャーセット
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                SampleDialogFragment dialogFragment = SampleDialogFragment.newInstance();
                dialogFragment.setTargetFragment(SampleFragment.this, 0);
                dialogFragment.show(fragmentManager, "");
            }
        });
        return view;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // コールバックしてきた処理
        Log.i("SampleFragment", "コールバック処理");
    }
}
