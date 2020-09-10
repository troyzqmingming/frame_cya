package com.cya.application.ui.article

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.cya.application.databinding.FragmentArticleListBinding
import com.cya.application.ui.HomeFragmentDirections
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.library_base.ext.nav
import com.cya.library_base.ui.CYABaseMVVMFragment
import org.koin.android.viewmodel.ext.android.getViewModel

class ArticleListFragment :
    CYABaseMVVMFragment<FragmentArticleListBinding, ArticleListViewModel>() {

    private val articleAdapter by lazy {
        ArticleListAdapter()
    }

    override fun initViewModel(): ArticleListViewModel {
        return getViewModel()
    }

    override fun getViewBinding(): FragmentArticleListBinding {
        return FragmentArticleListBinding.inflate(layoutInflater)
    }

    override fun startObserve() {
        vm.apply {
            articleListLiveData.observe(this@ArticleListFragment, Observer {
                (vm.curPageId == 0).yes {
                    articleAdapter.setNewData(it.list)
                    binding.refreshLayout.finishRefresh()
                }.otherwise {
                    it?.let { it1 -> articleAdapter.addData(it1.list) }
                    binding.refreshLayout.finishLoadMore()
                }
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
        binding.recyclerView.run {
            layoutManager = articleAdapter.getLayoutManager()
            adapter = articleAdapter
        }
        articleAdapter.run {
            setOnItemClickListener { adapter, view, position ->
                nav(
                    HomeFragmentDirections.actionHomeFragmentToArticleDetailFragment(
                        url = this.data[position].link,
                        title = this.data[position].title,
                        bgColor = this.data[position].bgColor
                    )
                )
            }
        }
        binding.refreshLayout.run {
            setOnRefreshListener {
                vm.loadArticle()
            }
            setOnLoadMoreListener {
                vm.loadArticle(false)
            }
        }
        binding.refreshLayout.autoRefresh()
    }

    override fun initData() {
    }

}