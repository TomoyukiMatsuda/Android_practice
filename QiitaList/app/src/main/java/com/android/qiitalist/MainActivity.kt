package com.android.qiitalist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.qiitalist.databinding.ActivityMainBinding
import com.android.qiitalist.view.ArticleListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id, ArticleListFragment())
            .commit()
    }
}
