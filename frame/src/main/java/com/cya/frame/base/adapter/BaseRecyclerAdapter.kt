package com.cya.frame.base.adapter

import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

abstract class BaseRecyclerAdapter<T> :
    BaseQuickAdapter<T, BaseViewHolder> {

    constructor(layoutResId: Int, data: MutableList<T>?) : super(layoutResId, data)

    constructor(data: MutableList<T>?) : super(data)

    constructor(layoutResId: Int) : super(layoutResId)


    abstract fun getLayoutManager(): RecyclerView.LayoutManager
}