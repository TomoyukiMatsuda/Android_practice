package com.android.qiitalist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.qiitalist.databinding.FragmentArticleListBinding
import com.android.qiitalist.model.Article
import com.android.qiitalist.viewModel.ArticleListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class ArticleListFragment : Fragment() {
    private lateinit var binding: FragmentArticleListBinding
    private lateinit var viewModel: ArticleListViewModel
    private var currentPage = 1
    private var disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleListBinding.inflate(inflater)
        // ViewModel を取得している？
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(ArticleListViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleList: MutableList<Article> = ArrayList()
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.adapter = groupAdapter

        // ボタンクリック
        binding.button.setOnClickListener {
            // 一旦リストをクリア
            articleList.clear()
            if (binding.keywordEditText.text.isNotEmpty()) {
                viewModel.keyword = binding.keywordEditText.text.toString()
                currentPage = 1
                // Qiita Apiを叩く
                viewModel.fetchArticles(viewModel.keyword.toString(), currentPage)
            }
        }

        // リストを取得してadapterにリストをセット
        viewModel.fetchArticleList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { articles ->
                // エラー時の処理を入れたほうが良いかも
                articleList.addAll(articles)
                groupAdapter.update(articleList.map { ArticleListItemFactory(it) })
            }
            .addTo(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
