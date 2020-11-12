package com.android.callloglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 通話ログの取得
        ContentResolver resolver = getContentResolver();
        // TODO: 23行目でpermission denial エラー
        Cursor cursor =resolver.query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                CallLog.Calls.DEFAULT_SORT_ORDER
        );

        Log.v("CALL", Arrays.toString(cursor.getColumnNames())); // 項目名一覧
        Log.v("CALL", "Num = " + cursor.getCount()); // 取得件数
    }
}
