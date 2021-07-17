package com.android.qiitalist

import com.android.qiitalist.model.Article
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {
    // https://qiita.com/api/v2/tags/kotlin/items?page=1&per_page=20 で叩く
    // Single を返す
    @GET("tags/{tag_id}/items")
    fun fetchArticles(
        @Path("tag_id") tagId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<List<Article>>
    // <Response<List<Article>>> ？？
}
