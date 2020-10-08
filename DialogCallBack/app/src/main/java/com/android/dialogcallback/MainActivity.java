package com.android.dialogcallback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activityにフラグメントをセット
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_sample, new SampleFragment())
                .commit();
    }
}
