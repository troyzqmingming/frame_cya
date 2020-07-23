package com.cya.frame.demo.ui.article

import androidx.lifecycle.Observer
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseMVVMFragment
import com.cya.frame.demo.databinding.FragmentArticleListBinding
import com.cya.frame.demo.ext.nav
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import org.koin.android.viewmodel.ext.android.getViewModel

class ArticleListFragment :
    DemoBaseMVVMFragment<FragmentArticleListBinding, ArticleListViewModel>() {

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

    override fun initView() {
        binding.recyclerView.run {
            layoutManager = articleAdapter.getLayoutManager()
            adapter = articleAdapter
        }
        articleAdapter.run {
            setOnItemClickListener { adapter, view, position ->
                nav(R.id.action_mainFragment_to_articleDetailFragment)
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