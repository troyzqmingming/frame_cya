package com.cya.application.ui.article

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import com.cya.application.R
import com.cya.application.bean.Article
import com.cya.frame.base.adapter.BaseRecyclerAdapter
import com.cya.frame.ext.randomColor


class ArticleListAdapter : BaseRecyclerAdapter<Article>(R.layout.adapter_home_list) {
    override fun getLayoutManager(): GridLayoutManager {
        return GridLayoutManager(mContext,2)
    }

    @SuppressLint("NewApi")
    override fun convert(helper: BaseViewHolder, item: Article) {
        item.bgColor = randomColor()
        helper.getView<TextView>(R.id.tv_msg).apply {
            text = item.title
            transitionName =
                "${item.title}${mContext.getString(R.string.share_element_detail_title)}"
        }
        helper.getView<ImageView>(R.id.iv_image).apply {
            setBackgroundColor(item.bgColor)
            transitionName =
                "${item.title}${mContext.getString(R.string.share_element_detail_image)}"
        }
    }

}