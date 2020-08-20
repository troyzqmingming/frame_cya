package com.cya.frame.demo.ui.article.detail

import android.os.Build
import android.view.KeyEvent
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentArticleDetailBinding
import com.cya.frame.demo.ext.finish
import com.cya.frame.demo.ext.nav
import com.cya.frame.demo.view.DemoWebView
import com.cya.frame.ext.toast
import com.cya.frame.ext.yes

class ArticleDetailFragment : DemoBaseFragment<FragmentArticleDetailBinding>() {

    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentArticleDetailBinding {
        return FragmentArticleDetailBinding.inflate(layoutInflater)
    }

    override fun initData() {
//        binding.refreshLayout.autoRefresh()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
        args.apply {
            binding.ivImage.setBackgroundColor(bgColor)
            binding.ivImage.transitionName =
                "${title}${mContext.getString(R.string.share_element_detail_image)}"
            binding.tvTitle.text = title
            binding.tvTitle.transitionName =
                "${title}${getString(R.string.share_element_detail_title)}"
        }
        TransitionInflater.from(mContext).inflateTransition(R.transition.transition).apply {
            sharedElementEnterTransition = this
            sharedElementReturnTransition = this
        }
        binding.refreshLayout.setOnRefreshListener {
            binding.webview.loadUrl(args.url)

        }
        binding.webview.iCallback = object : DemoWebView.ICallback {
            override fun loadPageFinish() {
                binding.refreshLayout.finishRefresh()
            }
        }
    }
}