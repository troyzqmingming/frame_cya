package com.cya.frame.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.cya.frame.base.ICallback

abstract class BasePagingAdapter<V : ViewBinding, T : Any>(t: IDiffer<T>) :
    PagingDataAdapter<T, BasePagingAdapter.BaseVH<V>>(BaseDiffer<T>(t)) {

    lateinit var layoutInflater: LayoutInflater

    var onItemClickListener: ICallback<T>? = null

    abstract fun getViewBinding(parent: ViewGroup, viewType: Int): V

    abstract fun onBind(holder: BaseVH<V>, position: Int)

    class BaseVH<V : ViewBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: BaseVH<V>, position: Int) {
        holder.binding.root.setOnClickListener {
            getItem(position)?.let { it1 ->
                onItemClickListener?.invoke(
                    it1
                )
            }
        }
        onBind(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<V> {
        layoutInflater = LayoutInflater.from(parent.context)
        return BaseVH(getViewBinding(parent, viewType))
    }
}

interface IDiffer<T> {

    fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    fun areContentsTheSame(oldItem: T, newItem: T): Boolean
}

private class BaseDiffer<T : Any>(private val differ: IDiffer<T>) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return differ.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return differ.areContentsTheSame(oldItem, newItem)
    }
}