package com.cya.frame.demo.navi

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cya.frame.base.ui.BaseActivity
import com.cya.frame.demo.R
import com.cya.frame.demo.databinding.ActivityNaviBinding
import com.cya.frame.demo.home.HomeListFragment
import com.cya.frame.demo.personal.PersonalFragment

class NavigationActivity : BaseActivity<ActivityNaviBinding>() {

    private val homeFragment by lazy {
        HomeListFragment()
    }
    private val personalFragment by lazy {
        PersonalFragment()
    }

    private val fragmentList = mutableListOf<Fragment>()

    init {
        fragmentList.run {
            add(homeFragment)
            add(personalFragment)
        }
    }

    override fun getViewBinding(): ActivityNaviBinding {
        return ActivityNaviBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.viewpager.run {
            offscreenPageLimit = fragmentList.size
            isUserInputEnabled = false
            adapter = object : FragmentStateAdapter(this@NavigationActivity) {
                override fun getItemCount(): Int {
                    return fragmentList.size
                }

                override fun createFragment(position: Int): Fragment {
                    return fragmentList[position]
                }
            }
        }
        binding.navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeListFragment -> {
                    binding.viewpager.currentItem = 0
                }
                R.id.personalFragment -> {
                    binding.viewpager.currentItem = 1
                }
            }
            true
        }
    }

    override fun initData() {
    }

}