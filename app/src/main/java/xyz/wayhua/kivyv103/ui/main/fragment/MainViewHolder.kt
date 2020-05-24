package xyz.wayhua.kivyv103.ui.main.fragment

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_event.view.*
import xyz.wayhua.kivyv103.R
import xyz.wayhua.kivyv103.data.domain.Event
import xyz.wayhua.xframework.ext.setTextOrHideIfNull

import xyz.wayhua.xframework.mvvm.IRecycleViewCallback
import xyz.wayhua.xframework.mvvm.viewholder.ViewHolder

class MainViewHolder(
    private val containerView: View,
    callback: IRecycleViewCallback<Event>
) : ViewHolder<Event>(containerView, callback) {

    override fun onBind(event: Event) {
        super.onBind(event)
        with(containerView) {
            tv_title.text = event.title
            tv_details.text = event.description
            tv_location.setTextOrHideIfNull(event.place?.title)
            tv_date.setTextOrHideIfNull(event.dates)
            tv_price.setTextOrHideIfNull(event.price)
            event.images?.let {
                Glide.with(iv_image)
                    .load(it[0].imageUrl)
                    .placeholder(R.drawable.ic_image_placeholder)
                    .override(EVENT_IMAGE_WIDTH, EVENT_IMAGE_HEIGHT)
                    .fitCenter()
                    .into(iv_image)
            }
        }
    }
}
private const val EVENT_IMAGE_WIDTH = 600
private const val EVENT_IMAGE_HEIGHT = 280