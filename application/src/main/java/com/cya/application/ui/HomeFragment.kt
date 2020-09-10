package com.cya.application.ui

import androidx.fragment.app.Fragment
import com.cya.application.R
import com.cya.application.databinding.FragmentHomeBinding
import com.cya.application.ui.article.ArticleListFragment
import com.cya.application.ui.me.MineFragment
import com.cya.frame.ext.listener.setDefaultAdapter
import com.cya.library_base.ui.CYABaseFragment

class HomeFragment : CYABaseFragment<FragmentHomeBinding>() {

    private val homeFragment by lazy {
        ArticleListFragment()
    }
    private val personalFragment by lazy {
        MineFragment()
    }

    private val fragmentList = mutableListOf<Fragment>()

    init {
        fragmentList.run {
            add(homeFragment)
            add(personalFragment)
        }
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.viewpager.run {
            offscreenPageLimit = fragmentList.size
            isUserInputEnabled = true
            setDefaultAdapter(this@HomeFragment, fragmentList) {
                when (it) {
                    0 -> binding.navigationView.selectedItemId = R.id.articleListFragment
                    1 -> binding.navigationView.selectedItemId = R.id.mineFragment
                }
            }
        }
        binding.navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.articleListFragment -> {
                    binding.viewpager.currentItem = 0
                }
                R.id.mineFragment -> {
                    binding.viewpager.currentItem = 1
                }
            }
            true
        }
    }

    override fun initData() {
    }

}