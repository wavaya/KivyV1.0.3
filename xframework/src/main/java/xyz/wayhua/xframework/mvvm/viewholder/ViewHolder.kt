package xyz.wayhua.xframework.mvvm.viewholder


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import xyz.wayhua.xframework.mvvm.IRecycleViewCallback


open class ViewHolder<T>(
    private val containerView: View,
    callback: IRecycleViewCallback<T>
) : RecyclerView.ViewHolder(containerView), IViewHolderBind<T> {
      var current: T? = null

    override fun onBind(t: T) {
        current = t

    }


    init {
        setIsRecyclable(false)
        containerView.setOnClickListener {
            callback.onModelClicked(current!!, containerView)
        }
        containerView.setOnLongClickListener {
            callback.onModelLongClicked(current!!, containerView)
        }
    }

}