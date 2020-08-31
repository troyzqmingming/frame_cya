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
import com.cya.frame.demo.di.FILE_PRIVATE_PATH_ROOT
import com.cya.frame.demo.ext.nav
import com.cya.frame.demo.ui.main.MainFragmentDirections
import com.cya.frame.demo.view.Progress
import com.cya.frame.demo.view.dismiss
import com.cya.frame.demo.view.show
import com.cya.frame.ext.clickNoRepeat
import com.cya.frame.ext.setNoRepeatClick
import com.cya.frame.ext.toast
import com.cya.frame.media.DownloadMedia
import com.cya.frame.media.ImageMedia
import com.orhanobut.logger.Logger
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import zlc.season.rxdownload4.download
import zlc.season.rxdownload4.file
import zlc.season.rxdownload4.task.Task


class TestFragment : DemoBaseFragment<FragmentTestBinding>() {

    val cacheImageFileName = "cacheImage.jpg"
    val localImageFileName = "localImage.jpg"

    private var progress: Progress? = null

    override fun getViewBinding(): FragmentTestBinding {
        return FragmentTestBinding.inflate(layoutInflater)
    }

    override fun initData() {
        progress = Progress(mActivity).setMsg("下载").isCancelable(false)
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
                        ImageMedia.saveImageToCache(this, fileName = cacheImageFileName).run {
                            toast("缓存:${this}")
                        }
                    }
                }
                binding.btnGetCache -> {
                    //读取缓存
                    ImageMedia.getImageFromCache(fileName = cacheImageFileName) { bitmap ->
                        binding.ivImage.setImageBitmap(bitmap)
                    }

                }
                binding.btnSaveLocal -> {
                    //保存本地
                    BitmapFactory.decodeResource(resources, R.drawable.a).run {
                        ImageMedia.saveImageToPhone(this, localImageFileName).run {
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
                            ImageMedia.getImageFromLocal(localImageFileName) { bitmap ->
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
        binding.ibCenter.clickNoRepeat {
            val saveName = "chebaba1.apk"
            val url =
                "https://imtt.dd.qq.com/16891/apk/2FA0E483C607FEED12E4C1519DAF834A.apk?fsname=com.dndc.apps.chebaba_1.0.5_2.apk&csr=1bbd"
            Task(url = url, saveName = saveName, savePath = FILE_PRIVATE_PATH_ROOT)
                .download()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress?.show(mActivity)
                }
                .doFinally {
                    progress?.dismiss(mActivity)
                }
                .subscribeBy(
                    onNext = {
                        progress?.setMsg("下载进度:${it.downloadSizeStr()}/${it.totalSizeStr()}")
                    },
                    onComplete = {
                        Logger.i("下载完成:${url.file().absolutePath}")
                        //保存到download
                        DownloadMedia.saveToLocal(saveName, url.file()) {
                            toast("保存结果:${it}")
                        }
                    },
                    onError = {
                        Logger.i("下载异常${it.message}")
                    }
                )
        }
    }


}