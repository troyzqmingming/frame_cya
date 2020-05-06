package com.cya.frame.demo.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseViewHolder
import com.cya.frame.base.adapter.BaseRecyclerAdapter
import com.cya.frame.demo.R
import com.cya.frame.demo.bean.result.Article

class HomeListAdapter : BaseRecyclerAdapter<Article>(R.layout.adapter_home_list) {
    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(mContext)
    }

    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.tv_msg, item.title)
    }

}