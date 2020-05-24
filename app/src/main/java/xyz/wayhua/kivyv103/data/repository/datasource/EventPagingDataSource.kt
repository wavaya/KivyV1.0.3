package xyz.wayhua.kivyv103.data.repository.datasource

import xyz.wayhua.kivyv103.data.domain.Event
import xyz.wayhua.kivyv103.data.repository.EventRepository
import xyz.wayhua.xframework.mvvm.data.PagingResult
import xyz.wayhua.xframework.mvvm.dataresource.PagingDataSource

class EventPagingDataSource(pagingRepository: EventRepository) :
    PagingDataSource<String, Event, EventRepository>(pagingRepository) {
    override fun getFirstID(): String = "1"

    override fun getNext(events: PagingResult<String, Event>): String {
        val nextPageLink = events.nextPage
        val next = nextPageLink?.substringAfterLast("page=")?.substringBefore("&")
        return next.toString()
    }

}