package com.cya.wan_android.ui.main.home.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import com.cya.frame.adapter.BaseRecyclerAdapter
import com.cya.frame.ext.randomColor
import com.cya.wan_android.R
import com.cya.wan_android.entity.Article

class ArticleListAdapter : BaseRecyclerAdapter<Article>(R.layout.adapter_home_article_list) {
    override fun getLayoutManager(): GridLayoutManager {
        return GridLayoutManager(mContext, 2)
    }

    override fun convert(helper: BaseViewHolder, item: Article) {
        val tvTitle = helper.getView<TextView>(R.id.tv_title)
        val ivImage = helper.getView<ImageView>(R.id.iv_image)
        with(item) {
            tvTitle.text = title
            ivImage.setBackgroundColor(randomColor())
        }
    }

}