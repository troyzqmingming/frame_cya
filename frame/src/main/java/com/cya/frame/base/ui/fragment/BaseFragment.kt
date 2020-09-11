package com.cya.frame.base.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ui.IView
import com.cya.frame.base.ui.activity.BaseActivity

abstract class BaseFragment<V : ViewBinding> : Fragment(), IView<V> {

    lateinit var binding: V

    lateinit var mActivity: BaseActivity<*>

    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObserve()
        initView()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as BaseActivity<*>
    }

}