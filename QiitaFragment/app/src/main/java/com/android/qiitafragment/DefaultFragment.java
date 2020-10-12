package com.android.qiitafragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DefaultFragment extends Fragment {

    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setupBackButton(false);

        Button showFirst = view.findViewById(R.id.bt_show_first);
        Button showSecond = view.findViewById(R.id.bt_show_second);

        onClickShowFragment(showFirst, new FirstFragment());
        onClickShowFragment(showSecond, new SecondFragment());

        return view;
    }

    // ボタンクリックでフラグメントを表示させるメソッド
    private void onClickShowFragment(Button button, final Fragment fragment) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainActivity.showFragment(fragment, getFragmentManager());
            }
        });
    }
}
