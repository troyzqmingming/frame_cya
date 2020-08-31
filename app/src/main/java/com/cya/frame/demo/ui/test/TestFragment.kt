package com.cya.frame.demo.ui.test

import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.navigation.ActivityNavigator
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentTestBinding
import com.cya.frame.demo.ext.nav
import com.cya.frame.demo.ui.main.MainFragmentDirections
import com.cya.frame.ext.clickNoRepeat
import com.cya.frame.ext.setNoRepeatClick
import com.cya.frame.ext.toast
import com.cya.frame.media.ImageUtils
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission


class TestFragment : DemoBaseFragment<FragmentTestBinding>() {

    val cacheImageFileName = "cacheImage.jpg"
    val localImageFileName = "localImage.jpg"

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
        setNoRepeatClick(
            binding.btnSaveCache,
            binding.btnGetCache,
            binding.btnSaveLocal,
            binding.btnGetLocal
        ) {
            when (it) {
                binding.btnSaveCache -> {
                    //保存到缓存
                    BitmapFactory.decodeResource(resources, R.drawable.a).run {
                        ImageUtils.saveImageToCache(this, fileName = cacheImageFileName).run {
                            toast("缓存:${this}")
                        }
                    }
                }
                binding.btnGetCache -> {
                    //读取缓存
                    ImageUtils.getImageFromCache(fileName = cacheImageFileName) { bitmap ->
                        binding.ivImage.setImageBitmap(bitmap)
                    }

                }
                binding.btnSaveLocal -> {
                    //保存本地
                    BitmapFactory.decodeResource(resources, R.drawable.a).run {
                        ImageUtils.saveImageToPhone(this, localImageFileName).run {
                            toast("本地:${this}")
                        }
                    }
                }
                binding.btnGetLocal -> {
                    //读取本地
                    AndPermission.with(this)
                        .runtime()
                        .permission(Permission.READ_EXTERNAL_STORAGE)
                        .onGranted {
                            ImageUtils.getImageFromLocal(localImageFileName) { bitmap ->
                                binding.ibCenter.setImageBitmap(bitmap)
                            }
                        }.start()
                }
            }
        }
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