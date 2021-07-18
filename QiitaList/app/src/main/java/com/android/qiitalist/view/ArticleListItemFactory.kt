package com.android.qiitalist.view

import com.android.qiitalist.R
import com.android.qiitalist.databinding.CellArticleBinding
import com.android.qiitalist.model.Article
import com.xwray.groupie.databinding.BindableItem

class ArticleListItemFactory(
    private val article: Article
) : BindableItem<CellArticleBinding>() {
    override fun getLayout(): Int = R.layout.cell_article

    override fun bind(viewBinding: CellArticleBinding, position: Int) {
        viewBinding.titleText.text = article.title
        viewBinding.likesCountText.text = "LGTM ${article.likeCount}"
    }
}
