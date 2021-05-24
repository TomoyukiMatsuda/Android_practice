package com.android.groupiesampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.groupiesampleapp.databinding.ItemGroupieBinding
import com.xwray.groupie.databinding.BindableItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
