package com.cya.frame.demo.ui.article.detail

import androidx.navigation.fragment.navArgs
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentArticleDetailBinding
import com.cya.frame.demo.ext.back2Main
import com.cya.frame.demo.ext.getNavController
import com.cya.frame.demo.ext.nav
import com.cya.frame.demo.view.DemoWebView
import com.cya.frame.ext.*

class ArticleDetailFragment : DemoBaseFragment<FragmentArticleDetailBinding>() {

    val args: ArticleDetailFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentArticleDetailBinding {
        return FragmentArticleDetailBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.refreshLayout.autoRefresh()
        args.isShowMore.yes {
            binding.btnMore.visible()
            binding.btnCloseAll.gone()
        }.otherwise {
            binding.btnMore.gone()
            binding.btnCloseAll.visible()
        }
    }

    override fun initView() {
        binding.refreshLayout.setOnRefreshListener {
            //            binding.webview.loadUrl("https://www.autohome.com.cn/drive/202007/1009303.html#pvareaid=3311301")
//            binding.webview.loadUrl("https://www.baidu.com")
            binding.webview.loadUrl(args.url)

        }
        binding.webview.iCallback = object : DemoWebView.ICallback {
            override fun loadPageFinish() {
                binding.refreshLayout.finishRefresh()
            }

        }
        binding.btnMore.clickNoRepeat {
            nav(
                ArticleDetailFragmentDirections.actionArticleDetailFragmentSelf(
                    "http://www.jianshu.com",
                    false
                )
            )
        }
        binding.btnCloseAll.clickNoRepeat {
            back2Main()
        }
    }
}