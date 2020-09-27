package com.websarva.wings.android.asyncsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // リストビュー要素をレイアウトファイルより取得
        ListView lvCityList = findViewById(R.id.lvCityList);
        // Mapオブジェクトを持つ配列を作成
        List<Map<String, String>> cityList = new ArrayList<>();
        // Mapオブジェクトインスタンスを作成し、配列cityListに格納
        Map<String, String> city = new HashMap<>();
        city.put("name", "大阪");
        city.put("id", "270000");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "神戸");
        city.put("id", "280010");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "豊岡");
        city.put("id", "280020");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "京都");
        city.put("id", "260010");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "舞鶴");
        city.put("id", "260020");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "奈良");
        city.put("id", "290010");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "風屋");
        city.put("id", "290020");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "和歌山");
        city.put("id", "300010");
        cityList.add(city);
        city = new HashMap<>();
        city.put("name", "潮岬");
        city.put("id", "300020");
        cityList.add(city);

        // SimpleAdapterで使用するfrom to要変数の用意
        String[] from = {"name"};
        int[] to = {android.R.id.text1};
        // SimpleAdapter を生成
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, cityList, android.R.layout.simple_expandable_list_item_1, from, to);
        // lvCityListにアダプターをセット
        lvCityList.setAdapter(adapter);

        // ListViewにクリックリスなを設定
        lvCityList.setOnItemClickListener(new ListItemClickListener());
    }

    // リストが選択された時の処理が記述されたメンバクラス？　メンバクラスとは？
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

            // ListViewでタップされた業の都市名とIDを取得
            Map<String, String> item = (Map<String, String>) adapterView.getItemAtPosition(i);
            String cityName = item.get("name");
            String cityId = item.get("id");

            TextView tvCityName = findViewById(R.id.tvCityName);
            tvCityName.setText(cityName + "の天気：　");
        }
    }

}
