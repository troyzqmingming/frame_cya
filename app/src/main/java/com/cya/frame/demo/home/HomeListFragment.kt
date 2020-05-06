package com.cya.frame.demo.home

import androidx.lifecycle.Observer
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.demo.databinding.FragmentHomeListBinding
import com.cya.frame.demo.home.vm.HomeListViewModel
import com.cya.frame.ext.toast
import org.koin.android.viewmodel.ext.android.getViewModel

class HomeListFragment : BaseMVVMFragment<FragmentHomeListBinding, HomeListViewModel>() {

    private val articleAdapter by lazy {
        HomeListAdapter()
    }

    override fun initViewModel(): HomeListViewModel {
        return getViewModel()
    }

    override fun getViewBinding(): FragmentHomeListBinding {
        return FragmentHomeListBinding.inflate(layoutInflater)
    }

    override fun startObserve() {
        vm.apply {
            uiState.observe(viewLifecycleOwner, Observer {
                if (it.isRefreshArticle) {
                    binding.refreshLayout.finishRefresh()
                }
                if (it.isLoadMoreArticle) {
                    binding.refreshLayout.finishLoadMore()
                }
                if (it.isGetDataSuccess) {
                    it.articleList?.let { list ->
                        if (it.isRefreshArticle) {
                            articleAdapter.setNewData(it.articleList)
                        } else {
                            articleAdapter.addData(list)
                        }
                    }
                }
                it.errorMsg?.let { msg ->
                    toast(msg)
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

            }
        }
        binding.refreshLayout.run {
            setOnRefreshListener {
                vm.refreshArticle()
            }
            setOnLoadMoreListener {
                vm.loadMoreArticle()
            }
        }
        runWithPermissions(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE) {
            binding.refreshLayout.autoRefresh()
        }
    }

    override fun initData() {
    }

}