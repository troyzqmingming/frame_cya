package com.cya.frame.demo.ui.article

import android.annotation.SuppressLint
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import com.cya.frame.base.adapter.BaseRecyclerAdapter
import com.cya.frame.demo.R
import com.cya.frame.demo.bean.result.Article
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