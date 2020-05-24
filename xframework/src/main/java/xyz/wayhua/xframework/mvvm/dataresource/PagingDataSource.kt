package xyz.wayhua.xframework.mvvm.dataresource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import xyz.wayhua.xframework.mvvm.data.PagingResult
import xyz.wayhua.xframework.mvvm.repository.IPagingRepository

abstract  class PagingDataSource<ID, T, PR : IPagingRepository<ID,T>>(val pagingRepository: PR): PageKeyedDataSource<ID, T>(){
    var coroutineScope: CoroutineScope? = null
    var onInitialLoadingStarted: OnInitialLoadingStarted? = null
    var onInitialLoadingFinished: OnInitialLoadingFinished? = null
    var onPagingLoadingStarted: OnPagingLoadingStarted? = null
    var onPagingLoadingFinished: OnPagingLoadingFinished? = null



    override fun loadInitial(params: LoadInitialParams<ID>, callback: LoadInitialCallback<ID, T>) {
        coroutineScope?.launch {
            try {
                onInitialLoadingStarted?.invoke()
                val events = pagingRepository.getEvents(getFirstID(), params.requestedLoadSize)
                val next = getNext(events)
                callback.onResult(events.results, null, next )
            } catch (exception: Exception) {
                Log.e("myLog", exception.message)
            } finally {
                onInitialLoadingFinished?.invoke()
            }
        }
    }

    abstract fun getFirstID(): ID

    override fun loadBefore(params: LoadParams<ID>, callback: LoadCallback<ID,T>) {}

    override fun loadAfter(params: LoadParams<ID>, callback: LoadCallback<ID,T>) {
        coroutineScope?.launch {
            try {
                onPagingLoadingStarted?.invoke()
                val events = pagingRepository.getEvents(params.key, params.requestedLoadSize)
                val next = getNext(events)
                callback.onResult(events.results, next )
            } catch (exception: Exception) {
                Log.e("myLog", exception.message)
            } finally {
                onPagingLoadingFinished?.invoke()
            }
        }
    }

    abstract fun getNext(events: PagingResult<ID, T>) :ID
//    {
//        val nextPageLink = events.nextPage
//        val next = nextPageLink?.substringAfterLast("page=")?.substringBefore("&")
//        return next
//    }


}

internal typealias OnPagingLoadingStarted = () -> Unit
internal typealias OnPagingLoadingFinished = () -> Unit
internal typealias OnInitialLoadingStarted = () -> Unit
internal typealias OnInitialLoadingFinished = () -> Unit