package com.android.dialogcallback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // フラグメントを呼びたい
        showFragment(new SampleFragment());
    }

    // フラグメント表示メソッド
    public void showFragment(Fragment fragment) {
        // replaceじゃなくaddでもいいかも
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_sample, fragment)
                .addToBackStack(null)
                .commit();
    }
}
