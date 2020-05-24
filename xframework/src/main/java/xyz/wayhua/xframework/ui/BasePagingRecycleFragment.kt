package xyz.wayhua.xframework.ui

import android.os.Bundle

import android.view.View

import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import kotlinx.android.synthetic.main.fragment_paging_recycle.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*


import xyz.wayhua.xframework.R

import xyz.wayhua.xframework.ext.setVisible
import xyz.wayhua.xframework.mvvm.IRecycleViewCallback
import xyz.wayhua.xframework.mvvm.adapter.BasePagingAdapter
import xyz.wayhua.xframework.mvvm.viewmodel.BasePagingViewModel

abstract class BasePagingRecycleFragment<T, A : BasePagingAdapter<T, *, *>,
        VM : BasePagingViewModel<*, T, *, *>>(

) : BaseFragmentBingToolBar(),IRecycleViewCallback<T> {
    protected abstract val viewModel: VM
    protected val adapter: BasePagingAdapter<*, *, *> = createAdapter()

    abstract fun createAdapter(): A
    override fun getLayoutId(): Int = R.layout.fragment_paging_recycle

    override fun initViews(view: View, savedInstanceState: Bundle?) {

        super.initViews(view, savedInstanceState)

        rv_events.adapter = adapter

        initObservers()

        swipe_refresh.setOnRefreshListener {
            viewModel.data.value?.dataSource?.invalidate()
        }
    }


    override fun initToolbar() {
        super.initToolbar()
        toolbar = ll_toolbar.toolbar
    }

    override val toolbarTitle: TextView?
        get() = ll_toolbar.toolbar_title


    private fun initObservers() {
        viewModel.data.observe(this, Observer {
            if (swipe_refresh.isRefreshing) swipe_refresh.isRefreshing = false
            showDatas(it)
        })
        viewModel.progressInitial.observe(this, Observer { visible ->
            rv_events.setVisible(!visible)
            pb_main.setVisible(visible)
        })
        viewModel.progressPaging.observe(this, Observer {
            showProgress(it)
        })

    }

    private fun showDatas(events: PagedList<T>) {
        (rv_events.adapter as BasePagingAdapter<T, *, *>).submitList(events)
    }

    private fun showProgress(isVisible: Boolean) {

    }
}