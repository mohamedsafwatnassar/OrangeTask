package com.orange.newsapp.utils.base

import androidx.fragment.app.Fragment
import com.orange.orangetask.ViewsManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    protected fun showLoading() {
        (requireActivity() as ViewsManager).showLoading()
    }

    protected fun hideLoading() {
        (requireActivity() as ViewsManager).hideLoading()
    }
}
