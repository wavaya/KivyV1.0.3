package xyz.wayhua.xframework.ui.toolbar

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar

import androidx.appcompat.widget.Toolbar
import xyz.wayhua.xframework.R
import xyz.wayhua.xframework.ui.BaseActivity

class XToolbar(var activity: BaseActivity, var toolbar : Toolbar?) {
    var actionBar: ActionBar?
    var toolbar_title: TextView? = null
    fun setToolbarTitle(toolbar_title: TextView?) {
        this.toolbar_title = toolbar_title
    }

    fun setTitle(id: Int) {
        setTitle(id, DEFAULT_CUSTOM)
    }

    fun setTitle(id: Int, isCustom: Boolean) {
        if (isCustom) {
            if (toolbar_title == null) {
                actionBar!!.setTitle(id)
            } else {
                actionBar!!.title = ""
                toolbar_title!!.setText(id)
            }
        } else {
            actionBar!!.setTitle(id)
            if (toolbar_title != null) toolbar_title!!.visibility = View.GONE
        }
    }

    fun setTitle(title: String?) {
        setTitle(title, DEFAULT_CUSTOM)
    }

    fun setTitle(title: String?, isCustom: Boolean) {
        if (isCustom) {
            if (toolbar_title == null) {
                actionBar!!.title = title
            } else {
                actionBar!!.title = ""
                toolbar_title!!.text = title
            }
        } else {
            actionBar!!.title = title
            if (toolbar_title != null) toolbar_title!!.visibility = View.GONE
        }
    }

    fun homeEnabled(show: Boolean) {
        actionBar!!.setDisplayHomeAsUpEnabled(show)
        actionBar!!.setDisplayShowHomeEnabled(show)
    }

    fun setHomeAsUpIndication(id: Int) {
        actionBar!!.setHomeAsUpIndicator(id)
    }

    companion object {
        const val DEFAULT_CUSTOM = true
    }

    init {
        activity.setSupportActionBar(toolbar)
        actionBar = activity.supportActionBar
        // On Lollipop, the action bar shadow is provided by default, so have to remove it explicitly
        actionBar!!.elevation = 0f
        setHomeAsUpIndication(R.mipmap.icon_back)
    }
}