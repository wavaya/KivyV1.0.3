package xyz.wayhua.kivyv103.ui.main.fragment

import android.view.View

import xyz.wayhua.kivyv103.data.domain.Event
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.wayhua.xframework.ui.BasePagingRecycleFragment


class MainFragment() : BasePagingRecycleFragment<Event, MainAdapter, MainViewModel>() {

    override fun createAdapter(): MainAdapter = MainAdapter(this, EventsDiffUtil())
    override fun onModelClicked(model: Event, sharedView: View?) {

    }

    override val viewModel: MainViewModel
            by viewModel<MainViewModel>()

}