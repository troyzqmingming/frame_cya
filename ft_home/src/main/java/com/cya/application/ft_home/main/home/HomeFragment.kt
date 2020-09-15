package com.cya.application.ft_home.main.home

import com.cya.application.ft_home.databinding.FragmentHomeBinding
import com.cya.application.ft_home.main.home.adapter.ArticleListAdapter
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.lib_base.base.CyaBaseVMFragment
import org.koin.android.viewmodel.ext.android.getViewModel

class HomeFragment : CyaBaseVMFragment<FragmentHomeBinding, HomeVM>() {

    private val articleListAdapter by lazy {
        ArticleListAdapter()
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.recyclerArticleList.run {
            adapter = articleListAdapter
            layoutManager = articleListAdapter.getLayoutManager()
        }
        binding.refreshLayout.run {
            setOnRefreshListener {
                vm.loadArticle(true)
            }
            setOnLoadMoreListener {
                vm.loadArticle(false)
            }
        }
    }

    override fun initData() {
        binding.refreshLayout.autoRefresh()
    }

    override fun initViewModel(): HomeVM {
        return getViewModel()
    }

    override fun setObserve() {
        vm.apply {
            articleListLiveData.observe(this@HomeFragment) { articleResult ->
                (curArticlePage == 0).yes {
                    articleListAdapter.setNewData(articleResult.list)
                    binding.refreshLayout.finishRefresh()
                }.otherwise {
                    articleListAdapter.addData(articleResult.list)
                    binding.refreshLayout.finishLoadMore()
                }
            }
        }
    }

}