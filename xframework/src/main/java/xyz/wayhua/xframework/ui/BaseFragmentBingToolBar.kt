package xyz.wayhua.xframework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import xyz.wayhua.xframework.R
import xyz.wayhua.xframework.ui.toolbar.XToolbar

abstract class BaseFragmentBingToolBar : BaseFragment() {
    @JvmField
    protected var xToolbar: XToolbar? = null
    override fun initViews(view: View, savedInstanceState: Bundle?) {
        super.initViews(view, savedInstanceState)

        initToolbar()
        xToolbar = XToolbar(activity as BaseActivity, toolbar)
        xToolbar!!.setToolbarTitle(toolbarTitle)

    }

   open   fun initToolbar() {


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    abstract fun getLayoutId(): Int


    override fun afterInitViews(view: View, savedInstanceState: Bundle?) {
        super.afterInitViews(view, savedInstanceState)
        afterInitToolbar()
    }

    protected fun afterInitToolbar() {
        xToolbar!!.homeEnabled(false)
        xToolbar!!.setTitle(R.string.app_name)
    }

    /***
     * 这个根据需要来设置，如果不设置也可以
     * @return
     */
    protected open val toolbarTitle: TextView?
        protected get() = null
}