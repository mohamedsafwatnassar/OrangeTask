package com.orange.newsapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orange.domain.entity.Article
import com.orange.newsapp.databinding.FragmentDetailsBinding
import com.orange.newsapp.utils.Constant
import com.orange.newsapp.utils.base.BaseFragment
import com.orange.newsapp.utils.onDebouncedListener
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailsBinding

    // initialize article to set bundle
    private var article: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideLoading()
        article = if (Build.VERSION.SDK_INT >= 33) {
            requireArguments().getParcelable(Constant.KEY_ARTICLE, Article::class.java)
        } else {
            requireArguments().getParcelable(Constant.KEY_ARTICLE)
        }

        initViews()
        setBtnListener()
    }

    private fun initViews() {
        // set view
        binding.title.text = article?.title
        Constant.loadImage(requireContext(), article?.urlToImage ?: "", binding.imgNews)
        binding.author.text = article?.author
        binding.publishedAt.text = convertDateFormat(article?.publishedAt.toString())
        binding.description.text = article?.description
        binding.content.text = article?.content
        // binding.source.text = article?.source?.name
    }

    private fun setBtnListener() {
        binding.source.onDebouncedListener {
            shareNews()
        }
    }

    private fun shareNews() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article?.url))
        startActivity(intent)
    }

    private fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        // Parse the input date string into a Date object
        val date = inputFormat.parse(inputDate)

        // Format the Date object into the desired output format
        return outputFormat.format(date ?: "")
    }
}
