package com.cya.frame.demo.home

import androidx.lifecycle.Observer
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.cya.frame.base.ui.BaseMVVMFragment
import com.cya.frame.demo.databinding.FragmentHomeListBinding
import com.cya.frame.demo.home.vm.HomeListViewModel
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.toast
import com.cya.frame.ext.yes
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
                (vm.curPageId == 0).yes {
                    articleAdapter.setNewData(it.data)
                    binding.refreshLayout.finishRefresh()
                }.otherwise {
                    it.data?.let { it1 -> articleAdapter.addData(it1) }
                    binding.refreshLayout.finishLoadMore()
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
                vm.loadArticle()
            }
            setOnLoadMoreListener {
                vm.loadArticle(false)
            }
        }
        runWithPermissions(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE) {
            binding.refreshLayout.autoRefresh()
        }
    }

    override fun initData() {
    }

}