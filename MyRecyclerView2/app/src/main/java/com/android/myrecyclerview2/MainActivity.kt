package com.android.myrecyclerview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val layout = LinearLayoutManager(applicationContext)

        recyclerView.layoutManager = layout

        val list = createList()

        val adapter = RecyclerListAdapter(list)

        recyclerView.adapter = adapter
    }

    // TODO: ダミーデータ挿入メソッド記述
    private fun createList(): MutableList<MutableMap<String, Any>> {
        val list: MutableList<MutableMap<String, Any>> = mutableListOf()
        //「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        var menu = mutableMapOf("name" to "から揚げ定食", "price" to 800, "desc" to "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        //「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        //以下データ登録の繰り返し。
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to 850, "desc" to "すりおろし生姜を使った生姜焼きにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to 1000, "desc" to "国産牛のステーキにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to 750, "desc" to "季節の野菜炒めにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to 900, "desc" to "ロースとんかつにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to 850, "desc" to "手ごねミンチカツにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        menu = mutableMapOf("name" to "チキンカツ定食", "price" to 900, "desc" to "ボリュームたっぷりチキンカツにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to 850, "desc" to "北海道ポテトコロッケにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        menu = mutableMapOf("name" to "焼き魚定食", "price" to 850, "desc" to "鰆の塩焼きにサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)
        menu = mutableMapOf("name" to "焼肉定食", "price" to 950, "desc" to "特性たれの焼肉にサラダ、ご飯とお味噌汁が付きます。")
        list.add(menu)

        return list
    }

    // RecyclerView ビューホルダクラス
    private inner class RecyclerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * リスト1行分中でメニュー名を表示する画面部品。
         */
        //var tvMenuName: TextView
        /**
         * リスト1行分中でメニュー金額を表示する画面部品。
         */
        //var tvMenuPrice: TextView

        init {
            //引数で渡されたリスト1行分の画面部品中から表示に使われるTextViewを取得。
            //tvMenuName = itemView.findViewById(R.id.tvMenuName)
            //tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice)
        }
    }

    // RecyclerViewのアダプタクラス
    private inner class RecyclerListAdapter(private val _listData: MutableList<MutableMap<String, Any>>): RecyclerView.Adapter<RecyclerListViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            // レイアウトインフレーたを取得
            val inflater = LayoutInflater.from(applicationContext)
            // row.xmlをインフレートし、1行分の画面部品とする
            val view = inflater.inflate(R.layout.row, parent, false)

            // ビューホルダオブジェクトを生成
            // 生成したビューホルダをリターン
            return RecyclerListViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {

            val item = _listData[position]
        }

        override fun getItemCount(): Int {
            return _listData.size
        }


    }
}
