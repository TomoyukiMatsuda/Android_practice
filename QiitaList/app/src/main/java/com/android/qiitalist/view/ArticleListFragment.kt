package com.android.qiitalist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.qiitalist.R
import com.android.qiitalist.databinding.FragmentArticleListBinding
import com.android.qiitalist.viewModel.ArticleListViewModel

class ArticleListFragment : Fragment() {
    private lateinit var binding: FragmentArticleListBinding
    private lateinit var viewModel: ArticleListViewModel

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


}
