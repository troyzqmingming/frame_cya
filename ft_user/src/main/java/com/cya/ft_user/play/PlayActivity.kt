package com.cya.ft_user.play

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import coil.load
import coil.transform.CircleCropTransformation
import com.alibaba.android.arouter.facade.annotation.Route
import com.cya.frame.ext.clickNoRepeat
import com.cya.ft_user.databinding.ActivityPlayBinding
import com.cya.lib_base.base.CyaBaseActivity
import com.cya.lib_base.contract.ConstantsPath
import kotlinx.android.synthetic.main.activity_play.*

/**
 * @packageName: com.cya.ft_user.play
 * @author: zengqingming
 * @date: 2020/10/9
 */
@Route(path = ConstantsPath.UI.PLAY)
class PlayActivity : CyaBaseActivity<ActivityPlayBinding>() {

    override fun getViewBinding(): ActivityPlayBinding {
        return ActivityPlayBinding.inflate(layoutInflater)
    }

    override fun setObserve() {
    }

    override fun initView() {
        ivCenter.load(
            "https://upload-images.jianshu.io/upload_images/1315745-b2723d649e24bede.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/500/format/webp"
        ) {
            transformations(CircleCropTransformation())
        }
        val roundAnim = ObjectAnimator.ofFloat(ivCenter, View.ROTATION, 0f, 360f).apply {
            repeatCount = -1
            duration = 6000
            interpolator = LinearInterpolator()
        }
        ivCenter.clickNoRepeat {
            roundAnim.start()
        }
    }

    override fun initData() {
    }

}