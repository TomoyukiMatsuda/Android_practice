package com.websarva.wings.android.fragmentqiitasample;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SubFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub1, container, false);

        // 戻るボタン「←」をセット
        MainActivity activity = (MainActivity) getActivity();
        activity.setupBackButton(true);

        return view;
    }

    // アクションバーの戻るボタンを押した時の処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("backボタン", "onOptionsItemSelectedない");
        // 戻る処理
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.e("backボタンです", "戻るcaseない");
                getFragmentManager().popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
