package com.orange.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.orange.domain.entity.Article
import com.orange.domain.utils.ResponseHandler
import com.orange.newsapp.R
import com.orange.newsapp.adapter.NewsAdapter
import com.orange.newsapp.databinding.FragmentNewsBinding
import com.orange.newsapp.utils.Constant
import com.orange.newsapp.utils.base.BaseFragment
import com.orange.newsapp.utils.toast
import com.orange.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : BaseFragment() {

    private lateinit var binding: FragmentNewsBinding
    private val vm: NewsViewModel by viewModels()

    private lateinit var newsListAdapter: NewsAdapter

    private val itemNewClickCallBack: (article: Article) -> Unit =
        { article ->
            val bundle = Bundle()
            bundle.putParcelable(Constant.KEY_ARTICLE, article)
            findNavController().navigate(R.id.action_news_to_details, bundle)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBtnListeners()
        // observer MutableLive data in view model
        subscribeData()
    }

    private fun setBtnListeners() {
        binding.refreshNews.setOnRefreshListener {
            vm.getNewsList()
            binding.refreshNews.isRefreshing = false
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch(Dispatchers.Main) {
                    val searchText = newText?.trim()
                    searchText?.let {
                        delay(1000)
                        vm.getNewsList(it)
                    }
                }
                return false
            }
        })
    }

    private fun subscribeData() {
        vm.getNewsLiveState.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseHandler.Success -> {
                    hideLoading()
                    // initialize recycler view to set news list
                    initRecycler(it.data!!.articles)
                }
                is ResponseHandler.Error -> {
                    toast(it.message ?: "")
                }
                else -> {}
            }
        }
    }

    private fun initRecycler(articles: List<Article>) {
        binding.rvNews.apply {
            newsListAdapter =
                NewsAdapter(itemNewClickCallBack)
            adapter = newsListAdapter
            newsListAdapter.submitData(articles)
        }
    }
}
