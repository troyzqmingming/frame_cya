package com.cya.frame.demo.ui.article.detail

import android.os.Build
import android.view.KeyEvent
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentArticleDetailBinding
import com.cya.frame.demo.ext.back2Main
import com.cya.frame.demo.ext.finish
import com.cya.frame.demo.ext.nav
import com.cya.frame.demo.view.DemoWebView
import com.cya.frame.ext.*

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
        super.initView()
        sharedElementEnterTransition = android.transition.TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        args.apply {
            binding.toolbarLayout.setTitleText(title)
            binding.ivImage.setBackgroundColor(bgColor)
            binding.tvTitle.text = title
            isFirstPage.no {
                binding.toolbarLayout.setCloseClickListener {
                    back2Main()
                }
            }
            ViewCompat.setTransitionName(binding.ivImage , "${title}${mContext.getString(R.string.share_element_detail_image)}")
            ViewCompat.setTransitionName(binding.tvTitle , "${title}${getString(R.string.share_element_detail_title)}")
        }
        binding.refreshLayout.setOnRefreshListener {
            binding.webview.loadUrl(args.url)

        }
        binding.webview.iCallback = object : DemoWebView.ICallback {
            override fun loadPageFinish() {
                binding.refreshLayout.finishRefresh()
            }
        }
        binding.floatingActionButton.clickNoRepeat {
            nav(
                ArticleDetailFragmentDirections.actionArticleDetailFragmentSelf(
                    "http://www.baidu.com",
                    "BaiDu",
                    randomColor(),
                    false
                )
            )
        }
    }
}