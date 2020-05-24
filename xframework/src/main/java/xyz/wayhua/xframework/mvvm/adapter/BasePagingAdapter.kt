package xyz.wayhua.xframework.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import xyz.wayhua.xframework.exception.XException


import xyz.wayhua.xframework.mvvm.IRecycleViewCallback
import xyz.wayhua.xframework.mvvm.viewholder.ViewHolder
import java.lang.reflect.ParameterizedType

/**
 * 说明T指实体类
 * VH  viewHolder
 * CB  实体类的对比
 */
open abstract class BasePagingAdapter<T, VH : ViewHolder<T>, CB : DiffUtil.ItemCallback<T>>
    (private val callback: IRecycleViewCallback<T>, diffCallback: CB) :
    PagedListAdapter<T, VH>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        //创建viewholder
        val mLayoutInflater = LayoutInflater.from(parent.context)
        val view = mLayoutInflater.inflate(getLayoutId(), parent, false)
        return viewHolder(view, callback)
    }

    abstract fun viewHolder(view: View, callback: IRecycleViewCallback<T>): VH

    abstract fun getLayoutId(): Int

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position)!!)
    }

}
