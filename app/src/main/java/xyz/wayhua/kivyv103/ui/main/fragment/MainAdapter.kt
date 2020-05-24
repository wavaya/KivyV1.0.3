package xyz.wayhua.kivyv103.ui.main.fragment

import android.view.View
import xyz.wayhua.kivyv103.R
import xyz.wayhua.kivyv103.data.domain.Event
import xyz.wayhua.xframework.mvvm.IRecycleViewCallback
import xyz.wayhua.xframework.mvvm.adapter.BasePagingAdapter

class MainAdapter(val callback: IRecycleViewCallback<Event>, diffCallback: EventsDiffUtil) :
    BasePagingAdapter<Event, MainViewHolder, EventsDiffUtil>(callback, diffCallback) {
    override fun viewHolder(view: View, callback: IRecycleViewCallback<Event>): MainViewHolder =
        MainViewHolder(view, callback)

    override fun getLayoutId(): Int = R.layout.item_event

}