package com.cya.application.ft_home

import androidx.fragment.app.Fragment
import com.cya.application.ft_home.databinding.FragmentMainBinding
import com.cya.application.ft_home.main.dynamic.DynamicFragment
import com.cya.application.ft_home.main.home.HomeFragment
import com.cya.application.ft_home.main.mine.MineFragment
import com.cya.frame.ext.listener.setDefaultAdapter
import com.cya.lib_base.base.CyaBaseFragment

class MainFragment : CyaBaseFragment<FragmentMainBinding>() {

    private val fragmentList = mutableListOf<Fragment>()

    private val homeFragment by lazy {
        HomeFragment()
    }

    private val dynamicFragment by lazy {
        DynamicFragment()
    }

    private val mineFragment by lazy {
        MineFragment()
    }

    init {
        fragmentList.apply {
            add(homeFragment)
            add(dynamicFragment)
            add(mineFragment)
        }
    }

    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.viewpage.apply {
            offscreenPageLimit = fragmentList.size
            isUserInputEnabled = true
            setDefaultAdapter(this@MainFragment, fragmentList) {
                when (it) {
                    0 -> binding.bottomNavView.selectedItemId = R.id.menu_main_home
                    1 -> binding.bottomNavView.selectedItemId = R.id.menu_main_dynamic
                    2 -> binding.bottomNavView.selectedItemId = R.id.menu_main_mine
                }
            }
        }
        binding.bottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main_home -> binding.viewpage.currentItem = 0
                R.id.menu_main_dynamic -> binding.viewpage.currentItem = 1
                R.id.menu_main_mine -> binding.viewpage.currentItem = 2

            }
            true
        }
    }

    override fun initData() {
    }

    override fun setObserve() {
    }

}