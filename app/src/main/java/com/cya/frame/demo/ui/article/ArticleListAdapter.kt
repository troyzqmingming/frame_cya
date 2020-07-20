package com.cya.frame.demo.ui.article

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import com.cya.frame.base.adapter.BaseRecyclerAdapter
import com.cya.frame.demo.R
import com.cya.frame.demo.bean.result.Article

class ArticleListAdapter : BaseRecyclerAdapter<Article>(R.layout.adapter_home_list) {
    override fun getLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(mContext)
    }

    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.tv_msg, item.title)
    }

}