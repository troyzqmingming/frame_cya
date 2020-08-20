package com.cya.frame.demo.ui.main

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentMainBinding
import com.cya.frame.demo.ui.article.ArticleListFragment
import com.cya.frame.demo.ui.mine.MineFragment
import com.cya.frame.demo.ui.test.TestFragment
import com.cya.frame.ext.listener.setDefaultAdapter
import com.cya.frame.ext.visible

class MainFragment : DemoBaseFragment<FragmentMainBinding>() {

    private val homeFragment by lazy {
        ArticleListFragment()
    }
    private val personalFragment by lazy {
        MineFragment()
    }

    private val testFragment by lazy {
        TestFragment()
    }

    private val fragmentList = mutableListOf<Fragment>()

    init {
        fragmentList.run {
            add(homeFragment)
            add(personalFragment)
            add(testFragment)
        }
    }

    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.viewpager.run {
            offscreenPageLimit = fragmentList.size
            isUserInputEnabled = true
            setDefaultAdapter(this@MainFragment, fragmentList) {
                when (it) {
                    0 -> binding.navigationView.selectedItemId = R.id.articleListFragment
                    1 -> binding.navigationView.selectedItemId = R.id.mineFragment
                    2 -> binding.navigationView.selectedItemId = R.id.testFragment
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
                R.id.testFragment -> {
                    binding.viewpager.currentItem = 2
                }
            }
            true
        }
    }

    override fun initData() {
    }

}