package com.cya.application.ft_home.main.dynamic.adapter

import android.view.ViewGroup
import com.cya.application.ft_home.databinding.AdapterDynamicListBinding
import com.cya.application.ft_home.main.dynamic.entity.DynamicItem
import com.cya.frame.paging.BasePagingAdapter
import com.cya.frame.paging.IDiffer

class DynamicPagingAdapter :
    BasePagingAdapter<AdapterDynamicListBinding, DynamicItem>(object : IDiffer<DynamicItem> {

        override fun areItemsTheSame(oldItem: DynamicItem, newItem: DynamicItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DynamicItem, newItem: DynamicItem): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): AdapterDynamicListBinding {
        return AdapterDynamicListBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBind(holder: BaseVH<AdapterDynamicListBinding>, position: Int) {
        val item = getItem(position)
        holder.binding.tvMsg.text = item?.title
    }

}