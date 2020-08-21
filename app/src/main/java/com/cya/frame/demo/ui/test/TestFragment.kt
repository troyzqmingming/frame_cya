package com.cya.frame.demo.ui.test

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.navigation.ActivityNavigator
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentTestBinding
import com.cya.frame.demo.ext.nav
import com.cya.frame.demo.ui.main.MainFragmentDirections
import com.cya.frame.ext.clickNoRepeat
import com.cya.frame.navigation.FragmentNavigatorExtras
import com.cya.frame.navigation.NavHostFragment.create


class TestFragment : DemoBaseFragment<FragmentTestBinding>() {
    override fun getViewBinding(): FragmentTestBinding {
        return FragmentTestBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
        ViewCompat.setTransitionName(binding.ivImage, getString(R.string.share_element_test_image))
        ViewCompat.setTransitionName(
            binding.tvUsername,
            getString(R.string.share_element_test_title)
        )
        binding.ivImage.clickNoRepeat {
//            nav(
//                MainFragmentDirections.actionMainFragmentToTestDetailFragment(
//                    "this is Title",
//                    R.mipmap.ic_launcher_round
//                ),
//                FragmentNavigatorExtras(
//                    binding.ivImage to binding.ivImage.transitionName,
//                    binding.tvUsername to binding.tvUsername.transitionName
//                )
//            )
            //跳转activity
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this.mActivity,
                Pair.create(binding.ivImage, getString(R.string.share_element_test_image))
            )
            nav(
                MainFragmentDirections.actionMainFragmentToTestDetailActivity(),
                ActivityNavigator.Extras.Builder().setActivityOptions(option).build()
            )
        }
    }

}