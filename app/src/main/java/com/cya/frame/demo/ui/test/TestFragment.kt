package com.cya.frame.demo.ui.test

import android.app.ActivityOptions
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentTestBinding
import com.cya.frame.demo.ext.nav
import com.cya.frame.demo.ui.main.MainFragmentDirections
import com.cya.frame.ext.clickNoRepeat
import com.cya.frame.navigation.FragmentNavigator


class TestFragment : DemoBaseFragment<FragmentTestBinding>() {
    override fun getViewBinding(): FragmentTestBinding {
        return FragmentTestBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initView() {
        binding.ivImage.clickNoRepeat {
            val option = FragmentNavigator.Extras.Builder()
                .addSharedElement(binding.ivImage, "item:image")
                .addSharedElement(binding.tvUsername, "item:title")
                .build()
            nav(
                MainFragmentDirections.actionMainFragmentToTestDetailFragment(
                    "this is Title",
                    R.mipmap.ic_launcher_round
                ),
                option
            )
            //跳转activity
//            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                this.mActivity,
//                Pair.create(binding.ivImage, "item:image")
//            )
//            nav(
//                MainFragmentDirections.actionMainFragmentToTestDetailActivity(),
//                ActivityNavigator.Extras.Builder().setActivityOptions(option).build()
//            )
        }
    }

}