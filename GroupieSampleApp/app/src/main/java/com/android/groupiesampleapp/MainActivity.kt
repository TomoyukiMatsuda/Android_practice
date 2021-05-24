package com.android.groupiesampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.groupiesampleapp.databinding.ActivityMainBinding
import com.android.groupiesampleapp.databinding.ItemGroupieBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // バインド
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Groupieのアダプターを生成してセット
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = groupAdapter
        // リストを作る
        val items = listOf("寿司", "ステーキ", "鳥の唐揚げ", "アヒージョ", "バームクーヘン", "ティラミス", "カツ丼")
        items.forEach { item ->
            // アダプターに Itemインスタンス を add していく
            groupAdapter.add(GroupieItem(item))
        }

    }
}

// リストアイテムのクラス
class GroupieItem(private val text: String) : BindableItem<ItemGroupieBinding>() {
    // レイアウトを返却
    override fun getLayout(): Int = R.layout.item_groupie

    // レイアウトの要素とバインド
    override fun bind(viewBinding: ItemGroupieBinding, position: Int) {
        viewBinding.text = text
    }
}
