package xyz.wayhua.xframework.mvvm.repository

import xyz.wayhua.xframework.mvvm.data.PagingResult

interface IPagingRepository<ID,T> {
    suspend fun getEvents(page: ID, size: Int):PagingResult<ID,T>
}