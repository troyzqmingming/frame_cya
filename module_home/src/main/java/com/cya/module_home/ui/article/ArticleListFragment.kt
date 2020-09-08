package com.cya.module_home.ui.article

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import com.cya.lib_base.ui.CYABaseMVVMFragment
import com.cya.module_home.R
import com.cya.module_home.databinding.FragmentArticleListBinding
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

    override fun initView() {
        binding.recyclerView.run {
            layoutManager = articleAdapter.getLayoutManager()
            adapter = articleAdapter
        }
        articleAdapter.run {
            setOnItemClickListener { adapter, view, position ->
                val tvTitle = view.findViewById<TextView>(R.id.tv_msg)
                val ivImage = view.findViewById<ImageView>(R.id.iv_image)
//                nav(
//                    MainFragmentDirections.actionMainFragmentToArticleDetailFragment(
//                        url = this.data[position].link,
//                        title = this.data[position].title,
//                        bgColor = this.data[position].bgColor
//                    ), FragmentNavigatorExtras(
//                        tvTitle to tvTitle.transitionName,
//                        ivImage to ivImage.transitionName
//                    )
//                )
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