package com.android.qiitalist.viewModel

import androidx.lifecycle.ViewModel
import com.android.qiitalist.model.Article
import com.android.qiitalist.repository.ArticleListRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ArticleListViewModel : ViewModel() {
    private val repository = ArticleListRepository()
    val fetchArticleList: BehaviorSubject<List<Article>> = BehaviorSubject.create()
    var keyword: String? = null
    private val disposable = CompositeDisposable()

    fun fetchArticles(keyword: String, page: Int) {
        repository.fetchArticles(keyword, page)
            .onErrorReturn { // todo: .onErrorReturnItem(ArrayList() ) これとの違いを確認
                // エラーの際はからのリストを流すようにする
                ArrayList()
            }
            .subscribeBy { articleList ->
                fetchArticleList.onNext(articleList)
            }
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
