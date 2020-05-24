package xyz.wayhua.xframework.ext

import android.view.View
import android.widget.TextView


val View.visible: View
    get() = apply {
        visibility = View.VISIBLE
    }

val View.gone: View
    get() = apply {
        visibility = View.GONE
    }

val View.invisible: View
    get() = apply {
        visibility = View.INVISIBLE
    }

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

val View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE

val View.isGone: Boolean
    get() = visibility == View.GONE

fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}


fun TextView.setTextOrHideIfNull(text: String?) {
    text?.let {
        this.setVisible(true)
        this.text = it
    } ?: this.setVisible(false)
}