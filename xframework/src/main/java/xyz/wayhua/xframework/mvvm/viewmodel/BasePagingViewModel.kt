package xyz.wayhua.xframework.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import xyz.wayhua.xframework.mvvm.dataresource.PagingDataSource
import xyz.wayhua.xframework.mvvm.repository.IPagingRepository

abstract class BasePagingViewModel<ID, T, PR : IPagingRepository<ID, T>, PDS : PagingDataSource<ID, T, PR>>() :
    BaseViewModel() {
    protected var datasLiveData: LiveData<PagedList<T>>
    val data: LiveData<PagedList<T>>
        get() = datasLiveData

    private val progressInitialLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val progressInitial: LiveData<Boolean>
        get() = progressInitialLiveData

    private val progressPagingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val progressPaging: LiveData<Boolean>
        get() = progressPagingLiveData


    private fun getPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<ID, T> {

        val dataSourceFactory = object : DataSource.Factory<ID, T>() {
            override fun create(): DataSource<ID, T> {
                val dataSource = createPDS()
                dataSource.coroutineScope = ioScope
                dataSource.onPagingLoadingStarted = { progressPagingLiveData.postValue(true) }
                dataSource.onPagingLoadingFinished = { progressPagingLiveData.postValue(false) }
                dataSource.onInitialLoadingStarted = { progressInitialLiveData.postValue(true) }
                dataSource.onInitialLoadingFinished = { progressInitialLiveData.postValue(false) }
                return dataSource
            }
        }
        return LivePagedListBuilder<ID, T>(dataSourceFactory, config)
    }

    abstract fun createPDS(): PDS

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        datasLiveData = getPagedListBuilder(config).build()
    }

    companion object {
        private var PAGE_SIZE = 10//本来是使用的是常量，但我想还是要有可能修改
    }
}