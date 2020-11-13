package com.android.callloglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_WRITE_EX_STR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // パーミッション許可を取る
        if (Build.VERSION.SDK_INT >= 23) {
            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CALL_LOG)
                    != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.READ_PHONE_STATE
                        },
                        PERMISSION_WRITE_EX_STR);
            }

            // 通話ログの取得
            ContentResolver resolver = getContentResolver();
            // TODO: permission denial 解消
            Cursor cursor =resolver.query(
                    CallLog.Calls.CONTENT_URI,
                    null,
                    null,
                    null,
                    CallLog.Calls.DEFAULT_SORT_ORDER
            );

            Log.v("CALL", Arrays.toString(cursor.getColumnNames())); // 項目名一覧
            Log.v("CALL", "Num = " + cursor.getCount()); // 取得件数

            System.out.println(Arrays.toString(cursor.getColumnNames()));

            if (cursor.moveToFirst()) {
                do {
                    Date date = new Date(cursor.getLong(cursor.getColumnIndex("DATE")));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                    String name = cursor.getString(cursor.getColumnIndex("NAME"));

                    String number = cursor.getString(cursor.getColumnIndex("NUMBER"));

                    System.out.println(name+date+number);

                    System.out.println(Arrays.toString(cursor.getColumnNames()));
                } while (cursor.moveToNext());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length <= 0) { return; }

        switch(requestCode){
            case PERMISSION_WRITE_EX_STR: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    /// 許可が取れた場合

                    // 通話ログの取得
                    ContentResolver resolver = getContentResolver();
                    // TODO: permission denial 解消
                    Cursor cursor =resolver.query(
                            CallLog.Calls.CONTENT_URI,
                            null,
                            null,
                            null,
                            CallLog.Calls.DEFAULT_SORT_ORDER
                    );

                    Log.v("CALL", Arrays.toString(cursor.getColumnNames())); // 項目名一覧
                    Log.v("CALL", "Num = " + cursor.getCount()); // 取得件数

                    System.out.println(Arrays.toString(cursor.getColumnNames()));

                } else {
                    /// 許可が取れなかった場合
                    Toast.makeText(this,"アプリを起動できません....", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            return;
        }
    }
}
