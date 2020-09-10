package com.cya.application.ui.article.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import com.cya.frame.ext.clickNoRepeat
import com.cya.frame.ext.randomColor
import com.cya.library_base.ext.nav
import androidx.navigation.fragment.navArgs
import com.cya.application.R
import com.cya.application.databinding.FragmentArticleDetailBinding
import com.cya.application.router.backHome
import com.cya.application.ui.article.ArticleDetailFragmentArgs
import com.cya.application.ui.article.ArticleDetailFragmentDirections
import com.cya.frame.ext.no
import com.cya.library_base.ui.CYABaseFragment
import com.cya.library_ui.webview.CYAWebView

class ArticleDetailFragment : CYABaseFragment<FragmentArticleDetailBinding>() {

    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentArticleDetailBinding {
        return FragmentArticleDetailBinding.inflate(layoutInflater)
    }

    override fun initData() {
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
        sharedElementEnterTransition = android.transition.TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
        args.apply {
            binding.toolbarLayout.setTitleText(title)
            binding.ivImage.setBackgroundColor(bgColor)
            binding.tvTitle.text = title
            isFirstPage.no {
                binding.toolbarLayout.setCloseClickListener {
                    backHome()
                }
            }
            ViewCompat.setTransitionName(
                binding.ivImage,
                "${title}${mContext.getString(R.string.share_element_detail_image)}"
            )
            ViewCompat.setTransitionName(
                binding.tvTitle,
                "${title}${getString(R.string.share_element_detail_title)}"
            )
        }
        binding.refreshLayout.setOnRefreshListener {
            binding.webview.loadUrl(args.url)
        }
        binding.webview.iCallback = object : CYAWebView.ICallback {
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