package com.cya.frame.demo.ui.article.detail

import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentArticleDetailBinding
import com.cya.frame.demo.view.DemoWebView

class ArticleDetailFragment : DemoBaseFragment<FragmentArticleDetailBinding>() {
    override fun getViewBinding(): FragmentArticleDetailBinding {
        return FragmentArticleDetailBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.refreshLayout.autoRefresh()
    }

    override fun initView() {
        binding.refreshLayout.setOnRefreshListener {
            binding.webview.loadUrl("https://www.autohome.com.cn/drive/202007/1009303.html#pvareaid=3311301")
        }
        binding.webview.iCallback = object : DemoWebView.ICallback {
            override fun loadPageFinish() {
                binding.refreshLayout.finishRefresh()
            }

        }
    }
}