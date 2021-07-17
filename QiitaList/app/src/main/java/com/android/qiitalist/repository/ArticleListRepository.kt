package com.android.qiitalist.repository

import com.android.qiitalist.ApiClient
import com.android.qiitalist.ApiRequest
import com.android.qiitalist.model.Article
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ArticleListRepository {

    fun fetchArticles(tag: String, page: Int): Single<List<Article>> {
        return ApiClient.retrofit
            .create(ApiRequest::class.java)
            .fetchArticles(tag, page, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
