package com.cya.application.ft_home.main.dynamic

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.cya.application.ft_home.databinding.FragmentDynamicBinding
import com.cya.application.ft_home.main.dynamic.adapter.DynamicPagingAdapter
import com.cya.application.ft_home.main.dynamic.entity.DynamicItem
import com.cya.frame.base.ICallback
import com.cya.frame.ext.toast
import com.cya.lib_base.base.CyaBaseVMFragment
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_dynamic.*
import org.koin.android.viewmodel.ext.android.getViewModel

class DynamicFragment : CyaBaseVMFragment<FragmentDynamicBinding, DynamicVM>() {

    private val dynamicAdapter by lazy {
        DynamicPagingAdapter()
    }

    override fun getViewBinding(): FragmentDynamicBinding {
        return FragmentDynamicBinding.inflate(layoutInflater)
    }

    override fun setObserve() {
        vm.apply {
            dynamicList.observe(this@DynamicFragment) {
                lifecycleScope.launchWhenCreated {
                    dynamicAdapter.submitData(it)
                }
            }
        }
    }

    override fun initView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = dynamicAdapter
        }
        dynamicAdapter.apply {
            addLoadStateListener {
                when (it.source.refresh) {
                    is LoadState.Error -> {
                        Logger.e("LoadState.Error")
//                        dynamicAdapter.retry()
                        refreshLayout.finishRefresh()
                    }
                    is LoadState.Loading -> {
                        Logger.e("LoadState.Loading")
                    }
                    is LoadState.NotLoading -> {
                        Logger.e("LoadState.NotLoading")
                        refreshLayout.finishRefresh()
                    }
                }
            }
            onItemClickListener = object : ICallback<DynamicItem> {
                override fun invoke(t: DynamicItem) {
                    toast(t.title)
                }

            }
        }
        refreshLayout.apply {
            setOnRefreshListener {
                dynamicAdapter.refresh()
            }
//            setEnableLoadMore(true)
        }
    }

    override fun initData() {
        refreshLayout.autoRefresh()
        vm.getList()
    }

    override fun initViewModel(): DynamicVM {
        return getViewModel()
    }

}