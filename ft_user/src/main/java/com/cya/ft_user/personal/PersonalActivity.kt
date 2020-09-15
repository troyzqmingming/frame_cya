package com.cya.ft_user.personal

import com.alibaba.android.arouter.facade.annotation.Route
import com.cya.ft_user.databinding.ActivityPersonalBinding
import com.cya.lib_base.base.CyaBaseActivity
import com.cya.lib_base.contract.ConstantsPath
import com.cya.lib_base.service.user.wrap.LoginServiceImplWrap

@Route(path = ConstantsPath.USER_PERSONAL)
class PersonalActivity : CyaBaseActivity<ActivityPersonalBinding>() {
    override fun getViewBinding(): ActivityPersonalBinding {
        return ActivityPersonalBinding.inflate(layoutInflater)
    }

    override fun setObserve() {
    }

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    override fun initData() {
        binding.tvMsg.text = ("这是我的昵称:${LoginServiceImplWrap.getUserCache()?.nickname}")
    }

}