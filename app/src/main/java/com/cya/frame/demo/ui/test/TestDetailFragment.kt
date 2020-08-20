package com.cya.frame.demo.ui.test

import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.cya.frame.demo.R
import com.cya.frame.demo.base.DemoBaseFragment
import com.cya.frame.demo.databinding.FragmentTestDetailBinding

class TestDetailFragment : DemoBaseFragment<FragmentTestDetailBinding>() {

    private val args: TestDetailFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentTestDetailBinding {
        return FragmentTestDetailBinding.inflate(layoutInflater)
    }

    override fun initView() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.transition)
        binding.tvTitle.text = args.title
        binding.ivImage.setImageResource(args.image)
    }

    override fun initData() {
    }

}