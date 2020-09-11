package com.cya.wan_android.ui

import androidx.fragment.app.Fragment
import com.cya.frame.ext.listener.setDefaultAdapter
import com.cya.wan_android.R
import com.cya.wan_android.base.CyaBaseFragment
import com.cya.wan_android.databinding.FragmentMainBinding
import com.cya.wan_android.ui.main.home.HomeFragment
import com.cya.wan_android.ui.main.mine.MineFragment

class MainFragment : CyaBaseFragment<FragmentMainBinding>() {

    private val fragmentList = mutableListOf<Fragment>()

    private val homeFragment by lazy {
        HomeFragment()
    }

    private val mineFragment by lazy {
        MineFragment()
    }

    init {
        fragmentList.apply {
            add(homeFragment)
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
                    1 -> binding.bottomNavView.selectedItemId = R.id.menu_main_mine
                }
            }
        }
        binding.bottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main_home -> binding.viewpage.currentItem = 0
                R.id.menu_main_mine -> binding.viewpage.currentItem = 1
            }
            true
        }
    }

    override fun initData() {
    }

}