package xyz.wayhua.kivyv103.ui.main.fragment

import androidx.recyclerview.widget.DiffUtil
import xyz.wayhua.kivyv103.data.domain.Event

class EventsDiffUtil: DiffUtil.ItemCallback<Event> (){
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}