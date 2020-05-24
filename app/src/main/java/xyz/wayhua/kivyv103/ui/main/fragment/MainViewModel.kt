package xyz.wayhua.kivyv103.ui.main.fragment

import xyz.wayhua.kivyv103.data.domain.Event
import xyz.wayhua.kivyv103.data.repository.EventRepository
import xyz.wayhua.kivyv103.data.repository.datasource.EventPagingDataSource
import xyz.wayhua.xframework.mvvm.viewmodel.BasePagingViewModel

class MainViewModel (val pagingRepository: EventRepository): BasePagingViewModel<String, Event, EventRepository,EventPagingDataSource>() {
    override fun createPDS(): EventPagingDataSource  = EventPagingDataSource(pagingRepository)
}