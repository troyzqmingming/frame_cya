package com.cya.wan_android.ui.main.mine

import com.cya.frame.ext.clickNoRepeat
import com.cya.wan_android.NavMainDirections
import com.cya.wan_android.base.CyaBaseFragment
import com.cya.wan_android.databinding.FragmentMineBinding
import com.cya.wan_android.ext.nav

class MineFragment : CyaBaseFragment<FragmentMineBinding>() {
    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.tvMine.clickNoRepeat {
            nav(NavMainDirections.actionGlobalLoginFragment())
        }
    }

    override fun initData() {
    }

    override fun setObserve() {
    }

}